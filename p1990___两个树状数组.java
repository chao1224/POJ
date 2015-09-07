import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class p1990___两个树状数组 {
	static int n;
	static int[] cVal = new int[20001], cNum = new int[20001];
	static cow[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		PrintWriter pw = new PrintWriter(System.out);

		n = Integer.parseInt(br.readLine());
		arr = new cow[n + 1];
		for (int i = 1; i <= n; i++) {
			str = new StringTokenizer(br.readLine());
			arr[i] = new cow(Integer.parseInt(str.nextToken()), Integer
					.parseInt(str.nextToken()));
		}
		Arrays.sort(arr, 1, n + 1, null);
		br.close();

		long ans = 0;
		long biggerNum = 0, smallerNum = 0, biggerSum = 0, smallerSum = 0, sum = 0;
		int a, b;
		for (int i = 1; i <= n; i++) {
			a = arr[i].x;
			smallerNum = sum(cNum, a);
			biggerNum = i - 1 - smallerNum;
			if (biggerNum < 0)
				biggerNum = 0;
			insert(cNum, a, 1);

			b = arr[i].v;
			smallerSum = sum(cVal, a);
			biggerSum = sum - smallerSum;
			insert(cVal, a, a);

			// System.out.println("samller: " + smallerNum + "   " +
			// smallerSum);
			// System.out.println("bigger: " + biggerNum + "   " + biggerSum);
			// System.out.println();
			ans += b
					* ((biggerSum - biggerNum * a) + (a * smallerNum - smallerSum));
			sum += a;
		}

		pw.println(ans);
		pw.close();
	}

	static long lowbit(long x) {
		return x & -x;
	}

	static void insert(int[] c, int x, int dif) {
		while (x <= 20000) {
			c[x] += dif;
			x += lowbit(x);
		}
	}

	static long sum(int[] c, int x) {
		long ans = 0;
		while (x > 0) {
			ans += c[x];
			x -= lowbit(x);
		}
		return ans;
	}
}

class cow implements Comparable<cow> {
	int v;
	int x;

	cow(int v, int x) {
		this.v = v;
		this.x = x;
	}

	public int compareTo(cow o) {
		if (this.v < o.v)
			return -1;
		else if (this.v > o.v)
			return 1;
		else if (this.x < o.x)
			return -1;
		else
			return 1;
	}

}
