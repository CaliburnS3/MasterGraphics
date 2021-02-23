
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * This code uses the class Canvas, through means of "public static Canvas myCanvas"
 * creating a canvas that can be manipulated by other classes. Of course, I could
 * easily just use the few lines of code per class that wants to use this, but
 * I wanted a challenge.
 *
 */
public class CanvasHost
{
    /**These variables are saved here so that I can get these values from other
     * classes, especially ones that have calculations based on width or height.
     */

    public static int width;
    public static int height;
    public static Canvas myCanvas;
    static int X, Y;
    public static void main(String[] args)
    {
        // Getting max size of the frame
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        double output = Math.min(d.getHeight() - 50, d.getWidth());

        /** X and Y are the x and y size of the canvas
         * width and height, despite the awful names i gave them, actually help
         * find the middle of the canvas created
         */
        X = (int) output;
        Y = (int) output;
        width = X / 2;
        height = Y / 2;

        /** Creation of the canvas, the format of how to create is shown in the
         * canvas class. I do not expect you to explore the canvas class
         * its gross and messy.
         *
         * End description. Feel free to look around, Next, go to spirograph.
         */
        myCanvas = new Canvas("Canvas Host", X, Y, Color.BLACK);
    }
}
