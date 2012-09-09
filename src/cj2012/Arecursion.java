package cj2012;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;



public class Arecursion {

  // cannot solve large problem

  public static void main(final String[] args) throws Exception {

    final Scanner scanner = new Scanner(new FileReader("A-large-practice.in"));
    final Writer out = new FileWriter("out");

    final int t = scanner.nextInt();

    long start = System.currentTimeMillis();

    for (int i = 0; i < t; i++) {

      final int n = scanner.nextInt();
      final int[] d = new int[n];
      final int[] l = new int[n];
      for (int j = 0; j < n; j++) {
        d[j] = scanner.nextInt();
        l[j] = scanner.nextInt();
      }
      final int dst = scanner.nextInt();

      final boolean possible = swing(d, l, dst, 0, 0);

      final StringBuilder solution = new StringBuilder();
      solution.append(possible ? "YES" : "NO");
      out.write("Case #" + (i + 1) + ": " + solution + "\n");
    }

    System.out.println(System.currentTimeMillis() - start);

    out.close();
    scanner.close();

  }

  private static boolean swing(final int[] d, final int[] l, final int dst, final int i, final int pos) {
    final int len = d[i] - pos;
    if (len >= dst - d[i]) { return true; }
    final int count = l.length;
    for (int v = count - 1; v > i; v--) {
      if (len >= d[v] - d[i]) {
        int nextPos = d[i];
        if (l[v] < d[v] - d[i]) { nextPos = d[v] - l[v]; }
        if (swing(d, l, dst, v, nextPos)) { return true; }
      }
    }
    return false;
  }

}
