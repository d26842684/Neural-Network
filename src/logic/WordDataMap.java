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
	 * �ܹ����ܵ�����ַ���
	 */
	public static final int MAX_LENTH = 10;

	WordDataMap() {
		data = new ArrayList<String>();
	}

	/**
	 * ����һ��ָ��λ�õ��ַ���
	 * 
	 * @param index
	 * @return
	 */
	public String getData(int index) {
		if (index >= data.size() || index < 0) {
			return "���꣡����";
		} else {
			return data.get(index);
		}
	}

	/**
	 * ����������Ƿ���ָ���ַ�����������򷵻�true���򷵻�false
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
	 * ���ص�ǰ���ݴʵ�Ĵ�С
	 * 
	 * @return
	 */
	public int getSize() {
		return data.size();
	}

	/**
	 * ���ַ����ݴʵ������һ���ַ��� ������ȴ���MAX_LENTH����Զ��ü�
	 * 
	 * @param str
	 */
	public void addData(String str) {
		if (str.length() > MAX_LENTH)
			str = str.substring(0, MAX_LENTH);
		data.add(str);
	}
}