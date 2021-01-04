package com.pickupppp.task4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameWindow extends JFrame {

	private JTextField fie1, fie2, fie3, fie4;
	private JTextArea are1;
	private Graphm graph;
	private JButton btn;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(new FlowLayout());

		JLabel lab1 = new JLabel("start word:");
		fie1 = new JTextField(8);
		fie1.setEditable(false);
		add(lab1);
		add(fie1);

		JLabel lab2 = new JLabel("end word:");
		fie2 = new JTextField(8);
		fie2.setEditable(false);
		add(lab2);
		add(fie2);

		JLabel lab3 = new JLabel("your answer:");
		are1 = new JTextArea(8, 30);
		are1.setEditable(false);
		add(lab3);
		add(are1);

		JLabel lab4 = new JLabel("please enter your answer:");
		fie3 = new JTextField(12);
		fie3.setEditable(false);
		fie3.addActionListener(new computeWord());
		add(lab4);
		add(fie3);

		btn = new JButton("start game");
		btn.addActionListener(new StartGame());
		add(btn);

		JLabel lab5 = new JLabel("game status:");
		fie4 = new JTextField(20);
		fie4.setText("please enter the button");
		fie4.setEditable(false);
		add(lab5);
		add(fie4);
	}

	class StartGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			File file = new File("src//com//pickupppp//task4//words5.txt");
			BufferedReader br = null;
			Map<Integer, Vertex> map = new HashMap<>();
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String word = "";
				int index = 0;
				while ((word = br.readLine()) != null) {
					map.put(index, new Vertexm(word));
					index++;
				}
				graph = new Graphm(map);
				for (int i = 0; i < graph.n(); i++) {
					for (int j = 0; j < graph.n(); j++) {
						if (Utils.isRight(map.get(i).value(), map.get(j).value())) {
							graph.setEdge(i, j, 1);
						}
					}
				}
				int v1 = -1;
				int v2 = -1;
				List<Integer> list = new ArrayList<>();
				do {
					v1 = (int) (Math.random() * map.size());
					v2 = (int) (Math.random() * map.size());
				} while (!graph.isLinked(v1, v2));

				graph.isLinked(v1, v2, list);
				System.out.println(list);
				System.out.println(map.get(v1).value() + "--->" + map.get(v2).value());
				for (int temp : list) {
					System.out.print(map.get(temp).value() + "-->");
				}

				fie1.setText(map.get(v1).value());
				fie2.setText(map.get(v2).value());
				fie3.setEditable(true);
				are1.setText(fie1.getText());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (null != br) {
					try {
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	class computeWord implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String word = fie3.getText();
			String[] words = are1.getText().split("-->");
			String word1 = words[words.length-1];
			fie3.setText("");
			System.out.println(word1);
			if (Utils.isRight(word1, word) && graph.isLinked(fie1.getText(), word)) {
				if (graph.isEdge(word, fie2.getText())) {
					are1.setText(are1.getText() + "-->" + word + "-->" + fie2.getText());
					fie4.setText("you win, enter button restart game");
					fie3.setEditable(false);
					btn.setText("restart game");
				} else if (graph.isLinked(word, fie2.getText())) {
					are1.setText(are1.getText() + "-->" + word);
					fie4.setText("you are right, please enter next word");
				} else {
					are1.setText(are1.getText() + "-->" + word);
					fie4.setText("you lose, enter button restart game");
					btn.setText("restart game");
					fie3.setEditable(false);
				}
			} else {
				are1.setText(are1.getText() + "-->" + word);
				fie4.setText("you lose, enter button restart game");
				btn.setText("restart game");
				fie3.setEditable(false);
			}
		}

	}
}
