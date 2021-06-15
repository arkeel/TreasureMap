package com.carbonit.treasuremap;

public class Mountain extends AbstractMapElement {

    /**
     * Initialize the mountain present on the map
     *
     * @param mountainPos x and y axis of the mountain
     */
    public Mountain(Position mountainPos) {
        super(mountainPos);
    }

    /**
     * {@inheritDoc}
     * Display the mountain as in the file
     * @return a string representing the mountain
     */
    @Override
    public String render() {
        return "M" + super.render();
    }
}
