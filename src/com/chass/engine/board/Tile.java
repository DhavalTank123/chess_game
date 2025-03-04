package com.chass.engine.board;

import com.chass.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;


import java.util.HashMap;
import java.util.Map;

//chass engine board
//chass engine pieces
public abstract class Tile {
    protected final int tileCoordinate; //only once
    private static final Map<Integer,EmptyTile> EMPTY_TILES=createAllPossibleEmptyTile();
    private static Map<Integer,EmptyTile> createAllPossibleEmptyTile(){
        final Map<Integer,EmptyTile> emptyTileMap= new HashMap<>();
        for (int i = 0; i <64 ; i++) {
            emptyTileMap.put(i,new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap); //thierd party libarary from google
    }
    public static Tile createTile(final int tileCoordinate,final Piece piece){
        return piece!=null? new OccupiedTile(tileCoordinate,piece):EMPTY_TILES.get(tileCoordinate);
    }
    private Tile(int tileCoordinate){
        this.tileCoordinate=tileCoordinate;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
    public static final class EmptyTile extends Tile{
        EmptyTile(final int coordinate){
            super(coordinate);
        }
        public boolean isTileOccupied(){
            return false;
        }
        public Piece getPiece(){
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        OccupiedTile(int tileCoordinate,Peice pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }
        public boolean isTileOccupied(){
            return true;
        }

        public Piece getPiece(){
            return this.peiceOnTile;
        }
    }
}
