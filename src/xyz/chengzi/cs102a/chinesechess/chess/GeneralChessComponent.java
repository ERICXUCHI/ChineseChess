package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class GeneralChessComponent extends ChessComponent {
    public GeneralChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int count=0;
        if (chessboard[destination.getX()][destination.getY()].getChessColor().equals(chessboard[source.getX()][source.getY()].getChessColor())){
            return false;
        }
        else if (getChessColor().getColor() == Color.BLACK) {
            for (int i=source.getX()+1;i<10;i++){
                if (!(chessboard[i][source.getY()] instanceof EmptySlotComponent)){
                    count++;
                }
                if (chessboard[i][source.getY()] instanceof GeneralChessComponent){
                    break;
                }
            }
            if (chessboard[destination.getX()][destination.getY()] instanceof GeneralChessComponent && count==1){
                return true;
            }
            else if (Math.abs(destination.getX() - source.getX()) == 1
                    && destination.getY() == source.getY()
                    && destination.getY() >= 3
                    && destination.getY() <= 5
                    && destination.getX() >= 0
                    && destination.getX() <= 2
                    && (chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent
                    || chessboard[destination.getX()][destination.getY()].getChessColor().getColor() == Color.RED)) {
                return true;
            }
            else if (Math.abs(destination.getY() - source.getY()) == 1
                    && destination.getX() == source.getX()
                    && destination.getY() >= 3
                    && destination.getY() <= 5
                    && destination.getX() >= 0
                    && destination.getX() <= 2
                    && (chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent
                    || chessboard[destination.getX()][destination.getY()].getChessColor().getColor() == Color.RED)) {
                return true;
            }
        }

        else if (getChessColor().getColor() == Color.RED) {
            for (int i=source.getX()-1;i>0;i--){
                if (!(chessboard[i][source.getY()] instanceof EmptySlotComponent)){
                    count++;
                }
                if (chessboard[i][source.getY()] instanceof GeneralChessComponent){
                    break;
                }
            }
            if (chessboard[destination.getX()][destination.getY()] instanceof GeneralChessComponent && count==1){
                return true;
            }
            if (Math.abs(destination.getX() - source.getX()) == 1
                    && destination.getY() == source.getY()
                    && destination.getY() >= 3
                    && destination.getY() <= 5
                    && destination.getX() >= 7
                    && destination.getX() <= 9
                    && (chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent
                    || chessboard[destination.getX()][destination.getY()].getChessColor().getColor() ==Color.BLACK)) {
                return true;
            }
            else if (Math.abs(destination.getY() - source.getY()) == 1
                    && destination.getX() == source.getX()
                    && destination.getY() >= 3
                    && destination.getY() <= 5
                    && destination.getX() >= 7
                    && destination.getX() <= 9
                    && (chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent
                    || chessboard[destination.getX()][destination.getY()].getChessColor().getColor() == Color.BLACK)) {
                return true;
            }
        }
        return false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        if (getChessColor().getColor() == Color.BLACK){
            g.setColor(Color.BLACK);
            g.drawString("將",15,25);
        }
        if (getChessColor().getColor() == Color.RED){
            g.setColor(Color.RED);
            g.drawString("帥", 15, 25);} // FIXME: Use library to find the correct offset.
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
