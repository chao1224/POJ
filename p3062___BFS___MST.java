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

public class p3062___BFS___MST {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;
	static int index, translate[][] = new int[51][51], diri[] = new int[] { 0,
			0, 1, -1 }, dirj[] = new int[] { 1, -1, 0, 0 }, i, j;
	static int arr[][], val[][] = new int[51][51];

	public static void main(String[] args) throws IOException {
		int test = Integer.parseInt(br.readLine()), x, y;
		String str;
		LinkedList<pair> queue = new LinkedList<pair>();
		for (int kkk = 1; kkk <= test; kkk++) {
			index = 0;
			// x = nextInt();
			// y = nextInt();
			str = br.readLine();
			StringTokenizer stoke = new StringTokenizer(str);
			x = Integer.parseInt(stoke.nextToken());
			y = Integer.parseInt(stoke.nextToken());
			arr = new int[y + 1][x + 1];
			for (i = 1; i <= y; i++) {
				str = br.readLine();
				for (j = 0; j < x; j++) {
					if (str.charAt(j) == 'A') {
						arr[i][j + 1] = 1;
						translate[i][j + 1] = ++index;
						queue.add(new pair(i, j + 1));
					} else if (str.charAt(j) == 'S') {
						arr[i][j + 1] = 1;
						translate[i][j + 1] = ++index;
						queue.add(new pair(i, j + 1));
					} else if (str.charAt(j) == '#') {
						arr[i][j + 1] = -1;
					} else {
						arr[i][j + 1] = 0;
					}
				}
			}

			Prime MST = new Prime(index);
			pair temp;
			LinkedList<pair> list = new LinkedList<pair>();
			int xx, yy;
			while (!queue.isEmpty()) {
				temp = queue.poll();
				xx = temp.i;
				yy = temp.j;
				// bfs,start from(xx,yy)
				{
					for (int u = 1; u <= y; u++)
						Arrays.fill(val[u], -1);
					val[xx][yy] = 0;
					list.add(temp);
					while (!list.isEmpty()) {
						temp = list.poll();
						for (int c = 0; c <= 3; c++) {
							i = temp.i + diri[c];
							j = temp.j + dirj[c];

							if (i < 1 || i > y || j < 1 || j > x
									|| val[i][j] != -1 || arr[i][j] == -1)
								continue;
							if (arr[i][j] == 0) {
								val[i][j] = val[temp.i][temp.j] + 1;
								list.add(new pair(i, j));
							} else if (arr[i][j] == 1) {
								val[i][j] = val[temp.i][temp.j] + 1;
								list.add(new pair(i, j));
								MST.addEdge(translate[xx][yy], translate[i][j],
										val[i][j]);
								MST.addEdge(translate[i][j], translate[xx][yy],
										val[i][j]);
								if (kkk > 1) {
									// System.out.println("start from : " + xx
									// + " " + yy + " to " + i + " " + j);
									// System.out.println(translate[xx][yy]
									// + " -- " + translate[i][j] + "  "
									// + val[i][j]);
								}
							}
						}
					}
				}
			}

			out.println(MST.solve(1));
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

	static class pair {
		int i, j;

		pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static class Prime {
		int n, adj[][];

		Prime(int n) {
			this.n = n;
			adj = new int[n + 1][n + 1];
		}

		void addEdge(int u, int v, int val) {
			adj[u][v] = val;
		}

		int solve(int v0) {
			int dist[] = new int[n + 1];
			int used[] = new int[n + 1];

			used[v0] = 1;
			for (int i = 1; i <= n; i++)
				dist[i] = adj[v0][i];
			int min, u, sum = 0;
			for (int time = 1; time < n; time++) {
				min = Integer.MAX_VALUE;
				u = v0;
				for (int i = 1; i <= n; i++) {
					if (used[i] == 0 && dist[i] < min) {
						min = dist[i];
						u = i;
					}
				}

				sum += min;
				dist[u] = min;
				used[u] = 1;
				for (int i = 1; i <= n; i++) {
					if (used[i] == 0 && dist[i] > adj[u][i])
						dist[i] = adj[u][i];
				}
			}
			return sum;
		}
	}
}