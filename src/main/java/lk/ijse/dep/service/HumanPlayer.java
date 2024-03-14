package lk.ijse.dep.service;

import lk.ijse.dep.service.Board;
import lk.ijse.dep.service.Piece;
import lk.ijse.dep.service.Player;
import lk.ijse.dep.service.Winner;

public class HumanPlayer extends Player {
    public HumanPlayer(Board newBoard) {

        super(newBoard);
    }

   /* @Override
    public void movePiece(int col) {
        if (board.isLeagalMove( col)){
            board.updateMove(col, Piece.BLUE);
            board.getBoardUi().update(col, true);
            Winner winner = board.findWinner();
            if (winner.getWinningPiece() != Piece.EMPTY) {
                board.getBoardUi().notifyWinner(winner);
                
            }else {
                if (!board.existLeagalMoves());
                        board.getBoardUi().notifyWinner(winner);
            }
        }
    }
}
*/

    @Override
    public void movePiece(int col) {

        if (board.isLeagalMove(col)) {

            board.updateMove(col, Piece.BLUE);
            board.getBoardUi().update(col, true);

             if ((board.findWinner().getWinningPiece()).equals(Piece.EMPTY)) {

                if (!board.existLeagalMoves()) {
                    board.getBoardUi().notifyWinner(new Winner(Piece.EMPTY));
                }

            } else {
                board.getBoardUi().notifyWinner(board.findWinner());
            }

        }

    }
}