package cj2012;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;



public class B {

  /**
   * @param args
   */
  public static void main(final String[] args) throws Exception {

    final Scanner scanner = new Scanner(new FileReader("B-large-practice.in"));
    final Writer out = new FileWriter("out");

    final int t = scanner.nextInt();

    DecimalFormat format = new DecimalFormat("#.######", new DecimalFormatSymbols(Locale.US));
    
    final Random r = new Random();

    for (int caseN = 0; caseN < t; caseN++) {

      final int n = scanner.nextInt();
      final int w = scanner.nextInt();
      final int l = scanner.nextInt();

      final Pair[] radius = new Pair[n];
      for (int j = 0; j < n; j++) {
        radius[j] = new Pair(j, scanner.nextInt());
      }

      Arrays.sort(radius, new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
          return o2.radius - o1.radius;
        }
      });

      final Coord[] positions = new Coord[n];

      for (int i = 0; i < n; i++) {
        boolean set = false;
        while (!set) {
          positions[i] = new Coord(i, r.nextDouble() * w, r.nextDouble() * l);
          set = true;
          // check
          for (int j = 0; j < i; j++) {
            final double dx = positions[i].x - positions[j].x;
            final double dy = positions[i].y - positions[j].y;
            if (Math.sqrt(dx * dx + dy * dy) < radius[i].radius + radius[j].radius) {
              set = false;
              break;
            }
          }
        }
      }

      Arrays.sort(positions, new Comparator<Coord>() {
        @Override
        public int compare(Coord o1, Coord o2) {
          return radius[o1.order].order - radius[o2.order].order;
        }
      });
      
      final StringBuilder solution = new StringBuilder();
      for (int i = 0; i < positions.length; i++) {
        solution.append(' ').append(format.format(positions[i].x))
                .append(' ').append(format.format(positions[i].y));
      }

      out.write("Case #" + (caseN + 1) + ":" + solution + "\n");
    }

    out.close();
    scanner.close();
  }

  private static class Pair {
    int order, radius;
    public Pair(final int order, final int radius) {
      this.order = order;
      this.radius = radius;
    }
  }

  private static class Coord {
    int order;
    double x, y;
    public Coord(final int order, final double x, final double y) {
      this.order = order;
      this.x = x;
      this.y = y;
    }
  }

}
