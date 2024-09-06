import java.applet.Applet;
import java.awt.*;

// <applet code="Home.class" width="350" height="300"></applet>

public class Home extends Applet {

    @Override
    public void init() {
        setBackground(Color.CYAN);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(50, 150, 150, 100); 

        g.setColor(Color.WHITE);
        g.fillRect(90, 180, 30, 70); 

        g.setColor(Color.YELLOW);
        g.fillRect(150, 180, 40, 40); 

        g.setColor(Color.BLUE);
        int[] xPoints = {50, 130, 200};
        int[] yPoints = {150, 100, 150}; 
        g.fillPolygon(xPoints, yPoints, 3); 

        g.setColor(Color.DARK_GRAY);
        g.fillRect(160, 100, 20, 50); 
        g.fillRect(153, 90, 35, 15);

        g.setColor(Color.ORANGE);
        g.fillOval(240, 30, 50, 50); 

        g.setColor(Color.GREEN);
        g.fillRect(0, 250, 350, 50); 
    }
}