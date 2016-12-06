package com.junl.dms.mvc.choujian;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.jiling.JiLiangService;
import com.junl.dms.mvc.xunchainfo.StringUtil;
import com.platform.mvc.base.BaseService;



public class ChouJianService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ChouJianService.class);

	public static final ChouJianService service = new ChouJianService();
	
	//获取油污的维修记录			DMS_WX_youWuDispose
	//获取养护通用的维修记录		DMS_WX_yangHuTongYongWeiXiu
	//获取路面病害的维修记录		DMS_WX_luMianBingHaiWeiXiu
	//获取裂缝处理的维修记录		DMS_WX_lieFengDispose
	//获取交通安全设施维修记录	DMS_WX_jtaqssWeiXiu
	//防撞护栏的维修记录			DMS_WX_fzhlWeiXiu
	public static String[] weiXiuJiLu_Array={"DMS_WX_youWuDispose","DMS_WX_yangHuTongYongWeiXiu",
		"DMS_WX_luMianBingHaiWeiXiu","DMS_WX_lieFengDispose",
		"DMS_WX_jtaqssWeiXiu","DMS_WX_fzhlWeiXiu"};
	
	public boolean save(String cjIds, String weiXiu_array, String task_array) {
		boolean flag=false;
		if (StringUtil.isNotEmpty(cjIds)&&StringUtil.isNotEmpty(weiXiu_array)&&StringUtil.isNotEmpty(task_array)) {
			String[] weiXiu_arrays=weiXiu_array.split(",");
			String[] task_arrays=task_array.split(",");
			ChouJian chouJian = ChouJian.dao.findById(cjIds);
			chouJian.set("state", 1);
			chouJian.set("samplingTime",  new Timestamp(System.currentTimeMillis()));
			flag=chouJian.update();
			if (flag) {
				for (int i = 0; i < task_arrays.length; i++) {
					ChouJianRelate chouJianRelate = new ChouJianRelate();
					chouJianRelate.set("chouJianIds", cjIds);
					chouJianRelate.set("weiXiuIds", weiXiu_arrays[i]);
					chouJianRelate.set("taskId", task_arrays[i]);
					chouJianRelate.set("createTime",new Timestamp(System.currentTimeMillis()));
					chouJianRelate.save();
				}
			}
		}
		return flag;
	}
	/**
	 * 验收		
	 * @param weiXiuIds
	 * @param isAppcept		3验收		4未通过
	 * @return
	 */
	public boolean isAccept(String weiXiuIds, String isAppcept) {
		int res=-1;
		for (int i = 0; i < weiXiuJiLu_Array.length; i++) {
			if (isAppcept.equals("4")) {//未通过时，需要返修		返修时返修数需要+1 将之前计量的东西删除
				//查询维修记录
				Record record =Db.find("select * from xz_wx where ids='"+weiXiuIds+"'").get(0);
				//删除所有计量的细目
				Db.update("delete from  DMS_JL_jiLingXiMu  where weiXiuIds='"+weiXiuIds+"'");
				//修改计量主表的计量数和计量金额
				Db.update("update  DMS_JL_jiLing set meteringNum=meteringNum-"+record.get("meteringNum")
													+",meteringMoney=meteringMoney-"+record.get("meteringMoney")+"  where byIds='"+record.get("baoyanId")+"'");
				//返修+1			清空计量数  计量金额  
				Db.update("update "+weiXiuJiLu_Array[i]+" set state=2,repairNum=repairNum+1,meteringNum=0,meteringMoney=0 where ids='"+weiXiuIds+"'");
				
				res=Db.update("update "+weiXiuJiLu_Array[i]+" set state='"+isAppcept+"',repairNum=repairNum+1  where ids='"+weiXiuIds+"'");
			}else{
				res=Db.update("update "+weiXiuJiLu_Array[i]+" set state='"+isAppcept+"'  where ids='"+weiXiuIds+"'");
			}
			if (res==1) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 验收
	 * @param cjIds		state=2
	 * @return
	 */
	public boolean save1(String cjIds) {
		boolean flag=false;
		ChouJian chouJian = ChouJian.dao.findById(cjIds);
		chouJian.set("state", 2);
		chouJian.set("acceptTime",  new Timestamp(System.currentTimeMillis()));
		flag=chouJian.update();
		return flag;
	}
	public void autoSampling(String cjids) {
		//1.抽检任务
		//1.1.根据抽检的ids获取抽检的对象
		ChouJian chouJian = ChouJian.dao.findById(cjids);
		//1.2根据任务编号获取所有的维修记录数
		List<Map<String, Object>> list= JiLiangService.service.getWeiXiuRecord(chouJian.getStr("byIds"));
		//1.3设置数据
		String weiXiu_array="";
		String task_array="";
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			weiXiu_array+=map.get("ids")+",";
			task_array+=map.get("taskrelateIds")+",";
		}
		//1.4完成抽检
		if (save(cjids,weiXiu_array,task_array)) {
			//2.验收
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map=list.get(i);
				String weiXiuIds=map.get("ids").toString();
				isAccept(weiXiuIds,"3");
			}
			//3.验收完成
			save1(cjids);
		}
		
		
	}
	


	

}
