package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class SoldierChessComponent extends ChessComponent{
    public SoldierChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (getChessColor().getColor() == Color.BLACK) {
            if (source.getX() <= 4) {
                if (source.getX() == destination.getX()) {
                    return false;
                }
                else if (source.getY() == destination.getY()) {
                    if (destination.getX() - source.getX() == 1) {
                        return true;
                    }
                }
            }

            if (source.getX() > 4) {
                if (source.getX() == destination.getX()) {
                    if (Math.abs(destination.getY() - source.getY()) == 1) {
                        return true;
                    }
                } else if (source.getY() == destination.getY()) {
                    if (destination.getX() - source.getX() == 1) {
                        return true;
                    }
                }
            }
        }

        if (getChessColor().getColor() == Color.RED) {
            if (source.getX() > 4) {
                if (source.getX() == destination.getX()) {
                    return false;
                } else if (source.getY() == destination.getY()) {
                    if (destination.getX() - source.getX() == -1) {
                        return true;
                    }
                }
            }

            if (source.getX() <= 4) {
                if (source.getX() == destination.getX()) {
                    if (Math.abs(destination.getY() - source.getY()) == 1) {
                        return true;
                    }
                } else if (source.getY() == destination.getY()) {
                    if (destination.getX() - source.getX() == -1) {
                        return true;
                    }
                }
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
        if (getChessColor().getColor() == Color.BLACK){
            g.setColor(Color.BLACK);
            g.drawString("卒",15,25);
        }
        else if (getChessColor().getColor() == Color.RED){
            g.setColor(Color.RED);
            g.drawString("兵", 15, 25);
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

