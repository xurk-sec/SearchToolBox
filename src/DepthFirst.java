import java.util.*;

/**
 * This is the implementation of the Depth-First Search algorithm for
 * pathfinding.
 * 
 * @author Johan Hagelbäck
 */
public class DepthFirst extends SearchMethod
{
    /**
     * Initializes a new Depth-First Search.
     */
    public DepthFirst()
    {
        //Must be called to reset all variables and
        //data structures needed for the search.
        // reset map 未可视化
        // open 向量获取到第一个数据（start node）不一定是label1
        init();
    }
    
    /**
     * Finds a path from start to end node.
     * 
     * @return True when the search is ready, false if not ready.
     */
    // 与GUI.java中的run()配合循环执行step()。每执行一次step()
    public boolean step()
    {
        //Visit (expand) the first node in the open list （第一个节点是start节点，不一定是label1）
        Node n = open.remove(0);
        // 找它parent，若有parent，将parent与它之间的线设置为l.visibility = Link.VISITED; 若无，return；
        map.setLinkAsVisited(n);
        //Move the visited node to the closed list
        closed.add(n);
        //Increase the visited counter 将被实时显示出来
        noVisited++;

        //Check if we are finished = current node equals end node
        if (n.equals(end))
        {
            //Goal node reached! 所有的节点都被遍历过，step()循环结束，返回true
            path = reconstructPath(n);
            return true;
        }
        else
        {
            //Not finished yet. Keep iterating.

            //Find the nodes that are connected to the current node n
            // 传入当前处理的节点n， 得到所有相邻节点的 Clone 2 3
            Vector<Node> connected = getConnectedNodes(n);
            // 遍历相邻节点的CLone
            for (Node c:connected)
            {
                //If a node is not in the open or closed lists, add it  待处理
                //to the open list
                if (!isInOpenOrClosed(c))
                {
                    //Set the parent reference
                    // 将相邻节点的 parent 属性指向当前节点n  2.parent = 1; 3.parent = 1
                    c.parent = n;
                    //Add first in the open list (LIFO) 下标始终为0，同一次遍历中，后add的node覆盖前add的node
                    // 核心语句
                    open.add(0, c);
                }
            }
        }
       
        return false;
    }
}
