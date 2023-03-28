/*
 Created by JE-Chen on 2021/3/23.
 Socket Client
*/

#define WIN32_LEAN_AND_MEAN

#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <cstdio>
#include <thread>
#include <string>
#include <iostream>

#pragma comment (lib, "Ws2_32.lib")
#pragma comment (lib, "Mswsock.lib")
#pragma comment (lib, "AdvApi32.lib")

#define DEFAULT_BUFFER_LENGTH 512
#define DEFAULT_PORT "27010"

int canInitStartupWsaData;
char receiveBuffer[DEFAULT_BUFFER_LENGTH];
int receiveBufferLength = DEFAULT_BUFFER_LENGTH;


void startSocket(char address[], const char *sendBuffer) {
    WSADATA wsaData;
    auto ConnectSocket = INVALID_SOCKET;
    struct addrinfo *result = nullptr,
            *ptr,
            hints{};

    canInitStartupWsaData = WSAStartup(MAKEWORD(2, 2), &wsaData);
    if (canInitStartupWsaData != 0) {
        printf("WSAStartup failed with error: %d\n", canInitStartupWsaData);
    }

    ZeroMemory(&hints, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;

    // Resolve the server address and port
    canInitStartupWsaData = getaddrinfo(address, DEFAULT_PORT, &hints, &result);
    if (canInitStartupWsaData != 0) {
        printf("get address info failed with error: %d\n", canInitStartupWsaData);
        WSACleanup();
    }

    // Attempt to connect to an address until one succeeds
    for (ptr = result; ptr != nullptr; ptr = ptr->ai_next) {

        // Create a SOCKET for connecting to server
        ConnectSocket = socket(ptr->ai_family, ptr->ai_socktype,
                               ptr->ai_protocol);
        if (ConnectSocket == INVALID_SOCKET) {
            printf("socket failed with error: %d\n", WSAGetLastError());
            WSACleanup();
        }

        // Connect to server.
        canInitStartupWsaData = connect(ConnectSocket, ptr->ai_addr, (int) ptr->ai_addrlen);
        if (canInitStartupWsaData == SOCKET_ERROR) {
            closesocket(ConnectSocket);
            ConnectSocket = INVALID_SOCKET;
            continue;
        }
        break;
    }

    freeaddrinfo(result);

    if (ConnectSocket == INVALID_SOCKET) {
        printf("Unable to connect to server!\n");
        WSACleanup();
    }

    // Send an initial buffer
    canInitStartupWsaData = send(ConnectSocket, sendBuffer, (int) strlen(sendBuffer), 0);
    if (canInitStartupWsaData == SOCKET_ERROR) {
        printf("send failed with error: %d\n", WSAGetLastError());
        closesocket(ConnectSocket);
        WSACleanup();
    }

    printf("Bytes Sent: %d\n", canInitStartupWsaData);

    // shutdown the connection since no more data will be sent
    canInitStartupWsaData = shutdown(ConnectSocket, SD_SEND);
    if (canInitStartupWsaData == SOCKET_ERROR) {
        printf("shutdown failed with error: %d\n", WSAGetLastError());
        closesocket(ConnectSocket);
        WSACleanup();
    }

    // Receive until the peer closes the connection
    do {
        //接收資料
        canInitStartupWsaData = recv(ConnectSocket, receiveBuffer, receiveBufferLength, 0);
        if (canInitStartupWsaData > 0) {
            printf("Bytes received: %d\n", canInitStartupWsaData);
            // print receive data
            for (char size : receiveBuffer)
                printf("%c", size);
            std::memset(receiveBuffer, 0, receiveBufferLength);
        } else if (canInitStartupWsaData == 0)
            printf("Connection closed\n");
        else
            printf("recv failed with error: %d\n", WSAGetLastError());

    } while (canInitStartupWsaData > 0);
}

int __cdecl main(int argc, char **argv) {
    if (argc != 2) {
        printf("%s", "No address");
    }
    std::string control;
    while (std::cin >> control) {
        if (control == "exit") {
            exit(0);
        }
        if (control == "send") {
            std::string data;
            std::cin >> data;
            char *strPoint = new char[data.length() + 1];
            copy(data.begin(), data.end(), strPoint);
            strPoint[data.size()] = '\0';
            std::thread client(startSocket, argv[1], strPoint);
            client.detach();
        }
    }
    return 0;
}