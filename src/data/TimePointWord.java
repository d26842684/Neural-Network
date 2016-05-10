package data;

import java.util.Vector;

import data.TimePointData;

public class TimePointWord {
	
	/**
	 * save coordinate point info
	 */
	private Vector<TimePointData> points;
	
	public TimePointWord() {
		points=new Vector<TimePointData>();
	}
	
	/**
	 * add single coordinate point to dataset
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