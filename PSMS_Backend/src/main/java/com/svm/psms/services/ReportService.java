package com.svm.psms.services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.svm.psms.dao.MasterDAO;
import com.svm.psms.dao.ReportDAO;
import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceMapping;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.DeviceOnOffLog;
import com.svm.psms.entities.EventData;
import com.svm.psms.entities.LoadDistribution;
import com.svm.psms.entities.LoadDistributionJobStatus;
import com.svm.psms.entities.User;
import com.svm.psms.repository.LoadDIstributionJobStatusRepository;

@Service
public class ReportService {

	@Autowired
	ReportDAO repository;

	@Autowired
	MasterDAO masterRepository;

	@Autowired
	LoadDIstributionJobStatusRepository loadDistributionJobStatusRep;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public ArrayList<Object> getOperationReportData(String username, String imeinumber, Integer fromdate,
			Integer todate) {

		ArrayList<Object> table = new ArrayList<Object>();

		List<DeviceOnOffLog> donoff = null;
		System.out.println("=====> " + fromdate + "---" + todate + "---" + imeinumber + "---" + username);
		try {
			donoff = repository.getOperationReportData(username, imeinumber, fromdate, todate);
			HashMap<String, Object> tempHm;
			StringBuffer str=null;
			for (int i = 0; i < donoff.size(); i++) {
				tempHm = new HashMap<String, Object>();
				tempHm.put("imei", donoff.get(i).getImeinumber());

				java.util.Date time = new java.util.Date(((long) donoff.get(i).getDeviceontime() * 1000));
				tempHm.put("ontime", dateFormat.format(time.getTime()));

				if (donoff.get(i).getDeviceofftime() > 0) {
					time = new java.util.Date(((long) donoff.get(i).getDeviceofftime() * 1000));
					tempHm.put("offtime", dateFormat.format(time.getTime()));
				} else {
					tempHm.put("offtime", "");
				}
				int a = donoff.get(i).getDeviceontime();
				List<DeviceAlert> alert = repository.getAlert(a, donoff.get(i).getDeviceofftime(),
						donoff.get(i).getImeinumber().toString());
				str=new StringBuffer();
				System.out.print("=========>>>aaaaaaaaaaaaaaa" + alert.size());
				for (int j = 0; j < alert.size(); j++) {
					DeviceAlert dr = alert.get(j);
					if(j==alert.size()-1)
					{
					str.append(dr.getAlert());
					}else
					{
					str.append(dr.getAlert()+", ");	
					}
				}
				tempHm.put("runhours", donoff.get(i).getRunhours());
				tempHm.put("alerts", str);
				table.add(tempHm);
			}
			System.out.println("GetOperationReportData---->" + donoff.size());
		} catch (Exception ex) {
			System.out.println("exception while getting the GetOperationReportData ==> " + ex.getMessage());
			ex.printStackTrace();
		}
		return table;
	}

	public ArrayList<Object> getAlertReportData(String username, String imeinumber, Integer fromdate, Integer todate) {
		ArrayList<Object> table = new ArrayList<Object>();
		List<DeviceAlert> donoff = null;
		System.out.println("=====> " + fromdate + "---" + todate + "---" + imeinumber + "---" + username);
		try {
			List phase = repository.getPhaseDetails(imeinumber);
			System.out.println("=====>phase " + phase);
			String dvobj = (String) phase.get(0);
			System.out.println("=====>dvobj " + dvobj);
			donoff = repository.getAlertReportData(username, imeinumber, fromdate, todate);
			HashMap<String, Object> tempHm;
			for (int i = 0; i < donoff.size(); i++) {
				tempHm = new HashMap<String, Object>();
				tempHm.put("imei", donoff.get(i).getImeinumber());
				java.util.Date time = new java.util.Date(((long) donoff.get(i).getTimestamp() * 1000));
				tempHm.put("timestamp", dateFormat.format(time.getTime()));
				tempHm.put("incident", donoff.get(i).getAlert());
				if (dvobj.contains("R")) {
					tempHm.put("rv", donoff.get(i).getRphasevoltagefinal());
					tempHm.put("rc", donoff.get(i).getRphasecurrentfinal());
				}
				if (dvobj.contains("Y")) {
					tempHm.put("yv", donoff.get(i).getYphasevoltagefinal());
					tempHm.put("yc", donoff.get(i).getYphasecurrentfinal());
				}
				if (dvobj.contains("B")) {
					tempHm.put("bv", donoff.get(i).getBphasevoltagefinal());
					tempHm.put("bc", donoff.get(i).getBphasecurrentfinal());
				}
				table.add(tempHm);
			}
			System.out.println("getAlertReportData---->" + donoff.size());
		} catch (Exception ex) {
			System.out.println("exception while getting the getAlertReportData ==> " + ex.getMessage());
			ex.printStackTrace();
		}
		return table;
	}

	public ArrayList<Object> getDistributionReportData(String username, String imeinumber, Integer fromdate,
			Integer todate) {

		ArrayList<Object> table = new ArrayList<Object>();

		List<LoadDistribution> ld = null;
		System.out.println("=====> " + fromdate + "---" + todate + "---" + imeinumber);
		try {
			List phase = repository.getPhaseDetails(imeinumber);
			System.out.println("=====>phase " + phase);
			String dvobj = (String) phase.get(0);
			System.out.println("=====>dvobj " + dvobj);
			ld = repository.getDistributionReportData(username, imeinumber, fromdate, todate);
			HashMap<String, Object> tempHm;
			for (int i = 0; i < ld.size(); i++) {

				tempHm = new HashMap<String, Object>();

				java.util.Date time = new java.util.Date(((long) ld.get(i).getTimestamp() * 1000));
				tempHm.put("time", dateFormat1.format(time.getTime()));
				if (dvobj.contains("R")) {
					tempHm.put("r", ld.get(i).getRphase());
				}
				if (dvobj.contains("Y")) {
					tempHm.put("y", ld.get(i).getYphase());
				}
				if (dvobj.contains("B")) {
					tempHm.put("b", ld.get(i).getBphase());
				}

				table.add(tempHm);
			}
			System.out.println("GetDistributionReportData---->" + ld.size());
		} catch (Exception ex) {
			System.out.println("exception while getting the GetDistributionReportData ==> " + ex.getMessage());
			ex.printStackTrace();
		}
		return table;
	}

	public ArrayList<Object> getVoltageReportData(String username, String imeinumber, Integer fromdate,
			Integer todate) {

		ArrayList<Object> table = new ArrayList<Object>();

		List<EventData> ld = null;
		System.out.println("=====> " + fromdate + "---" + todate + "---" + imeinumber);
		try {
			List phase = repository.getPhaseDetails(imeinumber);
			System.out.println("=====>phase " + phase);
			String dvobj = (String) phase.get(0);
			System.out.println("=====>dvobj " + dvobj);
			ld = repository.getVoltageReportData(username, imeinumber, fromdate, todate);
			HashMap<String, Object> temp;

			for (int i = 0; i < ld.size(); i++) {

				temp = new HashMap<String, Object>();

				java.util.Date time = new java.util.Date(((long) ld.get(i).getTimestamp() * 1000));
				temp.put("time", dateFormat1.format(time.getTime()));
				if (dvobj.contains("R")) {
					temp.put("r", ld.get(i).getRphasevoltagefinal());
				}
				if (dvobj.contains("Y")) {
					temp.put("y", ld.get(i).getYphasevoltagefinal());
				}
				if (dvobj.contains("B")) {
					temp.put("b", ld.get(i).getBphasevoltagefinal());
				}
				table.add(temp);

			}
			System.out.println("getVoltageReportData---->" + ld.size());
		} catch (Exception ex) {
			System.out.println("exception while getting the getVoltageReportData ==> " + ex.getMessage());
			ex.printStackTrace();
		}
		return table;
	}

	public ArrayList<Object> getCurrentReportData(String username, String imeinumber, Integer fromdate,
			Integer todate) {

		ArrayList<Object> table = new ArrayList<Object>();

		List<EventData> ld = null;
		System.out.println("=====> " + fromdate + "---" + todate + "---" + imeinumber);
		try {
			List phase = repository.getPhaseDetails(imeinumber);
			System.out.println("=====>phase " + phase);
			String dvobj = (String) phase.get(0);
			System.out.println("=====>dvobj " + dvobj);
			ld = repository.getCurrentReportData(username, imeinumber, fromdate, todate);
			HashMap<String, Object> temp;

			for (int i = 0; i < ld.size(); i++) {

				temp = new HashMap<String, Object>();

				java.util.Date time = new java.util.Date(((long) ld.get(i).getTimestamp() * 1000));
				temp.put("time", dateFormat1.format(time.getTime()));
				if (dvobj.contains("R")) {
					temp.put("r", ld.get(i).getRphasecurrentfinal());
				}
				if (dvobj.contains("Y")) {
					temp.put("y", ld.get(i).getYphasecurrentfinal());
				}
				if (dvobj.contains("B")) {
					temp.put("b", ld.get(i).getBphasecurrentfinal());
				}

				table.add(temp);

			}
			System.out.println("getCurrentReportData---->" + ld.size());
		} catch (Exception ex) {
			System.out.println("exception while getting the getCurrentReportData ==> " + ex.getMessage());
			ex.printStackTrace();
		}
		return table;
	}

	public List<Object> getWharehouselist(String username) {

		List<DeviceMaster> dm = repository.getDeviceList(username);

		List<Object> dropdown = new ArrayList<Object>();
		for (int i = 0; i < dm.size(); i++) {
			HashMap<String, String> tmpP = new HashMap<String, String>();
			tmpP.put("label", dm.get(i).getDescription());
			tmpP.put("value", dm.get(i).getImeinumber());

			dropdown.add(tmpP);

		}
		return dropdown;
	}

	public String getimedetails(String username) {

		List<DeviceMaster> dm = repository.getDeviceList(username);

		List<Object> dropdown = new ArrayList<Object>();
		String result_ime = "";
		for (int i = 0; i < 1; i++) {
			result_ime = dm.get(i).getImeinumber();
		}
		System.out.print("==========>>>result_ime" + result_ime);
		return result_ime;
	}

	@Scheduled(fixedRate = 3600000)
	public void reportCurrentTime() {
		System.out.println("The time is now {}" + dateFormat.format(new Date()));
		getOperationReport();
	}

	public void getOperationReport() {

		int rCount = 0, yCount = 0, bCount = 0;
		Double rPhaseCurrent = 0.0, yPhaseCurrent = 0.0, bPhaseCurrent = 0.0;
		Double rPhaseVoltage = 0.0, yPhaseVoltage = 0.0, bPhaseVoltage = 0.0;

		List<DeviceMaster> device = masterRepository.getAllDevice();

		for (int j = 0; j < device.size(); j++) {
			DeviceMaster dm = device.get(j);

			System.out.println(dm.getImeinumber() + "-------------->" + dm.getDescription());

			LoadDistributionJobStatus ldjs = loadDistributionJobStatusRep.findByImeinumber(dm.getImeinumber());
			if (ldjs == null) {
				ldjs = new LoadDistributionJobStatus();
				ldjs.setImeinumber(dm.getImeinumber());
				ldjs.setLasttimestamp(1519803000);
			}
			int startTime = ldjs.getLasttimestamp();

			int currentTime = (int) (System.currentTimeMillis() / 1000);
			int loopCount = (currentTime - startTime) / 1800;

			System.out.println(startTime + "----" + currentTime + "---" + loopCount);

			for (int k = 0; k < loopCount; k++) {
				int endTime = startTime + 1800;
				ldjs.setLasttimestamp(endTime);

				List<EventData> led = repository.getEventData(dm.getImeinumber(), startTime, endTime);
				System.out.println("Total eventdata count is " + led.size());
				EventData ed;

				for (int i = 0; i < led.size(); i++) {
					ed = led.get(i);

					if (ed.getRphasecurrentfinal() > 0 || ed.getYphasecurrentfinal() > 0
							|| ed.getBphasecurrentfinal() > 0) {

						if (ed.getRphasecurrentfinal() > 0) {
							rPhaseCurrent += ed.getRphasecurrentfinal();
							rPhaseVoltage += ed.getRphasevoltagefinal();
							rCount++;
						}

						if (ed.getYphasecurrentfinal() > 0) {
							yPhaseCurrent += ed.getYphasecurrentfinal();
							yPhaseVoltage += ed.getYphasevoltagefinal();
							yCount++;

						}
						if (ed.getBphasecurrentfinal() > 0) {
							bPhaseCurrent += ed.getBphasecurrentfinal();
							bPhaseVoltage += ed.getBphasevoltagefinal();
							bCount++;
						}

					}

				}
				System.out.println(
						"Current --> " + bPhaseCurrent + "/" + bCount + "  Voltage--> " + bPhaseVoltage + "/" + bCount);

				LoadDistribution ld = new LoadDistribution();
				ld.setImeinumber(dm.getImeinumber());
				ld.setTimestamp(startTime);
				if (rPhaseCurrent > 0)
					ld.setRphase(calculateKWH(rPhaseCurrent / rCount, rPhaseVoltage / rCount));
				if (yPhaseCurrent > 0)
					ld.setYphase(calculateKWH(yPhaseCurrent / yCount, yPhaseVoltage / yCount));
				if (bPhaseCurrent > 0)
					ld.setBphase(calculateKWH(bPhaseCurrent / bCount, bPhaseVoltage / bCount));

				System.out.println("##################");
				try {

					repository.saveLoadDistribution(ld);

					repository.saveDistributionalert(ldjs);

				} catch (ParseException e) {

					e.printStackTrace();
				}

				startTime += 1800;
			}

		}

	}

	public ArrayList<Object> getDetailedReport(String username, String imeinumber, Integer fromdate, Integer todate) {

		ArrayList<Object> table = new ArrayList<Object>();
		try {
			List<EventData> ed = repository.getDetailedReport(username, imeinumber, fromdate, todate);

			System.out.println("getDetailedReport size  " + ed.size());

			HashMap<String, Integer> tempPie = new HashMap<String, Integer>();

			tempPie.put("On", 0);
			tempPie.put("Off", 0);
			HashMap<String, String> temp;

			for (int i = 0; i < ed.size(); i++) {

				temp = new HashMap<String, String>();

				java.util.Date time = new java.util.Date(((long) ed.get(i).getTimestamp() * 1000));
				temp.put("timestamp", dateFormat.format(time.getTime()));
				temp.put("status", ed.get(i).getStatus());
				if (ed.get(i).getElectricalcontractorstatus() == 0)
					temp.put("status", "Off");
				else
					temp.put("status", "On");
				temp.put("rc", Double.toString(ed.get(i).getRphasecurrentfinal()));
				temp.put("yc", Double.toString(ed.get(i).getYphasecurrentfinal()));
				temp.put("bc", Double.toString(ed.get(i).getBphasecurrentfinal()));

				temp.put("rv", Double.toString(ed.get(i).getRphasevoltagefinal()));
				temp.put("yv", Double.toString(ed.get(i).getYphasevoltagefinal()));
				temp.put("bv", Double.toString(ed.get(i).getBphasevoltagefinal()));
				table.add(temp);

			}
			ArrayList<Object> pie = new ArrayList<Object>();

			for (String key : tempPie.keySet()) {
				HashMap<String, Object> tmpP = new HashMap<String, Object>();
				tmpP.put("status", key);
				tmpP.put("count", tempPie.get(key));
				pie.add(tmpP);
			}

		} catch (Exception ex) {
			System.out.println("exception while getting the score Card details ==> " + ex.getMessage());
			ex.printStackTrace();
		}
		return table;
	}

	DecimalFormat df = new DecimalFormat("#.00");

	private double calculateKWH(Double val1, Double val2) {
		Double val3 = (val1 * val2) / 1000;
		return Double.parseDouble(df.format(val3));
	}

	public Map<String, Object> AddDevicemaster(DeviceMaster deviceMaster, String devicemastertype) {
		return masterRepository.AddDevicemaster(deviceMaster, devicemastertype);
	}

	public String deleteDeviceMaster(String imenumber) {

		return masterRepository.deleteDeviceMaster(imenumber);
	}

	public List getAllDeviceMasterDetails() {
		return masterRepository.getAllDeviceMasterDetails();
	}

	public Map<String, Object> AddDeviceMapping(String username, String selimeinumbers, String devicemappingtype,
			String id) {
		return masterRepository.AddDeviceMapping(username, selimeinumbers, devicemappingtype, id);
	}

	public List getAllDeviceMappingDetails() {
		return masterRepository.getAllDeviceMappingDetails();
	}

	public String deleteDeviceMapping(String username, String imenumber) {
		return masterRepository.deleteDeviceMapping(username, imenumber);
	}

	public List getAllUserNameListDetails() {
		return masterRepository.getAllUserNameListDetails();
	}

	public List getAllDeviceDetails() {
		return masterRepository.getAllDeviceDetails();
	}

	public List getPhaseDetails(String imenumber) {
		List phase=null;
		String dvobj = null;
		List buf=null;
		try {
			phase = repository.getPhaseDetails(imenumber);
			buf=new ArrayList();
			if(phase!=null && phase.size()>0)
			{
				dvobj=phase.get(0).toString();
				String temp[]=dvobj.split(",");
				for(int i=0;i<temp.length;i++)
				{
					buf.add(temp[i]);
				}	
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return buf;
	}

}
