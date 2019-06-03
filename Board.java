package othello;
import java.util.Scanner;

// あくまでこれはオセロの局面を決めるクラスとし、
// 標準入力などの部分はこのクラスとは分けて記述すると良い
public class Board {
    // TODO: 白と黒のコマ数を保存するようなものを用意しても良いかもしれない
	private String[][] goban = new String[4][4];
	private String bc = "●";
	private String wt = "○";
	private String putStone;
	static int MASU = 4;
	static String BLANK = "　";
	public int color = 0;
    // TODO: Scannerはメイン関数に
	Scanner scan = new Scanner(System.in);

	public Board(int len) {
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				goban[i][j] = "　";
			}
		}
        // 中央の座標
        int x = len/2;
        // 中央に配置
		goban[x-1][x] = bc;
		goban[x][x-1] = bc;
		goban[x-1][x-1] = wt;
		goban[x][x] = wt;
	}

	public void print() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
                // TODO:
                // printをわかりやすくし、
                // 行数、列数を表示
                // どこが打つことが可能かを明示
				System.out.print(goban[i][j]);
				System.out.print("|");
			}
			System.out.print("\n");
		}
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setProt(int color) {
		int Y = 2;
		int X = 2;
        // TODO: whileの条件がおかしい
        // ここは、コマが全て起き終わるまでを条件とする
        // つまり、4*4の場合、12コマ置かれたらwhileを抜ける
        // 標準入力を受ける部分は、全てmain関数に持っていく
		while(canPutDown(Y,X) == false){
            // TODO: 今が白か黒かを教える
			System.out.println("行の数字を入力してください");	//プロットしてはいけないとき
			Y = scan.nextInt();
			System.out.println("列の数字を入力してください");
			X = scan.nextInt();
			if(canPutDown(Y, X)) {
				//以下trueだったとき
				if(color == 0) {//黒バージョン
					goban[Y][X] = bc;
					this.print();
				}else {				//白バージョン
					goban[Y][X] = wt;
					this.print();
				}
			}
			System.out.println("そこには置けません");
		}
	}

	public boolean canPutDown(int y, int x) {
        // (x,y)が盤面の外だったら打てない
        if (x >= MASU || y >= MASU)
            return false;
        // (x,y)にすでに石が打たれてたら打てない
        if (goban[y][x] != BLANK)
            return false;
        // 8方向のうち一箇所でもひっくり返せればこの場所に打てる
        // ひっくり返せるかどうかはもう1つのcanPutDownで調べる

        int dx = [0, 1, 0, -1, 1, -1, -1, 1];
        int dy = [1, 0, -1, 0, 1, -1, 1, -1];
        for (int i = 0; i < 8; i++) {
            int vec_x = dx[i];
            int vec_y = dy[i];
            if (canPutDown(y, x, vec_y, vec_x)) {
                return true;
            }    
        }
        // どの方向もだめな場合はここには打てない
        return false;
        /*if (canPutDown(y, x, 1, 0))
            return true; // 右
        if (canPutDown(y, x, 0, 1))
            return true; // 下
        if (canPutDown(y, x, -1, 0))
            return true; // 左
        if (canPutDown(y, x, 0, -1))
            return true; // 上
        if (canPutDown(y, x, 1, 1))
            return true; // 右下
        if (canPutDown(y, x, -1, -1))
            return true; // 左上
        if (canPutDown(y, x, 1, -1))
            return true; // 右上
        if (canPutDown(y, x, -1, 1))
            return true; // 左下
        */
    }

	public boolean canPutDown(int y, int x, int vecY, int vecX) {
		if (color == 0) {
            putStone = bc;
        } else {
            putStone = wt;
        }
		// 隣の場所へ。どの隣かは(vecX, vecY)が決める。
        x += vecX;
        y += vecY;
        // 盤面外だったら打てない
        if (x < 0 || x >= MASU || y < 0 || y >= MASU)
            return false;
        // 隣が自分の石の場合は打てない
        if (goban[y][x] == putStone)
            return false;
        // 隣が空白の場合は打てない
        if (goban[y][x] == BLANK)
            return false;

        // さらに隣を調べていく
        x += vecX;
        y += vecY;
        // となりに石がある間ループがまわる
        while (x >= 0 && x < MASU && y >= 0 && y < MASU) {
            // 空白が見つかったら打てない（1つもはさめないから）
            if (goban[y][x] == BLANK)
                return false;
            // 自分の石があればはさめるので打てる
            if (goban[y][x] == putStone)
                return true;
            x += vecX;
            y += vecY;
        }
        // 相手の石しかない場合はいずれ盤面の外にでてしまうのでこのfalse
        return false;
	}
}
