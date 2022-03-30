package com.je_chen.game.Canvas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.*;

public class Canvas_Game extends JPanel implements Runnable{

	private boolean GameRun = true;
	private int newY = 0;

		public Canvas_Game() {
			KeyListener listener = new MyKeyListener();
			addKeyListener(listener);
			setFocusable(true);
		}


	   @Override
	    protected void paintComponent (Graphics g ) {
			super.paintComponent (g);

		   g.setColor(new Color(83, 83, 83));
		   g.fillRect(0,0,this.getWidth(),this.getHeight());

		   String path = new File("").getAbsolutePath();
		   Image picture = Toolkit.getDefaultToolkit().getImage(path+"/Resource/firefox_test.png");
		   g.drawImage(picture,0,newY,this);
	    }

	@Override
	public void run() {
			while (GameRun) {
				try {
					Thread.sleep(1000/60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
				newY+=1;
			}
	}

	public class MyKeyListener implements KeyListener {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
			
				case KeyEvent.VK_DOWN:
					System.out.println("VK_DOWN");
					break;
					
				case KeyEvent.VK_UP:
					System.out.println("VK_UP");
					break;
					
				case KeyEvent.VK_LEFT:
					System.out.println("VK_LEFT");
					break;
					
				case KeyEvent.VK_RIGHT:
					System.out.println("VK_RIGHT");
					break;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		}
	}

