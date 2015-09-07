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

public class p3624___01背包___滚动数组实现 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();
		int[] w = new int[n + 1];
		int[] v = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			w[i] = nextInt();
			v[i] = nextInt();
		}
		int f[][] = new int[2][m + 1];
		int ans = 0;
		int cur = 0, pre = 1;
		for (int i = 1; i <= n; i++) {
			cur = cur ^ 1;
			pre = pre ^ 1;
			for (int j = 0; j <= m; j++) {
				if (j >= w[i])
					f[cur][j] = Math.max(f[pre][j], f[pre][j - w[i]] + v[i]);
				else
					f[cur][j]=f[pre][j];
				if (f[cur][j] > ans)
					ans = f[cur][j];
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
