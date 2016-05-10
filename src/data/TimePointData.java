package data;

public class TimePointData {
	
	/**
	 * record the x,y,z position
	 */
	public int x,y,z;
	
	/**
	 * record x,y,z speed vector
	 */
	public double vx,vy,vz;
	
	/**
	 * index
	 */
	public long index;
	
	public TimePointData(int x,int y,int z,int index) {
		this.x=x;
		this.y=y;
		this.z=z;
		this.index=index;
	}

}
