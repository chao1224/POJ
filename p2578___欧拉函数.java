import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class p2578___欧拉函数 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;
	static int inf = 1 << 30, cap[][], flow[][], cost[][], vertex;

	public static void main(String[] args) throws IOException {
		int limit = 1000000;
		int prime[] = new int[limit + 1];
		double prime2[] = new double[limit + 1];
		for (int i = 1; i <= limit; i++)
			prime2[i] = i;

		for (int i = 2; i <= limit; i++) {
			if (prime[i] == 0) {
				for (int j = 2; j * i <= limit; j++) {
					prime[i * j] = 1;
					prime2[i * j] = (prime2[i * j] * (1.0 - 1.0 / i));
				}
			}
		}
		for (int i = 1; i <= limit; i++) {
			if (prime[i] == 0)
				prime2[i] = i - 1;
			prime2[i] += prime2[i - 1];
		}

		while (true) {
			n = nextInt();
			if (n == 0)
				break;
			out.println((long) prime2[n]);
		}

		out.flush();
		out.close();
	}

	static int pre[];

	// spfa判断负圈
	// 即计算某个点入队次数是否超过n
	static boolean spfa(int s, int t) {
		int n = s;
		int inf = Integer.MAX_VALUE;
		int dist[] = new int[n + 1];
		Arrays.fill(dist, inf);
		int visit[] = new int[n + 1];
		int queue[] = new int[n + 1];
		pre = new int[n + 1];
		int count[] = new int[n + 1];

		int begin = 0, end = 1, u;
		visit[s] = 1;
		dist[s] = 0;
		count[s]++;
		queue[begin] = s;
		while (begin != end) {
			u = queue[begin++];
			begin %= n;
			visit[u] = 0;
			for (int i = 0; i <= n; i++) {
				if (cap[u][i] > flow[u][i] && cost[u][i] < inf
						&& dist[i] > dist[u] + cost[u][i]) {
					dist[i] = dist[u] + cost[u][i];
					pre[i] = u;
					if (visit[i] == 0) {
						visit[i] = 1;
						count[i]++;
						if (count[i] >= 1 + n) {
							vertex = i;
							return true;
						}
						queue[end++] = i;
						end %= n;
					}
				}
			}
		}
		return false;
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

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}
