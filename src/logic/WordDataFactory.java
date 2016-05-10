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
	 * 无参数初始化
	 */
	public static void initialization() {
		data = new WordDataMap();
	}

	/**
	 * 从储存好的数据中初始化
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
	 * 将目前的数据字典储存到文件中
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
	 * 向数据字典中添加数据
	 * 
	 * @param str
	 */
	public static void addData(String str) {
		data.addData(str);
	}

	/**
	 * 返回一个指定位置的字符串
	 * 
	 * @param index
	 * @return
	 */
	public static String getData(int index) {
		return data.getData(index);
	}

	/**
	 * 字符串转换成能够匹配的二进制 长度为MAX_LENTH*16
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
	 * 返回两个字符串的匹配度 此匹配算法有待改进
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
	 * 检查容器内是否有指定字符串，如果有则返回true否则返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkContains(String str) {
		return data.checkContains(str);
	}

	/**
	 * 返回当前数据词典的大小
	 * 
	 * @return
	 */
	public static int getSize() {
		return data.getSize();
	}
	
}
