package snakev3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyPanel
  extends JPanel
{
  private static final int UPDATE_RATE = 30;
  private int startAppleX = 3;
  private int startAppleY = 7;
  private Board board;
  private GameState gameState;
  private Snake snake;
  
  public MyPanel(JFrame frame)
  {
    JPanel jPanel = new JPanel();
    jPanel.setLayout(new FlowLayout());
    
    add(jPanel);
   
    setBorder(BorderFactory.createLineBorder(Color.black));
    
    board = new Board(startAppleX, startAppleY);
    board.init();
    snake = new Snake();
    
    initGame();
    
    frame.setFocusable(true);
    frame.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("left klik");
            snake.turnLeft();
            board.eraseSnake();
            board.pasteSnake(snake);
            repaint();
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("right klik");
            snake.turnRight();
            board.eraseSnake();
            board.pasteSnake(snake);
        }   
      }
    });
    
    Thread gameThread = new Thread() {
      public void run() {
        while (gameState == GameState.PLAYING)
        {
          SnakeField erasedField = snake.moveForward();
          
          if (snake.iAteMe())
          {
            gameState = GameState.LOST;
          }
          
          if (board.justAteApple(snake))
          {
            board.setAppleAtRandom();
            

            snake.lengthen(erasedField);
          }
          
          board.eraseSnake();
          board.pasteSnake(snake);
          
          repaint();
          try
          {
            //Thread.sleep(333L);

            Thread.sleep(150L);
          } catch (InterruptedException localInterruptedException) {}
        }
      }
    };
    gameThread.start();

  }
  
  public void initGame()
  {
    gameState = GameState.PLAYING;
    
    snake.init(10, 8, Direction.UP);
    board.clear();
    board.putApple(startAppleX, startAppleY);
    board.pasteSnake(snake);
  }
  
  public Dimension getPreferredSize() {
    return new Dimension(350, 300);
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    board.draw(g);
  }
}
