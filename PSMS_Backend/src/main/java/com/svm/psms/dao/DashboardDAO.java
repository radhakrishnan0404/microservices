package com.svm.psms.dao;

import java.util.List;

import com.svm.psms.entities.DeviceAlert;
import com.svm.psms.entities.DeviceStatus;

public interface DashboardDAO {

	public List<DeviceStatus> GetDashboardData(String username);
	public List<DeviceAlert> GetLatestAlertData(String username);
	
}
