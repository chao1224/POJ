import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class p1182___种类并查集___IO {
	static int n;
	static int[] father, relation, k;
	static int kind;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		int k = Integer.parseInt(temp[1]);
		int d, a, b, ra, rb, sum = 0;

		init();

		for (int i = 0; i < k; i++) {
			temp = br.readLine().split(" ");
			d = Integer.parseInt(temp[0]);
			a = Integer.parseInt(temp[1]);
			b = Integer.parseInt(temp[2]);
			if (a > n || b > n) {
				sum++;
				continue;
			}
			if (a == b) {
				if (d == 2)
					sum++;
				continue;
			}

			ra = find(a);
			rb = find(b);
			if (ra == rb) {
				// if (d == 1) {
				// // a和b同类
				// if (relation[a] % kind != relation[b] % kind) {
				// sum++;
				// }
				// } else {
				// // a吃b
				// if ((relation[b] + 2) % kind != relation[a] % kind) {
				// sum++;
				// }
				// }
				if ((relation[b] + d + 2) % 3 != relation[a])
					sum++;
			} else {
				merge(a, b, ra, rb, d);
			}
		}
		br.close();
		System.out.println(sum);
	}

	static void init() {
		father = new int[n + 1];
		relation = new int[n + 1];
		kind = 3;
		for (int i = 1; i <= n; i++) {
			father[i] = i;
			relation[i] = 0;
		}
	}

	static int find(int x) {
		if (x == father[x])
			return x;
		int temp = father[x];
		father[x] = find(father[x]);
		relation[x] = (relation[temp] + relation[x]) % kind;
		return father[x];
	}

	static void merge(int a, int b, int ra, int rb, int d) {
		// if (relation[a] < relation[b]) {
		// father[ra] = rb;
		// if (d == 2)
		// relation[ra] = (relation[b] - relation[a] + 2) % kind;
		// else
		// relation[ra] = (relation[b] - relation[a]) % kind;
		// } else {
		// father[rb] = ra;
		// if (d == 2)
		// relation[rb] = (relation[a] - relation[b] + 1) % kind;
		// else
		// relation[rb] = (relation[a] - relation[b]) % kind;
		// }
		//
		father[ra] = rb;
		relation[ra] = (relation[b] + d + 2 - relation[a]) % kind;
	}
}
