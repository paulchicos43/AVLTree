import java.lang.Math;
import java.util.Random;

public class AVL {
  private class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }


  public Node root;

  /**
   * Constructor for the AVL tree class
   */
  public AVL() {
    this.root = null;
  }

  /**
   * Gets the height of a given branch
   * 
   * @param branch is the root of the branch
   * @return the height
   */
  public int getHeight(Node branch) {
    if (branch == null) {
      return 0;
    } else {
      return 1 + Math.max(getHeight(branch.left), getHeight(branch.right));
    }
  }

  /**
   * Insert data into the tree
   * 
   * @param data is the data
   */
  public void insert(int data) {
    root = insertHelp(root, data);
    Node starter = getGrandparent(data);

    while (starter != null) {
      Node helper = propagate(starter);
      if (helper != null) {
        root = replace(root, starter, helper);
        starter = getGrandparent(starter.data);
      } else {
        starter = getParent(starter.data);
      }
    }
  }


  /**
   * Helper method for insertion into the tree
   * 
   * @param root is the root of the tree
   * @param data is the data to insert
   * @return the new root
   */
  private Node insertHelp(Node root, int data) {
    if (root == null) {
      return new Node(data);
    } else if (data < root.data) {
      root.left = insertHelp(root.left, data);
      return root;
    } else if (data > root.data) {
      root.right = insertHelp(root.right, data);
      return root;
    } else {
      throw new IllegalArgumentException("Duplicate key");
    }
  }

  public Node rotateLeft(Node base) {

    int balanceFactor =
        getHeight(base.right.left) - getHeight(base.right.right);
    if (balanceFactor > 0) {
      base.right = rotateRight(base.right);
    }
    Node holder = base.right;
    Node second = base.right.left;
    base.right = null;
    holder.left = base;
    holder.left.right = second;
    return holder;
  }

  public Node rotateRight(Node base) {
    int balanceFactor = getHeight(base.left.left) - getHeight(base.left.right);
    if (balanceFactor < 0) {
      base.left = rotateLeft(base.left);
    }

    Node holder = base.left;
    Node second = base.left.right;
    base.left = null;
    holder.right = base;
    holder.right.left = second;
    return holder;
  }



  /**
   * Gets the parent of the given node
   * 
   * @param data is the data to find the parent
   * @return the parent of the node containing given data
   */
  public Node getParent(int data) {
    return getParentHelp(root, data);
  }

  /**
   * Helper method for getParent function
   * 
   * @param root
   * @param data
   * @return
   */
  private Node getParentHelp(Node root, int data) {
    if (root == null || (root.right == null && root.left == null)) {
      return null; // If both children are null or if root is null
    } else if ((root.right != null && root.right.data == data)
        || (root.left != null && root.left.data == data)) {
      return root;
    } else if (data < root.data) {
      return getParentHelp(root.left, data);
    } else {
      return getParentHelp(root.right, data);
    }
  }

  /**
   * Gets the grandparent of the given data
   * 
   * @param data
   * @return
   */
  public Node getGrandparent(int data) {
    if (getParent(data) != null) {
      return getParent(getParent(data).data);
    } else {
      return null;
    }
  }

  private Node propagate(Node node) {
    int balanceFactor = getHeight(node.left) - getHeight(node.right);
    if (balanceFactor < -1) {
      return rotateLeft(node);
    } else if (balanceFactor > 1) {
      return rotateRight(node);
    } else {
      return null;
    }
  }

  private Node replace(Node root, Node node, Node replace) {
    if (node.data == root.data) {
      return replace;
    } else if (node.data < root.data) {
      root.left = replace(root.left, node, replace);
      return root;
    } else {
      root.right = replace(root.right, node, replace);
      return root;
    }
  }

  private Node getNode(Node root, Node key) {
    if (root == null) {
      return null;
    } else if (key.data == root.data) {
      return root;
    } else if (key.data < root.data) {
      return getNode(root.left, key);
    } else {
      return getNode(root.right, key);
    }
  }

  public boolean contains(int value) {
    if (getNode(root, new Node(value)) == null) {
      return false;
    }
    return true;
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * My personal testing
   * 
   * @param args
   */
  public static void main(String[] args) {
    AVL tree = new AVL();
    Random rand = new Random();
    tree.insert(55);
    tree.insert(66);
    tree.insert(17);
    tree.insert(88);
    tree.insert(18);
    tree.insert(21);
    tree.insert(80);
    tree.insert(25);
    tree.insert(7);
    tree.insert(40);
    tree.insert(94);
    tree.insert(76);
    tree.insert(70);
    tree.insert(90);
    tree.insert(5);
    tree.insert(3);
    tree.insert(10);
    tree.insert(16);
    tree.insert(65);
    tree.insert(15);
    System.out.println(tree.root.left.data);
    }

  }

