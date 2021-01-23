package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class HorseChessComponent extends ChessComponent {
    public HorseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (chessboard[destination.getX()][destination.getY()].getChessColor().equals(chessboard[source.getX()][source.getY()].getChessColor())){
            return false;
        }
        else if (destination.getX() - source.getX() == 1) {
            if (destination.getY() - source.getY() == 2 && chessboard[source.getX()][source.getY() + 1] instanceof EmptySlotComponent) {
                return true;
            }
            else if (destination.getY() - source.getY() == -2 && chessboard[source.getX()][source.getY() - 1] instanceof EmptySlotComponent) {
                return true;
            }
        }
        else if (destination.getX() - source.getX() == -1) {
            if (destination.getY() - source.getY() == 2 && chessboard[source.getX()][source.getY() + 1] instanceof EmptySlotComponent) {
                return true;
            }
            else if (destination.getY() - source.getY() == -2 && chessboard[source.getX()][source.getY() - 1] instanceof EmptySlotComponent) {
                return true;
            }
        }
        else if (destination.getX() - source.getX() == 2) {
            if (destination.getY() - source.getY() == 1 && chessboard[source.getX() + 1][source.getY()] instanceof EmptySlotComponent) {
                return true;
            }
            else if (destination.getY() - source.getY() == -1 && chessboard[source.getX() + 1][source.getY()] instanceof EmptySlotComponent) {
                return true;
            }
        }
        else if (destination.getX() - source.getX() == -2) {
            if (destination.getY() - source.getY() == 1 && chessboard[source.getX() - 1][source.getY()] instanceof EmptySlotComponent) {
                return true;
            }
            else if (destination.getY() - source.getY() == -1 && chessboard[source.getX() - 1][source.getY()] instanceof EmptySlotComponent) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        if (getChessColor().getColor()==Color.BLACK){
            g.setColor(Color.BLACK);
            g.drawString("馬", 15, 25);
        }
        else if (getChessColor().getColor()==Color.RED){
            g.setColor(Color.RED);
            g.drawString("傌",15,25);
        }
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

