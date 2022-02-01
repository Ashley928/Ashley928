package cs3500.marblesolitaire.model.hw04;


/**
 * Represents the model of the European version of Marble Solitaire.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  private SlotState[][] board;

  /**
   * The first constructor should take no parameters, and initialize the
   * game board as shown above (arm thickness 3 with the empty slot at the center).
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * The second constructor should take the arm thickness as its only parameter and initialize
   * a game board with the empty slot at the center.
   *
   * @param armThickness the width marbles of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness);

    if (!this.validInBounds(rows, columns)) {
      throw new IllegalArgumentException("Ashley, That is not a valid position");
    }
  }


  /**
   * A second constructor should take two parameters: sRow and sCol. It should initialize the
   * game board so that the arm thickness is 3 and the empty slot is at the position (sRow, sCol).
   * If this specified position is invalid, it should throw an IllegalArgumentException
   * with a message "Invalid empty cell position (r,c)" with 𝑟 and 𝑐 replaced with
   * the row and column passed to it.
   *
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell is invalid
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);
    if (!this.validInBounds(rows, columns)) {
      throw new IllegalArgumentException("Ashley, That is not a valid position");
    }
  }

  /**
   * Finally a fourth constructor should take the arm thickness, row and column of the empty slot
   * in that order. It should throw an IllegalArgumentException if the arm thickness is not a
   * positive odd number, or the empty cell position is invalid.
   *
   * @param armThickness the width marbles of the board
   * @param sRow       the row of the empty space
   * @param sCol       the column of the empty space
   * @throws IllegalArgumentException if the armThickness negative/even, or  empty cell is invalid.
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
    if (!this.validInBounds(rows, columns)) {
      throw new IllegalArgumentException("Ashley, That is not a valid position");
    }
  }

  //Returns true if the given row and column is in the dimensions of the grid
  //and if this.armThickness is a positive odd number
  @Override
  protected Boolean inBounds(int row, int col) {
    if (col < 0 || col > ((this.armThickness - 1) * 3)) {
      throw new IllegalArgumentException("The columns must be within the board's dimensions");
    } else if (row < 0 || row > ((this.armThickness - 1) * 3)) {
      throw new IllegalArgumentException("The rows must be within the board's dimensions");
    } else if (this.armThickness % 2 == 0 || this.armThickness < 0) {
      throw new IllegalArgumentException("The arm thickness must be a positive odd number");
    }
    return true;
  }

  //Returns true if in_bounds is true and if the given row and column is not an Invalid slot
  @Override
  protected Boolean validInBounds(int row, int col) {
    this.inBounds(row, col);
    if (row > (this.armThickness - 2) && row < (this.armThickness * 2) - 1) {
      return true;
    } else if (row < (this.armThickness - 1)) {
      if (col > ((this.armThickness - 2) - row) && col < (((this.armThickness * 2) - 1) + row)) {
        return true;
      }
      return false;
    } else if (row > (this.armThickness * 2) - 2) {
      row = row - ((this.armThickness * 2) - 1);
      if (col > 0 + row && col < ((this.armThickness - 1) * 2) + this.armThickness - row - 1) {
        return true;
      }
      return false;
    }
    return false;
  }

  //Creates an array of arrays that hold a SlotStates; represents the starting state of the board.
  @Override
  protected SlotState[][] createBoard() {
    int dimension = ((this.armThickness - 1) * 3) + 1;
    SlotState[][] empty_board = new SlotState[dimension][dimension];

    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        empty_board[i][j] = SlotState.Invalid;
      }
    }

    for (int a = 0; a < dimension; a++) {
      for (int b = 0; b < dimension; b++) {
        if (this.validInBounds(a, b)) {
          empty_board[a][b] = SlotState.Marble;
        }
      }
    }

    empty_board[this.rows][this.columns] = SlotState.Empty;

    if (this.armThickness == 1) {
      empty_board = new SlotState[1][1];
      empty_board[0][0] = SlotState.Empty;
    }

    return empty_board;
  }

}
