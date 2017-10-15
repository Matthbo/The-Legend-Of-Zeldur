package matthbo.game.engine;

import javax.swing.*;
import java.awt.*;

public abstract class Game extends Canvas implements Runnable, GameInterface {

    private static int defaultWidth = 1280;
    private static int defaultHeight = 720;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    public Game(int width, int height) {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);

        frame = new JFrame();
    }

    public Game() {
        this(defaultWidth, defaultHeight);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(running){
            update();
            render();
        }
    }

    public JFrame getFrame(){
        return frame;
    }
}
