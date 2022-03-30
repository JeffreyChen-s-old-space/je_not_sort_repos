/*
 Created by JE-Chen on 2021/3/23.
 Socket Server
*/

#undef UNICODE

#define WIN32_LEAN_AND_MEAN

#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <cstdio>
#include <thread>
#include <string>
#include <iostream>

#pragma comment (lib, "Ws2_32.lib")

#define DEFAULT_BUFFER_LENGTH 512
#define DEFAULT_PORT "27010"

int state = 555;

void closeSocket(SOCKET ClientSocket) {
    printf("shutdown failed with error: %d\n", WSAGetLastError());
    closesocket(ClientSocket);
    WSACleanup();
    exit(0);
}

void startServer(SOCKET ListenSocket, SOCKET ClientSocket) {
    WSADATA wsaData;
    int canInitStartupWsaData;
    struct addrinfo *result = nullptr;
    struct addrinfo hints{};

    int echoResult;
    char receiveBuffer[DEFAULT_BUFFER_LENGTH];
    int receiveBufferLength = DEFAULT_BUFFER_LENGTH;

    canInitStartupWsaData = WSAStartup(MAKEWORD(2, 2), &wsaData);
    if (canInitStartupWsaData != 0) {
        printf("WSAStartup failed with error: %d\n", canInitStartupWsaData);
    }

    ZeroMemory(&hints, sizeof(hints));
    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;
    hints.ai_flags = AI_PASSIVE;

    // Resolve the server address and port
    canInitStartupWsaData = getaddrinfo(nullptr, DEFAULT_PORT, &hints, &result);
    if (canInitStartupWsaData != 0) {
        printf("get address info failed with error: %d\n", canInitStartupWsaData);
        WSACleanup();
    }

    // Create a SOCKET for connecting to server
    ListenSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);
    if (ListenSocket == INVALID_SOCKET) {
        printf("socket failed with error: %d\n", WSAGetLastError());
        freeaddrinfo(result);
        WSACleanup();
    }

    // Setup the TCP listening socket
    canInitStartupWsaData = bind(ListenSocket, result->ai_addr, (int) result->ai_addrlen);
    if (canInitStartupWsaData == SOCKET_ERROR) {
        freeaddrinfo(result);
        closeSocket(ListenSocket);
    }

    freeaddrinfo(result);

    do {
        canInitStartupWsaData = listen(ListenSocket, SOMAXCONN);
        if (canInitStartupWsaData == SOCKET_ERROR) {
            closeSocket(ListenSocket);
        }

        // Accept a client socket
        ClientSocket = accept(ListenSocket, nullptr, nullptr);
        if (ClientSocket == INVALID_SOCKET) {
            closeSocket(ListenSocket);
        }
        canInitStartupWsaData = recv(ClientSocket, receiveBuffer, receiveBufferLength, 0);
        if (canInitStartupWsaData > 0) {
            printf("Bytes received: %d\n", canInitStartupWsaData);
            // Echo the buffer back to the sender
            echoResult = send(ClientSocket, receiveBuffer, canInitStartupWsaData, 0);
            if (echoResult == SOCKET_ERROR) {
                closeSocket(ClientSocket);
            }
            printf("Bytes sent: %d\n", echoResult);
            // print receive data
            for (char size : receiveBuffer)
                printf("%c", size);
            printf("\n");
            std::memset(receiveBuffer, 0, receiveBufferLength);
        }

    } while (state == 555);

    if (state == 0) {
        canInitStartupWsaData = shutdown(ClientSocket, SD_SEND);
        if (canInitStartupWsaData == SOCKET_ERROR) {
            closeSocket(ClientSocket);
        }
    }
}

int __cdecl main() {
    auto ListenSocket = INVALID_SOCKET;
    auto ClientSocket = INVALID_SOCKET;
    std::thread server(startServer, ListenSocket, ClientSocket);
    server.detach();
    std::string control;
    while (std::cin >> control) {
        if (control == "exit") {
            state = 0;
            exit(0);
        }
        return 0;
    }
}