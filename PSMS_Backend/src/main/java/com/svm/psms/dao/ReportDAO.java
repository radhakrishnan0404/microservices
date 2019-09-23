package com.svm.psms.dao;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.DeviceOnOffLog;
import com.svm.psms.entities.EventData;
import com.svm.psms.entities.LoadDistribution;
import com.svm.psms.entities.LoadDistributionJobStatus;

public interface ReportDAO {

	public List<DeviceOnOffLog> getOperationReportData(String username,String imeinumber,int fromdate,int todate) throws ParseException;
	
	public List<EventData> getDetailedReport(String username,String imeinumber,int fromdate,int todate) throws ParseException;
	
	public List<LoadDistribution> getDistributionReportData(String username,String imeinumber,int fromdate,int todate) throws ParseException;
	
	public List<DeviceMaster> getDeviceList(String username);
	
	public List<EventData> getEventData(String imeinumber,int start,int end);
	
	public String saveLoadDistribution(LoadDistribution ld) throws ParseException ;
	
	public String saveDistributionalert(LoadDistributionJobStatus ld) throws ParseException ;
	
	public List<DeviceAlert> getAlertReportData(String username,String imeinumber,int fromdate,int todate) throws ParseException;
	
	public List<EventData> getVoltageReportData(String username,String imeinumber,int fromdate,int todate) throws ParseException;
	
	public List<EventData> getCurrentReportData(String username,String imeinumber,int fromdate,int todate) throws ParseException;
	
	public List getPhaseDetails(String imeinumber) throws ParseException ;
	
	public List<DeviceAlert> getAlert(int fromdate, int todate,String imeinumber) throws ParseException ;


}
