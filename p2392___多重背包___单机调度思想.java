import java.io.*;
import java.util.*;

public class p2392___多重背包___单机调度思想 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, arr[], color[], dp[], max;

	static class node implements Comparable<node> {
		int h, a, c;

		node(int h, int a, int c) {
			this.h = h;
			this.a = a;
			this.c = c;
		}

		public int compareTo(node o) {
			if (this.a < o.a)
				return -1;
			else if (this.a > o.a)
				return 1;
			return 0;
		}

	}

	public static void main(String[] args) throws IOException {
		int t = nextInt();
		node list[] = new node[t];
		for (int i = 0; i < t; i++)
			list[i] = new node(nextInt(), nextInt(), nextInt());
		Arrays.sort(list);
		dp = new int[list[t - 1].a + 1];
		max = 0;
		dp[0] = 1;
		for (int i = 0; i < t; i++)
			MultiplePack(list[i].h, list[i].c, list[i].a);
		out.println(max);

		out.flush();
		out.close();
	}

	// 多重背包
	static void MultiplePack(int cost, int amount, int v) {
		if (cost * amount >= v) {
			CompletePack(cost, v);
		} else {
			int k = 1;
			while (k < amount) {
				ZeroPack(cost * k, v);
				amount -= k;
				k *= 2;
			}
			ZeroPack(cost * amount, v);
		}
	}

	// 完全背包
	static void CompletePack(int cost, int v) {
		for (int i = cost; i <= v; i++)
			if (dp[i] == 0 && dp[i - cost] == 1) {
				dp[i] = 1;
				max = Math.max(max, i);
			}
	}

	// 01背包
	static void ZeroPack(int cost, int v) {
		for (int i = v; i >= cost; i--)
			if (dp[i] == 0 && dp[i - cost] == 1) {
				dp[i] = 1;
				max = Math.max(max, i);
			}
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}