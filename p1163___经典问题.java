import java.util.Scanner;

public class p1163___�������� {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int line = scan.nextInt();
		int[][] arr = new int[line][line];
		for (int i = 0; i < line; i++) {
			for (int j = 0; j <= i; j++) { // ע�� �˴��Ⱥ�ǧ�������ǣ�����
				arr[i][j] = scan.nextInt();
			}
		}
		for (int n = line - 2; n >= 0; n--) {
			for (int k = 0; k <= n; k++) {
				if (arr[n + 1][k] > arr[n + 1][k + 1]) {
					arr[n][k] += arr[n + 1][k];
				} else {
					arr[n][k] += arr[n + 1][k + 1];
				}
			}
		}
		System.out.println(arr[0][0]);
	}
}