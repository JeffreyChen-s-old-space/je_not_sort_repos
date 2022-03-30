package algorithm.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIFather extends JFrame {

    protected String windowName;

    public GUIFather(String windowName) {
        this.windowName = windowName;
        setTitle(windowName);
        setSize(800, 800);
        closeEvent();
    }

    protected void closeEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println(windowName + " Frame Closed");
                GUIFather.this.dispose();
            }
        });
    }

}
