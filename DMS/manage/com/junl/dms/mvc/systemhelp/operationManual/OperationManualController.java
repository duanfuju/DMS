package com.junl.dms.mvc.systemhelp.operationManual;

import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;

@Controller(controllerKey = "/jf/manage/systemhelp/operationManual")
public class OperationManualController extends BaseController {

	public void index()
	{
		render("/manage/systemhelp/operationManual/index.html");
	}
}
