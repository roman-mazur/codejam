package cj2012;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class AnoGraph {

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
			
			boolean decision = solve(d, l);
			
			out.write("Case #" + (i + 1) + ": " + (decision ? "YES" : "NO") + "\n");
			out.flush();
		}
		
		System.out.println(System.currentTimeMillis() - start);

		out.close();
		scanner.close();
	}
	
	private static boolean solve(final int[] d, final int[] l) {
		int[] res = new int[d.length];
		Arrays.fill(res, -1);

		for (int v = 0; v < d.length - 1; v++) {
			if (v > 0 && res[v] == -1) { return false; }
			for (int i = v + 1; i < d.length; i++) {
				if (res[i] != -1) { continue; }
				// can we reach?
				if (d[i] <= d[v] + Math.min(l[v], d[v] - (v == 0 ? 0 : d[res[v]]))) {
					if (i == d.length - 1) { return true; }
					res[i] = v;
				}
			}
		}
		
		return false;
	}
	
}
