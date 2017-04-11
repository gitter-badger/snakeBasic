package snakev3;

import java.awt.Rectangle;

public class Tile extends Rectangle {
  public int x;
  public int y;
  
  public Tile(int x, int y, int width, int height, Field field) {
    super(x, y, width, height);
    this.field = field; }
  
  public int width;
  
  public void setField(Field field) { this.field = field; }
  
  public int height;
  private Field field;
  public Field getField() { return field; }
}
