import java.util.ArrayList;

public class TreePathValue {

  public static class DoubleTreeNode {
    public DoubleTreeNode previous;
    public DoubleTreeNode left;
    public DoubleTreeNode right;
    public int value;
    public DoubleTreeNode() {
      this.previous = null;
      this.left = null;
      this.right = null;
      this.value = -1;
    }
    public DoubleTreeNode(int v) {
      this.previous = null;
      this.left = null;
      this.right = null;
      this.value = v;
    }
    public DoubleTreeNode(DoubleTreeNode p, DoubleTreeNode l, DoubleTreeNode r, int v) {
      this.previous = p;
      this.left = l;
      this.right = r;
      this.value = v;
    }

    public void setLeft(DoubleTreeNode node) {
      this.left = node;
      node.previous = this;
    }

    public void setRight(DoubleTreeNode node) {
      this.right = node;
      node.previous = this;
    }
  }

  public static class DoubleTree {
    private DoubleTreeNode root;

    /*
    Constructors
    */
    public DoubleTree() {
      this.root = null;
    }
    public DoubleTree(DoubleTreeNode root) {
      this.root = root;
    }

    /*
    Public methods
    */
    public DoubleTreeNode getRoot() {
      return this.root;
    }
    public void printPathWithSum(int sum) {
      ArrayList<DoubleTreeNode> nodeList = new ArrayList<DoubleTreeNode>();
      addNodeToList(this.root, nodeList);

      ArrayList<ArrayList<DoubleTreeNode>> results = new ArrayList<ArrayList<DoubleTreeNode>>();
      for ( DoubleTreeNode node : nodeList) {
        ArrayList<ArrayList<DoubleTreeNode>> resultForNode = getPathForValue(node,sum);
        for (ArrayList<DoubleTreeNode> list : resultForNode) {
          results.add(list);
        }
      }

      for (ArrayList<DoubleTreeNode> path : results) {
        System.out.print("start | ");
        for (DoubleTreeNode node : path) {
          System.out.print(node.value + " -> ");
        }
        System.out.println(" | end");
      }
    }

    /*
    Private methods
    */
    // InOrder
    private void addNodeToList(DoubleTreeNode node, ArrayList<DoubleTreeNode> list) {
      list.add(node);
      if (node.left != null) {
        addNodeToList(node.left, list);
      }
      if (node.right != null) {
        addNodeToList(node.right, list);
      }
    }

    private ArrayList<ArrayList<DoubleTreeNode>> getPathForValue(DoubleTreeNode end, int sum) {
      ArrayList<ArrayList<DoubleTreeNode>> results = new ArrayList<ArrayList<DoubleTreeNode>>();
      ArrayList<DoubleTreeNode> path = new ArrayList<DoubleTreeNode>();
      int currentSum = 0;
      while(end != null) {
        path.add(0, end);
        if ((currentSum += end.value) == sum) {
          ArrayList<DoubleTreeNode> newPath = new ArrayList<DoubleTreeNode>(path);
          results.add(newPath);
        }
        end = end.previous;
      }
      return results;
    }
  }

  /*
  Main methods
  */
  public static void main(String args[]) {
    System.out.println("Jere");
    DoubleTreeNode n0 = new DoubleTreeNode(1);
    DoubleTreeNode n01 = new DoubleTreeNode(-1);
    DoubleTreeNode n02 = new DoubleTreeNode(-1);

    DoubleTreeNode n011 = new DoubleTreeNode(2);
    DoubleTreeNode n012 = new DoubleTreeNode(2);
    DoubleTreeNode n021 = new DoubleTreeNode(2);
    DoubleTreeNode n022 = new DoubleTreeNode(2);

    DoubleTreeNode n0111 = new DoubleTreeNode(-1);
    DoubleTreeNode n0112 = new DoubleTreeNode(1);
    DoubleTreeNode n0121 = new DoubleTreeNode(1);
    DoubleTreeNode n0122 = new DoubleTreeNode(-1);
    DoubleTreeNode n0211 = new DoubleTreeNode(2);
    DoubleTreeNode n0212 = new DoubleTreeNode(-2);
    DoubleTreeNode n0221 = new DoubleTreeNode(-2);
    DoubleTreeNode n0222 = new DoubleTreeNode(2);

    n0.setLeft(n01);
    n0.setRight(n02);

    n01.setLeft(n011);
    n01.setRight(n012);

    n02.setLeft(n021);
    n02.setRight(n022);

    n011.setLeft(n0111);
    n011.setRight(n0112);

    n012.setLeft(n0121);
    n012.setRight(n0122);

    n021.setLeft(n0211);
    n021.setRight(n0212);

    n022.setLeft(n0221);
    n022.setRight(n0222);

    System.out.println("Jere");
    DoubleTree dt = new DoubleTree(n0);
    dt.printPathWithSum(1);
  }
}
