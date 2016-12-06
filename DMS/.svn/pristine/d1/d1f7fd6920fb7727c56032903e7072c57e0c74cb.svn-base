package com.junl.dms.mvc.liefeng;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelate;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelateService;
import com.platform.mvc.base.BaseService;


public class LieFengService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(LieFengService.class);

	public static final LieFengService service = new LieFengService();

	public boolean save(LieFeng lFList,List<LieFengRelate> lieFengRelateList,String[] paras) {
		lFList.set("createTime", new Timestamp(System.currentTimeMillis()));
		StringBuffer sb = new StringBuffer();		
		if(paras != null) {
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				lFList.set("imgs",newStr1);
			}
		}else{
			lFList.set("imgs","");
		}
		boolean result = lFList.save();
		for (LieFengRelate lieFengRelate : lieFengRelateList) {
			if(lieFengRelate.toJson().length()>2){
				lieFengRelate.set("lieFengDisposeId", lFList.getStr("ids"));
				lieFengRelate.save();
			}
		}
		String DMS_RW_task_info_relate_ids=lFList.get("taskInfoRelate");
		TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
		taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")+1);
		taskInfoRelate.update();
		return result;
	}
	
	public boolean update(LieFeng lieFeng,List<LieFengRelate> lieFengRelateList,String[] paras){
		StringBuffer sb = new StringBuffer();
		if(paras != null) {
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				lieFeng.set("imgs",newStr1);
			}
		}else{
			lieFeng.set("imgs","");
		}
		boolean result = lieFeng.update();
		for (LieFengRelate lieFengRelate : lieFengRelateList) {
			if(lieFengRelate.toJson().length()>2){
				if(lieFengRelate.get("ids") == null){
					lieFengRelate.set("lieFengDisposeId", lieFeng.getStr("ids"));
					lieFengRelate.save();
				}else{
					lieFengRelate.update();
				}
				
			}
		}
		return result;
	}
		
		
	/**
	 * 
	 * @author wlw
	 * @date 2016年7月15日 下午2:27:38
	 * @description 删除巡查信息表
	 *		TODO
	 * @param ids
	 * @return
	 *
	 */
	public boolean delete_WX_lieFengDispose(HttpServletRequest request,String ids) {
		boolean result = LieFeng.dao.deleteById(ids);
		if(result){
			LieFeng liefeng = LieFeng.dao.findById(ids);
			String imgs = liefeng.getStr("imgs");
			String DMS_RW_task_info_relate_ids=liefeng.get("taskInfoRelate");
			TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
			taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")+1);
			taskInfoRelate.update();
			deleteImages(request,imgs);//删除图片
		}
		
		return result;
	}
	/**
	 * 
	 * @author wlw
	 * @date 2016年7月15日 下午2:28:28
	 * @description 通过lieFengDisposeId删除养护记录表
	 *		TODO
	 * @param lieFengDisposeId
	 * @return
	 *
	 */
	public boolean delete_DMS_WX_lieFengDispose_wxItem_relate(String lieFengDisposeId) {
		int result =Db.update("DELETE FROM  DMS_WX_lieFengDispose_wxItem_relate where lieFengDisposeId =  '"+lieFengDisposeId+"'");
		System.out.println(result);
		return result ==0?false:true;
	}
	public LieFeng findById(String ids) {
/*		String sql = getSql("manage.liefeng.findById");*/
		String sql = getSql("manage.liefeng.findTaskForUpdate");
		LieFeng lieFeng = LieFeng.dao.findFirst(sql, ids);
		return lieFeng;
	}
	public List<Record> findBylieFengDisposeId(String lieFengDisposeId){
		List<Record> list = Db.find("SELECT * FROM DMS_WX_lieFengDispose_wxItem_relate where lieFengDisposeId =  '"+lieFengDisposeId+"' order by sort");
		return list;
	}
	
}
