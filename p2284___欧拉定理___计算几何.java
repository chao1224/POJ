import java.awt.*;
import java.io.*;
import java.util.*;

public class p2284___欧拉定理___计算几何 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m;
	static point Intersection[] = new point[90000];
	static double eps = 1e-10;
	static point point[];

	public static void main(String[] args) throws IOException {
		int N, test = 0;
		while (true) {
			N = nextInt();
			if (N == 0)
				break;
			test++;
			point = new point[N + 1];
			for (int i = 0; i < N; i++)
				point[i] = new point(nextInt(), nextInt());
			// for (int i = 0; i < N; i++)
			// out.println(point[i].x + " " + point[i].y);
			// out.println("------------------");

			LineSegment l1, l2;
			point p;
			n = m = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					l1 = new LineSegment(point[i], point[(i + 1) % N]);
					l2 = new LineSegment(point[j], point[(j + 1) % N]);
					p = new point();
					if (LineSegmentIntersect(l1, l2, p)) {
						// out.println("new point   " + p.x + "   " + p.y);
						Intersection[n++] = p;
					}
				}
			}
			Arrays.sort(Intersection, 0, n);

			// for (int i = 0; i < n; i++)
			// out.println(Intersection[i].x + " " + Intersection[i].y);
			// out.println("------------------");

			int index = 0;
			for (int i = 1; i < n; i++) {
				if (!equals(Intersection[i], Intersection[index])) {
					// out.println(Intersection[i].x + " " + Intersection[i].y
					// + " ---> " + Intersection[index].x + " "
					// + Intersection[index].y);
					Intersection[++index] = Intersection[i];
				}
			}

			// for (int i = 0; i <= index; i++)
			// out.println(Intersection[i].x + " " + Intersection[i].y);
			// out.println("--------------");

			n = index + 1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < N; j++) {
					l1 = new LineSegment(point[j], point[(j + 1) % N]);
					if (Online(l1, Intersection[i])
							&& !equals(l1.s, Intersection[i]))
						m++;
				}
			}
			out.println("Case " + test + ": There are " + (2 + m - n)
					+ " pieces.");
		}

		out.flush();
		out.close();
	}

	static boolean equals(point a, point b) {
		if (Math.abs(a.x - b.x) < eps && Math.abs(a.y - b.y) < eps)
			return true;
		return false;
	}

	static class point implements Comparable<point> {
		double x, y;

		point() {
		}

		point(double a, double b) {
			this.x = a;
			this.y = b;
		}

		public int compareTo(point o) {
			if (this.x < o.x)
				return -1;
			else if (this.x > o.x)
				return 1;
			else if (this.y < o.y)
				return -1;
			else if (this.y > o.y)
				return 1;
			return 0;
		}

	}

	static class LineSegment {
		point s, e;

		LineSegment(point a, point b) {
			s = a;
			e = b;
		}
	}

	static class Line {
		double a, b, c; // c是叉积

		Line() {
		}
	}

	static boolean Online(LineSegment l, point p) {
		if (Math.abs((l.e.x - p.x) * (l.s.y - p.y) - (l.e.y - p.y)
				* (l.s.x - p.x)) < eps
				&& p.x >= Math.min(l.s.x, l.e.x)
				&& p.x <= Math.max(l.s.x, l.e.x)
				&& p.y >= Math.min(l.s.y, l.e.y)
				&& p.y <= Math.max(l.s.y, l.e.y))
			return true;
		else
			return false;
	}

	static Line MakeLine(point a, point b) {
		Line l = new Line();
		if (a.y > b.y) {
			l.a = a.y - b.y;
			l.b = b.x - a.x;
			l.c = a.x * b.y - a.y * b.x;
		} else {
			l.a = b.y - a.y;
			l.b = a.x - b.x;
			l.c = a.y * b.x - a.x * b.y;
		}
		return l;
	}

	static boolean LineIntersect(Line l1, Line l2, point p) {
		double d = l1.a * l2.b - l1.b * l2.a;
		if (Math.abs(d) < eps)
			return false;
		p.x = (l2.c * l1.b - l1.c * l2.b) / d;
		p.y = (l2.a * l1.c - l1.a * l2.c) / d;
		return true;
	}

	static boolean LineSegmentIntersect(LineSegment l1, LineSegment l2, point p) {
		Line a, b;
		a = MakeLine(l1.s, l1.e);
		b = MakeLine(l2.s, l2.e);
		if (LineIntersect(a, b, p))
			return Online(l1, p) && Online(l2, p);
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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}