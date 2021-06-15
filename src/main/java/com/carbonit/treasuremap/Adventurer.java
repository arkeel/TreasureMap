package com.carbonit.treasuremap;

import java.util.List;

public class Adventurer extends AbstractMapElement {
    private final String name;
    private Direction direction;
    private final List<Movement> moves;
    private int nbTreasures;

    /**
     * Initialize Adventurers going through the map
     *
     * @param pos initial adventurer's position on map
     * @param name adventurer's name
     * @param direction initial direction the adventurer is facing
     * @param moves list of moves the adventurer have to do
     */
    public Adventurer(String name, Position pos, Direction direction, List<Movement> moves) {
        super(pos);
        this.name = name;
        this.direction = direction;
        this.moves = moves;
        this.nbTreasures = 0;
    }

    /**
     * Get the name of the adventurer
     * @return adventurer's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the direction the adventurer is facing
     * @return adventurer's direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Get the number of treasures collected by the adventurer
     * @return adventurer's treasure collected
     */
    public int getNbTreasures() {
        return nbTreasures;
    }

    /**
     * Get the list of movements the adventurer has to do
     * @return the list of movements
     */
    public List<Movement> getMoves() {
        return moves;
    }

    /**
     * Set the direction the adventurer is facing
     * @param direction the direction selected
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Treasure collection by the adventurer
     */
    public void collectTreasure() {
        this.nbTreasures += 1;
    }

    /**
     * Do the adventurer movement if possible and remove it from the list
     * @return the movement to execute
     */
    public Movement move() {
        return this.moves.isEmpty() ? null : moves.remove(0);
    }

    /**
     * Check if the adventurer can still move
     * @return a boolean to verify if the adventurer can move
     */
    public boolean canStillMove() {
        return (!moves.isEmpty());
    }

    /**
     * Change the direction the direction is facing based on the movement executed
     * @param movement the adventurer will execute
     */
    public void changeDirection(Movement movement) {
        if (movement == Movement.RIGHT) {
            switch (this.getDirection()) {
                case NORTH -> this.setDirection(Direction.WEST);
                case EAST -> this.setDirection(Direction.NORTH);
                case WEST -> this.setDirection(Direction.SOUTH);
                case SOUTH -> this.setDirection(Direction.EAST);
            }
        } else {
            switch (this.getDirection()) {
                case NORTH -> this.setDirection(Direction.EAST);
                case EAST -> this.setDirection(Direction.SOUTH);
                case WEST -> this.setDirection(Direction.NORTH);
                case SOUTH -> this.setDirection(Direction.WEST);
            }
        }
    }

    /**
     * {@inheritDoc}
     * Display the Adventurer as in the file
     * @return a string representing the Adventurer
     */
    @Override
    public String render() {
        return "A - " + this.getName() + super.render() + " - " + this.direction.getDirection() + " - " + this.getNbTreasures();
    }
}
