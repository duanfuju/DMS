package com.junl.dms.mvc.mappoint;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.jfinal.plugin.activerecord.ICallback;

public class MapPointerPro implements ICallback {
	
	private String roadName;
	
	private String direction;
	
	private double startKM;

	private String proName;
	
	private float result;
	
	
	public float getResult() {
		return result;
	}

	public MapPointerPro(String roadName, String direction, double startKM,
			String proName) {
		super();
		this.roadName = roadName;
		this.direction = direction;
		this.startKM = startKM;
		this.proName = proName;
	}


	@Override
	public Object call(Connection conn) throws SQLException {
		
		CallableStatement proc = null; 
		try {
			String call = "{ call "+proName+"(?,?,?,?) }";
			proc = (CallableStatement) conn.prepareCall(call);
			proc.setString(1, roadName);
			proc.setString(2, direction);
			proc.setFloat(3, (float)startKM);
			proc.registerOutParameter(4, java.sql.Types.FLOAT);
			proc.execute(); 
			result = proc.getFloat(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
