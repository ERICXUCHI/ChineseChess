package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class CannonChessComponent extends ChessComponent{
    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (chessboard[destination.getX()][destination.getY()].getChessColor().equals(chessboard[source.getX()][source.getY()].getChessColor())){
            return false;
        }
        else if (chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent){
            if (source.getX() == destination.getX()) {
                int row = source.getX();
                for (int col = Math.min(source.getY(), destination.getY()) + 1;
                     col < Math.max(source.getY(), destination.getY()); col++) {
                    if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (source.getY() == destination.getY()) {
                int col = source.getY();
                for (int row = Math.min(source.getX(), destination.getX()) + 1;
                     row < Math.max(source.getX(), destination.getX()); row++) {
                    if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else { // Not on the same row or the same column.
                return false;
            }
            return true;
        }
        else if (!(chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
            int count;
            if (source.getX() == destination.getX()) {
                count=0;
                int row = source.getX();
                for (int col = Math.min(source.getY(), destination.getY()) + 1;
                     col < Math.max(source.getY(), destination.getY()); col++) {
                    if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                        count++;
                    }
                }
                if (count!=1){return false;}
            } else if (source.getY() == destination.getY()) {
                count=0;
                int col = source.getY();
                for (int row = Math.min(source.getX(), destination.getX()) + 1;
                     row < Math.max(source.getX(), destination.getX()); row++) {
                    if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                        count++;
                    }
                }
                if (count!=1){return false;}
            } else { // Not on the same row or the same column.
                return false;
            }
        }

        return true;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        if (getChessColor().getColor() == Color.BLACK) {
            g.drawString("砲", 15, 25);
        }
        if (getChessColor().getColor() == Color.RED) {
            g.setColor(Color.RED);
            g.drawString("炮", 15, 25);
        } // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the chess if selected.
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        if (isCanMoveTo()) {
            g.setColor(Color.RED);
            g.fillOval(getWidth()/2-5,getWidth()/2-5,10,10);
        }
    }
}
