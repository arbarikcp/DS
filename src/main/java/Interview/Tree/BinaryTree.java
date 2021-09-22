package Interview.Tree;

import Interview.Tree.BinarySearchTree.MNode;
import Interview.Tree.BinarySearchTree.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.units.qual.A;


public class BinaryTree {

/*  public static void main(String[] args) {
    int[] parent = {-1, 0, 0, 1, 2, 2, 4, 4};
    Node root = createTree(parent);
    System.out.println("completed");
  }*/

  public static void main(String[] args)
  {

/*    Node root = new Node(1);

    root.left = new Node(2);
    root.mid = new Node(9);
    root.right = new Node(12);

    root.left.left = new Node(3);
    root.left.mid = new Node(6);
    root.left.right = new Node(8);

    root.mid.left = new Node(10);
    root.mid.right = new Node(11);

    root.right.mid = new Node(13);
    root.right.right = new Node(16);

    root.left.left.mid = new Node(4);
    root.left.left.right = new Node(5);
    root.left.mid.right = new Node(7);

    root.right.mid.left = new Node(14);
    root.right.mid.right = new Node(15);
    root.right.right.mid = new Node(17);

    Node head = ternaryTreeToLinkedList(root);
    printLinkList(head);
    System.out.println("............................");
    printDLLinkList(head);*/


    int[] inorder = { 4, 2, 1, 7, 5, 8, 3, 6 };
    int[] preorder = { 1, 2, 4, 3, 5, 7, 8, 6 };

    Node root = construct(inorder, preorder);

    List<Integer> keys = new ArrayList<>();
    rangeSearch(root,2,6, keys);
    System.out.println(keys);
  }

  private static void convertToLinkedList(Node root){
    if(root == null || isLeafNode(root)) return; //on leaf node there is nothing to flatten
    if(root.left != null){
      convertToLinkedList(root.left); //flatten the left subtree
      //make the left flattened tree as the right child of root.
      Node temp = root.right; // keep the reference of existing right child of root
      root.right = root.left; //make the left flattened tree as the right child of root
      root.left = null; // Set the left of root to null

      //Go to the end of right child (which is added after flattening the left child)
      while (root.right != null){
        root = root.right;
      }
      root.right = temp; // Add the old right child as the right of the end node.
    }
    convertToLinkedList(root.right); // Flatten that right subtree
  }

  private static void convertToDoublyLinkedList(Node root){
    if(root == null || isLeafNode(root)) return; //on leaf node there is nothing to flatten
    if(root.left != null){
      convertToDoublyLinkedList(root.left);
      //Make the left flattened tree as the right child of root.
      Node temp = root.right; // Keep the reference of existing right child of root
      root.right = root.left; //Make the left flattened tree as the right child of root
      root.left = null; // Set the left of root to null

      root.right.left = root; // Make the current right subtree (flattened LS) 's  left child must point to Root.

      //Go to the end of right child (which is added after flattening the left child)
      while (root.right != null){
        root = root.right;
      }
      root.right = temp; // Add the old right child as the right of the end node.
    }
    convertToDoublyLinkedList(root.right); // Flatten that right subtree
    //After flattened Right subtree make the left child of Flattened RS points to its parent.
    if(root.right != null){
      root.right.left = root;
    }
  }

  private static Node ternaryTreeToLinkedList(Node root){
    if(root == null) return null;
    Node leftSubtree = ternaryTreeToLinkedList(root.left);
    Node midSubtree = ternaryTreeToLinkedList(root.mid);
    Node rightSubtree = ternaryTreeToLinkedList(root.right);

    Node temp_rightSubtree = rightSubtree;
    if(leftSubtree != null){
      root.right = leftSubtree;
      leftSubtree.left = root;
      root.left = null;
    }
    if(midSubtree != null){
      leftSubtree = getNode(root, leftSubtree, midSubtree);
    }
    if(rightSubtree != null){
      if (midSubtree != null) {
        while (midSubtree.right != null) {
          midSubtree = midSubtree.right;
        }
        midSubtree.right = temp_rightSubtree;
        temp_rightSubtree.left = midSubtree;
      }else
        leftSubtree = getNode(root, leftSubtree, temp_rightSubtree);
    }
    return root;

  }

  public static void rangeSearch(Node root, int start, int end, List<Integer> keys){
    if(root == null) return;
    if(root.key >= start && root.key <= end){
      keys.add(root.key);
    }
    rangeSearch(root.left,start, end,keys);
    rangeSearch(root.right, start, end,keys);
  }


  // Recursive function to construct a binary tree from a given
  // inorder and preorder sequence
  public static Node construct(int start, int end,
      int[] preorder, AtomicInteger pIndex,
      Map<Integer, Integer> map) {
    // base case
    if (start > end) {
      return null;
    }

    // The next element in `preorder[]` will be the root node of subtree
    // formed by sequence represented by `inorder[start, end]`
    Node root = new Node(preorder[pIndex.getAndIncrement()]);

    // get the root node index in sequence `inorder[]` to determine the
    // left and right subtree boundary
    int index = map.get(root.key);

    // recursively construct the left subtree
    root.left = construct(start, index - 1, preorder, pIndex, map);

    // recursively construct the right subtree
    root.right = construct(index + 1, end, preorder, pIndex, map);

    // return current node
    return root;
  }

  // Construct a binary tree from inorder and preorder traversals.
  // This function assumes that the input is valid, i.e., given
  // inorder and preorder sequence forms a binary tree.
  public static Node construct(int[] inorder, int[] preorder)
  {
    // create a map to efficiently find the index of any element in
    // a given inorder sequence
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }

    // `pIndex` stores the index of the next unprocessed node in a preorder
    // sequence. We start with the root node (present at 0th index).
    AtomicInteger pIndex = new AtomicInteger(0);

    return construct(0, inorder.length - 1, preorder, pIndex, map);
  }



  private static Node getNode(Node root, Node leftSubtree, Node midSubtree) {
    if (leftSubtree != null) {
      while (leftSubtree.right != null) {
        leftSubtree = leftSubtree.right;
      }
      leftSubtree.right = midSubtree;
      midSubtree.left = leftSubtree;
    }else{
      root.right = midSubtree;
      midSubtree.left = root;
      root.left =null;
    }
    return leftSubtree;
  }

  private static void convertToLinkedList_2(Node root, ArrayList<Node> nodes){
    if(root != null){
      nodes.add(root);
      convertToLinkedList_2(root.left,nodes);
      convertToLinkedList_2(root.mid, nodes);
      convertToLinkedList_2(root.right, nodes);
    }
  }

  private static void printLinkList(Node head){
    Node curr = head;
    while (curr != null){
      System.out.print(curr.key +"->");
      curr = curr.right;
    }
    System.out.println(curr);
  }

  private static void printDLLinkList(Node head){
    Node curr = head;
    Node tail = head;
    while (curr != null){
      tail = curr;
      System.out.print(curr.key +"->");
      curr = curr.right;
    }
    System.out.println(curr);

    // Lets traverse from tail.

    System.out.println("From tail  .......");
    curr = tail;
    while (curr != null){
      System.out.print(curr.key+"->");
      curr = curr.left;
    }
    System.out.println(curr);
  }


  private static boolean isDCLConnectedLeafNode(Node node){
    return (node != null && (node.left != null && node.left.right == node )
        && (node.right != null && node.right.left == node));
  }

  private static int heightWithDCLLeafNodes(Node root){
    if(root == null) return 0;
    if(isDCLConnectedLeafNode(root)) return 1; // If leaf node, then height is 1
    return 1 + Math.max(heightWithDCLLeafNodes(root.left) , heightWithDCLLeafNodes(root.right));
  }

  //min and max are the range, in which the key must fall, root has [-Infinity, +Infinity]
  //left child of root must have range [-Infinity, root.key]
  //Right child of root must have range [root.key, +Infinity]
  //Like this every level we down, we must adjust the range.
  private static boolean isBST(Node root, int min, int max  ){
    if(root == null) return true;
    if(root.key < min || root.key > max) return false;
    return isBST(root.left,min,root.key ) && isBST(root.right, root.key, max );
  }


  private static int sizeOfTreeNode(Node root){
    if(root == null) return 0;
    return 1+ sizeOfTreeNode(root.left) + sizeOfTreeNode(root.right);
  }

  private static int findSizeOfLargestBST(Node root){
    if(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)){
      return sizeOfTreeNode(root);
    }
    return Math.max(findSizeOfLargestBST(root.left), findSizeOfLargestBST(root.right));
  }

  private static int findMin(int[] arr, int start, int end){
    int min = Integer.MAX_VALUE;
    int minIndex = -1;
    for(int  i = start; i <= end; i++){
      if(arr[i] < min){
        min = arr[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  private static Node constructTree(int[] arr, int start, int end){
    if(start > end) return null;
    int minIndex = findMin(arr, start, end);
    Node root = new Node(arr[minIndex]);
    root.left = constructTree(arr, start, minIndex-1);
    root.right = constructTree(arr, minIndex +1, end);
    return root;
  }


  private static int getMIS(Node root, int sumTillNow){
    if(root == null) return 0;
    sumTillNow += root.key;
    int sumWithoutRoot = root.key + getMIS(root.left,sumTillNow) + getMIS(root.right,sumTillNow);
    sumTillNow -= root.key;
    int sumWithRoot = 0;
    if(root.left!= null){
      sumTillNow+= root.left.key;
      sumWithoutRoot += root.left.key;
      sumWithRoot += getMIS(root.left.left,sumTillNow) + getMIS(root.left.right,sumTillNow);
    }
    if(root.right !=null){
      sumTillNow+= root.right.key;
      sumWithoutRoot += root.right.key;
      sumWithRoot += getMIS(root.right.left,sumTillNow) + getMIS(root.right.right,sumTillNow);
    }
    if(sumWithoutRoot > sumWithRoot){
      if(root.left != null){
        sumTillNow -= root.left.key;
      }
      if(root.right != null){
        sumTillNow -= root.right.key;
      }
    }else{
      sumTillNow -= root.key;
    }
    System.out.println("SumTill now ="+ sumTillNow);
    return Math.max(sumWithoutRoot, sumWithRoot);
  }

  private static int getMaxIndependentSum(Node root, List<Node> skipList, List<Node> nodesTaken){
    if(root == null) return 0;
    int sumWithRoot = 0;
    int sumWithOutRoot = 0;
    if (root.left != null) skipList.add(root.left);
    if (root.right != null) skipList.add(root.right);
    if (!skipList.contains(root)) {
      nodesTaken.add(root);
      sumWithRoot = root.key;
      sumWithRoot += getMaxIndependentSum(root.left, skipList,nodesTaken);
      sumWithRoot += getMaxIndependentSum(root.right, skipList,nodesTaken);
    } else {
      nodesTaken.add(root.left);
      nodesTaken.add(root.right);
      if(root.left != null) sumWithOutRoot += root.left.key;
      if(root.right != null) sumWithOutRoot += root.right.key;
      sumWithOutRoot += getMaxIndependentSum(root.left, skipList, nodesTaken);
      sumWithOutRoot += getMaxIndependentSum(root.right, skipList,nodesTaken);
    }
    if(sumWithOutRoot > sumWithRoot){
      nodesTaken.remove(root);
    }else{
      nodesTaken.remove(root.left);
      nodesTaken.remove(root.right);
    }
    System.out.println("Nodes taken ="+ nodesTaken);
    return Math.max(sumWithOutRoot, sumWithRoot);
  }

  public static void preorder(Node root)
  {
    if (root == null) {
      return;
    }

    System.out.print(root.key + " ");
    preorder(root.left);
    preorder(root.right);
  }

  private static void bottomUp(Node root){
    if(root == null) return;
    bottomUp(root.left);
    bottomUp(root.right);
    System.out.print(root.key+",");
  }



  private static void printLeftBoundary(Node root){
    Node node = root;
    while (!isLeafNode(node)){ //Go until we find a leaf node
      System.out.print(node.key+",");
      // if a node which on left boundary and doesn't have a left child
      //then its right child will be on Tree's left boundary,
      //SO check if left is there then go left or else go right.
      node = node.left != null? node.left : node.right;
    }
    System.out.println(node.key); //leaf node, in some cases, you may need just left boundary excluding leaf node.
  }

  private static void printRightBoundary(Node root){
    Node node = root;
    while (!isLeafNode(node)){ //Go until we find a leaf node
      System.out.print(node.key+",");
      // if a node which on right boundary and doesn't have a right child
      //then its left child will be on Tree's right boundary,
      //SO check if right is there then go right or else go left.
      node = node.right != null? node.right : node.left;
    }
    System.out.println(node.key); //leaf node
  }

  private static void printLeafNodes(Node root){
    if(root == null) return;
    if(isLeafNode(root)){
      System.out.print(root.key+",");
    }
    printLeafNodes(root.left);
    printLeafNodes(root.right);
  }

  private static boolean eachNodeHasOneChild(Node root){
    if(root == null || isLeafNode(root)) return true;
    return
        hasOneChild(root) &&
        eachNodeHasOneChild(root.left) &&
        eachNodeHasOneChild(root.right);
  }

  private void postOrderFix(Node root){
    if(root == null) return;
    //We are at node. check if we need to fix any child based on nodes value.
    postOrderFix(root.left); // Fix the left subtree
    postOrderFix(root.right); // Fix the right subtree
    //After fix of left and right subtree, fix the node again based on left and right node.
  }

  private static int maxPathSum(Node root,List<Integer>keys, AtomicInteger max){
    if(root == null) return 0;
    keys.add(root.key);
    updatepathSum(root, keys, max);
    int pathSumInLeftSubtree = maxPathSum(root.left, keys, max);
    int pathSumInRightSubtree = maxPathSum(root.right, keys, max);
    keys.remove(keys.size() -1);
    return root.key + Math.max(pathSumInLeftSubtree, pathSumInRightSubtree);

  }

  private static void updatepathSum(Node root, List<Integer> keys, AtomicInteger max) {
    if(isLeafNode(root)){
      int pathSum = 0;
      for(Integer key: keys){
        pathSum += key;
      }
      System.out.println("found a path keys= "+ keys +"sum=" + pathSum);
      if(pathSum > max.get()){
        max.set(pathSum);
      }
    }
  }

  private static void convertToMirror(MNode root){
    if(root == null) return;
    List<MNode> child = root.child;
    Collections.reverse(child);
    for(MNode childNode: child){
      convertToMirror(childNode);
    }
  }

  public static void printMTree(MNode root)
  {
    // base case
    if (root == null) {
      return;
    }

    // print the current node
    System.out.print(root.key + " ");

    // recur for all children nodes from left to right
    for (MNode child: root.child) {
      printMTree(child);
    }
  }

  private static void print2dView(Node root, int level){
    if(root == null) return;
    print2dView(root.right, level +1);
    for(int i = 0; i< level;i++){
      System.out.print("    ");
    }
    System.out.println(root.key);
    print2dView(root.left, level+1);
  }

  private static boolean isLeafNode(Node node){
    return node != null && node.left == null && node.right == null;
  }

  private static boolean hasOneChild(Node node){
    boolean hasOneChild =  node != null &&
        ((node.left!= null && node.right == null) ||(node.left == null && node.right != null));
    System.out.println(node.key +" has one child ? "+ hasOneChild);
    return hasOneChild;
  }

  public static List<Node> generateBinaryTrees(int[] in, int start, int end)
  {
    // create an empty list to store the root of the constructed binary trees
    List<Node> trees = new ArrayList<>();

    // base case
    if (start > end)
    {
      trees.add(null);
      return trees;
    }

    // consider each element in the inorder sequence as the root
    for (int i = start; i <= end; i++)
    {
      // recursively find all possible left subtrees for root `i`
      List<Node> left_subtrees = generateBinaryTrees(in, start, i - 1);

      // recursively find all possible right subtrees for root `i`
      List<Node> right_subtrees = generateBinaryTrees(in, i + 1, end);

      // do for each combination of left and right subtrees
      for (Node l: left_subtrees)
      {
        for (Node r: right_subtrees)
        {
          // construct a binary tree with i'th element as the root and whose
          // left and right children point to `l` and `r`, respectively
          Node tree = new Node(in[i], l, r);

          // add this tree to the output list
          trees.add(tree);
        }
      }
    }

    return trees;
  }


  public static void getLevelSum(Node root, AtomicInteger oddLevel,AtomicInteger evenLevel,  int currLevel){

    if(root == null) return ;
    if(currLevel % 2 == 0){
      evenLevel.set(evenLevel.get() + root.key);
    }else{
      oddLevel.set(oddLevel.get() + root.key);
    }
    getLevelSum(root.left, oddLevel, evenLevel, currLevel + 1);
    getLevelSum(root.right, oddLevel, evenLevel, currLevel + 1);
  }

  public static int findMaxSumPath(Node root, AtomicInteger max_sum)
  {
    // base case: empty tree
    if (root == null) {
      return 0;
    }

    // find the maximum sum node-to-leaf path starting from the left child
    int left = findMaxSumPath(root.left, max_sum);

    // find the maximum sum node-to-leaf path starting from the right child
    int right = findMaxSumPath(root.right, max_sum);

    // it is important to return the maximum sum node-to-leaf path starting
    // from the current node

    // case 1: left child is null
    if (root.left == null) {
      return right + root.key;
    }

    // case 2: right child is null
    if (root.right == null) {
      return left + root.key;
    }

    // find the maximum sum path "through" the current node
    int cur_sum = left + right + root.key;

    // update the maximum sum path found so far (Note that maximum sum path
    // "excluding" the current node in the subtree rooted at the current node
    // is already updated as we are doing postorder traversal)

    max_sum.set(Math.max(cur_sum, max_sum.get()));

    // case 3: both left and right child exists
    return Math.max(left, right) + root.key;
  }



  private static Node createTree(int[] parents){
    Node root = null;
    Map<Integer,Node> nodeMap = new HashMap<>();
    for(int i = 0; i < parents.length; i++){
      nodeMap.put(i, new Node(i));
    }
    for(int i = 0; i < parents.length; i++){
      if(parents[i] == -1){
        root = nodeMap.get(i);
      }else{
        //Get the parent node for current Index.
        Node parentNode = nodeMap.get(parents[i]);
        Node currentNode = nodeMap.get(i);

        if(parentNode.left != null){
          parentNode.right = currentNode;
        }else{
          parentNode.left = currentNode;
        }
      }
    }
    return root;
  }

}
