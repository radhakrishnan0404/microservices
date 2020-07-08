package com.svm.psms.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceMaster;



public interface PsmsDAO {
 
public Map<String, Object> AddEventData(String data);
//public List<DeviceAlert> getAlert(int fromdate, int todate,String imeinumber) throws ParseException ;

 
}
