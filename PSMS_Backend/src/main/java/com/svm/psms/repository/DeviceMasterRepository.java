package com.svm.psms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.svm.psms.entities.DeviceMaster;
import com.svm.psms.entities.DeviceStatus;

@RepositoryRestResource

public interface DeviceMasterRepository extends CrudRepository<DeviceMaster,Long>{
	
	DeviceMaster findByImeinumber(String imonumber);

}
