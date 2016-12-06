package com.junl.dms.mvc.taskinforelate;


import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class TaskInfoRelateService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(TaskInfoRelateService.class);

	public static final TaskInfoRelateService service = new TaskInfoRelateService();

	public boolean save(TaskInfoRelate taskInfoRelate) {

		return taskInfoRelate.save();
	}

	public boolean delete(String ids) {
		return TaskInfoRelate.dao.deleteById(ids);
	}

	public TaskInfoRelate findById(String ids) {
		TaskInfoRelate rwtask = TaskInfoRelate.dao.findById(ids);
		return rwtask;
	}
	
	public boolean update(TaskInfoRelate taskInfoRelate){
		return taskInfoRelate.update();
	}
	

}
