package com.svm.psms.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.svm.psms.dao.DashboardDAO;
import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceMapping;
import com.svm.psms.entities.DeviceStatus;

@Repository
public class DashboardDAOImpl implements DashboardDAO {

	@Autowired
	private SessionFactory _session;

	@Override
	@Transactional
	@SuppressWarnings("unchecked")

	public List<DeviceStatus> GetDashboardData(String username) {

		List<DeviceMapping> HdeviceList = _session.getCurrentSession().createCriteria(DeviceMapping.class)
				.add(Restrictions.eq("username", username)).list();
		List<String> deviceList = new ArrayList<String>();
		for (int i = 0; i < HdeviceList.size(); i++) {
			deviceList.add(HdeviceList.get(i).getImeinumber());
		}

		return _session.getCurrentSession().createCriteria(DeviceStatus.class)
				.add(Restrictions.in("imeinumber", deviceList)).addOrder(Order.asc("timestamp")).list();

	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")

	public List<DeviceAlert> GetLatestAlertData(String username) {
		List<DeviceAlert> da = new ArrayList<DeviceAlert>();
		List<DeviceMapping> HdeviceList = _session.getCurrentSession().createCriteria(DeviceMapping.class)
				.add(Restrictions.eq("username", username)).list();
		List<String> deviceList = new ArrayList<String>();
		for (int i = 0; i < HdeviceList.size(); i++) {
			deviceList.add(HdeviceList.get(i).getImeinumber());
		}
		for (int i = 0; i < deviceList.size(); i++) {
			//System.out.println(deviceList.get(i));
			da.addAll(_session.getCurrentSession().createCriteria(DeviceAlert.class)
					.add(Restrictions.eq("imeinumber", deviceList.get(i)))
					.setFirstResult(0).setMaxResults(1)
					.addOrder(Order.desc("timestamp")).list());
		}
		//System.out.println(da);
		return da;

	}

}
