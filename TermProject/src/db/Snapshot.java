package db;

public class Snapshot {

	/** snapshot */
	private Version version;
	
	/** snapshot id */
	private int sId;
	
	/** comment of snapshot */
	private String scomment;
	
	@Override
	public String toString() {
		return "Snapshot [snapshot=" + version.getDiagram() + ", snapshot id=" + sId + "snapshot comments=" + scomment + "]";
	}
	
	/**
	 * constructor to set snapshot and version.
	 * 
	 * @param 
	 * @return 
	 */
	public Snapshot(){
		
	}
	
	public Snapshot(Version v, int sid, String sc) {
		
		this.version = v;
		this.sId = sid;
		this.scomment = new String(sc);
		
	}

	public Version getVersion() {
		return version;
	}

	public int getsId() {
		return sId;
	}

	public void setVersion(Version v) {
		this.version = v;
	}

	public void setsID(int sid) {
		this.sId = sid;
	}
}
