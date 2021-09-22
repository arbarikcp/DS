package Tree.BST;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeTree {

   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

  TreeNode root;
  TreeNode target = new TreeNode();

  public LeetCodeTree(Integer[] elements) {
    root = buildTree(elements, 0);
  }

  private TreeNode buildTree(Integer[] elements, int pos){
    if (pos < elements.length && elements[pos] != null) {
      int left = 2 * pos + 1;
      int right = 2 * pos + 2;
      TreeNode node =
          new TreeNode(elements[pos], buildTree(elements, left), buildTree(elements, right));
      return node;
      } else{
      return null;
    }
  }

  public void flatten(){

    TreeNode target = new TreeNode();
    flattenTree(root, target);
    //root = target;
    root.val = target.val;
    root.right = target.right;
    TreeNode curr = root;
    ArrayList<Integer> list = new ArrayList<>();
    while (curr != null){
      list.add(curr.val);
      if(curr.left == null) list.add(null);
      curr = curr.right;
    }
    System.out.println(list);
  }

  private TreeNode flattenTree(TreeNode curr, TreeNode lst) {
    if (curr != null) {
      lst.val = curr.val;
      if (curr.left != null) {
        lst.right = new TreeNode();
        lst = flattenTree(curr.left, lst.right);
      }
      if (curr.right != null) {
        lst.right = new TreeNode();
        lst = flattenTree(curr.right, lst.right);
      }
    }
    return lst;
  }

  private void getAllPaths(){
    getAllPathSum(root, new ArrayList<>());
  }



  public void getPaths(int targetSum){

    List<List<Integer>> result = new ArrayList<>();
    getPaths(root, new ArrayList<>(), result, 0, targetSum);
    System.out.println(result);
  }


  private void getPaths(TreeNode curr,ArrayList<Integer> keys,
      List<List<Integer>> result, int pathSum , int targetSum){
    if (curr != null) {
      keys.add(keys.size(), curr.val);
      pathSum = pathSum + curr.val;
      if (curr.left == null && curr.right == null && pathSum == targetSum) {
        result.add((ArrayList) keys.clone());
      } else {
        if (curr.left != null) getPaths(curr.left, keys, result, pathSum, targetSum);
        if (curr.right != null) getPaths(curr.right, keys, result, pathSum, targetSum);
      }
      keys.remove(keys.size() - 1);
      pathSum -= curr.val;
    }
  }

  private void getAllPathSum(TreeNode current, List<Integer> keys){
    keys.add(keys.size(), current.val);
    if(current.left == null && current.right == null){
      System.out.println("path ="+ keys);
    } else {
      if (current.left != null) getAllPathSum(current.left, keys);
      if (current.right != null) getAllPathSum(current.right, keys);
    }
    keys.remove(keys.size() -1 );
  }

  public static void main(String[] args) {
/*    //Integer[] nodes = {5,4,8,11,null,13,4,7,2,null,null, null,null,5,1};
    Integer[] nodes = {-2, null, -3};
    TreePathSum tp =  new TreePathSum(nodes);
    tp.getAllPaths();
    tp.getPaths(-5);
    System.out.println("completed");*/


    Integer[] nodes = {1,2,5,3,4,null,6};
    LeetCodeTree tn = new LeetCodeTree(nodes);
    tn.flatten();

  }
}
