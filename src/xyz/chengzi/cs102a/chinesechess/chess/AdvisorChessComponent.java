package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class AdvisorChessComponent extends ChessComponent {
    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (chessboard[destination.getX()][destination.getY()].getChessColor().equals(chessboard[source.getX()][source.getY()].getChessColor())){
            return false;
        }
        else if (getChessColor().getColor() == Color.BLACK) {
            for (int i = 0; i < 10; i++) {

            }
            if (Math.abs(destination.getX() - source.getX()) == 1 &&
                    Math.abs(destination.getY() - source.getY()) == 1
                    && destination.getY() >= 3
                    && destination.getY() <= 5
                    && destination.getX() >= 0
                    && destination.getX() <= 2
                    && chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent) {
                return true;
            }
        }

        if (getChessColor().getColor() == Color.RED) {
            if (Math.abs(destination.getX() - source.getX()) == 1 &&
                    Math.abs(destination.getY() - source.getY()) == 1
                    && destination.getY() >= 3
                    && destination.getY() <= 5
                    && destination.getX() >= 7
                    && destination.getX() <= 9
                    && chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent) {
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
        if (getChessColor().getColor() == Color.BLACK){
            g.setColor(Color.BLACK);
            g.drawString("士",15,25);
        }
        else if (getChessColor().getColor() == Color.RED){
            g.setColor(Color.RED);
            g.drawString("仕", 15, 25);
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