package com.svm.psms.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.svm.psms.dao.ReportDAO;
import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceMapping;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.DeviceOnOffLog;
import com.svm.psms.entities.DeviceStatus;
import com.svm.psms.entities.EventData;
import com.svm.psms.entities.LoadDistribution;
import com.svm.psms.entities.LoadDistributionJobStatus;

@Repository
public class ReportDAOImpl implements ReportDAO {

	@Autowired
	private SessionFactory _session;

	SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DeviceOnOffLog> getOperationReportData(String username, String imeinumber, int fromdate, int todate)
			throws ParseException {
		List<String> deviceList = new ArrayList<String>();

		if (imeinumber.equalsIgnoreCase("all")) {
			List<DeviceMapping> HdeviceList = _session.getCurrentSession().createCriteria(DeviceMapping.class)
					.add(Restrictions.eq("username", username)).list();

			for (int i = 0; i < HdeviceList.size(); i++) {
				deviceList.add(HdeviceList.get(i).getImeinumber());
			}
		} else {
			deviceList.add(imeinumber);
		}

		return _session.getCurrentSession().createCriteria(DeviceOnOffLog.class)
				.add(Restrictions.in("imeinumber", deviceList)).add(Restrictions.gt("deviceontime", fromdate))
				.add(Restrictions.lt("deviceontime", todate)).addOrder(Order.asc("deviceontime")).list();
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DeviceAlert> getAlertReportData(String username, String imeinumber, int fromdate, int todate)
			throws ParseException {
		List<String> deviceList = new ArrayList<String>();

		if (imeinumber.equalsIgnoreCase("all")) {
			List<DeviceMapping> HdeviceList = _session.getCurrentSession().createCriteria(DeviceMapping.class)
					.add(Restrictions.eq("username", username)).list();

			for (int i = 0; i < HdeviceList.size(); i++) {
				deviceList.add(HdeviceList.get(i).getImeinumber());
			}
		} else {
			deviceList.add(imeinumber);
		}

		return _session.getCurrentSession().createCriteria(DeviceAlert.class)
				.add(Restrictions.in("imeinumber", deviceList)).add(Restrictions.gt("timestamp", fromdate))
				.add(Restrictions.lt("timestamp", todate)).addOrder(Order.asc("timestamp")).list();
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DeviceAlert> getAlert(int fromdate, int todate,String imeinumber)
			throws ParseException {
		
		
	

		return _session.getCurrentSession().createCriteria(DeviceAlert.class)
				.add(Restrictions.eq("imeinumber", imeinumber)).add(Restrictions.gt("timestamp", fromdate))
				.add(Restrictions.lt("timestamp", todate)).addOrder(Order.asc("timestamp")).list();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<LoadDistribution> getDistributionReportData(String username, String imeinumber, int fromdate,
			int todate) throws ParseException {

		return _session.getCurrentSession().createCriteria(LoadDistribution.class)
				.add(Restrictions.eq("imeinumber", imeinumber)).add(Restrictions.gt("timestamp", fromdate))
				.add(Restrictions.lt("timestamp", todate)).addOrder(Order.asc("timestamp")).list();

	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<EventData> getVoltageReportData(String username, String imeinumber, int fromdate,
			int todate) throws ParseException {

		return _session.getCurrentSession().createCriteria(EventData.class)
				.add(Restrictions.eq("imeinumber", imeinumber)).add(Restrictions.gt("timestamp", fromdate))
				.add(Restrictions.lt("timestamp", todate)).addOrder(Order.asc("timestamp")).list();

	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<EventData> getCurrentReportData(String username, String imeinumber, int fromdate,
			int todate) throws ParseException {

		return _session.getCurrentSession().createCriteria(EventData.class)
				.add(Restrictions.eq("imeinumber", imeinumber)).add(Restrictions.gt("timestamp", fromdate))
				.add(Restrictions.lt("timestamp", todate)).addOrder(Order.asc("timestamp")).list();

	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DeviceMaster> getDeviceList(String username) {

		List<String> deviceList = new ArrayList<String>();
		try {
			List<DeviceMapping> HdeviceList = _session.getCurrentSession().createCriteria(DeviceMapping.class)
					.add(Restrictions.eq("username", username)).list();

			for (int i = 0; i < HdeviceList.size(); i++) {
				deviceList.add(HdeviceList.get(i).getImeinumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return _session.getCurrentSession().createCriteria(DeviceMaster.class)
				.add(Restrictions.in("imeinumber", deviceList)).list();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<EventData> getEventData(String imeinumber, int start, int end) {
		List<EventData> ed = _session.getCurrentSession().createCriteria(EventData.class)
				.add(Restrictions.eq("imeinumber", imeinumber)).add(Restrictions.gt("timestamp", start))
				.add(Restrictions.le("timestamp", end)).list();
		return ed;
	}

	@Override
	@Transactional
	public String saveLoadDistribution(LoadDistribution ld) throws ParseException {

		try {
			this._session.getCurrentSession().saveOrUpdate(ld);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "success";

	}

	@Override
	@Transactional
	public String saveDistributionalert(LoadDistributionJobStatus ld) throws ParseException {

		try {
			this._session.getCurrentSession().saveOrUpdate(ld);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "success";

	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<EventData> getDetailedReport(String username, String imeinumber, int fromdate, int todate)
			throws ParseException {

		return _session.getCurrentSession().createCriteria(EventData.class)
				.add(Restrictions.eq("imeinumber", imeinumber)).add(Restrictions.gt("timestamp", fromdate))
				.add(Restrictions.lt("timestamp", todate)).addOrder(Order.asc("timestamp")).list();

	}
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List getPhaseDetails(String imeinumber)
			throws ParseException {
	Criteria criteria1 = _session.getCurrentSession().createCriteria(DeviceMaster.class);
	criteria1.add(Restrictions.eq("imeinumber", imeinumber));
	criteria1.setProjection(Projections.projectionList().add(Projections.property("phase")));
	List devicemasterlist= criteria1.list();
	return devicemasterlist;
	}

}
