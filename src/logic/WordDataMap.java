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
	 * the max String length
	 */
	public static final int MAX_LENTH = 10;

	WordDataMap() {
		data = new ArrayList<String>();
	}

	/**
	 * get a specific String
	 * 
	 * @param index
	 * @return
	 */
	public String getData(int index) {
		if (index >= data.size() || index < 0) {
			return "";
		} else {
			return data.get(index);
		}
	}

	/**
	 * check if there is specific string in the container
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
	 * get current data size
	 * 
	 * @return
	 */
	public int getSize() {
		return data.size();
	}

	/**
	 * add string to data dictionary
	 * 
	 * @param str
	 */
	public void addData(String str) {
		if (str.length() > MAX_LENTH)
			str = str.substring(0, MAX_LENTH);
		data.add(str);
	}
}