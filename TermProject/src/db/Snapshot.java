package db;

public class Snapshot {

	/** snapshot */
	private Diagram snapshot;
	
	@Override
	public String toString() {
		return "Snapshot [snapshot=" + snapshot + ", version=" + version + "]";
	}

	/** version */
	private int version;
	
	/**
	 * constructor to set snapshot and version.
	 * 
	 * @param .
	 * @return 
	 */
	public Snapshot(){
		
	}
	public Snapshot(Diagram s, int v) {
		
		this.snapshot = s;
		this.version = v;
		
	}

	public Diagram getSnapshot() {
		return snapshot;
	}

	public int getVersion() {
		return version;
	}

	public void setSnapshot(Diagram snapshot) {
		this.snapshot = snapshot;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
