package com.svm.psms.implementation;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.transaction.Transactional;	
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import com.svm.psms.cache.CacheManager;
import com.svm.psms.dao.PsmsDAO;
import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.DeviceOnOffLog;
import com.svm.psms.entities.DeviceStatus;
import com.svm.psms.entities.EventData;
import com.svm.psms.entities.User;
import com.svm.psms.repository.DeviceStatusRepository;
import com.svm.psms.repository.DeviceMasterRepository;
import com.svm.psms.repository.DeviceOnOffLogRepository;

@Repository
public class PsmsDAOImpl implements PsmsDAO {

	@Autowired
	private SessionFactory _session;

	@Autowired
	DeviceStatusRepository deviceStatusRepository;

	@Autowired
	DeviceOnOffLogRepository deviceOnOffLogRepository;

	@Autowired
	DeviceMasterRepository deviceMaster;

	SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm a");

	@Override
	@Transactional
	public Map<String, Object> AddEventData(String data) {
		Map<String, Object> hmRes = new HashMap<String, Object>();
		try {

			List<String> myList = new ArrayList<String>(Arrays.asList(data.split(",")));

			EventData edata = new EventData();
			edata.setImeinumber(myList.get(1));
			System.out.println("data--> " + data);

			int timestamp = Integer.parseInt(myList.get(2));

			edata.setTimestamp(timestamp);

			edata.setStatus(myList.get(3));
			edata.setTamper(Integer.parseInt(myList.get(8)));
			edata.setExtsupply(Double.parseDouble(myList.get(9)));
			edata.setIntsupply(Double.parseDouble(myList.get(10)));
			edata.setLatitude(Double.parseDouble(myList.get(4)));
			edata.setLongitude(Double.parseDouble(myList.get(5)));
			edata.setGpssignal(Integer.parseInt(myList.get(11)));
			edata.setGsmsignal(Integer.parseInt(myList.get(12)));

			/// current
			edata.setRphasecurrent(Double.parseDouble(myList.get(17)));
			edata.setRphasecurrentfinal(calculateVoltage(myList.get(17)));

			edata.setYphasecurrent(Double.parseDouble(myList.get(18)));
			edata.setYphasecurrentfinal(calculateVoltage(myList.get(18)));

			edata.setBphasecurrent(Double.parseDouble(myList.get(19)));
			edata.setBphasecurrentfinal(calculateVoltage(myList.get(19)));

			/// voltage
			edata.setRphasevoltage(Double.parseDouble(myList.get(20)));
			edata.setRphasevoltagefinal(calculateCurrent(myList.get(20)));

			edata.setYphasevoltage(Double.parseDouble(myList.get(21)));
			edata.setYphasevoltagefinal(calculateCurrent(myList.get(21)));

			edata.setBphasevoltage(Double.parseDouble(myList.get(22)));
			edata.setBphasevoltagefinal(calculateCurrent(myList.get(22)));

			edata.setWharehouseontime(Integer.parseInt(myList.get(23)));
			edata.setWharehouseofftime(Integer.parseInt(myList.get(24)));

			edata.setElectricalcontractorstatus(Integer.parseInt(myList.get(25)));

			// AN7_Volt_Level*(220/12),
			// (AN4_Volt_Level-2.5)*10)

			this._session.getCurrentSession().save(edata);

			// Status Update in DeviceStatus table
			DeviceStatus ds = deviceStatusRepository.findByImeinumber(myList.get(1));

			if (ds == null) {
				ds = new DeviceStatus();
				ds.setImeinumber(myList.get(1));
			}
			ds.setTimestamp(timestamp);

			if (Integer.parseInt(myList.get(25)) == 1) {
				ds.setStatus("On");
			} else {
				ds.setStatus("Off");
			}
			//

			ds.setRphasecurrentfinal(calculateVoltage(myList.get(17)));

			ds.setYphasecurrentfinal(calculateVoltage(myList.get(18)));
			ds.setBphasecurrentfinal(calculateVoltage(myList.get(19)));

			ds.setRphasevoltagefinal(calculateCurrent(myList.get(20)));
			ds.setYphasevoltagefinal(calculateCurrent(myList.get(21)));
			ds.setBphasevoltagefinal(calculateCurrent(myList.get(22)));

			// Device alert starts
			DeviceAlerts(ds);

			this._session.getCurrentSession().saveOrUpdate(ds);

			// Status Update in DeviceOnOffLog table
			// + 347155200
			int onTime = Integer.parseInt(myList.get(23));

			DeviceOnOffLog dl = deviceOnOffLogRepository.findByImeinumberAndDeviceontime(myList.get(1), onTime);

			if (dl == null) {
				dl = new DeviceOnOffLog();
				dl.setImeinumber(myList.get(1));
				if (Integer.parseInt(myList.get(25)) == 1) {
					dl.setDeviceontime(onTime);
					this._session.getCurrentSession().saveOrUpdate(dl);
				}
			} else {
				if (dl.getDeviceontime() > 0) {
					if (dl.getDeviceofftime() <= 0 && Integer.parseInt(myList.get(25)) == 0) {
						dl.setDeviceofftime(timestamp);

						long different = (timestamp * 1000) - (dl.getDeviceontime() * 1000);

						long secondsInMilli = 1000;
						long minutesInMilli = secondsInMilli * 60;
						long hoursInMilli = minutesInMilli * 60;
						// long daysInMilli = hoursInMilli * 24;

						// long elapsedDays = different / daysInMilli;
						// different = different % daysInMilli;

						long elapsedHours = different / hoursInMilli;
						different = different % hoursInMilli;

						long elapsedMinutes = different / minutesInMilli;
						different = different % minutesInMilli;

						//
						// List<DeviceAlert>
						// alert=getAlert(onTime,timestamp,myList.get(1));
						// System.out.print("=========>>>aaaaaaaaaaaaaaa"+alert.size());
						// for(int i=0;i<alert.size();i++)
						// {
						// DeviceAlert dr=alert.get(i);
						// System.out.print("=========>>>dr"+dr.getAlert());
						// }

						//

						dl.setRunhours(elapsedHours + "h " + elapsedMinutes + "m");
						this._session.getCurrentSession().saveOrUpdate(dl);
					}
				}
			}

			hmRes.put("status", "success");

		} catch (DataIntegrityViolationException e) {
			hmRes.put("status", "data already received");
		} catch (Exception ex) {
			hmRes.put("status", "Error == > " + ex.getMessage());
			ex.printStackTrace();
		}
		return hmRes;
	}

	DecimalFormat df = new DecimalFormat("#.00");

	/*
	 * @Override
	 * 
	 * @Transactional
	 * 
	 * @SuppressWarnings("unchecked") public List<DeviceAlert> getAlert(int
	 * fromdate, int todate,String imeinumber) throws ParseException {
	 * 
	 * return _session.getCurrentSession().createCriteria(DeviceAlert.class)
	 * .add(Restrictions.eq("imeinumber",
	 * imeinumber)).add(Restrictions.gt("timestamp", fromdate))
	 * .add(Restrictions.lt("timestamp",
	 * todate)).addOrder(Order.asc("timestamp")).list(); }
	 */

	private void DeviceAlerts(DeviceStatus ds) {
		DeviceMaster dm = deviceMaster.findByImeinumber(ds.getImeinumber());

		DeviceAlert da = new DeviceAlert();
		da.setImeinumber(ds.getImeinumber());
		String alert = "";
		// System.out.println("**************************");
		// System.out.println("phase "+dm.getPhase()+ds.getBphasecurrentfinal()
		// +"--"+dm.getBphasecurrenthigh()+"-- voltage
		// "+ds.getBphasevoltagefinal() +"--"+dm.getBphasevoltagehigh());
		if (dm.getPhase().contains("R")) {
			if (ds.getRphasecurrentfinal() > dm.getRphasecurrenthigh()) {
				da.setRphasecurrentfinal(ds.getRphasecurrentfinal());
				alert = alert + "R Phase overload ";
			}

			if (ds.getRphasevoltagefinal() > dm.getRphasevoltagehigh()) {
				da.setRphasevoltagefinal(ds.getRphasevoltagefinal());
				if (alert.length() > 0)
					alert = alert + ", ";
				alert = alert + "R Phase high voltage ";
			}

		}

		if (dm.getPhase().contains("Y")) {
			if (ds.getYphasecurrentfinal() > dm.getYphasecurrenthigh()) {
				da.setYphasecurrentfinal(ds.getYphasecurrentfinal());
				if (alert.length() > 0)
					alert = alert + ", ";
				alert = alert + "Y Phase overload ";
			}

			if (ds.getYphasevoltagefinal() > dm.getYphasevoltagethigh()) {
				da.setYphasevoltagefinal(ds.getYphasevoltagefinal());
				if (alert.length() > 0)
					alert = alert + ", ";
				alert = alert + "Y Phase high voltage ";
			}
		}

		if (dm.getPhase().contains("B")) {
			System.out.println("inside B phase" + ds.getBphasecurrentfinal() + "--" + dm.getBphasecurrenthigh() + "--"
					+ ds.getBphasevoltagefinal() + "--" + dm.getBphasevoltagehigh());
			if (ds.getBphasecurrentfinal() > dm.getBphasecurrenthigh()) {
				da.setBphasecurrentfinal(ds.getBphasecurrentfinal());
				if (alert.length() > 0)
					alert = alert + ", ";
				alert = alert + "B Phase overload ";
			}

			if (ds.getBphasevoltagefinal() > dm.getBphasevoltagehigh()) {
				da.setBphasevoltagefinal(ds.getBphasevoltagefinal());
				if (alert.length() > 0)
					alert = alert + ", ";
				alert = alert + "B Phase high voltage ";
			}

			// System.out.println("8888888 "+alert);
		}
		System.out.println("alert --" + alert);
		System.out.println("******************");
		if (alert.length() > 0) {
			da.setTimestamp(ds.getTimestamp());
			da.setAlert(alert);
			this._session.getCurrentSession().save(da);
		}

	}

	private double calculateCurrent(String rawValue) {

		Double format = Double.parseDouble(rawValue) * (220 / 12);
		if (format < 0)
			format = 0.0;
		return Double.parseDouble(df.format(format));
	}

	private double calculateVoltage(String rawValue) {

		Double format = (Double.parseDouble(rawValue) - 2.5) * 10;
		if (format < 0)
			format = 0.0;
		return Double.parseDouble(df.format(format));
	}

}
