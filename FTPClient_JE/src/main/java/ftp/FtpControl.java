package ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FtpControl {

    private final FTPClient ftpClient;

    public FtpControl(String serverHost, int serverPort) {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverHost, serverPort);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean setFtpClientFileType(int type) throws IOException {
        if (ftpClient != null) {
            ftpClient.setFileType(type);
            return true;
        }
        return false;
    }

    public int replayCode() {
        if (ftpClient != null)
            return ftpClient.getReplyCode();
        return -1;
    }

    public boolean changeDir(String dirPath) throws IOException {
        if (ftpClient != null)
            return ftpClient.changeWorkingDirectory(dirPath);
        return false;
    }

    public boolean parentDir() throws IOException {
        if (ftpClient != null)
            return ftpClient.changeToParentDirectory();
        return false;
    }

    public FTPFile[] getFTPFiles() throws IOException {
        return ftpClient.listFiles();
    }

    public FTPFile[] getFTPDirs() throws IOException {
        return ftpClient.listDirectories();
    }

    public boolean ftpDownload(FileOutputStream fileOutputStream, String fileName) throws IOException {
        boolean success;
        if (ftpClient != null) {
            success = ftpClient.retrieveFile(fileName, fileOutputStream);
            fileOutputStream.close();
            return success;
        }
        return false;
    }

    public boolean ftpUpload(FileInputStream fileInputStream, String fileName) throws IOException {
        boolean success;
        if (ftpClient != null) {
            success = ftpClient.storeFile(fileName, fileInputStream);
            fileInputStream.close();
            return success;
        }
        return false;
    }

    public boolean login(String username, String password) throws IOException {
        if (ftpClient != null)
            return ftpClient.login(username, password);
        return false;
    }

    public void close() throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
    }

}
