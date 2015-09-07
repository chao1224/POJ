import java.util.Scanner;

public class p2823___单调队列 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = scan.nextInt();

		// section 1
		node[] lis = new node[n + 1];
		int head = 0, tail = 0;
		lis[head] = new node(0, arr[0]);
		for (int i = 1; i < k - 1; i++) {
			while (head != tail + 1 && lis[tail].val > arr[i])
				tail--;
			lis[++tail] = new node(i, arr[i]);
		}

		for (int i = k - 1; i < n; i++) {
			while (head != tail + 1 && lis[tail].val >= arr[i])
				tail--;
			lis[++tail] = new node(i, arr[i]);
			if (i - lis[head].key >= k)
				head++;
			System.out.print(lis[head].val + " ");
		}
		System.out.println();

		// section 2
		node[] lds = new node[n + 1];
		head = tail = 0;
		lds[head] = new node(0, arr[0]);
		for (int i = 1; i < k - 1; i++) {
			while (head != tail + 1 && lds[tail].val < arr[i])
				tail--;
			lds[++tail] = new node(i, arr[i]);
		}

		for (int i = k - 1; i < n; i++) {
			while (head != tail + 1 && lds[tail].val <= arr[i])
				tail--;
			lds[++tail] = new node(i, arr[i]);
			if (i - lds[head].key >= k)
				head++;
			System.out.print(lds[head].val + " ");
		}
		System.out.println();
	}

	static class node {
		int key, val;

		node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
}