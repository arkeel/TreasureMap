package com.carbonit.treasuremap;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Map {
    private BufferedReader reader;
    private int height;
    private int width;
    private LinkedHashMap<String, List<AbstractMapElement>> mapElements;
    private AbstractMapElement[][] elementsPosition;

    /**
     * Initialize the map and the elements inside it
     *
     * @param mapTxt the map file provided
     */
    public Map (File mapTxt) {
        try {
            this.reader = new BufferedReader(new FileReader(mapTxt));
            this.mapElements = new LinkedHashMap<>();
            this.buildMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (this.reader != null)
                    this.reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read the file the map provided and create each elements
     */
    private void buildMap() {
        String current;
        List<AbstractMapElement> mountains = new ArrayList<>();
        List<AbstractMapElement> treasures = new ArrayList<>();
        List<AbstractMapElement> adventurers = new ArrayList<>();

        int lines = 1;

        try {
            while ((current = reader.readLine()) != null) {

                if (!current.startsWith("#")) {
                    String[] fields = current.replaceAll("\\s+", "").split("-");

                    if (lines == 1 && fields[0].equals("C") && fields.length == 3) {
                        try {
                            this.width = Integer.parseInt(fields[1]);
                            this.height = Integer.parseInt(fields[2]);
                        }
                        catch (NumberFormatException e) {
                            throw new NumberFormatException("Error : at line " + lines + " coordinates {x} - {y} provided for the map must be valid integers");
                        }

                        this.elementsPosition = new AbstractMapElement[this.height][this.width];
                    }
                    else if (lines > 1 && fields[0].equals("M") && fields.length == 3) {
                        mountains.add(this.buildMountain(fields, lines));
                    }
                    else if (lines > 1 && fields[0].equals("T") && fields.length == 4) {
                        treasures.add(this.buildTreasure(fields, lines));
                    }
                    else if (lines > 1 && fields[0].equals("A") && fields.length == 6) {
                        adventurers.add(this.buildAdventurer(fields, lines));
                    }
                    else {
                        if (lines > 1 && fields[0].equals("C")  && fields.length == 3)
                            throw new Exception("Error : at line " + lines + " map already defined");
                        else
                            throw new Exception("Error : at line " + lines + " item is not valid");

                    }
                }
                lines++;
            }
            mapElements.put("M", mountains);
            mapElements.put("T", treasures);
            mapElements.put("A", adventurers);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Check all the fields provided and create mountain element of the map
     * @param fields x and y axis of the mountain
     * @param lines line of the mountain definition in file
     * @return the mountain object
     * @throws Exception for every bad value provided
     */
    private AbstractMapElement buildMountain(String[] fields, int lines) throws Exception {
        int x;
        int y;

        try {
            x = Integer.parseInt(fields[1]);
            y = Integer.parseInt(fields[2]);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Error : at line " + lines + " coordinates {x} - {y} provided for mountains must be valid integers");
        }
        Mountain mountain = new Mountain(new Position(x, y));
        if (isPositionEmpty(x, y))
            this.elementsPosition[y][x] = mountain;
        else
            throw new Exception("Error : at line " + lines + " a map element is already at coordinate " + x + "- " + y);
        if (!isWithinMap(mountain)) {
            throw new Exception("Error : at line " + lines + "  coordinates {x} - {y} provided for mountains must be on the map");
        }

        return mountain;
    }

    /**
     * Check all the fields provided and create Treasure element of the map
     * @param fields x, y axis and the number of the treasure
     * @param lines line of the treasure definition in file
     * @return the treasure object
     * @throws Exception for every bad value provided
     */
    private AbstractMapElement buildTreasure(String[] fields, int lines) throws Exception {
        int x;
        int y;
        int nbTreasure;

        try {
            x = Integer.parseInt(fields[1]);
            y = Integer.parseInt(fields[2]);
            nbTreasure = Integer.parseInt(fields[3]);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Error : at line " + lines + " coordinates {x} - {y} and {nbTreasure} provided for treasure must be valid integers");
        }
        Treasure treasure = new Treasure(new Position(x, y), nbTreasure);
        if (isPositionEmpty(x, y))
            this.elementsPosition[y][x] = treasure;
        else
            throw new Exception("Error : at line " + lines + " a map element is already at coordinate " + x + "- " + y);
        if (!isWithinMap(treasure)) {
            throw new Exception("Error : at line " + lines + " coordinates {x} - {y} provided for treasure must be on the map");
        }
        return treasure;
    }

    /**
     * Check all the fields provided and create Adventurer element of the map
     * @param fields the name, x, y axis, direction and each movements of the adventurer
     * @param lines line of the adventurer definition in file
     * @return the adventurer object
     * @throws Exception for every bad value provided
     */
    private Adventurer buildAdventurer(String[] fields, int lines) throws Exception {
        int x;
        int y;
        Direction direction = null;
        List<Movement> moves = new ArrayList<>();

        try {
            x = Integer.parseInt(fields[2]);
            y = Integer.parseInt(fields[3]);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Error : at line " + lines + " coordinates {x} - {y} provided for adventurer must be valid integers");
        }

        for (Direction d : Direction.values()) {
            if (d.getDirection() == fields[4].charAt(0))
                direction = d;
        }

        if (direction == null)
            throw new Exception("Error : at line " + lines + " direction {D} provided for adventurer is not valid");

        char[] eachMoves = fields[5].toCharArray();

        for (char c : eachMoves) {
            for (Movement m : Movement.values()){
                if (m.getMove() == c)
                    moves.add(m);
            }
        }
        if (eachMoves.length != moves.size())
            throw new Exception("Error : at line " + lines + " movement provided for adventurer is not valid");
        Adventurer adventurer = new Adventurer(fields[1], new Position(x, y),
                direction, moves);
        if (isPositionEmpty(x, y))
            this.elementsPosition[y][x] = adventurer;
        else
            throw new Exception("Error : at line " + lines + " a map element is already at coordinate " + x + "- " + y);
        if (!isWithinMap(adventurer)) {
            throw new Exception("Error : at line " + lines + " coordinates {x} - {y} provided for adventurer must be on the map");
        }
        return adventurer;
    }

    /**
     * Get the height of the map
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width of the map
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Check if element position is inside the map
     * @return a boolean to verify the position
     */
    private boolean isWithinMap(AbstractMapElement mapElement) {
        return mapElement.getXFromPos() < this.width && mapElement.getYFromPos() < this.height;
    }

    /**
     * Check if element position is empty
     * @param x axis of the element in map
     * @param y axis of the element in map
     * @return a boolean to verify if the position is empty
     */
    public boolean isPositionEmpty(int x, int y) {
        return elementsPosition[y][x] == null;
    }

    /**
     * Get elements on map based on type
     * @param type elements in map
     * @return list of elements selected
     */
    public List<AbstractMapElement> getMapElements(String type) {
        return mapElements.get(type);
    }

    /**
     * Get the map elements and their position on map
     * @return the elements of the map at their position
     */
    public AbstractMapElement[][] getMapElementsPos() {
        return elementsPosition;
    }

    /**
     * Set the map elements at their position on map
     * @param newMapElementPos the new mapping of elements
     */
    public void setMapElementsPos(AbstractMapElement[][] newMapElementPos) {
        this.elementsPosition = newMapElementPos;
    }

    /**
     * Display the map as in the file
     * @return a string representing the map
     */
    public String render() {
        String a = "";
        String m = "";
        String t = "";

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (elementsPosition[i][j] != null) {
                    if (elementsPosition[i][j].render().startsWith("M"))
                        m += elementsPosition[i][j].render() + "\n";
                    else if (elementsPosition[i][j].render().startsWith("T"))
                        t += elementsPosition[i][j].render() + "\n";
                    else
                        a += elementsPosition[i][j].render() + "\n";
                }
            }
        }
        return "C - " + this.getWidth() + " - " + this.getHeight() + "\n" + m + t + a;
    }
}
