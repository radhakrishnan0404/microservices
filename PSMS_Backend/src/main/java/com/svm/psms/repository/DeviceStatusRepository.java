package com.svm.psms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.svm.psms.entities.DeviceStatus;

@RepositoryRestResource

public interface DeviceStatusRepository extends CrudRepository<DeviceStatus,Long>{
	
	DeviceStatus findByImeinumber(String imonumber);

}
