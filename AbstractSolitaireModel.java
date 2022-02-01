package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * An Abstract class for the Solitaire Model Interface.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {

  protected final int armThickness;
  protected final int rows;
  protected final int columns;
  protected SlotState[][] board;

  /**
   * The first constructor should take no parameters, and initialize the
   * game board as shown above (arm thickness 3 with the empty slot at the center).
   */
  public AbstractSolitaireModel() {
    this.armThickness = 3;
    this.rows = 3;
    this.columns = 3;
    this.board = this.createBoard();
  }

  /**
   * A second constructor should take two parameters: sRow and sCol. It should initialize the
   * game board so that the arm thickness is 3 and the empty slot is at the position (sRow, sCol).
   * If this specified position is invalid, it should throw an IllegalArgumentException
   * with a message "Invalid empty cell position (r,c)" with 𝑟 and 𝑐 replaced with
   * the row and column passed to it.
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell is invalid
   */
  public AbstractSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 3;
    this.rows = sRow;
    this.columns = sCol;
    this.validInBounds(rows, columns);
    this.board = this.createBoard();
  }

  /**
   * The third constructor should take the arm thickness as its only parameter and initialize
   * a game board with the empty slot at the center.
   * @param armThickness the width marbles of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public AbstractSolitaireModel(int armThickness) throws IllegalArgumentException {
    this.armThickness = armThickness;
    this.rows = ((this.armThickness - 1) * 3) / 2;
    this.columns = ((this.armThickness - 1) * 3) / 2;
    this.validInBounds(this.rows, this.columns);
    this.board = this.createBoard();
  }

  /**
   * Finally a fourth constructor should take the arm thickness, row and column of the empty slot
   * in that order. It should throw an IllegalArgumentException if the arm thickness is not a
   * positive odd number, or the empty cell position is invalid.
   * @param armThickness the width marbles of the board
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the armThickness negative/even, or  empty cell is invalid.
   */
  public AbstractSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    this.armThickness = armThickness;
    this.rows = sRow;
    this.columns = sCol;
    this.validInBounds(rows, columns);
    this.board = this.createBoard();
  }

  protected abstract Boolean inBounds(int row, int col);

  protected abstract Boolean validInBounds(int row, int col);

  protected abstract SlotState[][] createBoard();

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.validInBounds(fromRow, fromCol);
    this.validInBounds(toRow, toCol);
    if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("You must move a marble.");
    }
    else if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("You must move a marble to an EMPTY space.");
    }
    if (this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2) != SlotState.Marble) {
      throw new IllegalArgumentException("A move must jump over a marble");
    }

    if ((fromCol + 2 == toCol && fromRow == toRow) || (fromCol - 2 == toCol && fromRow == toRow)
           || (fromRow + 2 == toRow && fromCol == toCol)
            || (fromRow - 2 == toRow && fromCol == toCol)) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
    else {
      throw new IllegalArgumentException("A move must be two spaces away cardinally");
    }
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize() - 2; i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j] == SlotState.Marble && this.board[i + 1][j] == SlotState.Marble
                && this.board[i + 2][j] == SlotState.Empty) {
          return false;
        }
      }
    }
    for (int g = 0; g < this.getBoardSize(); g++) {
      for (int h = 0; h < this.getBoardSize() - 2; h++) {
        if (this.board[g][h] == SlotState.Marble
                && this.board[g][h + 1] == SlotState.Marble
                && this.board[g][h + 2] == SlotState.Empty) {
          return false;
        }
      }
    }
    for (int a = 2; a < this.getBoardSize(); a++) {
      for (int b = 0; b < this.getBoardSize(); b++) {
        if (this.board[a][b] == SlotState.Marble && this.board[a - 1][b] == SlotState.Marble
                && this.board[a - 2][b] == SlotState.Empty) {
          return false;
        }
      }
    }
    for (int c = 0; c < this.getBoardSize(); c++) {
      for (int d = 2; d < this.getBoardSize(); d++) {
        if (this.board[c][d] == SlotState.Marble && this.board[c][d - 1] == SlotState.Marble
                && this.board[c][d - 2] == SlotState.Empty) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.board.length;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    this.inBounds(row, col);
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int count = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j] == SlotState.Marble) {
          count = count + 1;
        }
      }
    }
    return count;
  }
}
