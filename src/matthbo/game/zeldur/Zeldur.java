package matthbo.game.zeldur;

import matthbo.game.engine.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Zeldur extends Game{

    private static ImageIcon icon = new ImageIcon(Zeldur.class.getResource("/assets/icons/icon.png"));

    public static void main(String[] args){
        Game game = new Zeldur();
        game.getFrame().setIconImage(icon.getImage());
        game.getFrame().setResizable(false);
        game.getFrame().setTitle("The Legend of Zeldur");
        game.getFrame().add(game);
        game.getFrame().pack();
        game.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getFrame().setLocationRelativeTo(null);
        game.getFrame().setVisible(true);

        game.start();
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        this.clearScreen();
        this.renderScreen();

        Graphics g = bs.getDrawGraphics();
        g.drawImage(this.getImage(), 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
}
