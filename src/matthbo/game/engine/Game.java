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
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + "ups, " + frames + "fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public JFrame getFrame(){
        return frame;
    }
}
