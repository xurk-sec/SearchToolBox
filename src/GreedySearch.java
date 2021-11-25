import java.util.*;

/**
 * This is where you implement the Greedy Search algorithm.
 * It currently has a Depth-First search implementation.
 * 
 * @author 
 */
public class GreedySearch extends SearchMethod
{
    // 是否被标记
//    public boolean[] arr = new boolean[Map.num_of_nodes];
//    public double[] distance = new double[Map.num_of_nodes];
//    public Vector<Node> clone_nodes = new Vector<Node>();

    /**
     * Initializes a new Greedy search.
     */
    public GreedySearch()
    {
        init();
//        Arrays.fill(arr, false);
//        Arrays.fill(distance, 1000);
//        distance[0] = 0;
//
//        Vector<Node> nodes = Map.getInstance().getNodes();
//        for(Node node:nodes){
//            clone_nodes.add(node.clone());
//        }
    }
    
    /**
     * Finds a path from start to end node.
     * 
     * @return True when the search is ready, false if not ready.
     */
    public boolean step()
    {
        // 获取到最小节点，找它的后继，更新distance
        //Visit (expand) the best node in the open list
        Node n = getNextToVisit();

        map.setLinkAsVisited(n);

        //Increase the visited counter
        noVisited++;

        // 找 flag 为 false 的邻近节点
        // 重复创建克隆对象，导致被覆盖
//        String[] labels = getConnectedNodesLabels(n);
//        Vector<Node> connected = new Vector<Node>();
//        for(int m = 1; m <= Integer.parseInt(labels[0]); m++){
//            for(Node node1:clone_nodes) {
//                if (node1.label == labels[m]) {
//                    connected.add(node1);
//                }
//            }
//        }
        Vector<Node> connected = getConnectedNodes(n);
        int d = 0;
            for (Node c:connected)
            {
                // 根据label找对应的arr（flag）的值
                // 为false
                if (!isInOpenOrClosed(c))
                {
                    c.parent = n;
                    open.add(d, c);
                    d++;
//                    System.out.println(c.label + "'s parent is " + n.label);
//
//                    Path path1 = new Path();
//                    double length_bet = path1.getLength(n, c);
//                    // 更短、更新
//                    double n_dis = distance[Integer.parseInt(n.label)-1];
//                    double c_dis = distance[Integer.parseInt(c.label)-1];
//                    if((n_dis + length_bet) < c_dis){
//                        c.parent = n;
//                        distance[Integer.parseInt(c.label)-1] = n_dis + length_bet;
////                        System.out.println(c.label +"'s label(step)" +System.identityHashCode(c));
//                        System.out.println(c.label + "'s shortest_num: " + distance[Integer.parseInt(c.label)-1]);
//
//                    }
                }
            }

            if (n.equals(end)) {
                //Goal node reached! 从后向前画
                path = reconstructPath(n);
                return true;
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
        //Pick first node...
        Node bestNode = open.elementAt(0);
        double min = 1000;
        Path pathh = new Path();
        Map mapp = new Map();
        for(Node node:open){
            if(pathh.getLength(node, mapp.getEndNode()) < min){
                min = pathh.getLength(node, mapp.getEndNode());
                bestNode = node;
            }
        }
        return bestNode;


//        Node bestNode = null;
//        int bestNodeLabel = -1;
//        double min = 1000000000;

//        Map maap = new Map();
//        Vector<Node> nodes = maap.getNodes();
        // 从未被标记node中找当前路径最小的
//
//        for (int i = 0; i < Map.num_of_nodes; i++){
//            if((distance[i] < min) && !arr[i]){
//                min = distance[i];
//                bestNodeLabel = i+1;
//            }
//        }

        // 根据label获取node
//        Vector<Node> nodes = Map.getInstance().getNodes();
//        for(Node node:clone_nodes){
//            if (Integer.parseInt(node.label) == bestNodeLabel)
//            {
//                arr[bestNodeLabel-1] = true;
//                System.out.println("node label: " + node.label);        //
//                return node;
//            }
//        }

    }


}
