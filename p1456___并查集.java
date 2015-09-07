import java.util.Arrays;
import java.util.Scanner;

//�ؼ������ڽ�ÿ�����Ȱ�װ������Ȼ������������һֱ�����ڵ�ָ��0�����Ų���
public class p1456___���鼯 {
	static int n, sum, da, db;
	static p1456_node[] arr;
	static int[] father = new int[10001];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			n = scan.nextInt();
			arr = new p1456_node[n + 1];
			sum = 0;
			init();
			for (int i = 1; i <= n; i++)
				arr[i] = new p1456_node(scan.nextInt(), scan.nextInt());

			Arrays.sort(arr, 1, n + 1, null);

			for (int i = 1; i <= n; i++) {
				da = arr[i].dead;
				db = find(da);
				if (db != 0) {
					sum += arr[i].val;
					father[db] = db - 1;
				}
			}
//			 System.out.println(arr[1].val + " " + arr[2].val + " " +
//			 arr[3].val
//			 + " " + arr[4].val);
//			 System.out.println(father[arr[1].dead] + " " +
//			 father[arr[2].dead]
//			 + " " + father[arr[3].dead] + " " + father[arr[4].dead]);
			System.out.println(sum);
		}
	}

	static void init() {
		for (int i = 1; i <= 10000; i++) {
			father[i] = i;
		}
	}

	static int find(int x) {
		if (father[x] == x) {
			return x;
		}
		father[x] = find(father[x]);
		return father[x];
	}
}

class p1456_node implements Comparable<p1456_node> {
	int val, dead;

	p1456_node(int val, int dead) {
		this.val = val;
		this.dead = dead;
	}

	public int compareTo(p1456_node n) {
		if (this.val < n.val)
			return 1;
		else if (this.val > n.val)
			return -1;
		else if (this.dead < n.dead)
			return 1;
		else
			return -1;
	}


}
