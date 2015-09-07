import java.io.*;
import java.util.*;

public class p3523___Ë«ÏòBFS___MLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, k;
	static boolean base[][] = new boolean[16][16];
	static short hash1[] = new short[1 << 24], hash2[] = new short[1 << 24];
	static short head1, tail1, head2, tail2, turnHead1, turnTail1, turnHead2,
			turnTail2, limit = 16500;
	static int front[] = new int[limit], rear[] = new int[limit];
	static int dir[][] = { { 0, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
	static short old[][] = new short[3][2], neo[][] = new short[3][2];
	static short Min, inf = 1 << 14;
	static int start, end;
	static int state;
	static short step;

	static final boolean valid(int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < m && base[i][j])
			return true;
		return false;
	}

	static final void init() {
		for (int i = 0; i < 16; i++)
			Arrays.fill(base[i], true);
		Arrays.fill(hash1, (short) -1);
		Arrays.fill(hash2, (short) -1);
		Min = inf;
		head1 = head2 = tail1 = tail2 = 0;
		turnHead1 = turnTail2 = turnHead2 = turnTail2 = 0;
	}

	static final boolean equals(int x1, int y1, int x2, int y2) {
		if (x1 == x2 && y1 == y2)
			return true;
		return false;
	}

	static int neos, x, y, tx, ty, ttt;

	static final void dfs1(int i) {
		if (Min < inf)
			return;
		if (i == k) {
			for (int a = 0; a < k; a++)
				for (int b = a + 1; b < k; b++)
					if ((equals(old[a][0], old[a][1], neo[b][0], neo[b][1]) && equals(
							neo[a][0], neo[a][1], old[b][0], old[b][1]))
							|| (equals(neo[a][0], neo[a][1], neo[b][0],
									neo[b][1])))
						return;

			neos = 0;
			for (int c = 0; c < k; c++) {
				x = neo[c][0];
				y = neo[c][1];
				neos = neos | (x << (c * 8));
				neos = neos | (y << (c * 8 + 4));
			}

			if (hash1[neos] > -1)
				return;
			if (neos == end) {
				Min = (short) (step + 1);
				return;
			}
			if (hash2[neos] > -1) {
				Min = (short) Math.min(Min, step + 1 + hash2[neos]);
				return;
			}
			hash1[neos] = (short) (step + 1);
			front[tail1++] = neos;
			if (tail1 == limit) {
				tail1 = 0;
				turnTail1++;
			}
		} else {
			ttt = state % (1 << 8 * (i + 1)) >> (8 * i);
			x = (short) (ttt % 16);
			y = (short) (ttt >> 4);
			old[i][0] = (short) x;
			old[i][1] = (short) y;
			for (int ch = 0; ch < 5; ch++) {
				tx = x + dir[ch][0];
				ty = y + dir[ch][1];
				if (valid(tx, ty)) {
					neo[i][0] = (short) tx;
					neo[i][1] = (short) ty;
					dfs1(i + 1);
					if (Min < inf)
						return;
				}
			}
		}
	}

	static final void dfs2(int i) {
		if (Min < inf)
			return;
		if (i == k) {
			for (int a = 0; a < k; a++)
				for (int b = a + 1; b < k; b++)
					if ((equals(old[a][0], old[a][1], neo[b][0], neo[b][1]) && equals(
							neo[a][0], neo[a][1], old[b][0], old[b][1]))
							|| (equals(neo[a][0], neo[a][1], neo[b][0],
									neo[b][1])))
						return;

			neos = 0;
			for (int c = 0; c < k; c++) {
				x = neo[c][0];
				y = neo[c][1];
				neos = neos | (x << (c * 8));
				neos = neos | (y << (c * 8 + 4));
			}

			if (hash2[neos] > -1)
				return;
			if (neos == start) {
				Min = (short) (step + 1);
				return;
			}
			if (hash1[neos] > -1) {
				Min = (short) Math.min(Min, step + 1 + hash1[neos]);
				return;
			}
			hash2[neos] = (short) (step + 1);
			rear[tail2++] = neos;
			if (tail2 == limit) {
				tail2 = 0;
				turnTail2++;
			}
		} else {
			ttt = state % (1 << 8 * (i + 1)) >> (8 * i);
			x = (short) (ttt % 16);
			y = (short) (ttt >> 4);
			old[i][0] = (short) x;
			old[i][1] = (short) y;
			for (int ch = 0; ch < 5; ch++) {
				tx = x + dir[ch][0];
				ty = y + dir[ch][1];
				if (valid(tx, ty)) {
					neo[i][0] = (short) tx;
					neo[i][1] = (short) ty;
					dfs2(i + 1);
					if (Min < inf)
						return;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String str;
		char ch;
		while (true) {
			StringTokenizer stoke = new StringTokenizer(br.readLine());
			m = Integer.parseInt(stoke.nextToken());
			n = Integer.parseInt(stoke.nextToken());
			k = Integer.parseInt(stoke.nextToken());
			if (n == 0 && m == 0 && k == 0)
				break;
			init();

			short[][] tst = new short[k][2];
			short[][] ted = new short[k][2];
			for (int i = 0; i < n; i++) {
				str = br.readLine();
				for (int j = 0; j < m; j++) {
					ch = str.charAt(j);
					if (ch == ' ') {
						base[i][j] = true;
					} else if (ch == '#')
						base[i][j] = false;
					else if (ch >= 'a') {
						tst[ch - 'a'][0] = (short) i;
						tst[ch - 'a'][1] = (short) j;
					} else {
						ted[ch - 'A'][0] = (short) i;
						ted[ch - 'A'][1] = (short) j;
					}
				}
			}

			start = 0;
			for (int i = 0; i < k; i++) {
				x = tst[i][0];
				y = tst[i][1];
				start = start | (x << (i * 8));
				start = start | (y << (i * 8 + 4));
			}
			end = 0;
			for (int i = 0; i < k; i++) {
				x = ted[i][0];
				y = ted[i][1];
				end = end | (x << (i * 8));
				end = end | (y << (i * 8 + 4));
			}
			hash1[start] = 0;
			front[tail1++] = start;
			hash2[end] = 0;
			rear[tail2++] = end;

			step = 0;
			while (head1 + turnHead1 * limit < tail1 + turnTail1 * limit
					|| head2 + turnHead2 * limit < tail2 + turnTail2 * limit) {
				while (head1 + turnHead1 * limit < tail1 + turnTail1 * limit) {
					state = front[head1];
					if (hash1[state] > step)
						break;
					head1++;
					if (head1 == limit) {
						head1 = 0;
						turnHead1++;
					}
					dfs1(0);
					if (Min < inf)
						break;
				}
				if (Min < inf)
					break;
				while (head2 + turnHead2 * limit < tail2 + turnTail2 * limit) {
					state = rear[head2];
					if (hash2[state] > step)
						break;
					head2++;
					if (head2 == limit) {
						head2 = 0;
						turnHead2++;
					}
					dfs2(0);
					if (Min < inf)
						break;
				}
				if (Min < inf)
					break;
				step++;
			}

			out.println(Min);
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

	static int nextint() throws IOException {
		in.nextToken();
		return (int) in.nval;
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