package data;

/**
 * save digit info
 * 
 * @author MyGhost
 * 
 */
public class WordMap {

	/**
	 * word map info
	 */
	private int data[][];

	/**
	 * word map info after fixed
	 */
	private int scan_data[][];

	/**
	 * format word map info
	 */
	private int format_data[][];

	/**
	 * digit position info
	 */
	private int start_w, end_w, start_h, end_h, word_w, word_h;

	/**
	 * standard map size
	 */
	public static final int unit_width = 28, unit_height = 28;

	/**
	 * map size
	 */
	private final int width, height;

	public WordMap(int width, int height) {
		data = new int[width][height];
		format_data = new int[unit_width][unit_height];
		this.width = width;
		this.height = height;
	}

	/**
	 * To gain word map info
	 * 
	 * @return word map info
	 */
	public int[][] getMap() {
		return data;
	}

	/**
	 * To check if there is any point in the area
	 * 
	 * @param start_x
	 * @param start_y
	 * @param end_x
	 * @param end_y
	 * @return
	 */
	private boolean checkContain(int start_x, int start_y, int end_x, int end_y) {
		for (int i = start_x; i < end_x; ++i) {
			for (int j = start_y; j < end_y; ++j) {
				if (scan_data[i][j] == 1)
					return true;
			}
		}
		return false;
	}

	/**
	 * copy the format map to the show board
	 */
	public int[][] copyMap() {
		data = new int[width][height];
		int temp_x, temp_y;
		temp_x = (width - unit_width) / 2;
		temp_y = (height - unit_height) / 2;
		for (int i = 0; i < unit_width; ++i) {
			for (int j = 0; j < unit_height; ++j) {
				data[temp_x + i][temp_y + j] = format_data[i][j];
			}
		}
		return format_data;
	}

	/**
	 * clear
	 */
	public void resetMap() {
		data = new int[width][height];
		scan_data=null;
		format_data=new int[unit_width][unit_height];
	}

	/**
	 * set the position
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean setPoint(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return false;
		if (data[x][y] != 1) {
			data[x][y] = 1;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 
	 * @return wor map
	 */
	public int[][] getWordMap() {
		scan_data = new int[word_w][word_h];
		for (int i = 0; i < word_w; ++i) {
			for (int j = 0; j < word_h; ++j) {
				scan_data[i][j] = data[start_w + i][start_h + j];
			}
		}
		return scan_data;
	}

	/**
	 * 
	 * @return format word map info
	 */
	public int[][] getFormateMap() {
		float scale_w, scale_h;
		scale_w = (float) word_w / (float) unit_width;
		scale_h = (float) word_h / (float) unit_height;
		for (int i = 0; i < unit_width; ++i) {
			for (int j = 0; j < unit_height; ++j) {
				if (checkContain((int) (i * scale_w), (int) (j * scale_h),
						(int) ((i + 1) * scale_w), (int) ((j + 1) * scale_h))) {
					format_data[i][j] = 1;
				}
			}
		}
		return format_data;
	}

	/**
	 * analyze every pixel to get the word width
	 * 
	 * @return word width
	 */
	public int getWordWidth() {
		start_w = 0;
		for (int i = 0; i < width; ++i) {
			boolean continue_key = false;
			for (int j = 0; j < height; ++j) {
				if (data[i][j] == 1) {
					continue_key = true;
					break;
				}
			}
			if (continue_key) {
				start_w = i;
				break;
			}
		}
		end_w = 0;
		for (int i = width - 1; i >= 0; --i) {
			boolean continue_key = false;
			for (int j = 0; j < height; ++j) {
				if (data[i][j] == 1) {
					continue_key = true;
					break;
				}
			}
			if (continue_key) {
				end_w = i;
				break;
			}
		}
		word_w = end_w - start_w;
		return word_w;
	}

	/**
	 * analyze every pixel to get the word height
	 * 
	 * @return word height
	 */
	public int getWordHeight() {
		start_h = 0;
		for (int i = 0; i < height; ++i) {
			boolean continue_key = false;
			for (int j = 0; j < width; ++j) {
				if (data[j][i] == 1) {
					continue_key = true;
					break;
				}
			}
			if (continue_key) {
				start_h = i;
				break;
			}
		}
		end_h = 0;
		for (int i = height - 1; i >= 0; --i) {
			boolean continue_key = false;
			for (int j = 0; j < width; ++j) {
				if (data[j][i] == 1) {
					continue_key = true;
					break;
				}
			}
			if (continue_key) {
				end_h = i;
				break;
			}
		}
		word_h = end_h - start_h;
		return word_h;
	}

}