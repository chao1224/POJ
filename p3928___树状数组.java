import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class p3928___Ê÷×´Êý×é {
	static int[] c;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine());
		StringTokenizer str;
		int ta, tb, tc, td, id;
		long ans;
		person[] arr;
		for (int i = 1; i <= t; i++) {
			str = new StringTokenizer(br.readLine());
			n = Integer.parseInt(str.nextToken());
			c = new int[n + 1];
			arr = new person[n + 1];
			ans = 0;
			for (int j = 1; j <= n; j++)
				arr[j] = new person(j, Integer.parseInt(str.nextToken()));
			Arrays.sort(arr, 1, n + 1, null);

			for (int j = 1; j <= n; j++) {
				id = arr[j].id;
				ta = sum(id);// prev smaller
				tb = id - 1 - ta;// after smaller
				tc = sum(n) - ta;// prev bigger
				td = n - id - tc;// after bigger
				if (ta > 0 && td > 0) 
					ans += ta * td;
				if (tb > 0 && tc > 0)
					ans += tb * tc;
				insert(id, 1);
			}
			pw.println(ans);
		}
		br.close();
		pw.close();
	}

	static int lowbit(int x) {
		return x & -x;
	}

	static void insert(int x, int dif) {
		while (x <= n) {
			c[x] += dif;
			x += lowbit(x);
		}
	}

	static int sum(int x) {
		int ans = 0;
		while (x > 0) {
			ans += c[x];
			x -= lowbit(x);
		}
		return ans;
	}
}

class person implements Comparable<person> {

	int id, power;

	person(int id, int power) {
		this.id = id;
		this.power = power;
	}

	public int compareTo(person o) {
		if (this.power < o.power)
			return -1;
		return 1;
	}

}
