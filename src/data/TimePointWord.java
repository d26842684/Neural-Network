package data;

import java.util.Vector;

import data.TimePointData;

public class TimePointWord {
	
	/**
	 * ���ڴ��������
	 */
	private Vector<TimePointData> points;
	
	public TimePointWord() {
		points=new Vector<TimePointData>();
	}
	
	/**
	 * ��ɼ��������һ�������������
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