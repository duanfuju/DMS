package com.test.mvc.blog;

import com.platform.tools.ToolRandoms;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<=10000;i++){
			String uuid = ToolRandoms.getUuid(true);
			System.out.println(uuid);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
