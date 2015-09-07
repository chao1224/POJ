import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class p2777___树状数组____位运算 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
	static int n;
	static p2777___nude[] arr;
	static char ch;

	public static void main(String[] args) throws Exception {
		n = nextInt();
		int t = nextInt();
		int o = nextInt();
		arr = new p2777___nude[n * 5];

		build(1, n, 1);

		int a, b, c;
		while (o-- > 0) {
			ch = next().charAt(0);
			if (ch == 'C') {
				a = nextInt();
				b = nextInt();
				if (a > b) {
					a = a ^ b;
					b = a ^ b;
					a = a ^ b;
				}
				c = nextInt();
				update(a, b, 1, calculate(c));
			} else {
				a = nextInt();
				b = nextInt();
				if (a > b) {
					a = a ^ b;
					b = a ^ b;
					a = a ^ b;
				}
				pw.println(calculateLog(query(a, b, 1)));
			}
		}
		pw.flush();
		pw.close();
	}

	static void build(int left, int right, int idx) {
		arr[idx] = new p2777___nude();
		arr[idx].left = left;
		arr[idx].right = right;
		if (left == right)
			return;
		int mid = arr[idx].calmid();
		build(left, mid, idx << 1);
		build(mid + 1, right, idx << 1 | 1);
		return;
	}

	static void update(int left, int right, int idx, int color) {
		if (arr[idx].left >= left && arr[idx].right <= right) {
			arr[idx].color = color;
			arr[idx].mark = true;
			return;
		}
		// pushDown(idx);
		if (arr[idx].mark) {
			arr[idx].mark = false;
			int temp = arr[idx].color;
			int t = idx << 1;
			arr[t].mark = true;
			arr[t].color = temp;
			t++;
			arr[t].mark = true;
			arr[t].color = temp;
		}
		int mid = arr[idx].calmid();
		if (left <= mid)
			update(left, right, idx << 1, color);
		if (right > mid)
			update(left, right, idx << 1 | 1, color);
		// pushUp(idx);
		arr[idx].color = arr[idx << 1].color | arr[idx << 1 | 1].color;
	}

	static int query(int left, int right, int idx) {
		if (left == arr[idx].left && right == arr[idx].right)
			return arr[idx].color;
		// pushDown(idx);
		if (arr[idx].mark) {
			arr[idx << 1].color = arr[idx << 1 | 1].color = arr[idx].color;
			arr[idx << 1].mark = arr[idx << 1 | 1].mark = true;
			arr[idx].mark = false;
		}
		int mid = arr[idx].calmid();
		if (right <= mid)
			return query(left, right, idx << 1);
		else if (mid < left)
			return query(left, right, idx << 1 | 1);
		else
			return query(left, mid, idx << 1)
					| query(mid + 1, right, idx << 1 | 1);
	}

	// static void pushUp(int idx) {
	// if (arr[idx].left == arr[idx].right)
	// return;
	// pushDown(idx << 1);
	// pushDown(idx << 1 | 1);
	// arr[idx].color = arr[idx << 1].color | arr[idx << 1 | 1].color;
	// }

	// static void pushDown(int idx) {
	// if (arr[idx].mark) {
	// arr[idx].mark = false;
	// int temp = arr[idx].color;
	// arr[idx].color = 0;
	// if (arr[idx].left != arr[idx].right) {
	// int t = idx << 1;
	// arr[t].mark = true;
	// arr[t].color = temp;
	// t++;
	// arr[t].mark = true;
	// arr[t].color = temp;
	// }
	// }
	// }

	static int calculate(int x) {
		int ans = 1;
		x--;
		while (x-- > 0) {
			ans <<= 1;
		}
		return ans;
	}

	static int lowbit(int x) {
		return x & -x;
	}

	static int calculateLog(int x) {
		int ans = 0;
		while (x > 0) {
			ans++;
			x -= lowbit(x);
		}
		return ans;
	}

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws IOException {
		in.nextToken();
		return (String) in.sval;
	}

}

class p2777___nude {
	int left, right, color = 1;
	boolean mark = false;

	p2777___nude() {
	}

	int calmid() {
		return (left + right) >> 1;
	}

}