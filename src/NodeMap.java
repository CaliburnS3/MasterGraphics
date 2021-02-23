import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NodeMap{
    static ArrayList<Node> nodeMap;
    static Node node;
    //static Canvas myCanvas;
    static Random rand;
    static Pen pen;

    public static void drawSeeded(long input)
    {
        CanvasHost.myCanvas.erase();
        nodeMap = new ArrayList<Node>();
        //myCanvas = new Canvas("Nodes", 500, 500, Color.BLACK);
        rand = new Random();
        if(input != 00000000){
            rand.setSeed(input);
        }

        nodePrep();
        draw();

        connect();
        while(nodeMap.size() > 0) {
            removeNodes();
        }

    }

    public static void nodePrep(){
        nodeMap.clear();
        int multiple = 25;
        int bounds = CanvasHost.X / multiple;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 2; j++) {
                int k = j * 2;
                node = new Node(multiple - (5*k), rand.nextInt(bounds) * multiple + (k), rand.nextInt(bounds) * multiple + (k));
                nodeMap.add(node);
            }
        }
    }

    public static void draw(){
        pen = new Pen(0, 0, CanvasHost.myCanvas);
        pen.setColor(Color.GREEN);
        CanvasHost.myCanvas.setForegroundColor(pen.getColor());
        for(int i = 0; i < nodeMap.size(); i++) {
            node = nodeMap.get(i);
            CanvasHost.myCanvas.drawCircle(node.posX, node.posY, node.data);
        }
    }

    public static void connect(){
        for(int i = 0; i < nodeMap.size() - 1; i++) {
            node = nodeMap.get(i);
            Node next = nodeMap.get(i + 1);
            node.north = next;
            next.east = node;
            pen = new Pen(node.posX + node.data/2, node.posY + node.data/2, CanvasHost.myCanvas);
            pen.setColor(Color.WHITE);
            pen.moveTo(next.posX + next.data/2, next.posY + next.data/2);
        }

    }

    public static void removeNodes() {
        pen = new Pen(0, 0, CanvasHost.myCanvas);
        pen.setRandomColor();
        CanvasHost.myCanvas.setForegroundColor(pen.getColor());

        for (int i = 0; i < nodeMap.size(); i++) {
            node = nodeMap.get(i);
            CanvasHost.myCanvas.drawCircle(node.posX, node.posY, node.data);
            nodeMap.remove(node);
        }
    }

    // TODO: Complete the NodeMap.Node class based on the driver method in BinarySearchTree
    public static class Node{
        int data;
        int posX;
        int posY;
        Node north, south, east, west;

        /**
         * Sets the size of the canvas.
         * @param  info     data of the node
         * @param  x        x position of the node
         * @param  y        y position of the node
         */
        public Node(int info, int x, int y){
            data = info;
            posX = x;
            posY = y;
            north = null;
            south = null;
            east = null;
            west = null;
        }
    }
}

