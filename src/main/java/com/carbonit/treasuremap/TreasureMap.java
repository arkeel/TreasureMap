package com.carbonit.treasuremap;

import java.io.File;

public class TreasureMap {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Error, usage: java TreasureMap inputfile");
            System.exit(1);
        }

        File mapTxt = new File(args[0]);
        TreasureHunt game = new TreasureHunt(mapTxt);
        game.hunt();
        game.printResult();
    }
}
