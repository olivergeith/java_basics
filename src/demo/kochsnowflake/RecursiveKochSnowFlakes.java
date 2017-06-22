
package demo.kochsnowflake;

// java source code for a recursive koch snow flakes

// java source code for a recursive koch snow flakes
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class RecursiveKochSnowFlakes extends JApplet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int level = 0;

    @Override
    public void init() {
        final String levelStr = JOptionPane.showInputDialog("Enter the depth of recursion");

        level = Integer.parseInt(levelStr);
    }

    @Override
    public void paint(final Graphics g) {

        drawSnow(g, level, 20, 280, 280, 280);
        drawSnow(g, level, 280, 280, 150, 20);
        drawSnow(g, level, 150, 20, 20, 280);

    }

    private void drawSnow(final Graphics g, final int lev, final int x1, final int y1, final int x5, final int y5) {
        int deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if (lev == 0) {

            g.drawLine(x1, y1, x5, y5);
        } else {
            deltaX = x5 - x1;
            deltaY = y5 - y1;

            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            x3 = (int) (0.5 * (x1 + x5) + Math.sqrt(3) * (y1 - y5) / 6);
            y3 = (int) (0.5 * (y1 + y5) + Math.sqrt(3) * (x5 - x1) / 6);

            x4 = x1 + 2 * deltaX / 3;
            y4 = y1 + 2 * deltaY / 3;

            drawSnow(g, lev - 1, x1, y1, x2, y2);
            drawSnow(g, lev - 1, x2, y2, x3, y3);
            drawSnow(g, lev - 1, x3, y3, x4, y4);
            drawSnow(g, lev - 1, x4, y4, x5, y5);
        }
    }
}