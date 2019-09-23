package com.svm.psms.controller;

 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.svm.psms.entities.DeviceMapping;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.User;
import com.svm.psms.services.ReportService;
 

@CrossOrigin
@RestController
public class ReportController {

	@Autowired
	ReportService _service;

	@RequestMapping(method = RequestMethod.GET, value = "/operationreport/{username}/{imeinumber}/{fromdate}/{todate}")
	private ArrayList<Object> GetOperationReportData(@PathVariable String username,@PathVariable String imeinumber,@PathVariable String fromdate,
			@PathVariable String todate) {
		return this._service.getOperationReportData(username,imeinumber,Integer.parseInt(fromdate),Integer.parseInt(todate));
	}
	@RequestMapping(method = RequestMethod.GET, value = "/alertreport/{username}/{imeinumber}/{fromdate}/{todate}")
	private ArrayList<Object> GetAlertReportData(@PathVariable String username,@PathVariable String imeinumber,@PathVariable String fromdate,
			@PathVariable String todate) {
		return this._service.getAlertReportData(username,imeinumber,Integer.parseInt(fromdate),Integer.parseInt(todate));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/distributionreport/{username}/{imeinumber}/{fromdate}/{todate}")
	private ArrayList<Object> GetDistributionReportData(@PathVariable String username,@PathVariable String imeinumber,@PathVariable String fromdate,
			@PathVariable String todate) {
		return this._service.getDistributionReportData(username,imeinumber,Integer.parseInt(fromdate),Integer.parseInt(todate));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/voltagereport/{username}/{imeinumber}/{fromdate}/{todate}")
	private ArrayList<Object> GetVoltageReportData(@PathVariable String username,@PathVariable String imeinumber,@PathVariable String fromdate,
			@PathVariable String todate) {
		return this._service.getVoltageReportData(username,imeinumber,Integer.parseInt(fromdate),Integer.parseInt(todate));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "currentreport/{username}/{imeinumber}/{fromdate}/{todate}")
	private ArrayList<Object> GetCurrentReportData(@PathVariable String username,@PathVariable String imeinumber,@PathVariable String fromdate,
			@PathVariable String todate) {
		return this._service.getCurrentReportData(username,imeinumber,Integer.parseInt(fromdate),Integer.parseInt(todate));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/detailedreport/{username}/{imeinumber}/{fromdate}/{todate}")
	private ArrayList<Object> getDetailedReport(@PathVariable String username,@PathVariable String imeinumber,@PathVariable String fromdate,
			@PathVariable String todate) {
		return this._service.getDetailedReport(username,imeinumber,Integer.parseInt(fromdate),Integer.parseInt(todate));
	}
 
	@RequestMapping(method = RequestMethod.GET, value = "/wharehouselist/{username}")
	private List<Object> getWharehouselist(@PathVariable String username) {
		return this._service.getWharehouselist(username);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/imenumber/{username}")
	private String getimedetails(@PathVariable String username) {
		return this._service.getimedetails(username);
	}
 
	@RequestMapping(method = RequestMethod.PUT, value = "/devicemaster/{devicemastertype}")
	private Map<String, Object> AddDevicemaster(@RequestBody DeviceMaster deviceMaster,@PathVariable String devicemastertype) {
		return this._service.AddDevicemaster(deviceMaster,devicemastertype);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/deletedevicemaster")
	public String deleteDeviceMaster(@RequestParam("imeinumber") String imenumber) {
		return this._service.deleteDeviceMaster(imenumber);
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/devicemasterdetails")
	private List<DeviceMaster> getAllDeviceMasterDetails() {
		return this._service.getAllDeviceMasterDetails();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/devicemapping")
	private Map<String, Object> AddDeviceMapping(@RequestParam("username") String username,@RequestParam("selectedimeinumbers") String selimeinumbers,@RequestParam("devicemappingtype") String devicemappingtype,@RequestParam("id") String id) {
		return this._service.AddDeviceMapping(username,selimeinumbers,devicemappingtype,id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/devicemappingdetails")
	private List<DeviceMapping> getAllDeviceMappingDetails() {
		return this._service.getAllDeviceMappingDetails();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deletedevicemapping")
	public String deleteDeviceMaster(@RequestParam("username") String username,@RequestParam("imeinumber") String imenumber) {
		return this._service.deleteDeviceMapping(username,imenumber);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/devicemappingusernamedetails")
	private List<User> getAllUserNameListDetails() {
		return this._service.getAllUserNameListDetails();
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/allimeinumberdetails")
	private List<User> getAllDeviceDetails() {
		return this._service.getAllDeviceDetails();
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/phasedetails/{imeinumber}")
	public List getPhaseDetails(@PathVariable("imeinumber") String imeinumber) {
		return this._service.getPhaseDetails(imeinumber);
	}
	
	
}
