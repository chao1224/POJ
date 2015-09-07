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

public class p1717___01背包___经典背包转换 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int[] d = new int[n + 1];
		for (int i = 1; i <= n; i++)
			d[i] = nextInt() - nextInt();

		int mid = 6 * n;
		int limit = -1;
		int f[][] = new int[2][12 * n + 1];
		Arrays.fill(f[0], limit);
		Arrays.fill(f[1], limit);
		int cur = 1, pre = 0;
		f[1][mid + d[1]] = 0;
		f[1][mid - d[1]] = 1;

		for (int i = 2; i <= n; i++) {
			cur = cur ^ 1;
			pre = pre ^ 1;
			Arrays.fill(f[cur], limit);
			for (int j = 0; j <= 12 * n; j++) {
				if (f[pre][j] != limit) {
					if (f[cur][j + d[i]] == limit)
						f[cur][j + d[i]] = f[pre][j];
					else
						f[cur][j + d[i]] = Math
								.min(f[pre][j], f[cur][j + d[i]]);

					if (f[cur][j - d[i]] == limit)
						f[cur][j - d[i]] = f[pre][j] + 1;
					else
						f[cur][j - d[i]] = Math.min(f[pre][j] + 1, f[cur][j
								- d[i]]);
				}
			}
		}
		int min = 12 * n, ans = n;
		for (int i = mid; i <= 12 * n; i++) {
			if (f[cur][i] != limit) {
				if (min > i - mid) {
					min = i - mid;
					ans = f[cur][i];
				} else if (min == i - mid)
					ans = Math.min(ans, f[cur][i]);
				break;
			}
		}
		for (int i = mid; i >= 0; i--) {
			if (f[cur][i] != limit) {
				if (min > mid - i) {
					min = mid - i;
					ans = f[cur][i];
				} else if (min == mid - i)
					ans = Math.min(ans, f[cur][i]);
				break;
			}
		}
		out.println(ans);
		out.flush();
		out.close();
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
