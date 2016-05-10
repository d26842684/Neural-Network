package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WordDataFactory {

	private static WordDataMap data;

	/**
	 * �޲�����ʼ��
	 */
	public static void initialization() {
		data = new WordDataMap();
	}

	/**
	 * �Ӵ���õ������г�ʼ��
	 * 
	 * @param file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void initialization(File file) throws IOException,
			ClassNotFoundException {
		FileInputStream fi = new FileInputStream(file);
		ObjectInputStream si = new ObjectInputStream(fi);
		data = (WordDataMap) si.readObject();
		si.close();
	}

	/**
	 * ��Ŀǰ�������ֵ䴢�浽�ļ���
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void save(File file) throws IOException {
		FileOutputStream fo = new FileOutputStream(file);
		ObjectOutputStream so = new ObjectOutputStream(fo);
		so.writeObject(data);
		so.close();
	}

	/**
	 * �������ֵ����������
	 * 
	 * @param str
	 */
	public static void addData(String str) {
		data.addData(str);
	}

	/**
	 * ����һ��ָ��λ�õ��ַ���
	 * 
	 * @param index
	 * @return
	 */
	public static String getData(int index) {
		return data.getData(index);
	}

	/**
	 * �ַ���ת�����ܹ�ƥ��Ķ����� ����ΪMAX_LENTH*16
	 * 
	 * @param data
	 * @return
	 */
	public static String StringToBinary(String data) {
		String result = "";
		for (int i = 0; i < data.length(); ++i) {
			int temp_char = (int) data.charAt(i);
			String temp_string = Integer.toBinaryString(temp_char);
			result = temp_string + result;
			for (int j = 16 - temp_string.length(); j > 0; --j) {
				result = "0" + result;
			}
		}
		for (int i = data.length(); i < WordDataMap.MAX_LENTH; ++i) {
			result = "0000000000000000" + result;
		}
		return result;
	}

	/**
	 * ���������ַ�����ƥ��� ��ƥ���㷨�д��Ľ�
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double matchDegree(String str1, String str2) {
		double base = str1.length();
		double matchs = 0;
		for (int i = 0; i < base; ++i) {
			if (str1.charAt(i) == str2.charAt(i)) {
				matchs++;
			}
		}
		return matchs / base;
	}

	/**
	 * ����������Ƿ���ָ���ַ�����������򷵻�true���򷵻�false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkContains(String str) {
		return data.checkContains(str);
	}

	/**
	 * ���ص�ǰ���ݴʵ�Ĵ�С
	 * 
	 * @return
	 */
	public static int getSize() {
		return data.getSize();
	}
	
}
