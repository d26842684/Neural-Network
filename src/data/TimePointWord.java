package data;

import java.util.Vector;

import data.TimePointData;

public class TimePointWord {
	
	/**
	 * 用于储存坐标点
	 */
	private Vector<TimePointData> points;
	
	public TimePointWord() {
		points=new Vector<TimePointData>();
	}
	
	/**
	 * 向采集库中添加一个单独的坐标点
	 * @param x
	 * @param y
	 * @param z
	 * @param index
	 */
	public void addPoint(int x,int y,int z,int index) {
		points.add(new TimePointData(x, y, z, index));
	}
	
	public void analysis() {
		for(int i=0;i<points.size();++i) {
			
		}
	}
}