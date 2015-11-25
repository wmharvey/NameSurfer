/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		removeAll();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		clear();
		createBackground();
	}
	
	private void createBackground() {
		GLine topline = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine bottomline = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, 
									getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(topline);
		add(bottomline);
		for (int i = 0; i < NDECADES; i++) {
			add(new GLine(i * (getWidth() / NDECADES), 0, i * (getWidth() / NDECADES), getHeight()) );
		}
		for (int i = 0; i < NDECADES; i++) {
			GLabel label = new GLabel("" + (START_DECADE + (5 * i)));
			double x = 10 + ((getWidth() / NDECADES) * i);
			double y = getHeight() - ((GRAPH_MARGIN_SIZE - label.getAscent()) / 2);
			add(label, x, y);
		}
		
	}	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
