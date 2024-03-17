package lk.ijse.dep.service;

public interface Board  {
    public int NUM_OF_ROWS =5;
    public int NUM_OF_COL = 6;

    public BoardUI getBoardUi();

    public int findNextAvailableSpot(int col);
    public boolean isLeagalMove(int col);
    public boolean existLeagalMoves();
    public void updateMove(int col, Piece move);

    public void updateMove(int col, int row, Piece move);

    public Winner findWinner();


    Piece[][] getPieces();
}
