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
		list.clear();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		list.add(entry);
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		createBackground();
		graph();
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
			GLabel label = new GLabel("" + (START_DECADE + (10 * i)));
			double x = 5 + ((getWidth() / NDECADES) * i);
			double y = getHeight() - ((GRAPH_MARGIN_SIZE - label.getAscent()) / 2);
			add(label, x, y);
		}
		
	}
	
	private void graph() {
		Iterator<NameSurferEntry> it = list.iterator();
		while (it.hasNext()) {
			graphEntry(it.next());
		}
	}
	
	private void graphEntry(NameSurferEntry entry) {
		double lastX = 0;
		double lastY = 0;
		Color color = nextColor(list.indexOf(entry));
		for (int i = 0; i < NDECADES; i++) {
			int popularity = entry.getRank(i);
			double currentX = (getWidth() / NDECADES) * i;
			double currentY = GRAPH_MARGIN_SIZE + popularity * 
					((getHeight() - (double)(GRAPH_MARGIN_SIZE * 2))/ 1000.0);
			if (popularity != 0) {
				GLabel label = new GLabel((entry.getName() + " " + popularity), currentX, currentY);
				label.setColor(color);
				add(label);
			} else {
				currentY = (getHeight() - GRAPH_MARGIN_SIZE);
				GLabel label = new GLabel((entry.getName() + " *"), currentX, currentY);
				label.setColor(color);
				add(label);
			}
			GLine line = new GLine(lastX, lastY, currentX, currentY);
			line.setColor(color);
			add(line);
			lastX = currentX;
			lastY = currentY;
		}
	}
	
	private Color nextColor(int number) {
		int colorNumber = number % 4;
		switch (colorNumber) {
		case 0: return Color.BLACK;
		case 1: return Color.RED;
		case 2: return Color.BLUE;
		case 3: return Color.MAGENTA;
		default: return Color.BLACK;
		}
	}
	
	// Private instance variables
	ArrayList<NameSurferEntry> list = new ArrayList<NameSurferEntry>();
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
