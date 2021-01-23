package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class EmptySlotComponent extends ChessComponent {
    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location) {
        super(chessboardPoint, location, ChessColor.NONE);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }
    protected void paintComponent(Graphics g){
        if (isCanMoveTo()) {
            g.setColor(Color.RED);
            g.fillOval(getWidth()/2-5,getWidth()/2-5,10,10);
        }
    }
}

