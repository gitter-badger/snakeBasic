package snakev3;

public class SnakeField {
  private int x;
  private int y;
  
  public SnakeField(int x, int y) { this.x = x;this.y = y;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public void addToX(int diff) {
    x += diff;
  }
  
  public void addToY(int diff) {
    y += diff;
  }
  
  public boolean equals(Object other)
  {
    SnakeField otherField = (SnakeField)other;
    return (x == otherField.getX()) && (y == otherField.getY());
  }
  
  public int hashCode() {
    return x * y * (x + 1);
  }
}
