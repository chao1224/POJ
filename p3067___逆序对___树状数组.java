import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class p3067___ÄæÐò¶Ô___Ê÷×´Êý×é {
	static int n, m, k;
	static int[] cc;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine());
		int a, b;
		long ans;
		StringTokenizer str;
		road[] arr;
		for (int ca = 1; ca <= t; ca++) {
			str = new StringTokenizer(br.readLine());
			n = Integer.parseInt(str.nextToken());
			m = Integer.parseInt(str.nextToken());
			k = Integer.parseInt(str.nextToken());
			arr = new road[k + 1];
			for (int i = 1; i <= k; i++) {
				str = new StringTokenizer(br.readLine());
				a = Integer.parseInt(str.nextToken());
				b = Integer.parseInt(str.nextToken());
				arr[i] = new road(a, b);
			}
			Arrays.sort(arr, 1, k + 1, null);

			ans = 0;
			cc = new int[m + 1];

			for (int i = 1; i <= k; i++) {
				ans += sum(m) - sum(arr[i].right);
				change(arr[i].right, 1);
			}

			pw.println("Test case " + ca + ": " + ans);
		}
		br.close();
		pw.close();
	}

	static int lowbit(int x) {
		return x & (-x);
	}

	static void change(int num, int dif) {
		while (num <= m) {
			cc[num] += dif;
			num += lowbit(num);
		}
	}

	static int sum(int x) {
		int ans = 0;
		while (x > 0) {
			ans += cc[x];
			x -= lowbit(x);
		}
		return ans;
	}
}

class road implements Comparable<road> {
	int left, right;

	road(int left, int right) {
		this.left = left;
		this.right = right;
	}

	public int compareTo(road o) {
		if (this.left < o.left)
			return -1;
		else if (this.left > o.left)
			return 1;
		else if (this.right < o.right)
			return -1;
		else
			return 1;
	}

}
