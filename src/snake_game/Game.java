package snake_game;

import javax.swing.JFrame;

/**
 * Ventana del juego Snake.
 * @author Felipe Buriticá
 */
public class Game extends JFrame {
    
    public static final int SCREEN_WIDTH = 400;
    public static final int SCREEN_HEIGHT = 400;
    public Board board;
    
    
    /** 
     * Establece las propiedades de la ventana y añade el objeto
     * Board que contiene el juego como tal.
     */
    public Game() {
        super("Snake Game");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        board = new Board();
        this.add(board);
    }
    
    
    /** 
     * Punto de ejecución de la aplicación.
     * @param args 
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}