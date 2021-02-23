import java.awt.Color;
import java.util.Random;

/**
 * Write a description of class doubleCircle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class doubleCircle
{
    private static int width;
    private static int height;

    public static void drawSeeded(long input){
        Random rand = new Random();
        if(input != 00000000){
            rand.setSeed(input);
        }

        int Seg1 = rand.nextInt(30) - 15;
        int Seg2 = rand.nextInt(30) - 15;
        int Seg3 = rand.nextInt(30) - 15;
        width = CanvasHost.width;
        height = CanvasHost.height;

        int Segment = (width / 3) - 3;
        int innerBeam = Segment;
        int middleBeam = Segment;
        int outerBeam = Segment;

        CanvasHost.myCanvas.erase();
        Pen base = new Pen(width, height, CanvasHost.myCanvas);
        Pen middle = new Pen(width, height, CanvasHost.myCanvas);
        Pen end = new Pen(width, height, CanvasHost.myCanvas);
        base.setRandomColor();
        //base.turn(rand.nextInt(360));
        middle.turn(rand.nextInt(360));
        end.turn(rand.nextInt(360));
        //base.penUp();
        //base.setRandomColor();
        //middle.setRandomColor();
        //end.setRandomColor();

        int x2 = 0;
        int y2 = 0;
        for(int i = 0; i < 500; i++){
            int x3 = x2;
            int y3 = y2;
            base.penUp();
            base.move(innerBeam);
            base.getPos();

            base.moveTo(width, height);
            base.turn(Seg1);
            int x = base.pX;
            int y = base.pY;

            middle.penUp();
            middle.setColor(Color.BLUE);

            middle.moveTo(x , y);
            //middle.penDown();
            middle.move(middleBeam);

            middle.turn(Seg2);
            middle.getPos();
            int x1 = middle.pX;
            int y1 = middle.pY;
            end.penUp();
            end.setColor(Color.YELLOW);

            end.moveTo(x1, y1);
            //end.penDown();
            end.move(outerBeam);

            end.turn(Seg3);
            end.getPos();
            x2 = end.pX;
            y2 = end.pY;
            if(i > 0){
                Pen output = new Pen(x3, y3, CanvasHost.myCanvas);
                //output.setColor(Color.BLACK);
                output.setColor(base.getColor());
                //output.setRandomColor();
                output.moveTo(x2, y2);
            }
            CanvasHost.myCanvas.wait(5);
        }






    }
}
