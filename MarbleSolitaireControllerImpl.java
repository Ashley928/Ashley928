package cs3500.marblesolitaire.controller;

import java.io.IOException;

import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents the Controller for the Solitaire Marble version.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  protected MarbleSolitaireModel model;
  protected MarbleSolitaireView view;
  protected Readable in;

  /**
   * The Constructor for MarbleSolitaireControllerImpl.
   *
   * @param model    The model for the board.
   * @param view     The view of the game.
   * @param readable The input from the user.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable readable) {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Ashley, none of the inputs can be null");
    }
    this.model = model;
    this.view = view;
    this.in = readable;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.in);
    //initialized to unique int;
    int fromRow = -2;
    int fromCol = -2;
    int toRow = -2;
    int toCol = -2;
    boolean readytoRender = true;

    this.renderCurrentState();
    this.displayScore();

    while (scan.hasNext()) {
      if (this.model.isGameOver()) {
        this.gameOver();
        return;
      }


      String input = scan.next();

      if (input.equals("q") || input.equals("Q")) {
        this.quitting();
        scan.close();
        return;
      }

      while (!this.isValidInput(input)) {
        try {
          this.view.renderMessage("The " + input
                  + " value is invalid, please re-enter that value again\n");
        } catch (IOException e) {
          throw new IllegalStateException("Ashley, Invalid input won't render");
        }
        if (scan.hasNext()) {
          input = scan.next();
        }
        else {
          input = "-1";
          break;
        }
      }

      int num = Integer.parseInt(input);

      if (fromRow == -2) {
        fromRow = num - 1;
        readytoRender = false;
      } else if (fromCol == -2) {
        fromCol = num - 1;
      } else if (toRow == -2) {
        toRow = num - 1;
      } else if (toCol == -2) {
        toCol = num - 1;
        this.move(fromRow, fromCol, toRow, toCol);
        fromRow = -2;
        fromCol = -2;
        toRow = -2;
        toCol = -2;
        readytoRender = true;
      } else if (num == -1) {
        throw new IllegalStateException("Ran out of inputs");
      }
      if (this.model.isGameOver()) {
        this.gameOver();
        scan.close();
        return;
      }

      if (readytoRender) {
        this.renderCurrentState();
        this.displayScore();
      }

    }
    //5. end the game
    if (this.model.isGameOver()) {
      this.gameOver();
      scan.close();
      return;
    }

    throw new IllegalStateException("There are no more inputs and game is not over"
    + this.view.toString());

  }


  private void renderCurrentState() throws IllegalStateException {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Ashley, the Current State won't render");
    }
  }

  private void displayScore() throws IllegalStateException {
    try {
      int score = this.model.getScore();
      this.view.renderMessage("Score: " + score + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Ashley, the Current State won't render");
    }
  }

  private void gameOver() {
    if (this.model.getScore() == 1) {
      try {
        this.view.renderMessage("You Won!!!\n");
        this.renderCurrentState();
        this.displayScore();
      } catch (IOException e) {
        throw new IllegalStateException("Ashley, the game over won't render");
      }
    }
    else {
      try {
        this.view.renderMessage("Game over!\n");
        this.renderCurrentState();
        this.displayScore();
      } catch (IOException e) {
        throw new IllegalStateException("Ashley, the game over won't render");
      }
    }
  }

  private boolean isValidInput(String s) {
    try {
      int i = Integer.parseInt(s);
      if (i > 0) {
        return true;
      }
    } catch (NumberFormatException nfe) {
      return false;
    }
    return false;
  }

  private void quitting() {
    try {
      this.view.renderMessage("Game quit!\n");
      this.view.renderMessage("State of game when quit:\n");
      this.renderCurrentState();
      int score = this.model.getScore();
      this.view.renderMessage("Score: " + score);
    } catch (IOException e) {
      throw new IllegalStateException("Ashley, the quitting won't render");
    }
  }

  private void move(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      this.model.move(fromRow, fromCol, toRow, toCol);
    } catch (IllegalArgumentException e) {
      this.moveHelper(fromRow, fromCol, toRow, toCol);
    }
  }

  private void moveHelper(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      this.view.renderMessage("Invalid move. Play again. " + fromRow + fromCol + toRow + toCol
              + " use better inputs\n");
    }
    catch (IOException e) {
      throw new IllegalStateException("Ashley, the move won't render");
    }
  }

}



