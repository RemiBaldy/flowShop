package flowShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Frame {
    JFrame frame;

    public Frame(JFrame frame) {
        this.frame = frame;
        setFullScreen();
        //listen();
    }

    public void setFullScreen(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public void listen(){
        frame.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                System.out.println("x : "+x+" y: "+y);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


    public void drawRectangles(ArrayList<Rectangle> rectangles){
        frame.add(new Drawer(rectangles));
    }


}
