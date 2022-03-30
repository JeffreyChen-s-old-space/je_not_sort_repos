package gui.ftp.control;

import ftp.FtpControl;
import gui.GUIFather;
import org.apache.commons.net.ftp.FTPFile;
import util.file.FileChooser;
import util.file.FileInput;
import util.file.FileOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FTPControlGui extends GUIFather {
    private final FtpControl ftpControl;
    private final FileChooser fileChooser = new FileChooser();
    private final FileInput fileInput = new FileInput();
    private final FileOutput fileOutput = new FileOutput();

    private JList<String> fileJlist;
    private final ArrayList<String> data;
    private String downloadPath = new File("").getAbsolutePath().replace("\\", "/");

    private JPanel jPanel;
    private JScrollPane jScroll;
    private JButton backButton;
    private JButton changeDirButton;
    private JTextField changeDirTextField;
    private JButton uploadButton;
    private JButton downloadButton;
    private JTextField downloadNameTextField;
    private JButton chooseDownloadDir;
    private JTextField uploadNameTextField;

    public FTPControlGui(String windowName, FtpControl ftpControl) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.ftpControl = ftpControl;
        data = new ArrayList<>();
        resetJlist();
        fileJlist.setListData(data.toArray(new String[0]));


        fileJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedValue = fileJlist.getSelectedValue();
                System.out.println("Value Selected: " + selectedValue);
                String[] temp = selectedValue.split(":");
                if (temp[2].equals("dir")) {
                    try {
                        ftpControl.changeDir("/" + temp[1]);
                        resetJlist();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ftpControl.parentDir())
                        resetJlist();
                    else
                        JOptionPane.showMessageDialog(null, "更改資料夾錯誤", "更改資料夾錯誤", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        changeDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ftpControl.changeDir(changeDirTextField.getText()))
                        resetJlist();
                    else
                        JOptionPane.showMessageDialog(null, "更改資料夾錯誤", "更改資料夾錯誤", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        chooseDownloadDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File tempFile = fileChooser.chooseDir();
                if (!tempFile.isDirectory())
                    JOptionPane.showMessageDialog(null, "非選擇資料夾錯誤", "非選擇資料夾錯誤", JOptionPane.ERROR_MESSAGE);
                else
                    downloadPath = tempFile.getAbsolutePath().replace("\\", "/");
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String downloadPathAndName = downloadPath + "/" + downloadNameTextField.getText();
                    System.out.println(downloadPathAndName);
                    ftpControl.ftpDownload(new FileOutputStream(downloadPathAndName), downloadNameTextField.getText());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "上傳失敗錯誤", "上傳失敗錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File chooseFile = fileChooser.chooseFile(false, "");
                    ftpControl.ftpUpload(new FileInputStream(chooseFile.getAbsoluteFile()), uploadNameTextField.getText());
                    resetJlist();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "上傳失敗錯誤", "上傳失敗錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void resetJlist() {
        data.clear();
        try {
            FTPFile[] ftpFiles = ftpControl.getFTPFiles();
            for (FTPFile file : ftpFiles) {
                StringBuilder stringBuilder = new StringBuilder();
                if (file.isFile())
                    stringBuilder.append("檔案名稱:").append(file.getName()).append("檔案大小:").append(file.getSize());
                else
                    stringBuilder.append("資料夾名稱:").append(file.getName()).append(":dir");
                data.add(stringBuilder.toString());
            }
            fileJlist.setListData(data.toArray(new String[0]));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}

