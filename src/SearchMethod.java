import java.util.Vector;

/**
 * Abstract base class for all search algorithms. Each search algorithm must
 * extend this class and implement the findPath() method.
 *
 * @author Johan Hagelbäck
 */
public abstract class SearchMethod 
{
    /** Open list data structure */
    protected Vector<Node> open;
    /** Closed list data structure */
    protected Vector<Node> closed;
    /** The map to find the path in */
    protected Map map;
    /** End (destination) node */
    protected Node end;
    /** Start node */
    protected Node start;
    /** The found path */
    protected Path path;
    /** Counter for number of visited (expanded) nodes */
    public int noVisited = 0;
    
    /**
     * Initializes a new search. Must be called for a new search is conducted since it
     * resets all necessary variables and data structures.
     */
    public void init()
    {
        open = new Vector<Node>();
        closed = new Vector<Node>();
        noVisited = 0;
        // return instance = new Map();
        map = Map.getInstance();
        // reset links ： l.visibility = Link.NONE = 0
        // reset nodes ： node.parent = null
        map.reset();
        // return node / null
        end = map.getEndNode();
        // return node / null
        start = map.getStartNode();
        //Add start node to open list
        open.add(start.clone());
        // path = new Vector<Node>()
        // length = 0;
        path = new Path();
    }
    
    /**
     * Checks if a node is in the open list.
     * 
     * @param n The node to check
     * @return True if the node is in the open list, false otherwise
     */
    protected boolean isInOpen(Node n)
    {
        for (Node c:open)
        {
            if (c.equals(n)) return true;
        }
        return false;
    }
    
    /**
     * Checks if a node is in the open list. If it is, the copy of the node in
     * the open list is returned.
     * 
     * @param n The node to check
     * @return The copy of the node (if found in open list), null otherwise
     */
    protected Node findInOpen(Node n)
    {
        for (Node c:open)
        {
            if (c.equals(n)) return c;
        }
        return null;
    }
    
    /**
     * Checks if a node is in the closed list.
     * 
     * @param n The node to check
     * @return True if the node is in the closed list, false otherwise
     */
    protected boolean isInClosed(Node n)
    {
        for (Node c:closed)
        {
            if (c.equals(n)) return true;
        }
        return false;
    }
    
    /**
     * Checks if a node is in the closed list. If it is, the copy of the node in
     * the closed list is returned.
     * 
     * @param n The node to check
     * @return The copy of the node (if found in closed list), null otherwise
     */
    protected Node findInClosed(Node n)
    {
        for (Node c:closed)
        {
            if (c.equals(n)) return c;
        }
        return null;
    }
    
    /**
     * Checks if a node is in the open or closed lists.
     * 
     * @param n The node to check
     * @return True if the node is in the open or closed lists, false otherwise
     */
    protected boolean isInOpenOrClosed(Node n)
    {
        if (isInOpen(n) || isInClosed(n))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns a list with copies of all nodes connected to the specified node.
     * 
     * @param n The node to find connected nodes to
     * @return The list of connected nodes
     */
    // 获取所有相邻节点的向量组
    protected Vector<Node> getConnectedNodes(Node n)
    {
        // 新建空向量
        Vector<Node> nodes = new Vector<Node>();
        // map.getLinks() :: return links;
        // 遍历所有link 1 2； 1 3
        for (Link l:map.getLinks())
        {
            // if link 的左端(x)的节点，是传入的节点n
            if (l.a.equals(n)) 
            {
                // 条件满足，将相邻节点的 Clone 加入向量  2 3
                nodes.add(l.b.clone());
            }
        }
        // 返回（向量）所有满足条件（与节点n相邻）的节点
        return nodes;
    }

    protected String[] getConnectedNodesLabels(Node n)
    {
        // 新建空向量
        String[] nodes = new String[50];
        // map.getLinks() :: return links;
        // 遍历所有link 1 2； 1 3
        int i = 1;
        for (Link l:map.getLinks())
        {
            // if link 的左端(x)的节点，是传入的节点n
            if (l.a.equals(n))
            {
                // 条件满足，将相邻节点的 Clone 加入向量  2 3
                nodes[i] = l.b.label;
                i++;
            }
        }
        nodes[0] = String.valueOf(i-1);
        // 返回（向量）所有满足条件（与节点n相邻）的节点
        return nodes;
    }


    /**
     * Reconstructs a path by traversing from the end node back to the start node
     * using the parent references. Use when you want to create the actual path
     * from your search.
     * 
     * @param n The end node
     * @return The path (a list of nodes)
     */
    protected Path reconstructPath(Node n)
    {
        Path path = new Path();
        path.add(n);
        while (n.parent != null)
        {
            n = n.parent;
            path.add(n);
        }
        return path;
    }
    
    /**
     * Returns the found path.
     * 
     * @return The path
     */
    public Path getPath()
    {
        return path;
    }
    
    /**
     * Finds a path from start to end node.
     * 
     * @return True when the search is ready, false if not ready.
     */
    public abstract boolean step();
}
