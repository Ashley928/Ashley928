package cs3500.marblesolitaire.controller;

/**
 * Represents the controller.
 */
public interface MarbleSolitaireController {

  /**
   * This method should play a new game of Marble Solitaire.
   * @throws IllegalStateException only if the controller is unable to successfully read
   *         input or transmit output.
   */
  void playGame() throws IllegalStateException;
}
