package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the view of Marble Solitaire.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState model;
  protected Appendable appendable;

  /**
   * Takes in a Model State and prints the view.
   * @param model the board
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Ashley, Model can't be null");
    }
    this.model = model;
    this.appendable = System.out;
  }

  //use stringBuilder for tests

  /**
   * Takes a model State and the appendable output object.
   * @param model the board
   * @param appendable the output stream
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable) {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Ashley, Inputs can't be null");
    }
    this.model = model;
    this.appendable = appendable;
  }

  @Override
  public String toString() {
    StringBuilder print = new StringBuilder("");
    int length = this.model.getBoardSize();

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        switch (this.model.getSlotAt(i, j)) {
          case Invalid:
            if (j < length / 2) {
              print.append("  ");
            }
            break;
          case Marble:
            print.append("O ");
            break;
          case Empty:
            print.append("_ ");
            break;
          default:
            throw new IllegalArgumentException("Something odd fucked up");
        }
      }
      int index = print.lastIndexOf(" ");
      print.replace(index, index + 1, "");
      if (i != length - 1) {
        print.append("\n");
      }
    }

    String result = print.toString();
    return result;
  }


  //use a fake appendable to test

  @Override
  public void renderBoard() throws IOException {
    this.appendable.append(this.toString() + "\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }

}



