package com.svm.psms.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.svm.psms.entities.DeviceOnOffLog;

@RepositoryRestResource

public interface DeviceOnOffLogRepository extends CrudRepository<DeviceOnOffLog,Long>{
	
	DeviceOnOffLog findByImeinumberAndDeviceontime(String imonumber,Integer deviceontime);

}
