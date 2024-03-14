package lk.ijse.dep.service;

import lk.ijse.dep.service.Board;
import lk.ijse.dep.service.Piece;
import lk.ijse.dep.service.Player;
import lk.ijse.dep.service.Winner;

public class AiPlayer extends Player {

    protected static Piece[][] pieces;

    public AiPlayer(Board board) {
        super(board);
        pieces = board.getPieces();
    }

    @Override
    public void movePiece(int col) {
       AiPlayer.MctsAlgorithm mctsAlgorithm = new MctsAlgorithm();
       if(mctsAlgorithm.bcol != -1){
           col = mctsAlgorithm.bcol;
       }else {
           do {
               col = (int) (Math.random() * 6);


           } while (!board.isLeagalMove(col));
       }

        board.updateMove(col, Piece.GREEN);
        board.getBoardUi().update(col, false);

        if ((board.findWinner().getWinningPiece()).equals(Piece.EMPTY)) {

            if (!board.existLeagalMoves()) {
                board.getBoardUi().notifyWinner(new Winner(Piece.EMPTY));
            }

        } else {
            board.getBoardUi().notifyWinner(board.findWinner());
        }

       /* Winner winner = board.findWinner();
        if (winner.getWinningPiece() != Piece.EMPTY) {
            board.getBoardUi().notifyWinner(winner);

        }else {
            if (!board.existLeagalMoves());
            board.getBoardUi().notifyWinner(winner);
        }
*/
    }

    private static class MctsAlgorithm {
        protected int bcol ;
        protected int brow;

        public MctsAlgorithm() {
            winBlock();
        }

        //pieces[col][row] == pieces[col][row + 1] && pieces[col][row + 1] == pieces[col][row + 2]) == (Piece.BLUE)
        private void winBlock() {
            bcol = -1;

            for (int col = 0; col < pieces.length; col++) {
                for (int row = 0; row < 2; row++) {
                    if (pieces[col][row] == pieces[col][row + 1] && pieces[col][row + 1] == pieces[col][row + 2] && pieces[col][row + 2] == Piece.BLUE)
                        if (pieces[col][row + 3] == Piece.EMPTY) {
                                bcol = col;
                        }
                }

            }

            for (int i = 0;i < pieces[1].length;i++){
                for (int j = 0; j < 3; j++) {
                    if (pieces[j][i] == pieces[j + 1][i] && pieces[j + 1][i] == pieces[j + 2][i] && pieces[j + 2][i] == Piece.BLUE) {
                        if(pieces[j+3][i] == Piece.EMPTY){
                            bcol = j+3;
                            //row = i;

                        }

                    }
                }
            }




        }
    }
}
