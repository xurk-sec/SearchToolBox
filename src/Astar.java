import java.util.*;

/**
 * This is where you implement the A-star search algorithm.
 * It currently has a Depth-First search implementation.
 * 
 * @author 
 */
public class Astar extends SearchMethod
{
    /**
     * Initializes a new A-star search.
     */
    public Astar()
    {
        init();
    }
    
    /**
     * Finds a path from start to end node.
     * 
     * @return True when the search is ready, false if not ready.
     */
    public boolean step()
    {
        //Visit (expand) the best node in the open list
        Node n = getNextToVisit();

        map.setLinkAsVisited(n);

        //Move the visited node to the closed list
        closed.add(n);
        //Increase the visited counter
        noVisited++;

        //Check if we are finished = current node equals end node
        if (n.equals(end))
        {
            //Goal node reached!
            path = reconstructPath(n);
            return true;
        }
        else
        {
            Vector<Node> connected = getConnectedNodes(n);
            for (Node c:connected)
            {
                //If a node is not in the open or closed lists, add it to the open list
                if (!isInOpenOrClosed(c))
                {
                        c.parent = n;
                        open.add(c);
                        calculateActualCost(c);
                }
            }

            // 给 open 排序
            for(int i = 0; i < open.size()-1; i++){
                for(int j = 0; j < open.size()-1-i; j++){
                    if(open.elementAt(j).actual > open.elementAt(j+1).actual){
                        Node temp;
                        temp = open.elementAt(j+1);
                        open.set(j+1, open.elementAt(j));
                        open.set(j, temp);
                    }

                }
            }

        }
       
        return false;
    }
    
    /**
     * Returns the best node in the open list. This is where you implement
     * logic for finding the best node in the open list.
     * 
     * @return The best node in the open list
     */
    private Node getNextToVisit()
    {
        Node bestNode = open.firstElement();

        open.remove(0);
        
        return bestNode;
    }
    
    /**
     * Calculates the actual cost (path length) of going from the start node
     * to the specified node. Use it when calculating the best node in the
     * open list.
     * 
     * @param n The current node
     */
    private void calculateActualCost(Node n)
    {
        Map mapp = new Map();
        Path path = reconstructPath(n);
        path.recalculateLength();

        n.actual = path.getLength() + path.getLength(n, mapp.getEndNode());
    }
}

// 只有已经被确认为最短路径的节点才能用于判断是否actualCost是最近的