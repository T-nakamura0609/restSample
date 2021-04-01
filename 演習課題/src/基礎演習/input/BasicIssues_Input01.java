package 基礎演習.input;

import java.util.Scanner;

public class BasicIssues_Input01 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("何か数字を入力してください。");

		Scanner sc = new Scanner(System.in);
		int tmp = sc.nextInt();
		System.out.print("入力した数字は:" + tmp + " です。");
		sc.close();

	}

}
