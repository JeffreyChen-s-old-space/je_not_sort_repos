import gui.ftp.control.FTPControlGui;
import gui.ftp.login.FTPLoginGui;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] argv) {
        new FTPLoginGui("FTP登入頁面");
    }
}
