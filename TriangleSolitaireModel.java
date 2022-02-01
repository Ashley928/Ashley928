package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the model of the Triangle version of Marble Solitaire.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {
  private final int base;
  private final int rows;
  private final int columns;
  private MarbleSolitaireModelState.SlotState[][] board;

  /**
   * The first constructor should take no parameters, and initialize the
   * game board as shown above.
   */
  public TriangleSolitaireModel() {
    this.base = 5;
    this.rows = 0;
    this.columns = 0;
    this.board = this.createBoard();
  }

  /**
   * Second Constructor takes in how many rows and balls are in the last row.
   * @param base The amount of marbles in the last row.
   * @throws IllegalArgumentException If not positive
   */
  public TriangleSolitaireModel(int base) throws IllegalArgumentException {
    if (base <= 0) {
      throw new IllegalArgumentException("Ashley, The base should be positive");
    }
    this.base = base;
    this.rows = 0;
    this.columns = 0;
    this.board = this.createBoard();
  }

  /**
   * The third constructor allows the user to pick the empty space.
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.base = 5;
    this.rows = sRow;
    this.columns = sCol;
    if (!this.validInBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Ashley, this slot does not exist");
    }
    this.board = this.createBoard();
  }

  /**
   * Fourth constructor does both what the second and third constructor do.
   * @param height The amount of marbles in the last row.
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell is invalid
   */
  public TriangleSolitaireModel(int height, int sRow, int sCol)
          throws IllegalArgumentException {
    if (height <= 0) {
      throw new IllegalArgumentException("Ashley, The base should be positive");
    }
    this.base = height;
    this.rows = sRow;
    this.columns = sCol;
    if (!this.validInBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Ashley, this slot does not exist");
    }
    this.board = this.createBoard();

  }

  private Boolean inBounds(int row, int col) {
    if (col < 0 || col > this.base) {
      throw new IllegalArgumentException("The columns must be within the board's dimensions");
    } else if (row < 0 || row > this.base) {
      throw new IllegalArgumentException("The rows must be within the board's dimensions");
    }
    return true;
  }

  private Boolean validInBounds(int row, int col) {
    if (row < 0 || row >= this.base) {
      return false;
    }
    else if (col < 0 || col > row) {
      return false;
    }
    return true;
  }

  private SlotState[][] createBoard() {
    SlotState[][] empty_board = new SlotState[this.base + 1][this.base + 1];

    for (int i = 0; i < this.base + 1; i++) {
      for (int j = 0; j < this.base + 1; j++) {
        empty_board[i][j] = SlotState.Invalid;
      }
    }

    for (int i = 0; i < this.base; i++) {
      for (int j = 0; j < i + 1; j++) {
        empty_board[i][j] = SlotState.Marble;
      }
    }

    empty_board[this.rows][this.columns] = SlotState.Empty;

    return empty_board;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!this.inBounds(fromRow, fromCol)) {
      throw new IllegalArgumentException("The from coordianted is invalid" + fromRow + fromCol);
    }
    if (!this.inBounds(toRow, toCol)) {
      throw new IllegalArgumentException("The from coordianted is invalid" + toRow + toCol);
    }

    if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("You must move a marble.");
    }
    else if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("You must move a marble to an EMPTY space.");
    }

    //horizontal
    else if (fromCol + 2 == toCol && fromRow == toRow) {
      if (this.getSlotAt(fromRow, fromCol + 1) != SlotState.Marble) {
        throw new IllegalArgumentException("A move must jump over a marble");
      }
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow][fromCol + 1] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
    else if (fromCol - 2 == toCol && fromRow == toRow) {
      if (this.getSlotAt(fromRow, fromCol - 1) != SlotState.Marble) {
        throw new IllegalArgumentException("A move must jump over a marble");
      }
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow][fromCol - 1] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }

    //left slant
    else if (fromRow + 2 == toRow && fromCol == toCol) {
      if (this.getSlotAt(fromRow + 1, fromCol) != SlotState.Marble) {
        throw new IllegalArgumentException("A move must jump over a marble");
      }
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow + 1][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
    else if (fromRow - 2 == toRow && fromCol == toCol) {
      if (this.getSlotAt(fromRow - 1, fromCol) != SlotState.Marble) {
        throw new IllegalArgumentException("A move must jump over a marble");
      }
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow - 1][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }

    //NEW TO THE OTHER METHODS RIGHT SLANT
    else if (fromRow + 2 == toRow && fromCol + 2 == toCol) {
      if (this.getSlotAt(fromRow + 1, fromCol + 1) != SlotState.Marble) {
        throw new IllegalArgumentException("A move must jump over a marble");
      }
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow + 1][fromCol + 1] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
    else if (fromRow - 2 == toRow && fromCol - 2 == toCol) {
      if (this.getSlotAt(fromRow - 1, fromCol - 1) != SlotState.Marble) {
        throw new IllegalArgumentException("A move must jump over a marble");
      }
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow - 1][fromCol - 1] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }

    //not new
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
    for (int e = 0; e < this.getBoardSize() - 2; e++) {
      for (int f = 0; f < this.getBoardSize() - 2; f++) {
        if (this.board[e][f] == SlotState.Marble && this.board[e + 1][f + 1] == SlotState.Marble
                && this.board[e + 2][f + 2] == SlotState.Empty) {
          return false;
        }
      }
    }
    for (int e = 2; e < this.getBoardSize(); e++) {
      for (int f = 2; f < this.getBoardSize(); f++) {
        if (this.board[e][f] == SlotState.Marble && this.board[e - 1][f - 1] == SlotState.Marble
                && this.board[e - 2][f - 2] == SlotState.Empty) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public int getBoardSize() {
    return this.base;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    this.inBounds(row, col);
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int count = 0;
    for (int i = 0; i < this.base; i++) {
      for (int j = 0; j < i + 1; j++) {
        if (this.board[i][j] == SlotState.Marble) {
          count = count + 1;
        }
      }
    }
    return count;
  }

}
