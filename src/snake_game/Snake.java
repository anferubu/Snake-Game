package snake_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Juego de la culebrita.
 * @author Felipe Buriticá
 */
public class Snake extends JFrame implements KeyListener {
    
    private final int width = 400;
    private final int height = 400;
    private Point snake;
    private int direction = KeyEvent.VK_LEFT;
    public long frecuencia = 50;
    public Point comida;
    boolean gameOver = false;
    
    ArrayList<Point> lista = new ArrayList<Point>();
    
    
    public Snake() {
        super("Snake Game");
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        
       this.startGame();
        
        Momento momento = new Momento();
        Thread thread = new Thread(momento);
        thread.start();
    }
    
    
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.RED);
        
        for (int i=0; i<lista.size(); i++) {
            Point p = (Point) lista.get(i);
            g.fillRect(p.x, p.y, 10, 10);
        }
        
        g.setColor(Color.BLUE);
        g.fillRect(comida.x, comida.y, 10, 10);
        
        
        if (gameOver) {
            g.drawString("GAME OVER", 200, 200);
        }        
        
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) {
            direction = KeyEvent.VK_UP;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) {
            direction = KeyEvent.VK_DOWN;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT) {
            direction = KeyEvent.VK_RIGHT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) {
            direction = KeyEvent.VK_LEFT;
        }
        System.out.println(direction);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    public static void main(String[] args) {
        Snake game = new Snake();
        game.setVisible(true);
    }
    
    
    
    public void reload() {
        this.repaint();
        
        lista.add(0, new Point(snake.x, snake.y));
        lista.remove(lista.size() - 1);
        
        
        for (int i=1; i<lista.size(); i++) {
            Point p = lista.get(i);
            if (snake.x == p.x && snake.y == p.y) {
                gameOver = true;
            }
        }
        
        
        if ((snake.x > (comida.x - 10)) && (snake.x < (comida.x + 10)) && (snake.y > (comida.y - 10)) && (snake.y > (comida.y - 10))) {
            lista.add(0, new Point(snake.x, snake.y));
            crearComida();
        }
    }
    
    
    public void startGame() {
        comida = new Point(200, 200);
        snake = new Point(width/2, height/2);
        crearComida();
        lista = new ArrayList<>();
        //lista.add(snake);
    }
    
    public void crearComida() {
        Random random = new Random();
        comida.x = random.nextInt(width);
        
        if ((comida.x % 5) > 0) {
            comida.x = comida.x - (comida.x % 5);
        }
        
        if (comida.x < 5) {
            comida.x = comida.x + 5;
        }
        
        
        comida.y = random.nextInt(height);
        
        if ((comida.y % 5) > 0) {
            comida.y = comida.y - (comida.y % 5);
        }
        
        if (comida.y < 5) {
            comida.y = comida.y + 5;
        }
    }
    
    
    

    public class Momento extends Thread {
        long last = 0;
        
        @Override
        public void run() {
            while (true) {
                if ((java.lang.System.currentTimeMillis() - last) > frecuencia) {
                    
                    if (!gameOver) {
                        if (direction == KeyEvent.VK_UP) {
                            snake.y = snake.y - 10;
                            if (snake.y > height) {
                                snake.y = 0;
                            }
                            if (snake.y < 0) {
                                snake.y = height - 10;
                            }
                        }

                        else if (direction == KeyEvent.VK_DOWN) {
                            snake.y = snake.y + 10;
                            if (snake.y > height) {
                                snake.y = 0;
                            }
                            if (snake.y < 0) {
                                snake.y = height - 10;
                            }
                        }

                        else if (direction == KeyEvent.VK_LEFT) {
                            snake.x = snake.x - 10;
                            if (snake.x > width) {
                                snake.x = 0;
                            }
                            if (snake.x < 0) {
                                snake.x = width - 10;
                            }
                        }

                        else if (direction == KeyEvent.VK_RIGHT) {
                            snake.x = snake.x + 10;
                            if (snake.x > width) {
                                snake.x = 0;
                            }
                            if (snake.x < 0) {
                                snake.x = width - 10;
                            }
                        }
                    }
                    
                    reload();
                    last = java.lang.System.currentTimeMillis();
                }
            }
        }
    }
}