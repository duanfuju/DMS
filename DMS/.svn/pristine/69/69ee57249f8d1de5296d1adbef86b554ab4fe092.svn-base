package com.junl.dms.mvc.roadassessment.roadtechnicstate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.ICallback;

public class RoadTechnicStatePro implements ICallback {
	
	private String startTime;
	
	private String endTime;
	
	private String roadName;
	
	private String direction1;
	
	private String direction2;
	
	private String proName;
	
	private ResultSet result;
	
	private List<Map<String,Object>> resultHZData = new ArrayList<Map<String,Object>>();
	
	public List<Map<String,Object>> getResult() {
		return resultHZData;
	}
	
	//保留小数
	private DecimalFormat dfToThree = new DecimalFormat("#.000");
	private DecimalFormat dfToOne = new DecimalFormat("#.0");
	
	public RoadTechnicStatePro(String startTime, String endTime,
			String roadName, String direction1, String direction2,
			String proName) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.roadName = roadName;
		this.direction1 = direction1;
		this.direction2 = direction2;
		this.proName = proName;
	}


	@Override
	public Object call(Connection conn) throws SQLException {
		CallableStatement proc = null; 
		try {
			String call = "{ call "+proName+"(?,?,?,?,?) }";
			proc = (CallableStatement) conn.prepareCall(call);
			proc.setString(1, startTime);
			proc.setString(2, endTime);
			proc.setString(3, roadName);
			proc.setString(4, direction1);
			proc.setString(5, direction2);
			proc.execute(); 
			result = proc.getResultSet();
			if(result != null)
			{
				while(result.next()){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("V", result.getString("V").toString());
					map.put("CD", dfToThree.format(Double.valueOf(result.getString("CD")==null?"0.000":result.getString("CD").toString())));
					map.put("BL", dfToOne.format(Double.valueOf(result.getString("BL")==null?"0.0":result.getString("BL").toString())));
					map.put("FX1", dfToThree.format(Double.valueOf(result.getString("FX1")==null?"0.000":result.getString("FX1").toString())));
					map.put("FX1BL", dfToOne.format(Double.valueOf(result.getString("FX1BL")==null?"0.0":result.getString("FX1BL").toString())));
					map.put("FX2", dfToThree.format(Double.valueOf(result.getString("FX2")==null?"0.000":result.getString("FX2").toString())));
					map.put("FX2BL", dfToOne.format(Double.valueOf(result.getString("FX2BL")==null?"0.0":result.getString("FX2BL").toString())));
					resultHZData.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
