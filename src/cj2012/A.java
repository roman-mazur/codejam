package cj2012;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class A {

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(new File("A-small-practice.in"));
    //		Scanner scanner = new Scanner(new File("test.txt"));
    //		Scanner scanner = new Scanner(new File("case.txt"));
    Writer out = new FileWriter("out");

    int count = scanner.nextInt();

    long start = System.currentTimeMillis();

    for (int i = 0; i < count; i++) {

      int n = scanner.nextInt();
      int[] d = new int[n + 1];
      int[] l = new int[n + 1];
      for (int j = 0; j < n; j++) {
        d[j] = scanner.nextInt();
        l[j] = scanner.nextInt();
      }
      d[n] = scanner.nextInt();
      l[n] = 0;

      //			if (i == 6) {
      //				Writer outCase = new FileWriter("case.txt");
      //				outCase.write("1\n" + n + "\n");
      //				for (int v = 0; v < n; v++) {
      //					outCase.write(d[v] + " " + l[v] + "\n");
      //				}
      //				outCase.write(d[n] + "\n");
      //				outCase.close();
      //				System.exit(0);
      //			}

      boolean decision = solve(d, l);

      out.write("Case #" + (i + 1) + ": " + (decision ? "YES" : "NO") + "\n");
      out.flush();
    }

    System.out.println(System.currentTimeMillis() - start);

    out.close();
    scanner.close();
  }

  private static boolean solve(final int[] d, final int[] l) {
    LinkedList<State> stack = new LinkedList<State>();
    stack.push(new State(0, 0, 0));

    boolean[] reached = new boolean[d.length];
    Arrays.fill(reached, false);

    while (!stack.isEmpty()) {
      State current = stack.poll();
      //System.out.println(current);
      if (current.v == d.length - 1) { return true; }

      for (int v = current.v + 1; v < d.length; v++) {
        if (reached[v]) { continue; }
        if (d[v] <= d[current.v] + Math.min(l[current.v], d[current.v] - current.x)) {
          reached[v] = true;
          stack.add(new State(Math.max(d[current.v], d[v] - l[v]), v, current.i + 1));
        }
      }
    }

    return false;
  }

  private static class State {
    int x, v, i;

    public State(final int x, final int v, int i) {
      this.x = x;
      this.v = v;
      this.i = i;
    }

    @Override
    public String toString() {
      String s = "";
      for (int c = 0; c < i; c++) {
        s += " ";
      }
      return s + "(" + x + "," + v + ")";
    }
  }

}
