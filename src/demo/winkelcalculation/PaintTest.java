
package demo.winkelcalculation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintTest extends JFrame {
    int sizeX = 600;
    int sizeY = 600;

    public static void main(final String[] args) {
        new PaintTest();
    }

    public PaintTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(sizeX, sizeY);
        setLocation(600, 400);
        add(new DrawPanel());
        setVisible(true);
    }

    class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private int mouseX = 0;
        private int mouseY = 0;

        public DrawPanel() {
            setBackground(Color.WHITE);
            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseMoved(final MouseEvent e) {
                    mouseX = e.getX() - 4;
                    mouseY = e.getY() - 14;
                    repaint();
                }

                @Override
                public void mouseDragged(final MouseEvent e) {
                }
            });
        }

        @Override
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);

            /* Mauskoordinaten */
            final int mouX = mouseX;
            final int mouY = mouseY;

            /* Mittelpunkt-Koordinaten */
            final int midX = sizeX / 2;
            final int midY = sizeY / 2;

            /* Mittelpunkt zeichnen */
            g.fillOval(midX - 5, midY - 5, 10, 10);

            /* Punkt an Cursor zeichnen: */
            g.fillOval(mouX - 5, mouY - 5, 10, 10);

            /* Verbindungslinie zeichnen: */
            g.drawLine(midX, midX, mouX, mouY);

            /* Winkel berechnen: */
            final long angle = calculateAngle(midX, midY, mouX, mouY);

            /* Winkel als Text darstellen: */
            g.drawString(Long.toString(angle), mouX + 12, mouY);
        }

        /**
         * Winkelberechnung
         *
         * (bx,by) * /| / | dz / | dy / | /g | (ax,ay) *-----* dx
         *
         * @param ax
         *            x-Koordinate des Mittelpunktes
         * @param ay
         *            y-Koordinate des Mittelpunktes
         * @param bx
         *            x-Koordinate des Zielpunktes
         * @param by
         *            y-Koordinate des Zielpunktes
         *
         * @return Winkel
         */
        private long calculateAngle(final int ax, final int ay, final int bx, final int by) {
            if (ax == bx && ay == by) {
                return 0;
            }

            /* Berechnung der Seitenlängen des Dreiecks: */
            final double dx = bx - ax;
            final double dy = ay - by;
            final double dz = Math.sqrt(dx * dx + dy * dy);

            /*
             * Berechnung des Winkels nach Pythagoras: sin(gamma) = dy/dz <=>
             * gamma = arcsin(dy/dz)
             */
            double gamma = Math.asin(dy / dz);

            /* Umrechnung von RAD auf DEG: */
            gamma = 180 * gamma / Math.PI;

            long angle = Math.round(gamma);

            /* erster Quadrant: */
            if (bx >= ax && by <= ay) {
                ; /* passt schon so */
            } else if (bx <= ax && by <= ay) {
                angle = 180 - angle;
            } else if (bx <= ax && by >= ay) {
                angle = 180 - angle;
            } else if (bx >= ax && by >= ay) {
                angle = 360 + angle;
            }

            return angle;
        }
    }

}
