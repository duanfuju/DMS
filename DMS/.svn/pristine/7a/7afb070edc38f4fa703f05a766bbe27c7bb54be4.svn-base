package com.junl.dms.mvc.waydisease;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelate;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelateService;
import com.platform.mvc.base.BaseService;

public class WayDiseaseService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(WayDiseaseService.class);

	public static final WayDiseaseService service = new WayDiseaseService();

	public boolean save(WayDisease way,String[] paras) {
		way.set("createTime", new Timestamp(System.currentTimeMillis()));
		StringBuffer sb = new StringBuffer();
		if(paras != null){
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				way.set("imgs",newStr1);
			}
		}else{
			way.set("imgs","");
		}
		String DMS_RW_task_info_relate_ids=way.get("taskInfoRelate");
		TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
		taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")+1);
		taskInfoRelate.update();
		return way.save();
	}

	public boolean delete(HttpServletRequest request,String ids) {
		boolean rel = WayDisease.dao.deleteById(ids);
		if(rel){
			WayDisease waydisease = WayDiseaseService.service.findById(ids);
			String imgs = waydisease.getStr("imgs");
			String DMS_RW_task_info_relate_ids=waydisease.get("taskInfoRelate");
			TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
			taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")-1);
			taskInfoRelate.update();
			deleteImages(request,imgs);//删除图片
		}
		return rel;
	}

	public WayDisease findById(String ids) {
		WayDisease youwu = WayDisease.dao.findById(ids);
		return youwu;
	}
	
	public WayDisease findTask(String ids){
/*		String sql = getSql("manage.way.findTask");*/
		String sql = getSql("manage.way.findTaskForUpdate");
		WayDisease way = WayDisease.dao.findFirst(sql, ids);
		return way;
	}
	
	public boolean update(WayDisease way,String[]paras){
		
		StringBuffer sb = new StringBuffer();
		if(paras != null) {
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				way.set("imgs",newStr1);
			}
		}else{
			way.set("imgs","");
		}
		return way.update();
	}
		
	
	
	public WayDisease view(String ids){
//		String sql = getSql("manage.way.queryAllWayInfo");
//		WayDisease waydisease =  WayDisease.dao.findFirst(sql, ids);
//		return waydisease;
		
		WayDisease wayDisease = WayDisease.dao.findById(ids);
		return wayDisease;
	}
	
	public List<WayDiseaseCeng> findTable(String ids){
		String sql = getSql("manage.way.queryWayTable");
		List<WayDiseaseCeng> list = WayDiseaseCeng.dao.find(sql, ids);
		return list;
	}

	public boolean saveTable(WayDiseaseCeng ceng,String ids) {
		ceng.set("weiXiuId", ids);
		return ceng.save();
	}
	
	
	public void updateTable(WayDiseaseCeng ceng,String weiXiuId){
		String sql = getSql("manage.way.updateWayTable");
		Db.update(sql, ceng.get("chiCunChang"),ceng.get("chiCunKuan"),ceng.get("shenDu"),ceng.get("xiuBuCaiLiao"),
				ceng.get("caiLiaoWenDu"),weiXiuId,ceng.get("cengType"));
	}
	
	public void deleteTable(String weiXiuId){
		//String sql = getSql("manage.way.deleteWayTable");
		Db.deleteById("DMS_WX_luMianBingHaiWeiXiu_ceng_relate", "weiXiuId", weiXiuId);
	}
	
}
