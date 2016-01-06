package com.alberto.tictactoe;

public class PlayerFactory {
    private static Player xPlayer;
    private static Player oPlayer;

    public static Player getPlayer(final String symbol) {
        if (!isXSymbol(symbol) && !isOSymbol(symbol))
            throw new PlayerNotAllowedException("The symbol " + symbol + " is not allowed");
        return getAllowedPlayer(symbol);

    }

    private static Player getAllowedPlayer(final String symbol) {
        if (isXSymbol(symbol))
            return getXPlayer();
        return getOPlayer();
    }

    private static boolean isOSymbol(final String symbol) {
        return symbol.equalsIgnoreCase("O");
    }

    private static boolean isXSymbol(final String symbol) {
        return symbol.equalsIgnoreCase("X");
    }

    private static Player getOPlayer() {
        if (oPlayer == null) {
            oPlayer = new OPLayer();
        }
        return oPlayer;
    }

    private static Player getXPlayer() {
        if (xPlayer == null) {
            xPlayer = new XPlayer();
        }
        return xPlayer;
    }
}
