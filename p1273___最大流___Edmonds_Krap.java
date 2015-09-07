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

public class p1273___×î´óÁ÷___Edmonds_Krap {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, cap[][], flow[][];

	public static void main(String[] args) throws IOException {
		int x, y, val;
		while (in.nextToken() != in.TT_EOF) {
			m = (int) in.nval;
			n = nextInt();
			cap = new int[n + 1][n + 1];
			flow = new int[n + 1][n + 1];
			for (int i = 1; i <= m; i++) {
				x = nextInt();
				y = nextInt();
				val = nextInt();
				cap[x][y] += val;
			}
			out.println(Ford_Fulkserson());
		}

		out.flush();
		out.close();
	}

	static int pre[], tempFlow[], queue[];

	static int Ford_Fulkserson() {
		pre = new int[n + 1];
		queue = new int[n + 1];
		tempFlow = new int[n + 1];
		int begin, end, cur = 0;
		while (true) {
			tempFlow[1] = 10000010;
			Arrays.fill(pre, -1);
			begin = 1;
			queue[begin] = 1;
			end = 2;
			while (begin < end && pre[n] == -1) {
				cur = queue[begin];
				begin++;
				for (int i = 2; i <= n; i++) {
					if (cap[cur][i] - flow[cur][i] > 0 && pre[i] == -1) {
						pre[i] = cur;
						tempFlow[i] = Math.min(cap[cur][i] - flow[cur][i],
								tempFlow[cur]);
						queue[end] = i;
						end++;
					}
				}
			}
			if (pre[n] == -1) {
				break;
			}
			for (int i = n; i != 1; i = pre[i]) {
				cur = pre[i];
				flow[cur][i] += tempFlow[n];
				flow[i][cur] -= tempFlow[n];
			}
		}
		int ans = 0;
		for (int i = 1; i <= n; i++)
			ans += flow[1][i];
		return ans;
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
