package com.shpp.p2p.cs.pkrmarenko.namesurfer;

/*
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

import java.awt.*;

public interface NameSurferConstants {

    /**
     * The width of the application window
     */
    int APPLICATION_WIDTH = 800;

    /**
     * The name of the file containing the data
     */
    String NAMES_DATA_FILE = "Text_Name/names-data.txt";

    /**
     * The first decade in the database
     */
    int START_DECADE = 1900;

    /**
     * The number of decades
     */
    int N_DECADES = 12;

    /**
     * The maximum rank in the database
     */
    int MAX_RANK = 1000;

    /**
     * The number of pixels to reserve at the top and bottom
     */
    int GRAPH_MARGIN_SIZE = 20;
    /**
     * to move text away from lines
     */
    int MOVE_TEXT = 4;
    /**
     *
     */
    int HEIGHT_NAME = -5;
    /**
     * The constant for the color of the program line sketch
     */
    Color COLOR_LINE = Color.BLACK;
    /**
     * the constant for size the point of name
     */
    int SIZE_POINT = 5;
    /**
     * the constant used for size text numbers ages or names of grid
     */
    int SIZE_TEXT_AGE_NAME = 15;
    /**
     * the constant used for  the time interval in years
     */
    int INTERVAL = 10;
    /**
     * the constant user for interval colors of graph
     */
    int COLORS_COUNT = 4;
    /**
     * The token symbol displayed when a name's rank is beyond the maximum rank limit
     */
    String MAX_RANK_TOKEN = "*";

    /**
     * The font name used for rendering labels and tokens on the graph
     */
    String FONT_NAME = "Arial";

    /**
     * The vertical Y-axis offset for positioning the max rank token relative to the line
     */
    int MAX_RANK_TOKEN_Y_OFFSET = 18;

    /**
     * The font size for the max rank token symbol
     */
    int MAX_RANK_TOKEN_SIZE = 30;
    /**
     * Text label for the name input field
     */
    String LABEL_NAME_TEXT = "Name:";

    /**
     * Button text for displaying the graph
     */
    String BUTTON_GRAPH_TEXT = "Graph";

    /**
     * Button text for clearing the graph canvas
     */
    String BUTTON_CLEAR_TEXT = "Clear";

    /**
     * Button text for deleting the grid lines
     */
    String BUTTON_DELETE_GRID_TEXT = "Delete Grid";

    /**
     * Error message displayed when a name is not found in the database
     */
    String ERROR_NAME_NOT_FOUND = "Name not found";

    /**
     * Columns count (width) for the text field input
     */
    int TEXT_FIELD_COLUMNS = 25;


}
