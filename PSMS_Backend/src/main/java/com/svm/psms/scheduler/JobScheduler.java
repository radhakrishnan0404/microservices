package com.svm.psms.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.svm.psms.services.ReportService;

@Component
public class JobScheduler {
	
	@Autowired
	ReportService _service;
	
	private static final Logger log = LoggerFactory.getLogger(JobScheduler.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	//@Scheduled(cron = "0 0/30 * * * *")
	public void reportCurrentTime() {
		
		Date date = new Date();
		
		Calendar startcal = Calendar.getInstance();
		startcal.set(Calendar.SECOND, 00);
		startcal.set(Calendar.MILLISECOND, 000);
		
		
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.SECOND, 00);
		endCal.set(Calendar.MILLISECOND, 000);

		if (date.getMinutes() < 30) {
			startcal.set(Calendar.HOUR_OF_DAY, endCal.get(Calendar.HOUR_OF_DAY) - 1);
			startcal.set(Calendar.MINUTE, 30);
			
			endCal.set(Calendar.MINUTE, 00);
			
		} else {
			startcal.set(Calendar.MINUTE, 00);
			endCal.set(Calendar.MINUTE, 30);
		}
		Date startDate = startcal.getTime();
		Date endTime = endCal.getTime();
		System.out.println(startDate.getTime() + "---" + endTime.getTime());
		log.info("The time is now {}", dateFormat.format(new Date()));
		System.out.println(startDate.getTime()/1000+"---------"+(endTime.getTime())/1000);
		int start=(int) (startDate.getTime()/1000);
		int end=(int) (endTime.getTime()/1000);
		//this._service.getOperationReport(start,end);
	}
}
