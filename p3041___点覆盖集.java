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

public class p3041___µã¸²¸Ç¼¯ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m, k;
	static int limit = 510;
	static int match[][], mark[], cx[], cy[], cnt;

	public static void main(String[] args) throws IOException {
		int a, b;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			k = nextInt();
			match = new int[n + 1][n + 1];

			while (k-- > 0) {
				a = nextInt();
				b = nextInt();
				match[a][b] = 1;
			}

			cx = new int[n + 1];
			Arrays.fill(cx, -1);
			cy = new int[n + 1];
			Arrays.fill(cy, -1);
			mark = new int[n + 1];

			int ans = 0;
			for (int i = 1; i <= n; i++) {
				if (cx[i] == -1) {
					Arrays.fill(mark, 0);
					ans += path(i);
				}
			}
			out.println(ans);
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