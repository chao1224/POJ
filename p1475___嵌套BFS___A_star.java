import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class p1475___Ç¶Ì×BFS___A_star {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;
	static int Tx, Ty, Bx, By, Px, Py;
	static int N = 21;
	static boolean map[][] = new boolean[N][N],
			vis[][][] = new boolean[N][N][4], check[][] = new boolean[N][N];
	static int sp[][] = new int[N][N], inf = 1 << 20;
	static char ch;
	static int dir[][] = { { 0, 1 }, { -1, 0 }, { 1, 0 }, { 0, -1 } };
	static char convert1[] = { 'e', 'n', 's', 'w' };
	static char convert2[] = { 'E', 'N', 'S', 'W' };
	static PriorityQueue<node> queue = new PriorityQueue<p1475___Ç¶Ì×BFS___A_star.node>();

	static class node implements Comparable<node> {
		int x, y, h, g, op;
		String pre = "";

		node() {
		}

		node(int a, int b) {
			x = a;
			y = b;
		}

		node(int a, int b, int c) {
			x = a;
			y = b;
			g = c;
		}

		node(int a, int b, int c, int d) {
			x = a;
			y = b;
			h = c;
			g = d;
		}

		public int compareTo(node o) {
			if (this.h + this.g == o.h + o.g)
				return this.pre.length() - o.pre.length();
			return this.h + this.g - o.h - o.g;
		}
	}

	static void init() {
		queue.clear();
		for (int i = 0; i < n; i++)
			Arrays.fill(map[i], true);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				Arrays.fill(vis[i][j], false);
		for (int i = 0; i < n; i++)
			Arrays.fill(sp[i], inf);
	}

	static boolean valid(int a, int b) {
		if (a >= 0 && a < n && b >= 0 && b < m && map[a][b])
			return true;
		return false;
	}

	static node q[] = new node[N * N * 100];
	static int h, t;

	static void BFS1() {
		for (int i = 0; i < n; i++)
			Arrays.fill(check[i], false);
		int x, y, tx, ty;
		h = t = 0;
		node temp;
		q[t++] = new node(Tx, Ty, 0);
		check[Tx][Ty] = true;
		while (h < t) {
			temp = q[h++];
			x = temp.x;
			y = temp.y;
			sp[x][y] = temp.g;
			for (int i = 0; i < 4; i++) {
				tx = x + dir[i][0];
				ty = y + dir[i][1];
				if (valid(tx, ty) && !check[tx][ty]) {
					q[t++] = new node(tx, ty, temp.g + 1);
					check[tx][ty] = true;
				}
			}
		}

		for (int i = 0; i < n; i++)
			Arrays.fill(check[i], false);
		node old = new node(Px, Py, 0), neo;
		h = t = 0;
		q[t++] = old;
		check[Px][Py] = true;

		while (h < t) {
			old = q[h++];
			x = old.x;
			y = old.y;
			if (Math.abs(x - Bx) + Math.abs(y - By) == 1) {
				for (int i = 0; i < 4; i++) {
					tx = x + dir[i][0];
					ty = y + dir[i][1];
					if (tx == Bx && ty == By) {
						neo = new node(tx, ty, sp[Bx][By]);
						neo.op = i;
						neo.pre = old.pre;
						queue.add(neo);
						break;
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				tx = x + dir[i][0];
				ty = y + dir[i][1];
				if (valid(tx, ty) && !check[tx][ty]) {
					if (tx == Bx && ty == By)
						continue;
					neo = new node(tx, ty);
					neo.op = i;
					neo.pre = old.pre + convert1[i];
					q[t++] = neo;
					check[tx][ty] = true;
				}
			}
		}

	}

	static node BFS2() {
		node old, walk, neo;
		int x, y, tx, ty, op, prex, prey;
		while (!queue.isEmpty()) {
			old = queue.poll();
			x = old.x;
			y = old.y;
			op = old.op;
			if (x == Tx && y == Ty)
				return old;
			if (vis[x][y][old.op])
				continue;
			vis[x][y][old.op] = true;

			prex = x - dir[op][0];
			prey = y - dir[op][1];
			tx = x + dir[op][0];
			ty = y + dir[op][1];
			if (valid(tx, ty) && !vis[tx][ty][op]) {
				neo = new node(tx, ty, sp[tx][ty], old.g + 1);
				neo.op = op;
				neo.pre = old.pre + convert2[op];
				queue.add(neo);
			}

			for (int i = 0; i < 4; i++) {
				tx = x + dir[i][0];
				ty = y + dir[i][1];
				if (prex == tx && prey == ty)
					continue;
				if (vis[x][y][3 - i] || !valid(tx, ty))
					continue;
				walk = BFS3(prex, prey, tx, ty, x, y);
				if (walk == null)
					continue;
				neo = new node(x, y, old.h, old.g);
				neo.op = 3 - i;
				neo.pre = old.pre + walk.pre;
				queue.add(neo);
			}
		}
		return null;
	}

	// x1=neo.x y1=neo.y
	// from (x1,y1) to (x2,y2) without going through (x3,y3)
	static node BFS3(int x1, int y1, int x2, int y2, int x3, int y3) {
		h = t = 0;
		q[t++] = new node(x1, y1, 0);
		node old, neo;
		int x, y, tx, ty;
		for (int i = 0; i < n; i++)
			Arrays.fill(check[i], false);
		check[x1][y1] = true;

		while (h < t) {
			old = q[h++];
			x = old.x;
			y = old.y;
			for (int i = 0; i < 4; i++) {
				tx = x + dir[i][0];
				ty = y + dir[i][1];
				if (valid(tx, ty) && !check[tx][ty]) {
					if (tx == x3 && ty == y3)
						continue;
					neo = new node(tx, ty, old.g + 1);
					neo.pre = old.pre + convert1[i];
					if (tx == x2 && ty == y2)
						return neo;
					check[tx][ty] = true;
					q[t++] = neo;
				}
			}
		}
		return null;
	}

	static String str;
	static StringTokenizer stoke;

	public static void main(String[] args) throws IOException {
		int test = 0;
		while (true) {
			stoke = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stoke.nextToken());
			m = Integer.parseInt(stoke.nextToken());
			if (n == 0 && m == 0)
				break;
			init();
			for (int i = 0; i < n; i++) {
				str = br.readLine();
				for (int j = 0; j < m; j++) {
					ch = str.charAt(j);
					if (ch == '#') {
						map[i][j] = false;
					} else if (ch == 'T') {
						Tx = i;
						Ty = j;
					} else if (ch == 'B') {
						Bx = i;
						By = j;
					} else if (ch == 'S') {
						Px = i;
						Py = j;
					}
				}
			}

			out.println("Maze #" + (++test));
			BFS1();
			node temp = BFS2();
			if (temp != null) {
				out.println(temp.pre);
			} else
				out.println("Impossible.");
			out.println();
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