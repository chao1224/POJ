import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2349___Prime___×îÐ¡µÚk±ß {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, p;

	public static void main(String[] args) throws IOException {
		int testcase = nextInt();
		double cell[][];
		for (int kkk = 1; kkk <= testcase; kkk++) {
			p = nextInt();
			n = nextInt();
			cell = new double[n + 1][2];

			for (int i = 1; i <= n; i++) {
				cell[i][0] = nextInt();
				cell[i][1] = nextInt();
			}

			Prime MST = new Prime(n, n * (n - 1));
			for (int i = 1; i <= n; i++) {
				for (int j = i + 1; j <= n; j++) {
					MST.addEdge(i, j, (Math.sqrt((cell[i][0] - cell[j][0])
							* (cell[i][0] - cell[j][0])
							+ (cell[i][1] - cell[j][1])
							* (cell[i][1] - cell[j][1]))));
					MST.addEdge(j, i, (Math.sqrt((cell[i][0] - cell[j][0])
							* (cell[i][0] - cell[j][0])
							+ (cell[i][1] - cell[j][1])
							* (cell[i][1] - cell[j][1]))));
				}
			}
			MST.solve(n);
		}
		out.flush();
		out.close();
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

	static class Prime {
		int n, m, index;
		int head[];
		node edge[];

		Prime(int n, int m) {
			this.n = n;
			this.m = m;
			index = 0;
			head = new int[n + 1];
			Arrays.fill(head, -1);
			edge = new node[m + 1];
		}

		void addEdge(int u, int v, double val) {
			index++;
			edge[index] = new node();
			edge[index].next = head[u];
			edge[index].v = v;
			edge[index].val = val;
			head[u] = index;
		}

		void solve(int v0) {
			double dist[] = new double[n + 1];
			Arrays.fill(dist, Double.MAX_VALUE);
			dist[v0] = 0;
			int used[] = new int[n + 1];
			double arr[] = new double[n];
			int index = 0;

			used[v0] = 1;
			for (int i = head[v0]; i != -1; i = edge[i].next)
				dist[edge[i].v] = edge[i].val;

			int u;
			double min;
			for (int time = 1; time < n; time++) {
				min = Double.MAX_VALUE;
				u = v0;

				for (int i = 1; i <= n; i++) {
					if (used[i] == 0 && dist[i] < min) {
						min = dist[i];
						u = i;
					}
				}

				dist[u] = min;
				used[u] = 1;
				arr[index++] = min;

				for (int i = head[u]; i != -1; i = edge[i].next) {
					if (used[edge[i].v] == 0 && dist[edge[i].v] > edge[i].val)
						dist[edge[i].v] = edge[i].val;
				}
			}
			Arrays.sort(arr);
			out.printf("%.2f", arr[n - p]);
			out.println();
		}
	}

	static class node {
		int u, v, next;
		double val;

		node() {
			this.next = -1;
		}
	}
}