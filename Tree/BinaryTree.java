import java.util.ArrayList;

public class BinaryTree {
  private BinaryTreeNode root;
  public BinaryTree() {
    root = null;
  }

  public void setRoot(BinaryTreeNode root) {
    this.root = root;
  }

  public BinaryTreeNode getRoot() {
    return this.root;
  }

  // DFS
  public void inOrderTraverse() {
    // Left Root Right
    root.inOrder(root);
  }

  public void preOrderTraverse() {
    // Root Left Right
    root.preOrder(root);
  }

  public void postOrderTraverse() {
    // Left Right Root
    root.postOrder(root);
  }


  //BFS
  public void BFSTraverse() {
    ArrayList<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
    list.add(root);

    while (!list.isEmpty()) {
      BinaryTreeNode lNode = list.get(0).left;
      BinaryTreeNode rNode = list.get(0).right;

      if (lNode != null)
        list.add(lNode);
      if (rNode != null)
        list.add(rNode);

      System.out.println(list.remove(0).data);
    }
  }

  /*
  Interview 4.7 solution common ancestor
  */
  private class Result {
    public int currentFound;
    public BinaryTreeNode node;
    public Result() {
      this.currentFound = 0;
      this.node = null;
    }
  }

  private Result commonAncestor(BinaryTreeNode node, int a, int b, int currentFound) {
    if (node.data == a || node.data == b) {
      currentFound++;
    }

    Result resultLeft = new Result();
    Result resultRight = new Result();
    if ( node.left != null )
      resultLeft = commonAncestor(node.left, a, b, 0);
    if ( node.right != null )
      resultRight = commonAncestor(node.right, a, b, 0);

    Result r = new Result();
    r.currentFound = currentFound + resultLeft.currentFound + resultRight.currentFound;

    if ((resultLeft.currentFound + resultRight.currentFound) >= 2) {
      System.out.println("Found :(" + resultLeft.currentFound + ", " + resultRight.currentFound + ") at " + node.data);
      r.node = node;
      // r.currentFound = 0;
    }
    else {
      r.node = null;
    }

    return r;
  }

  public BinaryTreeNode commonAncestor(int a, int b) {
    Result result = commonAncestor(this.root, a, b, 0);
    return result.node;
  }

  public static void main(String[] args) {
    int n = 15;
    BinaryTreeNode[] nodes = new BinaryTreeNode[n];
    for (int i = 0; i < n; i++) {
      nodes[i] = new BinaryTreeNode(i,null,null);
    }

    for (int i = 0; i < n/2; i++ ) {
      nodes[i].left = nodes[(i+1)*2-1];
      nodes[i].right = nodes[(i+1)*2];
    }

    BinaryTree tree = new BinaryTree();
    tree.setRoot(nodes[0]);
    System.out.println("------DFS-------");
    tree.inOrderTraverse();
    System.out.println("-----");
    tree.preOrderTraverse();
    System.out.println("-----");
    tree.postOrderTraverse();
    System.out.println("------BFS-------");
    tree.BFSTraverse();
    System.out.println("------commonAncestor-------");
    System.out.println("CA: " + tree.commonAncestor(0, 2).data);




  }

}
