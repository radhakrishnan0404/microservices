package com.svm.psms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.svm.psms.entities.LoadDistributionJobStatus;

@RepositoryRestResource

public interface LoadDIstributionJobStatusRepository extends CrudRepository<LoadDistributionJobStatus,Long>{
	
	LoadDistributionJobStatus findByImeinumber(String imonumber);

}
