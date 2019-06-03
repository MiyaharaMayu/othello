package othello;
import java.util.Scanner;

public class Board {
	private String[][] goban = new String[4][4];
	private String bc = "●";
	private String wt = "○";
	private String putStone;
	static int MASU = 4;
	static String BLANK = "　";
	public int color;
	Scanner scan = new Scanner(System.in);

	public Board() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				goban[i][j] = "　";
			}
		}
		goban[1][2] = bc;
		goban[2][1] = bc;
		goban[1][1] = wt;
		goban[2][2] = wt;

		color = 0;
	}

	public void print() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(goban[i][j]);
				System.out.print(" ");
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
		while(canPutDown(Y,X) == false){
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
        if (canPutDown(y, x, 1, 0))
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

        // どの方向もだめな場合はここには打てない
        return false;
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
            // 空白が見つかったら打てない（１1つもはさめないから）
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
