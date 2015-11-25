/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		graph = new NameSurferGraph();
		add(graph);
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
		if (source == graphButton || source == name) {
			NameSurferEntry entry = database.findEntry(name.getText());
			if (entry != null) {
				graph.addEntry(entry);
				graph.update();
			}
		}
		if (source == clearButton) {
			graph.clear();
			graph.update();
		}
	}
	
	private void initControlBar() {
		name = new JTextField(15);
		name.addActionListener(this);
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		add(new JLabel("Name"), NORTH);
		add(name, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);
		addActionListeners();
		
	}
	
// Private Instance Variables
	private JTextField name;
	private JButton graphButton;
	private JButton clearButton;
	
	private NameSurferDataBase database = new NameSurferDataBase("names-data.txt");
	private NameSurferGraph graph;
}
