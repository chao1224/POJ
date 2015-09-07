import java.io.*;
import java.util.*;

public class p2018___斜率优化___维护下凸___二分___可以用单调队列继续优化 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, f, arr[];
	static point[] queue, p;

	public static void main(String[] args) throws IOException {
		point temp;
		n = nextInt();
		f = nextInt();
		arr = new int[n + 1];
		p = new point[n + 1];
		p[0] = new point(0, 0);
		for (int i = 1; i <= n; i++) {
			arr[i] = nextInt() + arr[i - 1];
			p[i] = new point(i, arr[i]);
		}
		queue = new point[n + 1];

		double max = -1.0;
		int size = 0, ll , rr, mid;
		for (int i = f; i <= n; i++) {
			temp = p[i - f];
			while (size >= 2
					&& cross(queue[size - 2], queue[size - 1], temp) < 0)
				--size;
			queue[size++] = temp;
			// 二分查找
			ll = 0;
			rr = size;
			while (rr - ll > 1) {
				mid = (ll + rr) / 2;
				if (cross(queue[mid - 1], p[i], queue[mid]) > 0)
					rr = mid;
				else
					ll = mid;
			}
			max = Math.max(getk(queue[ll], p[i]), max);
		}

		out.println((int) (max * 1000));

		out.flush();
		out.close();
	}

	static class point {
		double x, y;

		point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static double cross(point p0, point p1, point p2) {
		return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
	}

	static double getk(point aa, point bb) {
		return (bb.y - aa.y) / (bb.x - aa.x);
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