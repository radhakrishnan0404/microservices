package com.svm.psms.controller;

 
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.svm.psms.services.DashboardService;
 

@CrossOrigin
@RestController
public class DashboardController {

	@Autowired
	DashboardService _service;

	@RequestMapping(method = RequestMethod.GET, value = "/dashboard/{username}")
	private Map<String, Object> GetDashboardData(@PathVariable String username) {
		return this._service.GetDashboardData(username);
	}
 
}
