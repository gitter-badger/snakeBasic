package snakev3;

import java.util.LinkedList;

public class Snake
{
  public static final int ROWSY = 20;
  public static final int COLSX = 30;
  private LinkedList<SnakeField> snakeList;
  private Direction movingDirection;
  
  public Snake()
  {
    snakeList = new LinkedList();
  }
  
  public void init(int x, int y, Direction direction)
  {
    snakeList.add(new SnakeField(x, y));
    snakeList.add(new SnakeField(x, y + 1));
    snakeList.add(new SnakeField(x, y + 2));
    snakeList.add(new SnakeField(x, y + 3));
    snakeList.add(new SnakeField(x, y + 4));
    snakeList.add(new SnakeField(x, y + 5));
    snakeList.add(new SnakeField(x, y + 6));
    

    movingDirection = direction;
  }
  
  public LinkedList<SnakeField> getSnakeList() {
    return snakeList;
  }
  
  public boolean iAteMe() {
    boolean iDid = false;
    SnakeField head = (SnakeField)snakeList.getFirst();
    snakeList.removeFirst();
    
    iDid = snakeList.contains(head);
    
    snakeList.addFirst(head);
    return iDid;
  }
  
  public void lengthen(SnakeField erasedField) {
    snakeList.add(erasedField);
  }
  
  public SnakeField moveForward() {
    SnakeField lastField = (SnakeField)snakeList.getLast();
    snakeList.removeLast();
    
    int xChange = 0;int yChange = 0;
    
    switch (movingDirection) {
    case DOWN: 
      xChange = 0;
      yChange = 1;
      break;
    case LEFT: 
      xChange = -1;
      yChange = 0;
      break;
    case RIGHT: 
      xChange = 1;
      yChange = 0;
      break;
    case UP: 
      xChange = 0;
      yChange = -1;
    }
       
    int newX = ((SnakeField)snakeList.getFirst()).getX() + xChange;
    int newY = ((SnakeField)snakeList.getFirst()).getY() + yChange;
    newX %= 30;
    newY %= 20;
    
    if (newX < 0) newX += 30;
    if (newY < 0) newY += 20;
    snakeList.addFirst(new SnakeField(newX, newY));
    
    return lastField;
  }
  
  public void turnLeft() {
      System.out.printf("pre "+ movingDirection+ "->");
    switch (movingDirection) {
    case DOWN: 
      movingDirection = Direction.RIGHT;
      break;
    case UP: 
      movingDirection = Direction.LEFT;
      break;
    case RIGHT: 
      movingDirection = Direction.UP;
      break;
    case LEFT: 
      movingDirection = Direction.DOWN;
    }
    
      System.out.println("post "+movingDirection);
  }
  
  public void turnRight()
  {
    switch (movingDirection) {
    case DOWN: 
      movingDirection = Direction.LEFT;
      break;
    case UP: 
      movingDirection = Direction.RIGHT;
      break;
    case RIGHT: 
      movingDirection = Direction.DOWN;
      break;
    case LEFT: 
      movingDirection = Direction.UP;
    }
  }
}
