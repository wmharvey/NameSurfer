/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.event.*;

import javax.swing.*;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
	    initControlBar();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == graph || source == name) {
			NameSurferEntry entry = database.findEntry(name.getText());
			if (entry != null) {
				println("Graph: " + entry);
			} else {
				println("Graph: Name data not available");
			}
		}
		if (source == clear) {
			println("Clear");
		}
	}
	
	private void initControlBar() {
		name = new JTextField(15);
		name.addActionListener(this);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		add(new JLabel("Name"), NORTH);
		add(name, NORTH);
		add(graph, NORTH);
		add(clear, NORTH);
		addActionListeners();
		
	}
	
// Private Instance Variables
	private JTextField name;
	private JButton graph;
	private JButton clear;
	
	private NameSurferDataBase database = new NameSurferDataBase("names-data.txt");
}
