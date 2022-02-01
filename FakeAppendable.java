package cs3500.marblesolitaire.view;

import java.io.IOException;

/**
 * A fake appendable class that allows throws an IOException.
 */
public class FakeAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Exception");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Exception");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Exception");
  }
}
