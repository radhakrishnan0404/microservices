package com.svm.psms.dao;

import java.util.List;
import java.util.Map;

import com.svm.psms.entities.DeviceMapping;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.User;

public interface MasterDAO {
 
public List<DeviceMaster> getAllDevice();

public Map<String, Object> AddDevicemaster(DeviceMaster deviceMaster, String devicemastertype);

public String deleteDeviceMaster(String imenumber);

public List getAllDeviceMasterDetails();

public Map<String, Object> AddDeviceMapping(String username, String selimeinumbers, String devicemappingtype,String id);

public List getAllDeviceMappingDetails();

public String deleteDeviceMapping(String username, String imeinumber);

public List<User> getAllUserNameListDetails();
 
public List<DeviceMaster> getAllDeviceDetails();
}
