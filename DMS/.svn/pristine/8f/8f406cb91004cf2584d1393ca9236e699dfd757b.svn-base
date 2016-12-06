package com.junl.dms.mvc.taskinforelate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.chooseinfo.ChooseInfo;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "DMS_RW_task_info_relate")
public class TaskInfoRelate extends BaseModel<TaskInfoRelate> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(TaskInfoRelate.class);
	
	public static final TaskInfoRelate dao = new TaskInfoRelate();
	
	public static final String sqlId_splitPageSelect = "manage.taskinforelate.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.taskinforelate.splitPageFrom";
	
	
	
	
	public List<TaskInfoRelate> findListByTaskId(String taskId) {
		String sql = getSql("manage.taskinforelate.findListByTaskId");
		List<TaskInfoRelate> list = this.dao.find(sql, taskId);
		return list;
	}
	/*public List<Record> findListBytwo(String rwTaskIds,String taskRelateIds) {
		String sql = getSql("manage.taskinforelate.findListByTaskId");
		List<Record> list = Db.find("select * from DMS_RW_task_info_relate where rwTaskIds="+rwTaskIds+"xunChaInfoId="+taskRelateIds+"");
		return list;
	}*/
}
