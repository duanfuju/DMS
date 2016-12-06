package com.junl.dms.mvc.jiaotong;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelate;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelateService;
import com.platform.mvc.base.BaseService;


public class JiaoTongService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(JiaoTongService.class);

	public static final JiaoTongService service = new JiaoTongService();

	public boolean save(JiaoTong lFList,List<JiaoTongRelate> jiaoTongRelateList,String[] paras) {
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
		for (JiaoTongRelate jiaoTongRelate : jiaoTongRelateList) {
			if(jiaoTongRelate.toJson().length()>2){
				jiaoTongRelate.set("weiXiuId", lFList.getStr("ids"));
				jiaoTongRelate.save();
			}
		}
		String DMS_RW_task_info_relate_ids=lFList.get("taskInfoRelate");
		TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
		taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")+1);
		taskInfoRelate.update();
		return result;
	}
	
	public boolean update(JiaoTong jiaoTong,List<JiaoTongRelate> jiaoTongRelateList,String[] paras){
		StringBuffer sb = new StringBuffer();
		if(paras != null) {
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				jiaoTong.set("imgs",newStr1);
			}
		}else{
			jiaoTong.set("imgs","");
		}
		boolean result = jiaoTong.update();
		for (JiaoTongRelate JiaoTongRelate : jiaoTongRelateList) {
			if(JiaoTongRelate.toJson().length()>2){
				if(JiaoTongRelate.get("ids") == null){
					JiaoTongRelate.set("weiXiuId", jiaoTong.getStr("ids"));
					JiaoTongRelate.save();
				}else{
					JiaoTongRelate.update();
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
		boolean result = JiaoTong.dao.deleteById(ids);
		if(result){
			JiaoTong jiaotong = JiaoTong.dao.findById(ids);
			String imgs = jiaotong.getStr("imgs");
			String DMS_RW_task_info_relate_ids=jiaotong.get("taskInfoRelate");
			TaskInfoRelate taskInfoRelate=TaskInfoRelateService.service.findById(DMS_RW_task_info_relate_ids);
			taskInfoRelate.set("taskWeiXiuNum", taskInfoRelate.getInt("taskWeiXiuNum")+1);
			taskInfoRelate.update();
			deleteImages(request,imgs);//删除图片
		}
		return result;
	}
	
	public boolean delete_DMS_WX_lieFengDispose_wxItem_relate(String weiXiuId) {
		int result =Db.update("DELETE FROM  DMS_WX_jtaqssWeiXiuItem_relate where weiXiuId =  '"+weiXiuId+"'");
		System.out.println(result);
		return result ==0?false:true;
	}
	public JiaoTong findById(String ids) {
/*		String sql = getSql("manage.jiaotong.findById");*/
		String sql = getSql("manage.jiaotong.findTaskForUpdate");
		JiaoTong jiaoTong = JiaoTong.dao.findFirst(sql, ids);
		return jiaoTong;
	}
	public List<Record> findByweiXiuId(String weiXiuId){
		List<Record> list = Db.find("SELECT * FROM DMS_WX_jtaqssWeiXiuItem_relate where weiXiuId =  '"+weiXiuId+"'");
		return list;
	}
	
}