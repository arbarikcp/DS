package Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoggleBoard {

  Character[][] board = null;
  int row;
  int column;
  int dr[] = new int[] {-1, -1, -1, 0, 1, 1, 1, 0};
  int dc[] = new int[] {-1, 0, 1, 1, 1, 0, -1, -1};
  Trie trie = new Trie();
  Set<Character> initialCharSet;

  public BoggleBoard(int row, int column, Set<String> words,  Character[][] board) {
    this.board = board;
    this.row = row;
    this.column = column;
    for (String word : words) {
      trie.put(word);
    }
    initialCharSet = trie.head.children.keySet();
  }

  public void traverse() {
    for (int i = 0; i < row; i++) {
      for(int  j = 0; j < column; j++ ){
        boolean[][] visited = new boolean[row][column];
        List<String> wordsStartingFromChar = new ArrayList<>();
        findWord(i, j,new StringBuilder(), visited, wordsStartingFromChar );
        System.out.println(wordsStartingFromChar);
      }
    }
  }

  public void findWord(
      int sr, int sc, StringBuilder builder, boolean[][] visited, List<String> validWords) {

    if (!visited[sr][sc]) {
      visited[sr][sc] = true;
      builder.append(board[sr][sc]);
      System.out.println("searching   "+ builder.toString());
      if (trie.search(builder.toString())) {
        validWords.add(builder.toString());
      } else {
        List<String> prefixedString = trie.prefixSearch(builder.toString());
        if (prefixedString != null || !prefixedString.isEmpty()) {
          for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
              if (isValidRow(sr + dr[i]) && isValidColumn(sc + dc[j])) {
                findWord(sr + dr[i], sc + dc[j], builder, visited, validWords);
              }
            }
          }
        }
      }
      if(builder.length() > 0){
        builder.deleteCharAt(builder.length() -1);
      }
    }
  }

  private boolean isValidRow(int r){
    return r >= 0  && r < row;
  }

  private boolean isValidColumn(int c){
    return c >= 0  && c < column;
  }

  public static void main(String[] args) {
    Character[][] board =
        {
            {'M', 'S', 'E', 'F'},
            {'R', 'A', 'T', 'D'},
            {'L', 'O', 'N', 'E'},
            {'K', 'A', 'F', 'B'}
        };

    Set<String> words = Stream.of("START", "NOTE", "SAND", "STONED")
        .collect(Collectors.toSet());
    BoggleBoard boggleBoard = new BoggleBoard(4,4,words,board);
    boggleBoard.traverse();

  }
}
