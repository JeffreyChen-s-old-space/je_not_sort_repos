package com.je_chen.game.Main;
import com.je_chen.game.Canvas.Canvas_Game;

import javax.swing.*;
import java.awt.*;


public class Game{

	private JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Game() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 500,500,500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		Canvas_Game c = new Canvas_Game();
		Thread Loop = new Thread(c);
		Loop.start();
		frame.getContentPane().add(c);
	}
}
