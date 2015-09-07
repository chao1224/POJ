import java.awt.Stroke;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class p1469___Hungary {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, p;

	static int match[][], cx[], cy[], mark[];

	public static void main(String[] args) throws IOException {
		int test = nextInt(), count, person;
		for (int ttt = 1; ttt <= test; ttt++) {
			p = nextInt();
			n = nextInt();

			match = new int[p + 1][n + 1];
			for (int i = 1; i <= p; i++) {
				count = nextInt();
				while (count-- > 0) {
					person = nextInt();
					match[i][person] = 1;
				}
			}

			cx = new int[p + 1];
			Arrays.fill(cx, -1);
			cy = new int[n + 1];
			Arrays.fill(cy, -1);
			mark = new int[n + 1];
			int ans = 0;
			for (int i = 1; i <= p; i++) {
				if (cx[i] == -1) {
					Arrays.fill(mark, 0);
					ans += path(i);
				}
			}
			if (ans < p)
				out.println("NO");
			else
				out.println("YES");
		}

		out.flush();
		out.close();
	}

	static int path(int u) {
		for (int i = 1; i <= n; i++) {
			if (match[u][i] > 0 && mark[i] == 0) {
				mark[i] = 1;
				if (cy[i] == -1 || path(cy[i]) == 1) {
					cx[u] = i;
					cy[i] = u;
					return 1;
				}
			}
		}
		return 0;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}