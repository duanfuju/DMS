package com.junl.dms.mvc.youwuchuli;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.taskinforelate.TaskInfoRelate;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelateService;
import com.platform.mvc.base.BaseService;

public class YouWuService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YouWuService.class);

	public static final YouWuService service = new YouWuService();

	public boolean save(Youwu youwu,String[] paras) {
		youwu.set("createTime", new Timestamp(System.currentTimeMillis()));
		StringBuffer sb = new StringBuffer();
		if(paras != null){
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				youwu.set("imgs",newStr1);
			}
		}else{
			youwu.set("imgs", "");
		}
		boolean resule = youwu.save();
		if(resule){
			String DMS_RW_task_info_relate_ids=youwu.get("taskInfoRelate");
			TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
			taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")+1);
			taskInfoRelate.update();	
		}
		return resule;
	}

	public boolean delete(HttpServletRequest request,String ids) {
		boolean rel = Youwu.dao.deleteById(ids);
		if(rel){//啥啥啥-1
			Youwu youwu = YouWuService.service.findById(ids);
			String imgs = youwu.getStr("imgs");
			String DMS_RW_task_info_relate_ids=youwu.get("taskInfoRelate");
			TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
			taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")-1);
			taskInfoRelate.update();
			deleteImages(request,imgs);//删除图片
		}
		return rel;
	}

	public Youwu findById(String ids) {
		String sql = getSql("manage.youwu.findTask");
		Youwu youwu = Youwu.dao.findFirst(sql, ids);
		return youwu;
	}
	
	public Youwu findTask(String ids){
		/*String sql = getSql("manage.youwu.findTask");*/
		String sql = getSql("manage.youwu.findTaskForUpdate");
		Youwu youwu = Youwu.dao.findFirst(sql, ids);
		return youwu;
	}
	
	public boolean update(Youwu youwu,String[]paras){
		StringBuffer sb = new StringBuffer();
		if(paras != null) {
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				youwu.set("imgs",newStr1);
			}
		}else{
			youwu.set("imgs","");
		}
		return youwu.update();
	}
	

}
