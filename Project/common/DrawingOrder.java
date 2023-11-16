package Project.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Project.server.ServerThread;

public class DrawingOrder {
    private List<ServerThread> drawingOrder;
    private Iterator<ServerThread> drawingOrderIterator;

    public DrawingOrder() {
        drawingOrder = new LinkedList<>();
        drawingOrderIterator = drawingOrder.iterator();
    }

    public synchronized void addClient(ServerThread client) {
        drawingOrder.add(client);
        drawingOrderIterator = drawingOrder.iterator(); 
    }

    public synchronized ServerThread getNextDrawer() {
        if (!drawingOrderIterator.hasNext()) {
            drawingOrderIterator = drawingOrder.iterator(); 
        }
        return drawingOrderIterator.next();
    }
}