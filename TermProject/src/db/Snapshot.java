package db;

public class Snapshot {

	/** snapshot */
	private Version version;
	
	/** snapshot id */
	private int sId;
	
	/** comment of snapshot */
	private String scomment;

	/**
	 * constructor to set snapshot and version.
	 * 
	 * @param 
	 * @return 
	 */
	public Snapshot(){
		
	}
	
	/**
	 * constructor to set snapshot and version.
	 * 
	 * @param Version version number.
	 * @param integer snapshot id.
	 * @param String snapshot comment.
	 * @return 
	 */
	public Snapshot(Version v, int sid, String sc) {
		
		this.version = v;
		this.sId = sid;
		this.scomment = new String(sc);
		
	}
	
	/**
	 * Make snapshot to String.
	 * 
	 * @param 
	 * @return String Snapshot information.
	 */
	@Override
	public String toString() {
		return "Snapshot [snapshot=" + version.getDiagram() + ", snapshot id=" + sId + "snapshot comments=" + scomment + "]";
	}
	
	/**
	 * get version of snapshot.
	 * 
	 * @param 
	 * @return Version version of snapshot.
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * get snapshot ID.
	 * 
	 * @param 
	 * @return integer sid.
	 */
	public int getsId() {
		return sId;
	}
	
	/**
	 * get snapshot comment.
	 * 
	 * @param 
	 * @return String scomment.
	 */
	public String getScomment() {
		return scomment;
	}

}
