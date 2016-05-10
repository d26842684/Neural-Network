package src.ghost.writer.data;

public class TimePointData {
	
	/**
	 * 分别用于记录点的x，y坐标以及z轴的压力值
	 */
	public int x,y,z;
	
	/**
	 * 用于记录x,y,z上的速度分量
	 */
	public double vx,vy,vz;
	
	/**
	 * 点的序号
	 */
	public long index;
	
	public TimePointData(int x,int y,int z,int index) {
		this.x=x;
		this.y=y;
		this.z=z;
		this.index=index;
	}

}
