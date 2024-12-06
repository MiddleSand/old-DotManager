package dotManager.utility;

import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

public class CacheWrapper {
	public Hashtable<String,Object> data;
	public CacheWrapper() {
		data = new Hashtable<String,Object>();
	}
	public void addData(String key, Object dataInput) {
		data.put(key, dataInput);
	}
}