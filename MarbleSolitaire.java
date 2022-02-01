package cs3500.marblesolitaire.model.hw04;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * The class the runs the program.
 */
public final class MarbleSolitaire {
  /**
   * Runs the program.
   *
   * @param args I don't know
   */
  public static void main(String[] args) {
    int size;
    int thirdArgument;
    String second;
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    try {
      second = args[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      second = "nothing";
    }

    switch (second) {
      case "nothing":
        switch (args[0]) {
          case "english":
            model = new EnglishSolitaireModel();
            view = new MarbleSolitaireTextView(model);
            break;
          case "european":
            model = new EuropeanSolitaireModel();
            view = new MarbleSolitaireTextView(model);
            break;
          case "triangular":
            model = new TriangleSolitaireModel();
            view = new TriangleSolitaireTextView(model);
            break;
          default:
            System.out.println("This did not work");
        }
        break;
      case "-size":
        switch (args[0]) {
          case "english":
            try {
              model = new EnglishSolitaireModel(Integer.parseInt(args[2]));
              view = new MarbleSolitaireTextView(model);
            } catch (NumberFormatException nfe) {
              System.out.println("Was not given a integer");
            } catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("A integer was not given");
            }
            break;
          case "european":
            try {
              model = new EuropeanSolitaireModel(Integer.parseInt(args[2]));
              view = new MarbleSolitaireTextView(model);
            } catch (NumberFormatException nfe) {
              System.out.println("Was not given a integer");
            } catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("A integer was not given");
            }
            break;
          case "triangular":
            try {
              model = new TriangleSolitaireModel(Integer.parseInt(args[2]));
              view = new TriangleSolitaireTextView(model);
            } catch (NumberFormatException nfe) {
              System.out.println("Was not given a integer");
            } catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("A integer was not given");
            }
            break;
          default:
            System.out.println("This did not work");
        }
        break;
      case "-hole":
        switch (args[0]) {
          case "english":
            try {
              model = new EnglishSolitaireModel(Integer.parseInt(args[2]) - 1,
                      Integer.parseInt(args[3]) - 1);
              view = new MarbleSolitaireTextView(model);
              break;
            } catch (NumberFormatException nfe) {
              System.out.println("Was not given a integer");
            } catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("An integer was not given");
            }
            break;
          case "european":
            try {
              model = new EuropeanSolitaireModel(Integer.parseInt(args[2]) - 1,
                      Integer.parseInt(args[3]) - 1);
              view = new MarbleSolitaireTextView(model);
              break;
            } catch (NumberFormatException nfe) {
              System.out.println("Was not given a integer");
            } catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("An integer was not given");
            }
            break;
          case "triangular":
            try {
              model = new TriangleSolitaireModel(Integer.parseInt(args[2]) - 1,
                      Integer.parseInt(args[3]) - 1);
              view = new TriangleSolitaireTextView(model);
              break;
            } catch (NumberFormatException nfe) {
              System.out.println("Was not given a integer");
            } catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("An integer was not given");
            }
            break;
          default:
            System.out.println("This did not work");
        }
        break;
      default:
        System.out.println("This did not work");
    }

    MarbleSolitaireControllerImpl impl =
            new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
    impl.playGame();
  }


}




