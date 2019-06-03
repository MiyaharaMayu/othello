package othello;
import java.util.Scanner;

public class StartUp {


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// Scanner scan = new Scanner(System.in);
		// 4 * 4 のオセロ盤面の作成
		Board goban = new Board(4);
		// 先攻が黒 0
		// 後攻が白 1
		// TODO: ランダムで先攻と後攻を決めるプログラムの作成

		// TODO: ここをwhileにする。
		for(int i = 0; i < 12 ; i++) {
			// 標準入力を促す前に、今の碁盤の状況を示す
			goban.print();
			// TODO: ここで標準入力を入れる
			// 標準入力で受け取ったxy座標を入れ、正しくオセロができればtrue, そうでなければfalseを返す関数fを作り、
			// if (f(x,y)) 
			// count += 1
			// else
			// continue
			// みたいな感じにする
			int color = i%2;
			// ここら辺も直す
			goban.setColor(color);
			goban.setProt(color);
		}
		
		// TODO: break後に、どちらが勝利したか
		// 黒と白、それぞれ何コマとれたかを表示して、勝敗を教える
	}

}
