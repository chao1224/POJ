import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class p1703___种类并查集 {
	static int n, kind = 2;
	static int[] father, relation;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int case_num = Integer.parseInt(br.readLine()), m, a, b, ra, rb;
		String[] temp;
		char mark;
		for (int ttt = 1; ttt <= case_num; ttt++) {
			temp = br.readLine().split(" ");
			n = Integer.parseInt(temp[0]);
			m = Integer.parseInt(temp[1]);
			// System.out.println(n + "  " + m);
			init();
			if (n == 2) {
				for (int j = 0; j < m; j++) {
					br.readLine();
					System.out.println("In different gangs.");
				}
				continue;
			}

			for (int i = 1; i <= m; i++) {
				temp = br.readLine().split(" ");
				mark = temp[0].charAt(0);
				a = Integer.parseInt(temp[1]);
				b = Integer.parseInt(temp[2]);
				if (mark == 'D') {
					ra = find(a);
					rb = find(b);
					if (ra != rb)
						merge(a, b, ra, rb);
				} else {
					ra = find(a);
					rb = find(b);
					if (ra != rb) {
						System.out.println("Not sure yet.");
					} else if (relation[a] == relation[b])
						System.out.println("In the same gang.");
					else
						System.out.println("In different gangs.");
				}
			}

		}
	}

	static void init() {
		father = new int[n + 1];
		relation = new int[n + 1];
		for (int i = 1; i <= n; i++)
			father[i] = i;
	}

	static int find(int x) {
		if (x == father[x])
			return x;
		int temp = father[x];
		father[x] = find(father[x]);
		relation[x] = (relation[temp] + relation[x]) % kind;
		return father[x];
	}

	static void merge(int a, int b, int ra, int rb) {
		father[ra] = rb;
		relation[ra] = (relation[b] + 1 - relation[a]) % kind;
	}
}
