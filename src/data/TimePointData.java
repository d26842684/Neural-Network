package data;

public class TimePointData {
	
	/**
	 * �ֱ����ڼ�¼���x��y�����Լ�z���ѹ��ֵ
	 */
	public int x,y,z;
	
	/**
	 * ���ڼ�¼x,y,z�ϵ��ٶȷ���
	 */
	public double vx,vy,vz;
	
	/**
	 * ������
	 */
	public long index;
	
	public TimePointData(int x,int y,int z,int index) {
		this.x=x;
		this.y=y;
		this.z=z;
		this.index=index;
	}

}
