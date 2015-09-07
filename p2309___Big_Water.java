import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p2309___Big_Water {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, cnt, cap[], level[], head[], limit = 100000, change;

	public static void main(String[] args) throws IOException {
		int t = nextInt(), x, first, second, min, max, tmp;
		while (t-- > 0) {
			x = nextInt();
			min = 0;
			max = x;
			first = second = 0;
			first = lowbit(x);
			x -= first;
			second = lowbit(x);
			x -= second;
			min += first + second;
			while (x > 0) {
				tmp = lowbit(x);
				min += tmp;
				x -= tmp;
			}

			min -= first;

			out.println(min + 1 + " " + (max + first - 1));
		}

		out.flush();
		out.close();
	}

	static int lowbit(int x) {
		return x & -x;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
