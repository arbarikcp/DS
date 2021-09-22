package Tree;

import java.util.Arrays;

public class BIT_Fenwick {

 int[] arr;
 int[] bit;
 int size;

  public BIT_Fenwick(int arr[]) {

    this.arr = arr;
    this.size = arr.length;
    bit = new int[size];
    int[] temp = arr.clone();
    //construct_1(temp);
    construct_2(temp);

  }

  // Better approach: Construct Binary index tree.  Time complexity is O(n)

  private void construct_1(int[] temp){
    bit = Arrays.copyOf(temp, temp.length); //Copy initial elements
    for(int i = 1 ; i < size ; i++){ //Traverse once
      int j =  i + LSB(i); // J is immediate parent of i, it means bit[j] 's responsibility includes i.
      if(j < size){
        bit[j] += bit[i]; // Add each bit element to its immidiate parent.
      }
    }
  }

  //Construct Binary index tree.  Time complexity is O(nLogn)
  //Every element adds its value to all the bit element who are responsible for it.
  private void construct_2(int[] temp){
    int n = temp.length;
    for(int i = 1; i < n; i++){
      for(int j = i; j < n; j = j +  LSB(j)){ // j is parent of i, We need to add bit[i] to all parents of i.
        bit[j] = bit[j] + arr[i]; // add arr[i] to bit[j] and store it to bit[j]
      }
    }
  }

  private void update(int pos, int value){
    int delta = value - arr[pos];
    arr[pos] = value;
    for(int i =  pos; i < size ; i = i + LSB(i)){
      bit[i] = bit[i] + delta;
    }

  }

  private int rangeSum(int i, int j){
    if( i > j) return -45566576; // wrong range
    return prefixSum(j) - prefixSum(i-1);
  }

  private int prefixSum(int x){
    int sum = 0;
    for(int i = x; i > 0; i = i - LSB(i)){
      sum += bit[i];
    }
    return sum;
  }


  private int LSB(int x){
    return x & -x; // Same as x & ~(x-1)
  }

  private int LSB_(int x) { return  Integer.lowestOneBit(x);} // This also does the sam thing

  public static void main(String[] args) {
// Fenwick tree is a 1 based array, So we must provide a array having a dummy [0] value.
// If we want our client to use a 0 based index then we need to adjust all methods .
    int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
    BIT_Fenwick bit_fenwick = new BIT_Fenwick(arr);
    bit_fenwick.rangeSum(1,4);
    bit_fenwick.update(3,13 );

  }
}
