import java.awt.Color;
import java.util.Random;

public class Spirograph
{
    private static int width;
    private static int height;
    private static Random rand;


    public static void drawSeeded(long input, int doubleLines){
        width = CanvasHost.width;
        height = CanvasHost.height;

        Random rand = new Random();
        if(input != 00000000){
            rand.setSeed(input);
        }

        int Segment = width / 6;
        //for(int j = 0; j < 1; j--){
        int innerBeam = rand.nextInt(Segment) + (2 * Segment);
        if(rand.nextInt(2) == 0){
            innerBeam = -innerBeam;
        }


        int outerBeam = rand.nextInt(Segment) + (2 * Segment);
        if(rand.nextInt(2) == 0){
            outerBeam = -outerBeam;
        }

        int innerCircle = rand.nextInt(175) + 5;
        if(rand.nextInt(2) == 0){
            innerCircle = -innerCircle;
        }

        int outerCircle = rand.nextInt(175) + 5;
        if(rand.nextInt(2) == 0){
            outerCircle = -outerCircle;
        }
        CanvasHost.myCanvas.erase();
        Pen base = new Pen(width, height, CanvasHost.myCanvas);
        base.penUp();
        Pen end = new Pen(width, height, CanvasHost.myCanvas);
        end.setRandomColor();
        base.setRandomColor();
        int x1 = 0;
        int y1 = 0;
        for(int i = 0; i < 361; i++){
            int x2 = x1;
            int y2 = y1;
            base.move(innerBeam);
            base.getPos();
            base.moveTo(width, height);
            base.turn(innerCircle);
            int x = base.pX;
            int y = base.pY;
            end.penUp();
            end.moveTo(x, y);
            if(doubleLines == 1){
                end.penDown();
            }
            end.move(outerBeam);
            end.turn(outerCircle);
            end.getPos();
            x1 = end.pX;
            y1 = end.pY;
            if(i > 0){
                Pen output = new Pen(x2, y2, CanvasHost.myCanvas);
                output.setColor(base.getColor());
                output.moveTo(x1, y1);
            }
            CanvasHost.myCanvas.wait(3);
        }
    }

}