package bricBreaker;

public class MapGenrator {
	public int map[][];
	public int brickwidth;
	public int brickheight;
	public MapGenrator(int row, int col) {
		map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=1;
			}
		}
	}
}
