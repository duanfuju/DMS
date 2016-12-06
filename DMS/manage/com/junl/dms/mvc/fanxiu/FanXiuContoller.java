package com.junl.dms.mvc.fanxiu;

import java.util.Map;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.fzhlwx.FzhlWeiXiu;
import com.junl.dms.mvc.jiaotong.JiaoTong;
import com.junl.dms.mvc.liefeng.LieFeng;
import com.junl.dms.mvc.waydisease.WayDisease;
import com.junl.dms.mvc.yanghu.YangHu;
import com.junl.dms.mvc.youwuchuli.Youwu;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;

/** 
 * @author  hank E-mail: hankun@junl.cn 
 * @date 创建时间：2016年8月30日 上午4:09:55 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Controller(controllerKey = "/jf/manage/fanxiu")
public class FanXiuContoller extends BaseController {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(FanXiuContoller.class);

	/**
	 * 油污列表
	 */
	public void youwuIndex() {
		Map<String, Object> map = splitPage.getQueryParam();
		map.put("state", "1");
		splitPage.setQueryParam(map);
		paging(ConstantInit.db_dataSource_main, splitPage, Youwu.sqlId_splitPageSelect, Youwu.sqlId_splitPageFrom);
		render("/manage/fanxiu/youwulist.html");
	}
	/**
	 * 养护通用返修列表
	 * /jf/manage/fanxiu/yanghuIndex
	 */
	public void yanghuIndex() {
		Map<String, Object> map = splitPage.getQueryParam();
		map.put("state", "2");
		splitPage.setQueryParam(map);
		paging(ConstantInit.db_dataSource_main, splitPage, YangHu.sqlId_splitPageSelect, YangHu.sqlId_splitPageFrom);
		render("/manage/fanxiu/yanghulist.html");
	}
	/**
	 * 路面灾害返修列表
	 * /jf/manage/fanxiu/lumianIndex
	 */
	public void lumianIndex() {
		Map<String, Object> map = splitPage.getQueryParam();
		map.put("state", "2");
		splitPage.setQueryParam(map);
		paging(ConstantInit.db_dataSource_main, splitPage, WayDisease.sqlId_splitPageSelect, WayDisease.sqlId_splitPageFrom);
		render("/manage/fanxiu/lumianlist.html");
	}
	/**
	 * 裂缝返修列表
	 * /jf/manage/fanxiu/liefengIndex
	 */
	public void liefengIndex() {
		Map<String, Object> map = splitPage.getQueryParam();
		map.put("state", "2");
		splitPage.setQueryParam(map);
		paging(ConstantInit.db_dataSource_main, splitPage, LieFeng.sqlId_splitPageSelect, LieFeng.sqlId_splitPageFrom);
		render("/manage/fanxiu/liefenglist.html");
	}
	/**
	 * 交通返修列表
	 * /jf/manage/fanxiu/jiaotongIndex
	 */
	public void jiaotongIndex() {
		Map<String, Object> map = splitPage.getQueryParam();
		map.put("state", "2");
		splitPage.setQueryParam(map);
		paging(ConstantInit.db_dataSource_main, splitPage, JiaoTong.sqlId_splitPageSelect, JiaoTong.sqlId_splitPageFrom);
		render("/manage/fanxiu/jiaotonglist.html");
	}
	/**
	 * 防撞护栏返修列表
	 * /jf/manage/fanxiu/fzhlIndex
	 */
	public void fzhlIndex() {
		Map<String, Object> map = splitPage.getQueryParam();
		map.put("state", "2");
		splitPage.setQueryParam(map);
		paging(ConstantInit.db_dataSource_main, splitPage, FzhlWeiXiu.sqlId_splitPageSelect, FzhlWeiXiu.sqlId_splitPageFrom);
		render("/manage/fanxiu/fzhllist.html");
	}
}
