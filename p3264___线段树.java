import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class p3264___Ïß¶ÎÊ÷ {
	static int n, q;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		q = Integer.parseInt(str.nextToken());
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(br.readLine());
		point root = make(1, n);

		int a, b;
		for (int i = 1; i <= q; i++) {
			str = new StringTokenizer(br.readLine());
			a = Integer.parseInt(str.nextToken());
			b = Integer.parseInt(str.nextToken());
			pw.println(getMax(a, b, root) - getMin(a, b, root));
		}

		// pw.println(getMax(2, 2, root) + " " + getMin(2, 2, root));

		br.close();
		pw.close();
	}

	static int getMax(int a, int b, point p) {
		int x = p.l, y = p.r;
		if (a == x && b == y)
			return p.max;
		if (x == y)
			return p.max;
		int mid = (x + y) / 2;
		if (mid >= b)
			return getMax(a, b, p.left);
		else if (mid < a)
			return getMax(a, b, p.right);
		else {
			int left = getMax(a, mid, p.left);
			int right = getMax(mid + 1, b, p.right);
			return Math.max(left, right);
		}
	}

	static int getMin(int a, int b, point p) {
		int x = p.l, y = p.r;
		if (a == x && b == y)
			return p.min;
		if (x == y)
			return p.min;
		int mid = (x + y) / 2;
		if (mid >= b)
			return getMin(a, b, p.left);
		else if (mid < a)
			return getMin(a, b, p.right);
		else {
			int left = getMin(a, mid, p.left);
			int right = getMin(mid + 1, b, p.right);
			return Math.min(left, right);
		}
	}

	static point make(int a, int b) {
		point p = new point(a, b);
		if (a == b) {
			p.max = p.min = arr[a];
			return p;
		}
		int l = (a + b) / 2;
		p.left = make(a, l);
		p.right = make(l + 1, b);
		p.max = Math.max(p.left.max, p.right.max);
		p.min = Math.min(p.left.min, p.right.min);
		return p;
	}
}

class point {
	int l, r, max, min;
	point left, right;

	point(int a, int b) {
		this.l = a;
		this.r = b;
	}

}
