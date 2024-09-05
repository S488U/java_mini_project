import java.applet.Applet;
import java.awt.*;

// <applet code="Home.class" width="300" height="300"></applet>

public class Home extends Applet {

    @Override
    public void init() {
        // Initialization code
        setBackground(Color.CYAN);
    }

    @Override
    public void paint(Graphics g) {
        // Draw the base of the house
        g.setColor(Color.RED);
        g.fillRect(50, 150, 150, 100); // x, y, width, height

        // Draw the door
        g.setColor(Color.WHITE);
        g.fillRect(90, 180, 30, 70); // x, y, width, height

        // Draw a window
        g.setColor(Color.YELLOW);
        g.fillRect(150, 180, 40, 40); // x, y, width, height

        // Draw the roof
        g.setColor(Color.BLUE);
        int[] xPoints = {50, 130, 200}; // x-coordinates of the roof
        int[] yPoints = {150, 100, 150}; // y-coordinates of the roof
        g.fillPolygon(xPoints, yPoints, 3); // Polygon with 3 points (a triangle)

        // Draw the chimney
        g.setColor(Color.DARK_GRAY);
        g.fillRect(160, 100, 20, 50); // x, y, width, height
        g.fillRect(153, 90, 35, 15);

        // Draw the sun
        g.setColor(Color.ORANGE);
        g.fillOval(240, 30, 50, 50); // x, y, width, height

        // Draw the grass
        g.setColor(Color.GREEN);
        g.fillRect(0, 250, 350, 50); // x, y, width, height
    }
}
// <applet code="Home.class" width="350" height="300"></applet>