public class Main {
                  /*** DESCRIPTION ***/
  /*personal coding challenge about solving the time complexity divergence between the two main branches of a binary tree. 
  When inserting nodes, many iterations can create an irregular binary tree regarding its branches depth, which can lead to odd time complexity. 
  This script aims to reorder the nodes so that we get even processing time from the root node downwards*/

  
  static int greatest_depth_l = 0;
  static int greatest_depth_r = 0;
    
  public static void main(String[] args) {
    insert(6);
    insert(5);
    insert(4);
    insert(3);
    insert(1);
    insert(7);
    insert(8);

    traverseNormal(root);
    System.out.println(" ");

    traverse(root.left, 0, "left");
    traverse(root.right, 0, "right");

    System.out.println("left-subtree depth: " + greatest_depth_l);
    System.out.println("right-subtree depth: " + greatest_depth_r);

    balanceTree(greatest_depth_l, greatest_depth_r);
    traverseNormal(root);

    System.out.println(" ");

    traverse(root.left, 0, "left");
    traverse(root.right, 0, "right");

    System.out.println("left-subtree depth: " + greatest_depth_l);
    System.out.println("right-subtree depth: " + greatest_depth_r);
  }

  static Node root;

  static class Node {
    Node left;
    Node right;
    int key;
  }

  static void insert(int key) {
    Node node = new Node();
    node.key = key;
    Node pointer = root;
    if (root == null) {
      root = new Node();
      root.key = key;
      return;
    }
    Node parent;
    while (true) {
      parent = pointer;
      if (node.key < pointer.key) {
        pointer = pointer.left;
        if (pointer == null) {
          parent.left = node;
          break;
        }
      } else if (node.key > pointer.key) {
        pointer = pointer.right;
        if (pointer == null) {
          parent.right = node;
          break;
        }
      } else {
        break;
      }
    }
  }

  static void balanceTree(int left_depth, int right_depth) {
    if (left_depth == right_depth) {
      return;
    }
    
    if (left_depth > right_depth + 1) {
      Node node = root.left;
      Node parent;
      if (node.right != null) {
        while (true) {
          parent = node;
          node = node.right;
          if (node.right == null) {
            break;
          }
        }
        if (node.left != null) {
          parent.right = node.left;
        } else {
          parent.right = null;
        }
        node.right = root;
        node.left = root.left;
        root.left = null;
        root = node;

      } else {
        node.right = root;
        node.right.left = null;
        root = node;
      }
      greatest_depth_l = 0;
      greatest_depth_r = 0;
      return;
    }

    if (left_depth < right_depth) {
      Node node = root.right;
      Node parent;
      if (node.left != null) {
        while (true) {
          parent = node;
          node = node.left;
          if (node.left == null) {
            break;
          }
        }
        node.left = root;
        root = node;
        if (node.right != null) {
          parent.left = node.right;
        } else {
          parent.left = null;
        }

      } else {
        root.right.left = root;
        root = root.right;
      }
    }
  }

  static void traverse(Node node, int k, String side) {
    if (node != null) {
      k++;
      // System.out.print(node.key);
      /*--> recursion*/traverse(node.left, k, side);
      /*--> recursion*/traverse(node.right, k, side);
    }
    if (k > greatest_depth_l && side == "left") {
      greatest_depth_l = k;
    }
    if (k > greatest_depth_r && side == "right") {
      greatest_depth_r = k;
    }
  }

  static void traverseNormal(Node node) {
    if (node != null) {
      System.out.print(node.key + " ");
      traverseNormal(node.left);
      traverseNormal(node.right);
    }
  }
  
}
