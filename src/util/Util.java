package util;

import data.WordMap;
import logic.BPFactory;
import logic.WordDataFactory;
import logic.WordDataMap;

public class Util {
	
	/**
	 * 通过传入地图数据和指定字符串进行训练
	 * @param map
	 * @param data
	 */
	public static void train(int map[][],String data) {
		double double_map[]=new double[WordMap.unit_width*WordMap.unit_height];
		int temp_index=0;
		for(int i=0;i<WordMap.unit_width;++i) {
			for(int j=0;j<WordMap.unit_height;++j) {
				double_map[temp_index]=map[i][j];
				temp_index++;
			}
		}
		String temp_str=WordDataFactory.StringToBinary(data);
		double double_data[]=new double[16*WordDataMap.MAX_LENTH];
		for(int i=0;i<16*WordDataMap.MAX_LENTH;++i) {
			double_data[i]=Double.parseDouble(temp_str.charAt(i)+"");
		}
		BPFactory.train(double_map, double_data);
		if(!WordDataFactory.checkContains(data)) {
			WordDataFactory.addData(data);
		}
	}
	
	/**
	 * 返回前五个匹配度最高的字符串 此算法需要改进
	 * @param map
	 * @return
	 */
	public static String[] getMatchString(int map[][]) {
		double double_map[]=new double[WordMap.unit_width*WordMap.unit_height];
		int temp_index=0;
		for(int i=0;i<WordMap.unit_width;++i) {
			for(int j=0;j<WordMap.unit_height;++j) {
				double_map[temp_index]=map[i][j];
				temp_index++;
			}
		}
		double temp_value[]=BPFactory.test(double_map);
		String str1="";
		for(int i=0;i<16*WordDataMap.MAX_LENTH;++i) {
			if (temp_value[i] > 0.5) {
				str1 += 1;
			} else {
				str1 += 0;
			}
		}
		String result[]=new String[]{"我不知道!","我不知道!","我不知道!","我不知道!","我不知道!"};
		double rank[]=new double[5];
		for(int i=0;i<WordDataFactory.getSize();++i) {
			String str2=WordDataFactory.StringToBinary(WordDataFactory.getData(i));
			double match_degree=WordDataFactory.matchDegree(str1, str2);
			for(int j=0;j<5;++j) {
				if(match_degree>rank[j]) {
					for(int k=4;k>j;k--) {
						rank[k]=rank[k-1];
						result[k]=result[k-1];
					}
					result[j]=WordDataFactory.getData(i);
					rank[j]=match_degree;
					break;
				}
			}
		}
		for(int i=0;i<5;++i) {
			System.out.print(rank[i]+"|");
		}
		System.out.println();
		return result;
	}
	
}
