package com.junl.dms.mvc.yanghu;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelate;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelateService;
import com.platform.mvc.base.BaseService;


public class YangHuService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YangHuService.class);

	public static final YangHuService service = new YangHuService();

	public boolean save(YangHu lFList,List<YangHuRelate> yangHuRelateList,String[] paras) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sf.format(new Date());
		lFList.set("createTime", format);
		StringBuffer sb = new StringBuffer();
		lFList.set("createTime", new Timestamp(System.currentTimeMillis()));

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
		for (YangHuRelate yangHuRelate : yangHuRelateList) {
			if(yangHuRelate.toJson().length()>2){
				yangHuRelate.set("weiXiuId", lFList.getStr("ids"));
				yangHuRelate.save();
			}
		}
		String DMS_RW_task_info_relate_ids=lFList.get("taskInfoRelate");
		TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
		taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")+1);
		taskInfoRelate.update();
		return result;
	}
	
	public boolean update(YangHu lieFeng,List<YangHuRelate> lieFengRelateList,String[] paras){
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
		for (YangHuRelate lieFengRelate : lieFengRelateList) {
			if(lieFengRelate.toJson().length()>2){
				if(lieFengRelate.get("ids") == null){
					lieFengRelate.set("weiXiuId", lieFeng.getStr("ids"));
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
		boolean result = YangHu.dao.deleteById(ids);
		if(result){
			YangHu yanghu = YangHu.dao.findById(ids);
			String imgs = yanghu.getStr("imgs");
			String DMS_RW_task_info_relate_ids=yanghu.get("taskInfoRelate");
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
	public boolean delete_DMS_WX_lieFengDispose_wxItem_relate(String weiXiuId) {
		int result =Db.update("DELETE FROM  DMS_WX_yangHuTongYongWeiXiu_item_relate where weiXiuId =  '"+weiXiuId+"'");
		System.out.println(result);
		return result ==0?false:true;
	}
	public YangHu findById(String ids) {
		/*String sql = getSql("manage.yanghu.findById");*/
		String sql = getSql("manage.yanghu.findTaskForUpdate");
		YangHu yangHu = YangHu.dao.findFirst(sql, ids);
		return yangHu;
	}
	public List<Record> findBylieFengDisposeId(String weiXiuId){
		List<Record> list = Db.find("SELECT * FROM DMS_WX_yangHuTongYongWeiXiu_item_relate where weiXiuId =  '"+weiXiuId+"'");
		return list;
	}
	
}
