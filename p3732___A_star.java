import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class p3732___A_star {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	// 启发函数就是与0颜色不同的格子个数

	static int n, m;
	static int N = 10;
	static int current[][] = new int[N][N];
	static int record[][] = new int[N][N];
	static String goal;
	static int dir[][] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static boolean visColor[] = new boolean[35];
	static boolean vis[][] = new boolean[N][N];

	static HashMap<String, Integer> hash = new HashMap<String, Integer>();
	static PriorityQueue<node> queue = new PriorityQueue<node>();

	static class node implements Comparable<node> {
		String state;
		int g, h;
		node pre;
		String str;

		node(String s, int a, int b) {
			state = s;
			h = a;
			g = b;
			pre = null;
			str = "";
		}

		public int compareTo(node o) {
			return this.g + this.h - o.g - o.h;
		}
	}

	static String convertToState(int[][] record) {
		String str = "";
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (record[i][j] < 10)
					str = str + "0" + record[i][j];
				else
					str = str + record[i][j];
		return str;
	}

	static int getH(int[][] record) {
		Arrays.fill(visColor, false);
		int sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				visColor[record[i][j]] = true;
		for (int i = 1; i < 30; i++)
			if (visColor[i])
				sum++;
		return sum;
	}

	static void init() {
		queue.clear();
		hash.clear();
	}

	static boolean valid(int a, int b) {
		if (a >= 0 && a < n && b >= 0 && b < m)
			return true;
		return false;
	}

	static void copy() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				record[i][j] = current[i][j];
	}

	// 在record当中
	// 从(x,y)往四周填充颜色color
	static void FloodFill(int x, int y, int color) {
		int tx, ty, tc = record[x][y];
		vis[x][y] = true;
		record[x][y] = color;
		for (int i = 0; i < 4; i++) {
			tx = x + dir[i][0];
			ty = y + dir[i][1];
			if (valid(tx, ty) && !vis[tx][ty] && record[tx][ty] == tc)
				FloodFill(tx, ty, color);
		}
	}

	static node Astar() {
		node old, neo;
		String oldState, neoState;
		int tx, ty, color, tc, id;
		while (!queue.isEmpty()) {
			old = queue.poll();
			oldState = old.state;
			if (oldState.equals(goal))
				return old;
			if (hash.containsKey(oldState))
				continue;
			hash.put(oldState, 0);

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++) {
					id = i * m + j;
					current[i][j] = (oldState.charAt(id * 2) - '0') * 10
							+ oldState.charAt(id * 2 + 1) - '0';
				}

			for (int x = 0; x < n; x++)
				for (int y = 0; y < m; y++) {
					color = current[x][y];

					for (int k = 0; k < 4; k++) {
						tx = x + dir[k][0];
						ty = y + dir[k][1];
						if (valid(tx, ty) && (tc = current[tx][ty]) != color) {
							copy();
							for (int i = 0; i < n; i++)
								Arrays.fill(vis[i], false);
							FloodFill(x, y, tc);
							neoState = convertToState(record);
							if (!hash.containsKey(neoState)) {
								neo = new node(neoState, getH(record),
										old.g + 1);
								neo.pre = old;
								neo.str = (x + 1) + " " + (y + 1) + " " + tc;
								queue.add(neo);
							}
						}
					}

					if (color != 0) {
						copy();
						for (int i = 0; i < N; i++)
							Arrays.fill(vis[i], false);
						FloodFill(x, y, 0);
						neoState = convertToState(record);
						if (!hash.containsKey(neoState)) {
							neo = new node(neoState, getH(record), old.g + 1);
							neo.pre = old;
							neo.str = (x + 1) + " " + (y + 1) + " " + 0;
							queue.add(neo);
						}
					}

				}
		}

		return null;
	}

	public static void main(String[] args) throws IOException {
		node neo;

		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			m = nextInt();
			;
			init();
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					record[i][j] = nextInt();
			neo = new node(convertToState(record), getH(record), 0);
			queue.add(neo);
			goal = "";
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					goal += "00";

			neo = Astar();
			out.println(neo.g);
			String ans[] = new String[30];
			int top = 0;
			while (neo != null) {
				ans[top++] = neo.str;
				neo = neo.pre;
			}
			for (int i = top - 2; i >= 0; i--)
				out.println(ans[i]);
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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}