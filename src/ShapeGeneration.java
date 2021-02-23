import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;


public class ShapeGeneration {
    static Random rand;
    static int range;
    static Integer count;

    public static void randomShape(long input){
        CanvasHost.myCanvas.erase();
        rand = new Random();
        if(input != 00000000){
            rand.setSeed(input);
        }
        range = (CanvasHost.height / 2) / 10;
        //10 is the pen movement distance to allow for angles up to 10°


        ArrayList<Pen> shapeArray = new ArrayList();

        Pen g = new Pen(0, 0, CanvasHost.myCanvas);

        for(int i = 0; i < 8; i++){
            g = new Pen(CanvasHost.height, CanvasHost.width, CanvasHost.myCanvas);
            shapeArray.add(g);
        }

        for(int i = 0; i < shapeArray.size(); i++){
            Pen construct = shapeArray.get(i);
            construct.turn((360 / shapeArray.size()) * i);
            construct.setRandomColor();
            construct.move(CanvasHost.height / 2);
        }




        for(int j = 0; j < 1000; j++){

            g.getPos();
            Pen outline = new Pen(g.pX, g.pY, CanvasHost.myCanvas);

            for(int i = 0; i < shapeArray.size(); i++){
                Pen movement = shapeArray.get(i);
                movement.move(10 * (rand.nextInt(3) - 1));

                outline.setColor(movement.getColor());

                movement.getPos();
                outline.moveTo(movement.pX, movement.pY);
            }

            outline.moveTo(g.pX, g.pY);
            CanvasHost.myCanvas.wait(5);
        }

    }


}
