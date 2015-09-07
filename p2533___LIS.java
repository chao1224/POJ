import java.io.IOException;
import java.util.Scanner;

public class p2533___LIS {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int[] arr = new int[num + 1];
		int[] d = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			d[i] = -1;
			arr[i] = scan.nextInt();
		}

		int max = 0;

		int begin, end, middle;
		for (int i = 1; i <= num; i++) {
			if (arr[i] > arr[d[max]] || i == 1 && arr[i] == 0) {
				d[++max] = i;
			} else {
				begin = 1;
				end = max;
				while (begin <= end) {
					middle = (begin + end) / 2;
					if (arr[i] > arr[d[middle]])
						begin = middle + 1;
					else
						end = middle - 1;
				}
				d[begin] = i;
			}
		}

		System.out.println(max);
	}
}
