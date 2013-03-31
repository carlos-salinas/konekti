package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Job;

public interface JobService {
	public List<Job> getAll() throws Exception;
	public Job get( Integer jobId ) throws Exception;
	public Job save(Job job) throws Exception;
	public void delete(Job job) throws Exception;
	public Job getByGroupName(String group, String name) throws Exception;
	public void setLastExecution(Job job) throws Exception;
	public void setLastExecution(Job job, Boolean error) throws Exception;
	public void setOkStatus(Job job) throws Exception;
	public void setErrorStatus(Job job) throws Exception;
}
