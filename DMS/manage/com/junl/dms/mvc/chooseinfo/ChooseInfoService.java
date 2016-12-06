package com.junl.dms.mvc.chooseinfo;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

public class ChooseInfoService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ChooseInfoService.class);

	public static final ChooseInfoService service = new ChooseInfoService();

	/**
	 * 类型:1表示位置类型,2表示维修模块 <br/>
	 * 用到的字段有name，ids
	 * 
	 * @param type
	 * @return
	 */
	public List<ChooseInfo> findListByType(int type) {
		List<ChooseInfo> list = ChooseInfo.dao.findListByType(type);
		return list;
	}
	
	/**
	 * 根据Id获得模块名字
	 * @param ids
	 * @return
	 */
	public String getMKNameByIds(String ids)
	{
		String name = "";
		String sql = " select name from DMS_PZ_chooseInfo where ids = '"+ids+"' ";
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			name = recordList.get(0).get("name");
		}
		return name;
	}

}
