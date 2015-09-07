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
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p3501___BFS___终于他妈给我A了 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static int n, X, Y, visit[][], ans, limit, a, b, cnt, s1, s2, dis[][],
			mat[][], beginX, beginY, endX, endY;
	static p3501___node enemy[], cur, pre;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int[] dirx, diry;
	static LinkedList<p3501___node> list;
	static Queue<Integer> queue;
	static String result;

	public static void main(String[] args) throws IOException {
		int t = nextInt();
		dirx = new int[] { 1, 0, -1, 0 };
		diry = new int[] { 0, -1, 0, 1 };
		list = new LinkedList<p3501___node>();
		while (t-- > 0) {
			n = nextInt();
			X = nextInt();
			Y = nextInt();
			beginX = nextInt();
			beginY = nextInt();
			endX = nextInt();
			endY = nextInt();

			enemy = new p3501___node[n];
			for (int i = 0; i < n; i++) {
				a = nextInt();
				b = nextInt();
				enemy[i] = new p3501___node(a, b);
			}

			dis = new int[X][Y];
			preWork();
			mat = new int[X][Y];
			limit = Math.min(dis[beginX][beginY], dis[endX][endY]);
			int left = 0, right = limit + 1, middle;
			while (left < right) {
				middle = (left + right + 1) >> 1;
				if (bfs(middle)) {
					result = middle + " " + mat[endX][endY];
					left = middle;
				} else {
					right = middle - 1;
				}
			}

			bfs(left);
			out.println(left + " " + mat[endX][endY]);

		}
		out.flush();
		out.close();
	}

	static void preWork() {
		list.clear();
		for (int i = 0; i < X; i++)
			for (int j = 0; j < Y; j++)
				dis[i][j] = -1;

		for (int i = 0; i < n; i++) {
			a = enemy[i].x;
			b = enemy[i].y;
			cur = new p3501___node(a, b);
			list.add(cur);
			dis[a][b] = 0;
		}

		while (!list.isEmpty()) {
			pre = list.poll();
			for (int k = 0; k < 4; k++) {
				a = pre.x + dirx[k];
				b = pre.y + diry[k];
				if (legal(a, b) && dis[a][b] == -1) {
					dis[a][b] = dis[pre.x][pre.y] + 1;
					cur = new p3501___node(a, b);
					list.add(cur);
				}
			}
		}

	}

	static boolean legal(int x, int y) {
		if (x < 0 || x >= X || y < 0 | y >= Y)
			return false;
		return true;
	}

	static boolean bfs(int max) {
		ans = 0;
		for (int i = 0; i < X; i++)
			Arrays.fill(mat[i], -1);

		// WA了n次，就是少了这个特判
		if (dis[beginX][beginY] < max || dis[endX][endY] < max)
			return false;

		list.clear();
		list.add(new p3501___node(beginX, beginY));
		mat[beginX][beginY] = 0;
		while (!list.isEmpty()) {
			pre = list.poll();

			for (int k = 0; k < 4; k++) {
				a = pre.x + dirx[k];
				b = pre.y + diry[k];
				if (legal(a, b) && mat[a][b] == -1 && dis[a][b] >= max) {
					mat[a][b] = mat[pre.x][pre.y] + 1;
					if (a == endX && b == endY) {
						ans = mat[a][b];
						return true;
					}
					cur = new p3501___node(a, b);
					list.add(cur);
				}
			}
		}
		return false;
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

class p3501___node {
	int x, y;

	p3501___node(int a, int b) {
		this.x = a;
		this.y = b;
	}
}
