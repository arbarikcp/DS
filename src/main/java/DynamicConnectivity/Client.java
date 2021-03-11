package DynamicConnectivity;

public class Client {

  public static void main(String[] args) throws Exception {
    //ConnectivityProvider qf = new QuickFind(10);
    //ConnectivityProvider qc = new QuickConnect(10);
    //QuickWeightedConnect qwc = new QuickWeightedConnect(10);
    QuickWeightedConnectWithPathHalving qwch = new QuickWeightedConnectWithPathHalving(10);

    //testSequenceConnect(qwc);
    randomTest(qwch);


  }


  private static void randomTest(ConnectivityProvider dc) {
    dc.connect(5, 10);
    dc.connect(3, 4);
    dc.connect(4, 9);
    dc.connect(8, 0);
    dc.connect(2, 3);
    dc.connect(5, 6);
    dc.connect(2, 9);
    dc.connect(5, 9);
    dc.connect(7, 3);
    dc.connect(4, 8);
    dc.connect(5, 6);
    dc.connect(0, 2);
    dc.connect(6, 1);

    System.out.println(dc.isConnected(1, 9));// true
    System.out.println(dc.isConnected(2, 10));//false
    System.out.println(dc.isConnected(1, 2));//true
    System.out.println(dc.isConnected(1, 6));//true
  }

  private static void testSequenceConnect(ConnectivityProvider provider) {
    for (int i = 0; i < 9; i++) {
      provider.connect(i, i + 1);
    }
    System.out.println(provider.isConnected(1, 9));// true
    System.out.println(provider.isConnected(2, 10));//false
    System.out.println(provider.isConnected(1, 2));//true
    System.out.println(provider.isConnected(1, 6));//true
  }
}
