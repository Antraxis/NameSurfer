package com.shpp.p2p.cs.pkrmarenko.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    // List of entries received from the database
    ArrayList<NameSurferEntry> graph = new ArrayList<>();

    // Array used for storing graph colors as well as grid elements
    Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};

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
        graph.clear();
        removeAll();
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (entry != null) {
            graph.add(entry);
            update();
        }
    }

    public void deleteGrid() {
        removeAll();
        drawGraphName();
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        createGridForGraph();
        drawGraphName();
    }

    /**
     * Draws the application grid with decade indicators and boundary lines.
     */
    private void createGridForGraph() {
        // Variables modified at each step of the program to draw the next interval
        double wight = (getWidth() - GRAPH_MARGIN_SIZE * 2.0) / N_DECADES;
        double newline = GRAPH_MARGIN_SIZE;
        int decade = START_DECADE;

        // Create top and bottom boundary lines
        createLine(GRAPH_MARGIN_SIZE, GRAPH_MARGIN_SIZE, getWidth() - GRAPH_MARGIN_SIZE, GRAPH_MARGIN_SIZE, COLOR_LINE);
        createLine(GRAPH_MARGIN_SIZE, getHeight() - GRAPH_MARGIN_SIZE, getWidth() - GRAPH_MARGIN_SIZE, getHeight() - GRAPH_MARGIN_SIZE, COLOR_LINE);

        // Loop to create vertical grid lines and decade labels
        for (int i = 0; i < N_DECADES; i++) {
            String decadeText = Integer.toString(decade);
            createLine(newline, GRAPH_MARGIN_SIZE / 2.0, newline, getHeight() - GRAPH_MARGIN_SIZE / 2.0, COLOR_LINE);
            createLabel(decadeText, newline, getHeight() - GRAPH_MARGIN_SIZE / 2.0, MOVE_TEXT, MOVE_TEXT, SIZE_TEXT_AGE_NAME);
            newline += wight;
            decade += INTERVAL;
        }
    }

    /**
     * Draws the graph lines and data points based on the ranks of each name entry.
     */
    private void drawGraphName() {
        // Variables modified at each loop iteration to construct the next step of the graph
        double wight = (getWidth() - GRAPH_MARGIN_SIZE * 2.0) / N_DECADES;
        double newline = GRAPH_MARGIN_SIZE;
        int decade = START_DECADE;

        for (int j = 0; j < N_DECADES; j++) {
            if (!graph.isEmpty()) {
                for (int i = 0; i < graph.size(); i++) {
                    String name = graph.get(i).getName();
                    double pointOfName = pointOfGraph(graph.get(i), decade, 0);
                    createLabel(name, newline, pointOfName, 0, HEIGHT_NAME, SIZE_TEXT_AGE_NAME);
                    createOval(newline, pointOfName, colors[i % COLORS_COUNT]);

                    if (decade != START_DECADE) {
                        // Create a graph line starting from the second decade to use the previous value
                        // and avoid going out of array bounds.
                        // Drawing is performed using the points obtained for rendering the name.
                        double pointOfRank1 = pointOfGraph(graph.get(i), decade, INTERVAL);
                        createLine(newline, pointOfName, newline - (getWidth() - GRAPH_MARGIN_SIZE * 2.0) / N_DECADES,
                                pointOfRank1, colors[i % COLORS_COUNT]);
                    }
                }
            }
            newline += wight;
            decade += INTERVAL;
        }
    }

    /**
     * Calculates the Y-axis coordinate height for a name's rank point
     * to build the graph and display the name properly.
     *
     * @param nameEntry the object storing name data located within the graph array
     * @param decade    the year for which the point height on the coordinate axis is calculated
     * @param time      the time interval offset used for the graph segment
     * @return the Y-axis coordinate height for the point
     */
    public double pointOfGraph(NameSurferEntry nameEntry, int decade, int time) {
        int rank = nameEntry.getRank(decade - time);
        if (rank != 0) {
            return GRAPH_MARGIN_SIZE + ((rank - 1.0) / (MAX_RANK - 1.0)) * ((getHeight() - GRAPH_MARGIN_SIZE) - GRAPH_MARGIN_SIZE);
        } else {
            return getHeight() - GRAPH_MARGIN_SIZE;
        }
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        removeAll();
        update();
    }

    public void componentShown(ComponentEvent e) {
    }

    public void createLine(double x, double y, double x2, double y2, Color color) {
        GLine Line = new GLine(x, y, x2, y2);
        Line.setColor(color);
        add(Line);
    }

    /**
     * Creates and displays a new text label on the graph with specified positioning and styling.
     *
     * @param text       the string text to display
     * @param x          the base X coordinate
     * @param y          the base Y coordinate
     * @param heightForX additional offset added to the X coordinate
     * @param heightForY additional offset added to the Y coordinate
     * @param size       the font size of the label text
     */
    public void createLabel(String text, double x, double y, double heightForX, double heightForY, int size) {
        GLabel label = new GLabel(text, x + heightForX, y + heightForY);
        label.setColor(Color.BLACK);
        label.setFont(new Font(FONT_NAME, Font.BOLD, size));
        add(label);
    }

    /**
     * Draws either a filled oval point or a max-rank token symbol depending on the point's position.
     *
     * @param x     the X coordinate of the point
     * @param y     the Y coordinate of the point
     * @param color the fill color for the oval point
     */
    public void createOval(double x, double y, Color color) {
        if (y == getHeight() - GRAPH_MARGIN_SIZE) {
            createLabel(MAX_RANK_TOKEN, x, y, 0, MAX_RANK_TOKEN_Y_OFFSET, MAX_RANK_TOKEN_SIZE);
        } else {
            GOval oval = new GOval(x, y, SIZE_POINT, SIZE_POINT);
            oval.setColor(color);
            oval.setFilled(true);
            add(oval);
        }
    }
}
