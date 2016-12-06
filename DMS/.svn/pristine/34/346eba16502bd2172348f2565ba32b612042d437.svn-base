package com.junl.dms.mvc.binghaiindex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class BingHaiIndexService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BingHaiIndexService.class);

	public static final BingHaiIndexService service = new BingHaiIndexService();

	public boolean save(BingHaiIndex bingHaiIndex) {
		return bingHaiIndex.save();
	}

	public boolean delete(String id) {
		return BingHaiIndex.dao.deleteById(id);
	}

	public BingHaiIndex findById(String id) {
		return BingHaiIndex.dao.findById(id);
	}

	public boolean update(BingHaiIndex bingHaiIndex) {
		return bingHaiIndex.update();
	}

	public List<Map<String, Object>> findByWxmkId(String wxmkId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<BingHaiIndex> bhList = BingHaiIndex.dao.findByWxmkId(wxmkId);
		for (BingHaiIndex bh : bhList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", bh.getStr("name"));
			String level = bh.getStr("level");
			if (level==null) {
				level = "";
			}
			String[] levels=level.split("\\|");
			
			map.put("levels", levels);
			String gclDw = bh.getStr("gclDw");
			if (gclDw == null) {
				gclDw = "";
			}
			map.put("gclDws", gclDw);
			list.add(map);
		}
		return list;
	}

}
