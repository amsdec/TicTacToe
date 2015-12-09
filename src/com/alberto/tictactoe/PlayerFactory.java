package com.alberto.tictactoe;

public class PlayerFactory {
    private PlayerFactory(){}
    private static Player xPlayer;
    private static Player oPlayer;
    public static Player getPlayer(final String symbol) {
        if(symbol.equalsIgnoreCase("X")){
            if(xPlayer == null) {
                xPlayer = new XPlayer();
            }
            return xPlayer;
        }

        if(symbol.equalsIgnoreCase("O")){
            if(oPlayer == null) {
                oPlayer = new OPLayer();
            }
            return oPlayer;
        }

        throw new PlayerNotAllowedException("The symbol " + symbol + " is not allowed");
    }

}
