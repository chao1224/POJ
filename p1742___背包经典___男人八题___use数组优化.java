//3629
//1417
//2352

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

public class p1742___背包经典___男人八题___use数组优化 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n, m, a[], num[], f[], use[], ans;
		while (true) {
			n = nextInt();
			m = nextInt();
			if (n == 0 && m == 0)
				break;
			a = new int[n + 1];
			num = new int[n + 1];
			for (int i = 1; i <= n; i++)
				a[i] = nextInt();
			for (int i = 1; i <= n; i++)
				num[i] = nextInt();
			f = new int[m + 1];
			f[0] = 1;
			ans = 0;

			use = new int[m + 1];
			for (int i = 1; i <= n; i++) {
				Arrays.fill(use, 0);
				for (int j = a[i]; j <= m; j++)
					if (f[j] == 0 && f[j - a[i]] == 1 && use[j - a[i]] < num[i]) {
						use[j] = use[j - a[i]] + 1;
						f[j] = 1;
						ans++;
					}
			}

			out.println(ans);

		}

		out.flush();
		out.close();
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
