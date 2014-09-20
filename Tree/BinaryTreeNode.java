public class BinaryTreeNode {
  public int data;
  public BinaryTreeNode left;
  public BinaryTreeNode right;

  public BinaryTreeNode() {
    this.data = 0;
    this.left = null;
    this.right = null;
  }

  public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }


  public void inOrder(BinaryTreeNode node) {
    if (node == null)
      return;

    node.inOrder(node.left);
    System.out.println(node.data);
    node.inOrder(node.right);
  }

  public void preOrder(BinaryTreeNode node) {
    if (node == null)
      return;

    System.out.println(node.data);
    node.preOrder(node.left);
    node.preOrder(node.right);
  }

  public void postOrder(BinaryTreeNode node) {
    if (node == null)
      return;

    node.postOrder(node.left);
    node.postOrder(node.right);
    System.out.println(node.data);
  }
}
