import java.util.ArrayList;
import java.util.List;

public class Subset {

  int[] arr;
  List<List<Integer>> subsets = new ArrayList<>();

  public Subset(int[] arr) {
    this.arr = arr;
    subsets.add(new ArrayList<>());
    createSubsets();
  }

  public List<List<Integer>> getSubsets(){
    return subsets;
  }

  private void createSubsets(){

    for(int num: arr){ // for each number
      int size = subsets.size();
      for(int i = 0; i < size; i++){
        subsets.add(createNewSubset(subsets.get(i),num));
      }
    }
  }

  private List<Integer> createNewSubset(List<Integer> existing, Integer num){
    List<Integer> subset = new ArrayList<>();
    subset.addAll(existing);
    subset.add(num);
    return subset;
  }

  public static void main(String[] args) {
    Subset st = new Subset(new int[]{1,3,5});
    System.out.println(st.getSubsets());
  }
}
