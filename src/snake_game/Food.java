package snake_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Representa el objeto que se come la serpiente para crecer de tamaño.
 * @author Felipe Buriticá
 */
public class Food {
    
    public final int size = 12;
    public int x;
    public int y;
    
    
    /**
     * Método constructor: inicializa el objeto en una posición aleatoria.
     * @param areaWidth
     * @param areaHeight 
     */
    public Food(int areaWidth, int areaHeight) {
        this.randomPosition(areaWidth, areaHeight);
    }
    
    
    /**
     * Establece la posición del objeto de manera aleatoria dentro de un área
     * definida por los parámetros 'areaWidth' y 'areaHeight'
     * @param areaWidth
     * @param areaHeight 
     */
    public void randomPosition(int areaWidth, int areaHeight) {
        int xMargin = 25;
        int yMargin = 25;
        
        x = (int) (xMargin + (Math.random() * (areaWidth - this.size - (xMargin * 2))));
        y = (int) (yMargin + (Math.random() * (areaHeight - this.size - (yMargin * 2))));
    }
    
    
    /**
     * Retorna un objeto Rectangle, utilizado para verificar colisiones.
     * @return 
     */
    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(this.x, this.y, size, size);
        return rectangle;
    }
    
    
    /**
     * Dibuja el objeto Food en su posición actual.
     * @param g 
     */
    public void paint(Graphics g) {
        int subSize = this.size / 3;
        
        g.setColor(new Color(45, 45, 45));
        
        g.fillRect(this.x + (subSize * 0), this.y + (subSize * 1), subSize, subSize);
        g.fillRect(this.x + (subSize * 1), this.y + (subSize * 0), subSize, subSize);
        g.fillRect(this.x + (subSize * 1), this.y + (subSize * 2), subSize, subSize);
        g.fillRect(this.x + (subSize * 2), this.y + (subSize * 1), subSize, subSize);
    }
}