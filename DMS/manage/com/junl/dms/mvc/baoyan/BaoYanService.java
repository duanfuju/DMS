package com.junl.dms.mvc.baoyan;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.jiling.JiLiang;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelate;
import com.junl.dms.mvc.taskinforelate.TaskInfoRelateService;
import com.junl.dms.mvc.xunchainfo.StringUtil;
import com.platform.mvc.base.BaseService;


public class BaoYanService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BaoYanService.class);

	public static final BaoYanService service = new BaoYanService();
	//获取油污的维修记录			DMS_WX_youWuDispose
	//获取养护通用的维修记录		DMS_WX_yangHuTongYongWeiXiu
	//获取路面病害的维修记录		DMS_WX_luMianBingHaiWeiXiu
	//获取裂缝处理的维修记录		DMS_WX_lieFengDispose
	//获取交通安全设施维修记录	DMS_WX_jtaqssWeiXiu
	//防撞护栏的维修记录			DMS_WX_fzhlWeiXiu
	public static String[] weiXiuJiLu_Array={"DMS_WX_youWuDispose","DMS_WX_yangHuTongYongWeiXiu",
		"DMS_WX_luMianBingHaiWeiXiu","DMS_WX_lieFengDispose",
		"DMS_WX_jtaqssWeiXiu","DMS_WX_fzhlWeiXiu"};
	
	public List<WxView> findAll(){
		return WxView.dao.findAll();
	}
	
	
	/**
	 * 获取维修记录
	 * @param byanIds
	 * @return
	 */
	public List<Map<String, Object>> getWeiXiuJiLuByIdFromTables(String baoyanid){
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Record> records=Db.find("select ids, startZHK,startZHM,endZHK,endZHM," + 
				"	 luXian,wzType,wzName,wzMiaoShu,weatherState,qiWen," + 
				"	 shiGongJiXie, weiXiuRenYuan,anQuanYuan,fuZeRen,pingJia,imgs" + 
				" from xz_wx where state=0 and taskInfoRelate='"+baoyanid+"'");
		if (records.size()>0) {
			for (int i = 0; i < records.size(); i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				Record record=records.get(i);
				map.put("ids", record.get("ids"));
				map.put("startZHK", record.get("startZHK"));
				map.put("startZHM", record.get("startZHM"));
				map.put("endZHK", record.get("endZHK"));
				map.put("endZHM", record.get("endZHM"));
				map.put("luXian", record.get("luXian"));
				map.put("wzType", record.get("wzType"));
				map.put("wzName", record.get("wzName"));
				map.put("wzMiaoShu", record.get("wzMiaoShu"));
				map.put("weatherState", record.get("weatherState"));
				map.put("qiWen", record.get("qiWen"));
				map.put("shiGongJiXie", record.get("shiGongJiXie"));
				map.put("weiXiuRenYuan", record.get("weiXiuRenYuan"));
				map.put("anQuanYuan", record.get("anQuanYuan"));
				map.put("fuZeRen", record.get("fuZeRen"));
				map.put("pingJia", record.get("pingJia"));
				map.put("imgs", record.get("imgs"));
				list.add(map);
			}
		}
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//在修改的界面的时候删除报验所绑定的任务关系编号
	public boolean deleteTaskRelateInUpdate(String taskRelateIds,String baoYanIds){
		boolean flag=false;
		//根据主键的ids获取外建表的集合
		List<BaoYanRelate> byrList=BaoYanRelate.dao.selectDMS_BY_weiXiuBaoYanFByByIds(baoYanIds);
		if (byrList.size()>0) {
			for (int j = 0; j < byrList.size(); j++) {
				BaoYanRelate bry=byrList.get(j);
				if (bry.getStr("taskId").equals(taskRelateIds)) {
					//修改任务关联表
					 TaskInfoRelate tir = TaskInfoRelateService.service.findById(taskRelateIds);
					 tir.set("state", 1);
					 tir.update();
					 //删除子表
					 flag=bry.delete();
				}
				 
			}
		}
		return flag;
	}
	
	
	
	/**
	 * 标记报验记录
	 * @param wxids
	 * @param baoYanId
	 */
	public void  saveBaoYanId(String wxids,String baoYanId){
		for (int i = 0; i < weiXiuJiLu_Array.length; i++) {
			//先解除当前报验下的所有的维修记录
			StringBuffer sb_d = new StringBuffer();
			sb_d.append("update ");
			sb_d.append(weiXiuJiLu_Array[i]);
			sb_d.append(" set baoyanId = '' , state=0 where baoyanId = '"+baoYanId+"'");
			
			//添加报验与维修记录的关系
			StringBuffer sb = new StringBuffer();
			sb.append("update ");
			sb.append(weiXiuJiLu_Array[i]);
			sb.append(" set baoyanId = '");
			sb.append(baoYanId);
			sb.append("' , state=5 where ids in (");
			sb.append(wxids);
			sb.append(") ");
			//执行
			Db.update(sb_d.toString());
			Db.update(sb.toString());
		}
	}
	
	/**
	 * edit  hank   
	 * @param baoyan
	 * @param paras
	 * @param choose_checkBox
	 * @return
	 */
	public boolean save(BaoYan baoyan, String[] paras, String choose_checkBox) {
		boolean flag=false;
		//保存图片
		StringBuffer sb = new StringBuffer();		
		if(paras != null) {
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				baoyan.set("imgs",newStr1);
			}
		}else{
			baoyan.set("imgs","");
		}
		//设置创建时间
		baoyan.set("createTime",new Timestamp(System.currentTimeMillis()));
		baoyan.set("state",0);
		
		if (StringUtil.isNotEmpty(choose_checkBox)) {
			String[] strs=choose_checkBox.split(",");
			//设置子表的关联数
			baoyan.set("byFNum", strs.length);
			flag = baoyan.save();
			
			if (strs.length>0) {//改造  hank
				saveBaoYanId(choose_checkBox, baoyan.getStr("ids"));
//				//添加子项表
//				 for (int i = 0; i < strs.length; i++) {
//					 BaoYanRelate baoYanRelate = new BaoYanRelate();
//					 baoYanRelate.set("byIds", baoyan.get("ids"));
//					 baoYanRelate.set("taskId", strs[i]);
//					 baoYanRelate.set("state", 0);
//					 baoYanRelate.save();
//					 //修改任务关联表
//					 TaskInfoRelate tir = TaskInfoRelateService.service.findById(strs[i]);
//					 tir.set("state", 2);
//					 tir.update();
//				}
			}
			if(flag){
				//在报验时，同时新增中间计量的数据
				JiLiang jiliang = new JiLiang();
				jiliang.set("byIds", baoyan.get("ids"));
				jiliang.set("returnNum", 0);
				jiliang.set("state", 0);
				jiliang.set("meteringNum", 0);
				jiliang.set("meteringMoney", 0);
				jiliang.save();
			}
		}
		return flag;
	}

	public boolean delete(String baoyanids) {
		boolean flag=false;
		for (int i = 0; i < weiXiuJiLu_Array.length; i++) {
			//先解除当前报验下的所有的维修记录
			StringBuffer sb_d = new StringBuffer();
			sb_d.append("update ");
			sb_d.append(weiXiuJiLu_Array[i]);
			sb_d.append(" set baoyanId = '' , state=0 where baoyanId = '"+baoyanids+"'");
			Db.update(sb_d.toString());
		}
		flag = BaoYan.dao.deleteById(baoyanids);
		//再删除时，同时删除中间计量				(加过权限判断了，只要计量过，就不可以删除报验)
		//delete_DMS_JL_jiLing_BybyIds(baoyanids);
		return flag;
	}
	
	//删除中间计量表
	public int delete_DMS_JL_jiLing_BybyIds(String ids){
		int num=-1;
		num=Db.update("delete from DMS_JL_jiLing where byIds='"+ids+"'");
		return num;
	}
	
	
	
	
	
	
	
	
	

	public BaoYan findById(String ids) {
		BaoYan baoyan = BaoYan.dao.findById(ids);
		return baoyan;
	}
	
	public boolean update(BaoYan baoyan, String[] paras, String choose_checkBox){
		boolean flag = false;
		//保存图片
		StringBuffer sb = new StringBuffer();
		if(paras != null) {
			for(int i=0;i<paras.length;i++){
				sb.append(paras[i]+",");
				String newStr = sb.toString();
				//去掉最后一个逗号
				String newStr1 = newStr.substring(0, newStr.length()-1);
				baoyan.set("imgs",newStr1);
			}
		}else{
			baoyan.set("imgs","");
		}
		if (StringUtil.isNotEmpty(choose_checkBox)) {
			String[] strs=choose_checkBox.split(",");
			//设置子表的关联数
			baoyan.set("byFNum", strs.length);
		}
	
		flag = baoyan.update();
		saveBaoYanId(choose_checkBox, baoyan.getStr("ids"));
		return flag;
	}
	//删除关联的子表
	public int delete_DMS_BY_weiXiuBaoYanF(String byIds) {
		int result =Db.update("DELETE FROM  DMS_BY_weiXiuBaoYanF where byIds =  '"+byIds+"'");
		return result;
	}
	
	public List<Record> getweixiuIDS(String baoyanId){
		String sql = "select * from xz_wx  where baoyanId ='"+baoyanId+"'";
		List<Record> records=Db.find(sql);
		return records;
	}
	
	
	
	//获取任务   废弃  by hank
	public List<Record> select_DMS_BY_weiXiuBaoYanF_ByIds(String byIds){
		List<BaoYanRelate> byrList=BaoYanRelate.dao.selectDMS_BY_weiXiuBaoYanFByByIds(byIds);
		String sql_ids="";
		if (byrList.size()>0) {
			for (int i = 0; i < byrList.size(); i++) {
				sql_ids+=byrList.get(i).getStr("taskId")+"\',\'";
			}
			//删除最后一个的逗号
			sql_ids = sql_ids.substring(0, sql_ids.length()-3);
		}
		if (!StringUtil.isNotEmpty(sql_ids)) {
			return null;
		}
		
		List<Record> list=getTaskByIds(sql_ids);
		return list;
	}
	
	//根据子表的中的byids集合获取指定的数据
	public List<Record> getTaskByIds(String ids){
		List<Record> list = Db.find("select "+
									"	t.DiseaseNumber DiseaseNumber, " + 
									"	t.fuZeRen fuZeRen, " + 
									"	t.taskNo taskNo, " + 
									"	tir.taskWeiXiuNum taskWeiXiuNum, "+
									"	tir.state tirState, "+
									"	tir.ids tirIds, "+
									"	x.xunChaStartZHK xunChaStartZHK, " + 
									"	x.xunChaStartZHM xunChaStartZHM, " + 
									"	x.xunChaEndZHK xunChaEndZHK, " + 
									"	x.xunChaEndZHM xunChaEndZHM, " + 
									"	x.xunChaTime xunChaTime, "+
									"	x.binghaiOrQueXianType binghaiOrQueXianType, " + 
									"	x.yuGuGongChengLiang yuGuGongChengLiang, " + 
									"	x.yuGuGongChengLiangDw yuGuGongChengLiangDw, " + 
									"	tir.state tirState, "+
									"	tir.overTime overTime, "+
									"	x.wzName wzName " + 
									"from DMS_RW_task_info_relate tir" + 
									"	left join DMS_XC_xunChaInfo x on x.ids=tir.xunChaInfoId" + 
									"	left join DMS_RW_task t  on t.ids=tir.taskId" + 
									"	LEFT JOIN DMS_PZ_chooseInfo ci on ci.ids=x.wxmkId" + 
									"			where 	tir.ids in ('"+ids+"')");
		return list;
	}
	
	
	
	
	
	/**
	 * 获取本月的任务数
	 * @return
	 */
	public int getTaskNo(){
		int res=1;
		List<BaoYan> list = BaoYan.dao.getTaskNo();
		if (list.size()>0) {
			BaoYan rt=list.get(0);
			res=rt.getInt("byNum")+1;
		}
		return res;
	}
	/**
	 * 转换任务数
	 * @param num
	 * @return
	 */
	public String intToStrong(int num){
		String str="";
		int length=String.valueOf(num).length();
		if (length==1) {
			str="00"+num;
		}else if (length==2) {
			str="0"+num;
		}else if (length==3) {
			str=""+num;
		}
		return str;
	}
	
	/**
	 * 
	 * @author wlw
	 * @date 2016年8月8日 下午5:28:15
	 * @description 通过ids查询维修报验数据库中的数据
	 *		TODO
	 * @param ids
	 * @return
	 *
	 */
	
	public BaoYan finddata(String ids){
		return BaoYan.dao.findById(ids);
	}
	public List<Record> findrenwu(String ids){
		String sql = getSql("manage.baoyan.findrenwu");
		List<Record> list = Db.find(sql,ids);
		return list;
	}



	public List<Record> showBaoYan(String ids) {
		List<Record> list = Db.find("SELECT "+
										"t.ids,"+
										"t.startZHK,"+
										"t.startZHM,"+
										"t.endZHK,"+
										"t.endZHM,"+
										"t.luXian,"+
										"t.wzType,"+
										"t.wzName,"+
										"t.wzMiaoShu,"+
										"t.weatherState,"+
										"t.qiWen,"+
										"t.shiGongJiXie,"+
										"t.weiXiuRenYuan,"+
										"t.anQuanYuan,"+
										"t.fuZeRen,"+
										"t.pingJia,"+
										"t.imgs,"+
										"t.diseaseType,"+
										"t.taskInfoRelate,"+
										"t.state,"+
										"t.meteringMoney,"+
										"t.meteringNum,"+
										"t.weiXiuTime,"+
										"t.baoyanId"+
									" FROM xz_wx t where  t.baoyanId='"+ids+"'");
		return list;
	}
	/**
	 * 获取维修记录
	 * @param byanIds
	 * @return
	 */
	public List<Map<String, Object>> getWeiXiuRecord(String byanIds){
		//将循环查询换成视图查询
		List<Map<String, Object>> _list=selectForEach("xz_wx",byanIds);
		return _list;
	}
	
	/**
	 * 循环查询各个维修记录表
	 */
	private List<Map<String, Object>> selectForEach(String tableName, String byanIds) {
		//首先根据报验的主键ids到报验外键表获取任务（任务表的外键表）的ids
		//根据获取到的任务（任务表的外键表）的ids获取维修表的记录数
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Record> list1=Db.find("select wx.state wxState,taskrelate.ids taskrelateIds, wx.meteringMoney meteringMoney,task.taskNo taskNo,wx.ids ids, wx.startZHK startZHK,wx.startZHM startZHM,wx.endZHK endZHK,wx.endZHM endZHM,wx.luXian luXian,wx.wzType wzType,wx.wzName wzName,wx.meteringNum meteringNum,xc.binghaiOrQueXianType binghaiOrQueXianType "+
					  "from "+tableName+" wx,DMS_RW_task_info_relate taskrelate,DMS_XC_xunChaInfo xc,DMS_RW_task task " + 
					  "where xc.ids=taskrelate.xunChaInfoId and wx.taskInfoRelate=taskrelate.ids and taskrelate.taskId=task.ids" + 
					  		" and isnull(wx.state,0)!=2 "+
					  		/*"	and wx.taskInfoRelate IN (select taskId from DMS_BY_weiXiuBaoYanF f " + 
					  									" where f.byIds='"+byanIds+"')");*/
					  		" and state=5 and wx.baoyanId='"+byanIds+"'");
					  									
		for (int i = 0; i < list1.size(); i++) {
			Record r=list1.get(i);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("taskrelateIds", r.get("taskrelateIds"));
			map.put("taskNo", r.get("taskNo"));
			map.put("ids", r.get("ids"));
			map.put("startZHK", r.get("startZHK"));
			map.put("startZHM", r.get("startZHM"));
			map.put("endZHK", r.get("endZHK"));
			map.put("endZHM", r.get("endZHM"));
			map.put("luXian", r.get("luXian"));
			map.put("wzType", r.get("wzType"));
			map.put("wzName", r.get("wzName"));
			map.put("wxState", r.get("wxState"));
			map.put("meteringNum", r.get("meteringNum"));
			map.put("meteringMoney", r.get("meteringMoney"));
			map.put("binghaiOrQueXianType", r.get("binghaiOrQueXianType"));
			list.add(map);
		}
		return list;
	}
}
