package logic;

import java.io.Serializable;
import java.util.ArrayList;

public class WordDataMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> data;

	/**
	 * 能够接受的最长的字符串
	 */
	public static final int MAX_LENTH = 10;

	WordDataMap() {
		data = new ArrayList<String>();
	}

	/**
	 * 返回一个指定位置的字符串
	 * 
	 * @param index
	 * @return
	 */
	public String getData(int index) {
		if (index >= data.size() || index < 0) {
			return "尼玛！乱来";
		} else {
			return data.get(index);
		}
	}

	/**
	 * 检查容器内是否有指定字符串，如果有则返回true否则返回false
	 * 
	 * @param str
	 * @return
	 */
	public boolean checkContains(String str) {
		for (int i = 0; i < data.size(); ++i) {
			if (str.equals(data.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回当前数据词典的大小
	 * 
	 * @return
	 */
	public int getSize() {
		return data.size();
	}

	/**
	 * 向字符数据词典中添加一个字符串 如果长度大于MAX_LENTH则会自动裁剪
	 * 
	 * @param str
	 */
	public void addData(String str) {
		if (str.length() > MAX_LENTH)
			str = str.substring(0, MAX_LENTH);
		data.add(str);
	}
}