package com.carbonit.treasuremap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TreasureHunt {
    private final Map gameMap;

    public TreasureHunt(File mapTxt) {
        this.gameMap = new Map(mapTxt);
    }

    /**
     * Game loop or each adventurer
     */
    public void hunt() {
        List<AbstractMapElement> adventurers = gameMap.getMapElements("A");

        if (!adventurers.isEmpty()) {
            {
                int i = 0;

                while (i < adventurers.size()) {
                    Adventurer a = (Adventurer) adventurers.get(i);
                    doMovements(a);
                    i++;
                    if (i == adventurers.size()) {
                        for (int j = 0; j < adventurers.size(); j++) {
                            Adventurer b = (Adventurer) adventurers.get(j);
                            if (b.canStillMove()) {
                                i = j;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Realise the movements listed for the adventurer
     * @param currentAdventurer adventurer currently moving
     */
    public void doMovements(Adventurer currentAdventurer) {
        Movement movement = currentAdventurer.move();

        if (movement == Movement.FORWARD) {
            switch (currentAdventurer.getDirection()) {
                case NORTH:
                    if (currentAdventurer.getYFromPos() - 1 >= 0)
                        moveToPos(currentAdventurer, currentAdventurer.getXFromPos(), currentAdventurer.getYFromPos() - 1);
                    break;
                case SOUTH:
                    if (currentAdventurer.getYFromPos() + 1 < gameMap.getHeight())
                        moveToPos(currentAdventurer, currentAdventurer.getXFromPos(), currentAdventurer.getYFromPos() + 1);
                    break;
                case EAST:
                    if (currentAdventurer.getXFromPos() - 1 >= 0)
                        moveToPos(currentAdventurer, currentAdventurer.getXFromPos() - 1, currentAdventurer.getYFromPos());
                    break;
                case WEST:
                    if (currentAdventurer.getXFromPos() + 1 < gameMap.getWidth())
                        moveToPos(currentAdventurer, currentAdventurer.getXFromPos() + 1, currentAdventurer.getYFromPos());
                    break;
            }
        }
        else {
            currentAdventurer.changeDirection(movement);
        }
    }

    /**
     * Move adventurer to new position on map
     * @param currentAdventurer adventurer currently moving
     * @param newX x axis of the position adventurer is moving to
     * @param newY y axis of the position adventurer is moving to
     */
    private void moveToPos(Adventurer currentAdventurer, int newX, int newY) {
        AbstractMapElement[][] allElements = gameMap.getMapElementsPos();

        AbstractMapElement currentPosElement = allElements[currentAdventurer.getYFromPos()][currentAdventurer.getXFromPos()];
        AbstractMapElement nextPosElement = allElements[newY][newX];


        if (nextPosElement == null) {
            if (currentPosElement instanceof Treasure && !((Treasure) currentPosElement).isTreasureFree())
                ((Treasure) currentPosElement).setTreasureStatus(true);
            else if (!(currentPosElement instanceof Treasure))
                allElements[currentAdventurer.getYFromPos()][currentAdventurer.getXFromPos()] = null;

            currentAdventurer.setPos(new Position(newX, newY));

            allElements[newY][newX] = currentAdventurer;

        }
        else if (nextPosElement instanceof Treasure && ((Treasure) nextPosElement).isTreasureFree()) {
            ((Treasure) nextPosElement).setTreasureStatus(false);


            if (!(currentPosElement instanceof Treasure))
                allElements[currentAdventurer.getYFromPos()][currentAdventurer.getXFromPos()] = null;

            currentAdventurer.setPos(new Position(newX, newY));

            currentAdventurer.collectTreasure();

            ((Treasure) nextPosElement).collectTreasure();

            if (((Treasure) nextPosElement).getNbTreasure() == 0) {
                allElements[newY][newX] = currentAdventurer;
            }

        }
        gameMap.setMapElementsPos(allElements);
    }

    /**
     * Display the treasure hunt result in file
     */
    public void printResult() {
        LocalDate now = LocalDate.now();

        try {
            File resultFile = new File("./TreasureMap" + now + ".txt");
            FileWriter writer = new FileWriter(resultFile);
            writer.write(gameMap.render());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
