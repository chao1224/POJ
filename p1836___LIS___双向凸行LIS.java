import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1836___LIS___Ë«ÏòÍ¹ÐÐLIS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n, up[], down[], a, b, temp, max;
		double[] arr;
		n = nextInt();
		arr = new double[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = nextDouble();

		up = new int[n + 1];
		a = 0;
		for (int i = 1; i <= n; i++) {
			temp = 0;
			for (int j = 1; j < i; j++) {
				if (arr[i] > arr[j] && temp < up[j])
					temp = up[j];
			}
			up[i] = temp + 1;
			if (up[i] > a)
				a = up[i];
		}

		down = new int[n + 1];
		b = 0;
		for (int i = n; i >= 1; i--) {
			temp = 0;
			for (int j = n; j > i; j--) {
				if (arr[i] > arr[j] && temp < down[j])
					temp = down[j];
			}
			down[i] = temp + 1;
			if (down[i] > b)
				b = down[i];
		}

		max = Math.max(a, b);

		for (int i = 1; i < n; i++) {
			for (int j = 1 + i; j <= n; j++) {
				if (up[i] + down[j] > max)
					max = up[i] + down[j];
			}
		}

		out.println(n - max);

		out.flush();
		out.close();
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

}

class p1836___rectangle implements Comparable<p1836___rectangle> {
	int x1, y1, x2, y2;

	p1836___rectangle(int a, int b, int c, int d) {
		this.x1 = a;
		this.y1 = b;
		this.x2 = c;
		this.y2 = d;
	}

	public int compareTo(p1836___rectangle o) {
		return 0;
	}
}
