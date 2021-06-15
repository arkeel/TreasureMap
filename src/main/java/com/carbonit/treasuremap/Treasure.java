package com.carbonit.treasuremap;

public class Treasure extends AbstractMapElement {

    private int nbTreasure;
    private boolean isTreasureFree;

    /**
     * Initialize the treasure available on map
     *
     * @param pos x and y axis of the treasure
     * @param nbTreasure number of treasure available
     */
    public Treasure(Position pos, int nbTreasure) {
        super(pos);
        this.nbTreasure = nbTreasure;
        isTreasureFree = true;
    }

    /**
     * Get the number of treasure still on the map
     * @return the number of treasure available
     */
    public int getNbTreasure() {
        return this.nbTreasure;
    }

    /**
     * Check if there is no adventurer on the treasure map
     * @return if the treasure position on map is available
     */
    public boolean isTreasureFree() {
        return this.isTreasureFree;
    }

    /**
     * Set the treasure on map availability
     * @param isTreasureFree flag to notify adventurer presence on treasure
     */
    public void setTreasureStatus(boolean isTreasureFree) {
            this.isTreasureFree = isTreasureFree;
    }

    /**
     * Remove the treasure collected on map
     */
    public void collectTreasure() {
        this.nbTreasure -= 1;
    }

    /**
     * {@inheritDoc}
     * Display the treasure as in the file
     * @return a string representing the treasure
     */
    @Override
    public String render() {
        return "T" + super.render() + " - " + nbTreasure;
    }

}
