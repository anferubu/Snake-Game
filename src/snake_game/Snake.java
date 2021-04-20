package snake_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * Representa la serpiente del juego.
 * @author Felipe Buriticá
 */
public class Snake {
    
    public int direction = KeyEvent.VK_RIGHT;
    public final int size = 12;
    public int x;
    public int y;
    
    
    /** 
     * Inicializa la serpiente en la posición dada por los parámetros.
     * @param x
     * @param y
     */
    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    /**
     * Permite mover la serpiente por medio del teclado.
     * @param e 
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && this.direction != KeyEvent.VK_DOWN) {
            this.direction = KeyEvent.VK_UP;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && this.direction != KeyEvent.VK_UP) {
            this.direction = KeyEvent.VK_DOWN;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.direction != KeyEvent.VK_LEFT) {
            this.direction = KeyEvent.VK_RIGHT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && this.direction != KeyEvent.VK_RIGHT) {
            this.direction = KeyEvent.VK_LEFT;
        }
    }
    
    
    /**
     * Retorna un objeto Rectangle, utilizado para verificar colisiones.
     * @return 
     */
    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(this.x, this.y, this.size, this.size);
        return rectangle;
    }
    
    
    /**
     * Dibuja el objeto Snake en su posición actual.
     * @param g 
     */
    public void paint(Graphics g) {
        g.setColor(new Color(45, 45, 45));
        g.fillRect(this.x, this.y, this.size, this.size);
        
        g.setColor(new Color(154, 197, 2));
        g.fillRect(this.x + (this.size/4), this.y + (this.size/4), this.size/2, this.size/2);
    }
}