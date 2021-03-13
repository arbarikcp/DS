package DynamicConnectivity;

public interface ConnectivityProvider {
  void connect(int src, int dest);
  boolean isConnected(int src, int dest);

  default boolean isValid(int size, int src, int dest) {
    return src >= 0 && src < size && dest >= 0 && dest < size;
  }
}
