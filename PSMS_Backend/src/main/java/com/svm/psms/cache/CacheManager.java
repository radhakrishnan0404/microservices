package com.svm.psms.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.svm.psms.dao.MasterDAO;
import com.svm.psms.entities.DeviceMaster;

public class CacheManager {

	private static Map<String, DeviceMaster> hmDevice = new HashMap<String, DeviceMaster>();

	static {
		loadMasterData();
	}
	
	@Autowired
	static
	MasterDAO repository;

	private static void loadMasterData() {
		System.out.println("Loading master data");
		List<DeviceMaster> device = new ArrayList<DeviceMaster>();

		try {
			device = repository.getAllDevice();

			for (DeviceMaster Obj : device) {
				hmDevice.put(Obj.getImeinumber(), Obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static DeviceMaster getDeviceDetails(String imeinumber) {
		if (hmDevice == null || hmDevice.containsKey(imeinumber))
			loadMasterData();
		return hmDevice.get(imeinumber);

	}

	public static void RefreshCache() {
		hmDevice = new HashMap<String, DeviceMaster>();
		loadMasterData();
	}
}
