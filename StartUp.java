package othello;
import java.util.Scanner;

public class StartUp {


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		Board goban = new Board();
		goban.print();
		for(int i = 0; i < 12 ; i++) {
			int color = i%2;
			goban.setColor(color);
			goban.setProt(color);
		}
	}

}
