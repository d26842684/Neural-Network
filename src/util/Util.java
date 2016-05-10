package util;

import data.WordMap;
import logic.BPFactory;
import logic.WordDataFactory;
import logic.WordDataMap;

public class Util {
	
	/**
	 * train by the specific map data and String
	 * @param map
	 * @param data
	 */
	public static double[] train(int map[][],String data) {
		double double_map[]=new double[WordMap.unit_width*WordMap.unit_height];
		int temp_index=0;
		for(int i=0;i<WordMap.unit_width;++i) {
			for(int j=0;j<WordMap.unit_height;++j) {
				double_map[temp_index]=map[i][j];
				temp_index++;
			}
		}
		int i;
		i=Integer.parseInt(data);
		
		//String temp_str=WordDataFactory.StringToBinary(data);
		double double_data[]=new double[10];
		double_data[i]=1;
		for(int a=0;a<10;a++){
			System.out.print(double_data[a]);
		}
		System.out.println();
		//for(int i=0;i<10;++i) {
			//double_data[i]=Double.parseDouble(temp_str.charAt(i)+"");
		//}
		double[] d = BPFactory.train(double_map, double_data);
		if(!WordDataFactory.checkContains(data)) {
			WordDataFactory.addData(data);
		}
		return d;
	}
	
	/**
	 * 
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
		double high=0;
		int index=0;
		for(int i=0;i<10;i++){
			if (temp_value[i]>high){
				high=temp_value[i];
				index=i;}
			System.out.println(temp_value[i]);
			
		}
		String[] s = {temp_value[0]+"",temp_value[1]+"",temp_value[2]+"",temp_value[3]+"",temp_value[4]+"",temp_value[5]+"",temp_value[6]+"",temp_value[7]+"",temp_value[8]+"",temp_value[9]+"",index+""};
		String out ="highest output"+high+" number "+index;
		System.out.println(out);
		String str1="";
		for(int i=0;i<10;++i) {
			if (temp_value[i] > 0.5) {
				str1 += 1;
			} else {
				str1 += 0;
			}
		}
		String result[]=new String[]{"None","None","None","None","None"};
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
//		for(int i=0;i<5;++i) {
//			System.out.print(rank[i]+"|");
//		}
		//System.out.println();
		return s;
	}
	
}
