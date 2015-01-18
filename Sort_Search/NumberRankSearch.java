// Chapter 11 Sorting and Searching
// Question 11.8

public class NumberRankSearch {
  public NumberRankTree tree;

  public static class NumberRankNode {
    public int left_value; // How many node there is in the left node
    public int data;
    public int number_of_data;
    public NumberRankNode left_node;
    public NumberRankNode right_node;
    public NumberRankNode() {
      this.left_node = null;
      this.right_node = null;
      this.data = 0;
      this.left_value = 0;
      this.number_of_data = 0;
    }
    public NumberRankNode(int data) {
      this.left_node = null;
      this.right_node = null;
      this.data = data;
      this.left_value = 0;
      this.number_of_data = 1;
    }
  }

  public static class NumberRankTree {
    public NumberRankNode root;
    public NumberRankTree() {
      root = null;
    }

    public void insert(int data) {
      if (root == null) {
        root = new NumberRankNode(data);
      }
      else {
        insert(data,root);
      }
    }

    public int search(int data) {
      return search(data,root,0);
    }

    private void insert(int data, NumberRankNode from) {
      if (from.data == data) {
        // If insert to current node, count ++
        from.number_of_data++;
      }
      else {
        if (from.data > data) {
          // If insert to left node, left_value++
          from.left_value++;
          System.out.println(from.data + " left_value increased to " + from.left_value);
          if (from.left_node == null) {
            from.left_node = new NumberRankNode(data);
          }
          else {
            insert(data,from.left_node);
          }
        }
        else {
          // If insert to left node, left_value++
          if (from.right_node == null) {
            from.right_node = new NumberRankNode(data);
          }
          else {
            insert(data,from.right_node);
          }
        }
      }
    }

    private int search(int data, NumberRankNode from, int currentRank) {
      // If this node is null, we cannot find the node
      if (from == null) {
        return -1;
      }
      else {
        // If this is the node, return currentRank + current.left_value
        if (from.data == data) {
          System.out.println("Found " + data + " , (cR, leftValue):(" + currentRank + ", " + from.left_value + ")" );
          return currentRank + from.left_value;
        }

        // If this node is bigger, should go left.
        if (from.data > data) {
          System.out.println("Finding " + from.data + " , (cR, leftValue):(" + currentRank + ", " + from.left_value + "), going left." );
          return search(data, from.left_node, currentRank);
        }
        // If this node is smaller, should go right. And all left points should be included.
        else {
          System.out.println("Finding " + from.data + " , (cR, leftValue, NoD):(" + currentRank + ", " + from.left_value + ", " + from.number_of_data + "), going right." );
          return search(data, from.right_node, currentRank + from.left_value + from.number_of_data);
        }
      }
    }

  }

  public NumberRankSearch() {
    this.tree = new NumberRankTree();
  }

  public int getRankOfNumber(int n) {
    return this.tree.search(n);
  }

  public void track(int n) {
    this.tree.insert(n);
  }


  public static void main(String[] args) {
    NumberRankSearch nrs = new NumberRankSearch();
    nrs.track(5);
    nrs.track(1);
    nrs.track(4);
    nrs.track(4);
    nrs.track(5);
    nrs.track(9);
    nrs.track(7);
    nrs.track(13);
    nrs.track(3);

    System.out.println(nrs.getRankOfNumber(1));
    System.out.println(nrs.getRankOfNumber(3));
    System.out.println(nrs.getRankOfNumber(4));
    System.out.println(nrs.getRankOfNumber(7));
    System.out.println(nrs.getRankOfNumber(13));
    System.out.println(nrs.getRankOfNumber(19));
  }

}
