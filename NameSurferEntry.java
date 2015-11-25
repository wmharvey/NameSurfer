/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		name = parseName(line);
		for (int decade = 0; decade < 11; decade++) {
			rankArray[decade] = parseDecade(line, decade);
		}
	}
	
	public String parseName(String line) {
		String newName = "";
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (ch == ' ') {
				break;
			}
			newName += ch;
			last++;
		}
		return newName;
	}
	
	public int parseDecade(String line, int decade) {
		String nextDecade = "";
		last++;
		for (int i = 0; i < 4; i++) {
			if (last == line.length()) break;
			char ch = line.charAt(last);
			if (ch == ' ') break;
			nextDecade += ch;
			last++;
		}
		int rank = Integer.parseInt(nextDecade);
		return rank;
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return rankArray[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String printout = name + " [";
		for (int i = 0; i < 11; i++) {
			printout += rankArray[i];
			if (i < 10) {
				printout += " ";
			}
			if (i == 10) {
				printout += "]";
			}
		}
		return printout;	
	}
	
	private String name;
	private int[] rankArray = new int[11];
	
	private int last = 0;
}

