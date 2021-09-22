package Graph.UG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DungeonPath {

  char[][] dungeon;
  int R,C; // Row count and Column count of the dungeon matrix

  int startRow;
  int startColumn;

  int endRow;
  int endColumn;

  boolean pathFound = false;
  boolean[][] visited;
  cell[][] pathTo;

  Queue<Integer> rowQueue = new LinkedList<>();
  Queue<Integer> columnQueue = new LinkedList<>();
  int nodeCountInCurrentLevel = 0;
  int nodeCountInNextLevel = 0;
  int moveCount = 0;

  //direction vector
  int[] dr = new int[]{-1,1,0,0};
  int[] dc = new int[]{0,0,-1,1};

  public DungeonPath(int startRow, int startColumn, char[][] dungeon, boolean isDfs) {
    this.startRow = startRow;
    this.startColumn = startColumn;
    this.dungeon = dungeon;
    R = dungeon.length;
    C = dungeon[0].length;
    this.visited = new boolean[R][C];
    this.pathTo = new cell[R][C];
    // bfs();
    if (isDfs) {
      dfs(startRow, startColumn);
    }else{
      bfs();
    }
  }

  public void bfs(){
    visited[startRow][startColumn] = true;
    rowQueue.add(startRow);
    columnQueue.add(startColumn);
    nodeCountInCurrentLevel = 1;
    while (!rowQueue.isEmpty()){
      int currentRow = rowQueue.poll();
      nodeCountInCurrentLevel--;
      int currentColumn = columnQueue.poll();
      //Found the exit
      if(dungeon[currentRow][currentColumn] == 'E'){
        pathFound = true;
        endRow = currentRow;
        endColumn = currentColumn;
        break;
      }
      //explore neighbor Cells
      exploreNeigbors(currentRow, currentColumn);

      //Simple level count
      if(nodeCountInCurrentLevel == 0){
        nodeCountInCurrentLevel = nodeCountInNextLevel;
        nodeCountInNextLevel = 0;
        moveCount++;
        //System.out.println("Traversing next level count="+ nodeCountInCurrentLevel);
      }
    }
  }

  private void exploreNeigbors(int currentRow, int currentColumn) {
    for(int i = 0; i < 4 ; i++){
      int nextRow = currentRow + dr[i];
      int nextColumn = currentColumn + dc[i];
      //validate nextRow and nextColumn
      if(nextRow < 0 || nextRow >= R) continue;
      if(nextColumn < 0 || nextColumn >= C) continue;
      //check if [nextRow][nextColumn] a invalid position to move
      if(dungeon[nextRow][nextColumn] == '#') continue;
      //check if we have already traversed [nextRow][nextColumn]
      if(visited[nextRow][nextColumn]) continue;
      rowQueue.add(nextRow);
      columnQueue.add(nextColumn);
      visited[nextRow][nextColumn] = true;
      nodeCountInNextLevel++;
      pathTo[nextRow][nextColumn] = new cell(currentRow, currentColumn);
    }
  }

  public boolean doesPathExists(){
    return pathFound;
  }

  public int getMoveCount(){
    return moveCount;
  }

  public List<cell> getPath(){
    List<cell> path = new ArrayList<>();
    path.add(new cell(endRow,endColumn));
    for(cell at = pathTo[endRow][endColumn]; at != null; at = pathTo[at.getRow()][at.getColumn()] ){
      path.add(at);
    }
    Collections.reverse(path);
    if(path.get(0) != null && path.get(0).getRow() == startRow && path.get(0).getColumn() == startColumn){
      return path;
    }
    return null;
  }


  public void dfs(int sr, int sc){
    visited[sr][sc] = true;
    moveCount++;
    for(cell c : getAllValidNeighbors(sr, sc)){
      int nRow = c.getRow();
      int nColumn = c.getColumn();
      if(!visited[nRow][nColumn]){
        //Found the exit
        if(dungeon[nRow][nColumn] == 'E'){
          pathTo[nRow][nColumn] = new cell(sr,sc);
          pathFound = true;
          endRow = nRow;
          endColumn = nColumn;
          System.out.println("found the path at"+ nRow +","+ nColumn);
        } else {
          pathTo[nRow][nColumn] = new cell(sr,sc);
          dfs(nRow, nColumn); // explore neighbor
        }
      }
    }
  }

  private List<cell> getAllValidNeighbors(int currentRow, int currentColumn){
    List<cell> cells = new ArrayList<>();
    for(int i = 0; i < 4 ; i++){
      int nextRow = currentRow + dr[i];
      int nextColumn = currentColumn + dc[i];
      //validate nextRow and nextColumn
      if(nextRow < 0 || nextRow >= R) continue;
      if(nextColumn < 0 || nextColumn >= C) continue;
      //check if [nextRow][nextColumn] a invalid position to move
      if(dungeon[nextRow][nextColumn] == '#') continue;
      //check if we have already traversed [nextRow][nextColumn]
      cells.add(new cell(nextRow, nextColumn));
    }
    return cells;
  }

  class cell{
    int row,column;

    public cell(int row, int column) {
      this.row = row;
      this.column = column;
    }

    public int getRow(){ return row;}
    public int getColumn(){return column;}

    public String toString(){
      return row+","+column;
    }
  }

  public static void main(String[] args) {

    char y = '.';
    char n = '#';
    char E = 'E';
    char[][] dungeon = {
        {y,y,E,n,y,y,y},
        {y,n,y,y,y,n,y},
        {y,n,y,y,y,y,n},
        {y,n,n,n,n,y,y},
        {n,y,n,n,y,n,y}};

    int startRow = 0;
    int startColumn = 0;

    DungeonPath dp_dfs =  new DungeonPath(startRow, startColumn, dungeon, true);
    System.out.println("Is path exists ="+ dp_dfs.doesPathExists());
    System.out.println("path is "+ dp_dfs.getPath());
    System.out.println("Move count="+ dp_dfs.getMoveCount());
    System.out.println("************************************");

    DungeonPath dp_bfs =  new DungeonPath(startRow, startColumn, dungeon, false);
    System.out.println("Is path exists ="+ dp_dfs.doesPathExists());
    System.out.println("path is "+ dp_dfs.getPath());
    System.out.println("Move count="+ dp_dfs.getMoveCount());

  }
}
