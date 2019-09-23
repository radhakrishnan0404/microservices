package com.svm.psms.services;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svm.psms.dao.PsmsDAO;
import com.svm.psms.entities.DeviceMaster;

@Service
public class Psms_Service {

	@Autowired
	PsmsDAO repository;

	public Map<String, Object> AddEventData(String data) {
		return repository.AddEventData(data);
	}

	
	
	

}
