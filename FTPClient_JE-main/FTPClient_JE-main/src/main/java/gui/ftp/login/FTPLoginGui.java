package gui.ftp.login;

import ftp.FtpControl;
import gui.GUIFather;
import gui.ftp.control.FTPControlGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FTPLoginGui extends GUIFather {

    private JButton loginButton;
    private JTextField usernameInputTextField;
    private JTextField passwordInputTextField;
    private JPanel jPanel;
    private JTextField serverHostInputField;

    public FTPLoginGui(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String host = serverHostInputField.getText();
                String username = usernameInputTextField.getText();
                String password = passwordInputTextField.getText();
                loginFtp(host, username, password);
            }
        });
    }

    private void loginFtp(String host, String username, String password) {
        FtpControl ftpControl = new FtpControl(host, 21);
        try {
            if (ftpControl.login(username, password)) {
                JOptionPane.showMessageDialog(null, "登入成功", "登入成功", JOptionPane.INFORMATION_MESSAGE);
                new FTPControlGui("FTP控制頁面", ftpControl);
            } else
                JOptionPane.showMessageDialog(null, "登入失敗錯誤", "登入失敗錯誤", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "輸入錯誤", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

}
