import java.util.Scanner;

public class p3100 {
	public static void main(String args[]) throws Exception {
		Scanner scan = new Scanner(System.in);
		int b, n, a;
		int[] arr = new int[1000001];
		while (scan.hasNext()) {
			b = scan.nextInt();
			n = scan.nextInt();
			if (b == 0 && n == 0)
				break;
			a = 1;
			arr[0] = b - 1;
			for (int i = 1; i < 1000001; i++) {
				arr[i] = Math.abs((int) Math.pow(i + 1, n) - b);
				if (arr[i] > arr[i - 1]) {
					a = i;
					break;
				}
			}
			System.out.println(a);
		}
	}
}