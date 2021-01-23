package xyz.chengzi.cs102a.chinesechess.listener;

import xyz.chengzi.cs102a.chinesechess.chess.ChessComponent;
import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardComponent;

public class ChessboardChessListener extends ChessListener {
    private ChessboardComponent chessboardComponent;
    private ChessComponent first;


    public ChessboardChessListener(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    @Override
    public void onClick(ChessComponent chessComponent) {
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                for (int i=0;i<chessboardComponent.getChessboard().length;i++) {
                    for (int j=0;j<chessboardComponent.getChessboard()[0].length;j++){
                        if (first.canMoveTo(chessboardComponent.getChessboard(),chessboardComponent.getChessboard()[i][j].getChessboardPoint())){
                            chessboardComponent.getChessboard()[i][j].setCanMoveTo(true);
                        }
                        else chessboardComponent.getChessboard()[i][j].setCanMoveTo(false);
                    }
                }
                chessboardComponent.repaint();
            }
        } else {
            if (first.getChessColor() == chessComponent.getChessColor() && first != chessComponent) {
                first.setSelected(false);
                chessComponent.setSelected(true);
                chessboardComponent.repaint();
                first = chessComponent;
                for (int i=0;i<chessboardComponent.getChessboard().length;i++) {
                    for (int j = 0; j < chessboardComponent.getChessboard()[0].length; j++) {
                        if (first.canMoveTo(chessboardComponent.getChessboard(), chessboardComponent.getChessboard()[i][j].getChessboardPoint())) {
                            chessboardComponent.getChessboard()[i][j].setCanMoveTo(true);
                        } else chessboardComponent.getChessboard()[i][j].setCanMoveTo(false);
                    }
                }
            }
            else if (first == chessComponent) { // Double-click to unselect.
                chessComponent.setSelected(false);
                first = null;


                for (int i=0;i<chessboardComponent.getChessboard().length;i++) {
                    for (int j = 0; j < chessboardComponent.getChessboard()[0].length; j++) {
                  chessboardComponent.getChessboard()[i][j].setCanMoveTo(false);
                    }
                }
                chessboardComponent.repaint();

            } else if (handleSecond(chessComponent)) {
                chessboardComponent.swapChessComponents(first, chessComponent);
                chessboardComponent.swapColor();

                first.setSelected(false);
                first = null;

                for (int i=0;i<chessboardComponent.getChessboard().length;i++) {
                    for (int j = 0; j < chessboardComponent.getChessboard()[0].length; j++) {
                       chessboardComponent.getChessboard()[i][j].setCanMoveTo(false);
                    }
                }
                chessboardComponent.repaint();
            }
        }
    }

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboardComponent.getCurrentColor();
    }

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboardComponent.getCurrentColor() &&
                first.canMoveTo(chessboardComponent.getChessboard(), chessComponent.getChessboardPoint());
    }

    private ChessComponent[][] copyArray(ChessComponent[][] original) {
        ChessComponent[][] chessComponents = new ChessComponent[original.length][original[0].length];
        for (int i=0;i<original.length;i++){
            for (int j=0;j<original.length;j++){
                chessComponents[i][j] = (ChessComponent)original[i][j];
            }
        }
        return chessComponents;
    }
}
