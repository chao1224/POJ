import java.util.PriorityQueue;
import java.util.Scanner;

public class p3253___优先队列 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PriorityQueue<Integer> q = new PriorityQueue();

		int len = scan.nextInt();
		for (int i = 1; i <= len; i++)
			q.add(scan.nextInt());

		int a, b;
		long answer = 0;
		while (q.size() != 1) {
			a = q.peek();
			q.poll();
			b = q.peek();
			q.poll();
			answer += a + b;
			q.add(a + b);
		}

		System.out.println(answer);

		// 堆的实现
		// int[] arr = new int[len + 1];
		// for (int i = 1; i <= len; i++)
		// arr[i] = scan.nextInt();
		//
		// int c;
		// for (int cur = len / 2; cur >= 1; cur--) {
		// c = 2 * cur;
		// while (c <= len) {
		// if (c < len && arr[c] < arr[c + 1])
		// c++;
		// if (arr[cur] >= arr[c])
		// break;
		// arr[c / 2] = arr[c];
		// c *= 2;
		// }
		// arr[c / 2] = arr[cur];
		// }

	}
}