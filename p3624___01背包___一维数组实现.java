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

public class p3624___01背包___一维数组实现 {
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
		int f[] = new int[m + 1];
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = m; j >= w[i]; j--) {
				f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
				if (f[j] > ans)
					ans = f[j];
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
