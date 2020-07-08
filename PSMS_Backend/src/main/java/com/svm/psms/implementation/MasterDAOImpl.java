package com.svm.psms.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.svm.psms.dao.MasterDAO;
import com.svm.psms.entities.DeviceMapping;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.User;

@Repository
public class MasterDAOImpl implements MasterDAO {

	@Autowired
	private SessionFactory _session;

	@Override
	@Transactional
	public List<DeviceMaster> getAllDevice() {
		return this._session.getCurrentSession().createCriteria(DeviceMaster.class).list();
	}

	@Override
	@Transactional
	public Map<String, Object> AddDevicemaster(DeviceMaster deviceMaster, String devicemastertype) {
		System.out.println("___AddDevicemaster___" + deviceMaster.getImeinumber() + "______" + devicemastertype);
		Map<String, Object> hmRes = new HashMap<String, Object>();
		String to = deviceMaster.getMailid();
		Criteria criteria = _session.getCurrentSession().createCriteria(DeviceMaster.class);
		criteria.add(Restrictions.eq("mailid", deviceMaster.getMailid()));
		criteria.add(Restrictions.ne("imeinumber", deviceMaster.getImeinumber()));
		criteria.setProjection(Projections.projectionList().add(Projections.property("imeinumber")));
		List mail = criteria.list();
		String email = mail.toString();
		int len1 = email.length();
		email = email.substring(1, len1 - 1);
		if (to.equals(email)) {
			hmRes.put("status", "0");
		} else {
			DeviceMaster obj = null;
			if (email.isEmpty()) {
				Criteria criteria1 = _session.getCurrentSession().createCriteria(DeviceMaster.class);
				criteria1.add(Restrictions.eq("imeinumber", deviceMaster.getImeinumber()));
				criteria1.setProjection(Projections.projectionList().add(Projections.property("imeinumber")));
				List devicemasterlist = criteria1.list();

				if (!devicemasterlist.isEmpty() && devicemastertype.equals("save")) {
					hmRes.put("status", "3");
				} else if (!devicemasterlist.isEmpty() && devicemastertype.equals("update")) {
					try {

						DeviceMaster ob = GetDeviceMasterdetails(deviceMaster.getImeinumber());
						if (ob != null) {
							System.out.println(
									"==deviceMaster.getBphasevoltagehigh()==" + deviceMaster.getBphasevoltagehigh());
							ob.setDescription(deviceMaster.getDescription());
							ob.setSimno(deviceMaster.getSimno());
							ob.setPhase(deviceMaster.getPhase());
							ob.setMobileno(deviceMaster.getMobileno());
							ob.setMailid(deviceMaster.getMailid());
							ob.setInstallationtime(deviceMaster.getInstallationtime());
							ob.setLastupdationtime(deviceMaster.getLastupdationtime());
							ob.setSmsalert(deviceMaster.getSmsalert());
							ob.setRphasecurrenthigh(deviceMaster.getRphasecurrenthigh());
							ob.setYphasecurrenthigh(deviceMaster.getYphasecurrenthigh());
							ob.setBphasecurrenthigh(deviceMaster.getBphasecurrenthigh());
							ob.setRphasevoltagelow(deviceMaster.getRphasevoltagelow());
							ob.setYphasevoltagetlow(deviceMaster.getYphasevoltagetlow());
							ob.setBphasevoltagelow(deviceMaster.getBphasevoltagelow());
							ob.setRphasevoltagehigh(deviceMaster.getRphasevoltagehigh());
							ob.setYphasevoltagethigh(deviceMaster.getYphasevoltagethigh());
							ob.setBphasevoltagehigh(deviceMaster.getBphasevoltagehigh());
							this._session.getCurrentSession().update(ob);
							hmRes.put("datadeviceMaster", ob);
							hmRes.put("status", "2");// update
						}

					} catch (Exception ex) {
						hmRes.put("status", "Error == > " + ex.getMessage());
						// System.out.println("Exception while Updating the
						// device master details == > " + ex.getMessage());
						ex.printStackTrace();
					}
				} else {
					System.out.println("__OTHER___new device master save___");
					try {
						int icont = this._session.getCurrentSession().createCriteria(DeviceMaster.class)
								.add(Restrictions.eq("imeinumber", deviceMaster.getImeinumber())).list().size();
						if (icont == 0) {
							this._session.getCurrentSession().save(deviceMaster);
							hmRes.put("datadeviceMaster", deviceMaster);
							hmRes.put("status", "1");
						} else {
							hmRes.put("status", "3");
						}
					} catch (Exception ex) {
						this._session.getCurrentSession().clear();
						hmRes.put("status", "Error == > " + ex.getMessage());
						ex.printStackTrace();
					}
				}
			}
		}
		System.out.println("___hmRes____" + hmRes);
		return hmRes;
	}

	@Override
	@Transactional
	public String deleteDeviceMaster(String imenumber) {
		Object persistentInstance = _session.getCurrentSession().load(DeviceMaster.class, imenumber);
		_session.getCurrentSession().delete(persistentInstance);
		return "1";
	}

	public DeviceMaster GetDeviceMasterdetails(String imenumber) {
		System.out.println("____GetDeviceMasterdetails___" + imenumber);
		return this._session.getCurrentSession().get(DeviceMaster.class, imenumber);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DeviceMaster> getAllDeviceMasterDetails() {
		System.out.println("__getAllDeviceMasterDetails___");
		return this._session.getCurrentSession().createCriteria(DeviceMaster.class).list();
	}

	@Override
	@Transactional
	public Map<String, Object> AddDeviceMapping(String username, String selimeinumbers, String devicemappingtype,
			String id) {

		System.out.println("__AddDeviceMapping___" + username + "___" + selimeinumbers + "___" + devicemappingtype+"_____"+id);
		Map<String, Object> hmRes = new HashMap<String, Object>();

		try {

			if ("save".equalsIgnoreCase(devicemappingtype)) {
				DeviceMapping deviceMapping = new DeviceMapping();
				Criteria criteria = _session.getCurrentSession().createCriteria(DeviceMapping.class);
				criteria.add(Restrictions.eq("username", username));
				criteria.add(Restrictions.eq("imeinumber", selimeinumbers));
				List result = criteria.list();
				System.out.print("============>>>>" + result);
				if (result.isEmpty()) {
					deviceMapping.setUsername(username);
					deviceMapping.setImeinumber(selimeinumbers);
					this._session.getCurrentSession().save(deviceMapping);
					hmRes.put("datadeviceMapping", deviceMapping);
					hmRes.put("status", "1");
				} else {
					hmRes.put("status", "0");
				}
			} else {
				DeviceMapping deviceMapping = new DeviceMapping();
				Criteria criteria = _session.getCurrentSession().createCriteria(DeviceMapping.class);
				criteria.add(Restrictions.eq("id", Integer.parseInt(id)));
				List result = criteria.list();
				System.out.print("============>>>>" + result);
				if (result.isEmpty()) {
					hmRes.put("status", "0");
				} else {
					deviceMapping.setId(Integer.parseInt(id));
					deviceMapping.setUsername(username);
					deviceMapping.setImeinumber(selimeinumbers);
					this._session.getCurrentSession().clear();
					this._session.getCurrentSession().update(deviceMapping);
					hmRes.put("datadeviceMapping", deviceMapping);
					hmRes.put("status", "1");

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("___hmRes_device mapping__" + hmRes);
		return hmRes;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DeviceMapping> getAllDeviceMappingDetails() {
		System.out.println("__getAllDeviceMappingDetails___");

		return this._session.getCurrentSession().createCriteria(DeviceMapping.class).list();

	}

	@Override
	@Transactional
	public String deleteDeviceMapping(String username, String imeinumber) {
		System.out.println("_deleteDeviceMapping____" + username + "____" + imeinumber);
		Criteria criteria1 = _session.getCurrentSession().createCriteria(DeviceMapping.class);
		criteria1.add(Restrictions.eq("username", username));
		criteria1.add(Restrictions.eq("imeinumber", imeinumber));
		DeviceMapping devicemappingid = (DeviceMapping) criteria1.list().get(0);
		System.out.println("_devicemappingid__" + devicemappingid + "====");

		_session.getCurrentSession().delete(devicemappingid);
		return "1";
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<User> getAllUserNameListDetails() {
		System.out.println("__getAllUserNameListDetails___");
		User user = new User();
		Criteria criteria = _session.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("status", "active"));
		criteria.setProjection(Projections.projectionList().add(Projections.property("username")));
		List usernamelist = criteria.list();
		return usernamelist;

	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DeviceMaster> getAllDeviceDetails() {
		System.out.println("getAllDeviceDetails");
		Criteria criteria = _session.getCurrentSession().createCriteria(DeviceMaster.class);
		criteria.setProjection(Projections.projectionList().add(Projections.property("imeinumber")));
		List devicelist = criteria.list();

		return devicelist;

	}

}
