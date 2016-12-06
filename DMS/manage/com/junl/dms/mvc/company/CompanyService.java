package com.junl.dms.mvc.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class CompanyService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(CompanyService.class);

	public static final CompanyService service = new CompanyService();

	public boolean save(Company company) {
		return company.save();
	}

	public boolean delete(String ids) {
		return Company.dao.deleteById(ids);
	}

	public Company findById(String ids) {
		Company luXian = Company.dao.findById(ids);
		return luXian;
	}
	
	public boolean update(Company company){
		return company.update();
	}
	public List<Map<String, String>> findList(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Company> clist = Company.dao.findList();
		for (Company c : clist) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", c.getStr("name"));
			list.add(map);
		}
		return list;
	}

}
