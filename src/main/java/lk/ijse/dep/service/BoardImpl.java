package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board {
    private final Piece[][] pieces;
    private final BoardUI boardUI;

    private static final int NUM_OF_COLS = 6;
    private static final int NUM_OF_ROWS = 5;




    public BoardImpl(BoardUI boardUI) {
        this.pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardUI = boardUI;




        for (int col = 0; col < NUM_OF_COLS; col++) {
            for (int row = 0; row <NUM_OF_ROWS ; row++) {
                pieces[col][row] = Piece.EMPTY;

            }
        }


    }

    /*public BoardImpl(BoardController bc, BoardUI boardUi){
        this.pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardUi = boardUi;
    }*/

    public Piece[][] getPieces(){
        return pieces;
    }

    @Override
    public BoardUI getBoardUi() {

        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int row = 0 ; row < NUM_OF_ROWS ; row++) {
            if (pieces[col][row].equals(Piece.EMPTY)) {
                return row;
            }
        }
        return -1;
    }

    @Override
    public boolean isLeagalMove(int col) {
        if (findNextAvailableSpot(col) == -1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existLeagalMoves() {
       /* for (int col = 0; col < NUM_OF_COLS; col++) {
            for (int row = 0; row <NUM_OF_ROWS ; row++) {
                if (pieces[col][row] == Piece.EMPTY) {
                    return true;
                }
            }
        }*/
        for (int col = 0; col < NUM_OF_COLS; col++) {
            if (isLeagalMove(col) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {

        for(int i=0;i<pieces[col].length;i++){
            if(pieces[col][i].equals(Piece.EMPTY)){
                pieces[col][i]=move;
                break;
            }
        }


     /*   int row = findNextAvailableSpot(col);

        // Assuming it's a legal move (no validation)
        pieces[col][row] = move; */


    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }


    @Override
    public Winner findWinner() {

        for (int col = 0; col < NUM_OF_COLS; col++) {
            for (int row = 0; row < NUM_OF_ROWS; row++) {
                Piece currentPiece = pieces[col][row];
                if (currentPiece != Piece.EMPTY) {
                    // Check horizontally
                    if (col + 3 < NUM_OF_COLS &&
                            currentPiece == pieces[col + 1][row] &&
                            currentPiece == pieces[col + 2][row] &&
                            currentPiece == pieces[col + 3][row]) {
                        return new Winner(currentPiece, col, row, col + 3, row);
                    }

                    // Check vertically
                    if (row + 3 < NUM_OF_ROWS &&
                            currentPiece == pieces[col][row + 1] &&
                            currentPiece == pieces[col][row + 2] &&
                            currentPiece == pieces[col][row + 3]) {
                        return new Winner(currentPiece, col, row, col, row + 3);
                    }
                }
            }
        }

        // No winner found
        return new Winner(Piece.EMPTY);
    }


    }

       /* int humanCount=0,aiCount=0;

        for (int i=0;i<NUM_OF_COLS;i++){

            humanCount=0;
            aiCount=0;

            for (int j=0;j<NUM_OF_ROWS;j++){
                if(pieces[i][j].equals(Piece.BLUE)){
                    humanCount++;
                    aiCount=0;
                } else if (pieces[i][j].equals(Piece.GREEN)) {
                    aiCount++;
                    humanCount=0;
                }

                if(humanCount==4){
                    return new Winner(Piece.BLUE,i,(j-3),i,j);
                } else if (aiCount==4) {
                    return new Winner(Piece.GREEN,i,(j-3),i,j);
                }

            }

        }

        for (int i=0;i<NUM_OF_ROWS;i++){

            humanCount=0;
            aiCount=0;

            for (int j=0;j<NUM_OF_COLS;j++){

                if(pieces[j][i].equals(Piece.BLUE)){
                    humanCount++;
                    aiCount=0;
                } else if (pieces[j][i].equals(Piece.GREEN)) {
                    aiCount++;
                    humanCount=0;
                }else {
                    aiCount=0;
                    humanCount=0;
                }

                if(humanCount==4){
                    return new Winner(Piece.BLUE,(j-3),i,j,i);
                } else if (aiCount==4) {
                    return new Winner(Piece.GREEN,(j-3),i,j,i);
                }

            }
        }
        return new Winner(Piece.EMPTY);

        }
*/






