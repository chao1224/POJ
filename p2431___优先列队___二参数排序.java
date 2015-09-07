import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p2431___优先列队___二参数排序 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int maxSize = scan.nextInt();

		node[] arr = new node[maxSize + 1];

		for (int i = 1; i <= maxSize; i++)
			arr[i] = new node(scan.nextInt(), scan.nextInt());

		int dis = scan.nextInt();
		int fuel = scan.nextInt();

		Arrays.sort(arr, 1, maxSize + 1, null);

		int left = dis - fuel, pos = 1, ans = 0;

		while (arr[pos].dis > dis) {
			pos++;
		}
		PriorityQueue<Integer> q = new PriorityQueue();
		while (left > 0) {
			ans++;
			while (pos <= maxSize && arr[pos].dis >= left) {
				q.add(-arr[pos++].fuel);
			}
			if (q.isEmpty()) {
				break;
			} else {
				left += q.peek();
				q.poll();
			}
		}
		if (left > 0) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}

	}
}

class node implements Comparable<node> {

	int fuel, dis;

	node(int dis, int fuel) {
		this.dis = dis;
		this.fuel = fuel;
	}

	public int compareTo(node n) {
		if (dis < n.dis)
			return 1;
		else if (dis > n.dis)
			return -1;
		else
			return 0;
	}

}
