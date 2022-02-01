package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the view of Triangle Solitaire.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState model;
  protected Appendable appendable;

  /**
   * Takes in a Model State and prints the view.
   * @param model the board
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Ashley, Model can't be null");
    }
    this.model = model;
    this.appendable = System.out;
  }

  /**
   * Takes a model State and the appendable output object.
   * @param model the board
   * @param appendable the output stream
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable) {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Ashley, Inputs can't be null");
    }
    this.model = model;
    this.appendable = appendable;
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("");
    int balls = this.model.getBoardSize();
    int intial_spaces = balls - 1;
    int balls_in_row = 1;
    int x = 0;
    int y = 0;

    for (int i = 0; i < balls; i++) {
      for (int j = 0; j < intial_spaces; j++) {
        result.append(" ");
      }
      for (int k = 0; k < balls_in_row ; k++) {

        switch (this.model.getSlotAt(x, y)) {
          case Invalid:
            break;
          case Marble:
            result.append("O");
            break;
          case Empty:
            result.append("_");
            break;
          default:
            throw new IllegalArgumentException("Something odd fucked up");
        }

        if (k != balls_in_row - 1) {
          result.append(" ");
        }
        y++;
      }
      if (i != balls - 1) {
        result.append("\n");
      }
      intial_spaces = intial_spaces - 1;
      balls_in_row = balls_in_row + 1;
      x++;
      y = 0;
    }

    String finished = result.toString();
    return finished;
  }


  @Override
  public void renderBoard() throws IOException {
    this.appendable.append(this.toString() + "\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }

}
