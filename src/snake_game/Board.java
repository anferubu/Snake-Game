package snake_game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Tablero del juego Snake.
 * @author Felipe Buriticá
 */
public class Board extends JPanel implements ActionListener, KeyListener {
    
    public static final int panelWidth = Game.SCREEN_WIDTH;
    public static final int panelHeigth = Game.SCREEN_HEIGHT - 30;
    public static final int xMargin = 20;
    public static final int yMargin = 20;
    
    public int score = 0;
    public Food food;
    public Snake snake;
    public Timer timer;
    public boolean gameOver = false;
    public ArrayList<Snake> growingSnake;
    
    
    public Board() {
        super();
        this.setBackground(new Color(154, 197, 2));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.initComponents();
    }
    
    
    /**
     * Inicializa los componentes del juego: la serpiente, el alimento de la serpiente y
     * el temporizador.
     */
    public void initComponents() {
        snake = new Snake(30, 30);
        food = new Food(panelWidth, panelHeigth);
        growingSnake = new ArrayList<>();
        growingSnake.add(snake);
        timer = new Timer(50, this);
        timer.start();
    }
    
    
    /**
     * Verifica si la serpiente come el alimento.
     */
    public void checkCollision() {
        if (snake.getRect().intersects(food.getRect())) {
            food.randomPosition(panelWidth, panelHeigth);
            growingSnake.add(0, new Snake(snake.x, snake.y));
            score += 2;
        }
        for (int i=1; i<growingSnake.size(); i++) {
            Snake snakeCell = growingSnake.get(i);
            if (snake.x == snakeCell.x && snake.y == snakeCell.y) {
                gameOver = true;
            }
        }
    }
    
    
    /**
     * Dibuja los componentes en el panel.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(45, 45, 45));
        
        if (!gameOver) {
            g2.setStroke(new BasicStroke(3));
            g2.drawRect(xMargin, yMargin, panelWidth - (xMargin * 2), panelHeigth - (yMargin * 2));
            
            g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
            g2.drawString("score: " + score, 20, 365);

            food.paint(g);
            
            for (Snake snakePart : growingSnake) {
                snakePart.paint(g);
            }
        }
        else {
            String message = "GAME OVER";
            Font font = new Font(Font.MONOSPACED, Font.BOLD, 17);
            FontMetrics fontMetrics = getFontMetrics(font);
            g2.setFont(font);
            
            g2.drawString(message, (panelWidth - fontMetrics.stringWidth(message)) / 2,
                                   (panelHeigth / 2));
        }
    }
      
    
    /**
     * Establece el valor del atributo snake.direction por medio del teclado.
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        snake.keyPressed(e);
    }

    
    /**
     * Acciones que se ejecutan cada cierto tiempo definido por el temporizador.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            if (snake.direction == KeyEvent.VK_UP) {
                snake.y -= snake.size;
                if (snake.y > panelHeigth - snake.size - yMargin)
                    snake.y = yMargin;
                if (snake.y < yMargin)
                    snake.y = panelHeigth - snake.size - yMargin;   
            }
            if (snake.direction == KeyEvent.VK_DOWN) {
                snake.y += snake.size;
                if (snake.y > panelHeigth - snake.size - yMargin)
                    snake.y = yMargin;
                if (snake.y < yMargin)
                    snake.y = panelHeigth - snake.size - yMargin;   
            }
            if (snake.direction == KeyEvent.VK_RIGHT) {
                snake.x += snake.size;
                if (snake.x > panelWidth - snake.size - xMargin)
                    snake.x = xMargin;
                if (snake.x < xMargin)
                    snake.x = panelWidth - snake.size - xMargin;   
            }
            if (snake.direction == KeyEvent.VK_LEFT) {
                snake.x -= snake.size;
                if (snake.x > panelWidth - snake.size - xMargin)
                    snake.x = xMargin;
                if (snake.x < xMargin)
                    snake.x = panelWidth - snake.size - xMargin;   
            }
            this.checkCollision();
            
            // Esto es lo que simula el movimiento de la serpiente, al añadir una celda
            // al inicio y quitar una al final.
            growingSnake.add(0, new Snake(snake.x, snake.y));
            growingSnake.remove(growingSnake.size() - 1);
        }
        this.repaint();
    }

    
    // Métodos sobrescritos no utilizados.
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}