package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class ElephantChessComponent extends ChessComponent{
    public ElephantChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int countG=0;
        int countT=0;
        for (int i = 0;i<10;i++){
            if (chessboard[i][source.getY()] instanceof GeneralChessComponent){
                countG++;
                countT++;
                for (int j=i;j<10;j++){
                    if (!(chessboard[j][source.getY()] instanceof EmptySlotComponent)){
                        countT++;
                    }
                    if (chessboard[j][source.getY()] instanceof GeneralChessComponent){
                        countG++;
                        countT++;
                        break;
                    }
                }
            }
        }
        if (chessboard[destination.getX()][destination.getY()].getChessColor().equals(chessboard[source.getX()][source.getY()].getChessColor())){
            return false;
        }
        else if (source.getX()<=4) {
            if (destination.getX() == source.getX() - 2 && destination.getY() == source.getY() - 2 &&
                    chessboard[source.getX() - 1][source.getY() - 1] instanceof EmptySlotComponent) {
                return true;
            } else if (destination.getX()<=4 && destination.getX() == source.getX() + 2 && destination.getY() == source.getY() - 2 &&
                    chessboard[source.getX() + 1][source.getY() - 1] instanceof EmptySlotComponent) {
                return true;
            } else if (destination.getX()<=4 && destination.getX() == source.getX() + 2 && destination.getY() == source.getY() + 2 &&
                    chessboard[source.getX() + 1][source.getY() + 1] instanceof EmptySlotComponent) {
                return true;
            } else if (destination.getX() == source.getX() - 2 && destination.getY() == source.getY() + 2 &&
                    chessboard[source.getX() - 1][source.getY() + 1] instanceof EmptySlotComponent) {
                return true;
            }
        }
        else if (source.getX()>4){
            if (destination.getX()>4 && destination.getX() == source.getX() - 2 && destination.getY() == source.getY() - 2 &&
                    chessboard[source.getX() - 1][source.getY() - 1] instanceof EmptySlotComponent) {
                return true;
            } else if (destination.getX() == source.getX() + 2 && destination.getY() == source.getY() - 2 &&
                    chessboard[source.getX() + 1][source.getY() - 1] instanceof EmptySlotComponent) {
                return true;
            } else if (destination.getX() == source.getX() + 2 && destination.getY() == source.getY() + 2 &&
                    chessboard[source.getX() + 1][source.getY() + 1] instanceof EmptySlotComponent) {
                return true;
            } else if (destination.getX()>4 && destination.getX() == source.getX() - 2 && destination.getY() == source.getY() + 2 &&
                    chessboard[source.getX() - 1][source.getY() + 1] instanceof EmptySlotComponent) {
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
        g.setColor(Color.BLACK);
        if (getChessColor().getColor()==Color.BLACK){
        g.drawString("象", 15, 25);}// FIXME: Use library to find the correct offset.
        if (getChessColor().getColor()==Color.RED){
            g.setColor(Color.RED);
            g.drawString("相",15,25);
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
