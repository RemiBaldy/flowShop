package flowShop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawer extends JPanel {
    ArrayList<Rectangle> rectangleList;

    public Drawer(ArrayList<Rectangle> rectangleList) {
        this.rectangleList = rectangleList;
    }

    public void rectangle(Graphics g){
        for (Rectangle rectangle : rectangleList){
            //System.out.println(rectangle.toString());
            g.setColor(rectangle.color);
            g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
    public void paintComponent(Graphics g) {
        rectangle(g);
    }
}
