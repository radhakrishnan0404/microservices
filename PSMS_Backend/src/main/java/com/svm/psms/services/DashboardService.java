package com.svm.psms.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.svm.psms.dao.DashboardDAO;
import com.svm.psms.dao.MasterDAO;
import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.DeviceStatus;

@Service
public class DashboardService {

	@Autowired
	DashboardDAO repository;

	@Autowired
	MasterDAO masterRepository;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

	public Map<String, Object> GetDashboardData(String username) {
		Map<String, Object> hm = new HashMap<String, Object>();
		try {

			List<DeviceStatus> list = repository.GetDashboardData(username);

			List<DeviceMaster> device = masterRepository.getAllDevice();

			System.out.println("Total no of device  " + device.size());
			ArrayList<Object> table = new ArrayList<Object>();

			HashMap<String, Integer> tempPie = new HashMap<String, Integer>();

			tempPie.put("On", 0);
			tempPie.put("Off", 0);
			HashMap<String, String> temp;

			for (int i = 0; i < list.size(); i++) {
				if (tempPie.containsKey(list.get(i).getStatus())) {
					tempPie.put(list.get(i).getStatus(), tempPie.get(list.get(i).getStatus()) + 1);
				} else {
					tempPie.put(list.get(i).getStatus(), 1);
				}

				temp = new HashMap<String, String>();
				for (int j = 0; j < device.size(); j++) {
					if (device.get(j).getImeinumber().equalsIgnoreCase(list.get(i).getImeinumber())) {

						temp.put("warehouse", device.get(j).getDescription());

						java.util.Date time = new java.util.Date(((long) list.get(i).getTimestamp() * 1000));

						temp.put("timestamp", dateFormat.format(time.getTime()));
						temp.put("status", list.get(i).getStatus());
						temp.put("rc", Double.toString(list.get(i).getRphasecurrentfinal()));
						temp.put("yc", Double.toString(list.get(i).getYphasecurrentfinal()));
						temp.put("bc", Double.toString(list.get(i).getBphasecurrentfinal()));

						temp.put("rv", Double.toString(list.get(i).getRphasevoltagefinal()));
						temp.put("yv", Double.toString(list.get(i).getYphasevoltagefinal()));
						temp.put("bv", Double.toString(list.get(i).getBphasevoltagefinal()));
						table.add(temp);
					}
				}

			}
			ArrayList<Object> pie = new ArrayList<Object>();

			for (String key : tempPie.keySet()) {
				HashMap<String, Object> tmpP = new HashMap<String, Object>();
				tmpP.put("status", key);
				tmpP.put("count", tempPie.get(key));
				pie.add(tmpP);
			}

			hm.put("pie", pie);
			hm.put("lateststatus", table);
			ArrayList<Object> alerttable = new ArrayList<Object>();
			List<DeviceAlert> alert = repository.GetLatestAlertData(username);

			
			for (int i = 0; i < alert.size(); i++) {
				//System.out.println("---"+alert.get(i).getImeinumber());
				temp = new HashMap<String, String>();
				for (int j = 0; j < device.size(); j++) {
					if (device.get(j).getImeinumber().equalsIgnoreCase(alert.get(i).getImeinumber())) {

						temp.put("warehouse", device.get(j).getDescription());

						java.util.Date time = new java.util.Date(((long) alert.get(i).getTimestamp() * 1000));

						temp.put("timestamp", dateFormat.format(time.getTime()));
						temp.put("status", alert.get(i).getAlert());
						alerttable.add(temp);
					}
				}
			}
			hm.put("alert", alerttable);

		} catch (Exception ex) {
			System.out.println("exception while getting the score Card details ==> " + ex.getMessage());
			ex.printStackTrace();
		}
		return hm;
	}

}
