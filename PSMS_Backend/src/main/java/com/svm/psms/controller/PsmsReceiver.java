package com.svm.psms.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.svm.psms.cache.CacheManager;
import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.User;
import com.svm.psms.services.Psms_Service;

@CrossOrigin
@RestController
public class PsmsReceiver {

	@Autowired
	Psms_Service service;
 
	@RequestMapping(method = RequestMethod.GET, value = "/PSMS/{data}")
	private Map<String, Object> AddEventData(@PathVariable String data) {
		return this.service.AddEventData(data);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test/{data1}")
	private Map<String, Object> test(@PathVariable String data1) {
		DeviceMaster dm=CacheManager.getDeviceDetails(data1);
		System.out.println(dm.getDescription()+"--"+dm.getBphasecurrenthigh());
	return null;
	}
 
	 
}

