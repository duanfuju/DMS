package com.platform.mvc.role;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.group.Group;
import com.platform.mvc.user.UserService;

/**
 * 角色管理
 * @author 董华健
 */
@SuppressWarnings("unused")
@Controller(controllerKey = "/jf/platform/role")
public class RoleController extends BaseController {
	
	private static Logger log = Logger.getLogger(RoleController.class);
	
	
	private List<Group> noCheckedList; // 分组没有的角色
	private List<Group> checkedList; // 分组拥有的角色
	
	
	private List<RolePhone> noCheckedList_rp; // 手机端分组没有的角色
	private List<RolePhone> checkedList_rp; // 	手机端分组拥有的角色
	
	
	
	
	
	private String moduleIds; // 模块ids
	private String operatorIds; // 功能ids
	
	
	private String phoneRoles;

	/**
	 * 角色列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Role.sqlId_splitPageFrom);
		render("/platform/role/list.html");
	}

	/**
	 * 保存角色
	 */
	@Before(RoleValidator.class)
	public void save() {
		ids = RoleService.service.save(getModel(Role.class));
		redirect("/jf/platform/role");
	}

	/**
	 * 准备更新角色
	 */
	public void edit() {
		setAttr("role", Role.dao.findById(getPara()));
		render("/platform/role/update.html");
	}

	/**
	 * 更新角色
	 */
	@Before(RoleValidator.class)
	public void update() {
		RoleService.service.update(getModel(Role.class));
		redirect("/jf/platform/role");
	}
	
	/**
	 * 删除角色
	 */
	public void delete() {
		RoleService.service.delete(getPara() == null ? ids : getPara());
		redirect("/jf/platform/role");
	}
	
	/**
	 * 分组对应的角色选择
	 * update 用户对应的角色选择
	 */
	@SuppressWarnings("unchecked")
	public void select(){
		Map<String,Object> map = RoleService.service.select(ids);
		noCheckedList = (List<Group>) map.get("noCheckedList");
		checkedList = (List<Group>) map.get("checkedList");
		render("/platform/role/select.html");
	}
	
	
	/**
	 * 手机端查询角色	/jf/platform/role/selectPhone
	 */
	@SuppressWarnings("unchecked")
	public void selectPhone(){
		Map<String,Object> map = RoleService.service.selectPhone(ids);
		noCheckedList_rp =  (List<RolePhone>) map.get("noCheckedList");
		checkedList_rp = (List<RolePhone>) map.get("checkedList");
		render("/platform/role/selectPhone.html");
	}
	
	/**
	 * 手机端设置	/jf/platform/role/setPhone
	 */
	public void setPhone(){
		RoleService.service.setPhone(ids, phoneRoles);
		renderText(ids);
	}
	/**
	 * 查询角色拥有的功能
	 */
	public void getOperator(){
		Role role = Role.dao.findById(ids);
		renderJson(role);
	}

	/**
	 * 设置角色拥有的功能
	 */
	public void setOperator(){
		RoleService.service.setOperator(ids, moduleIds, operatorIds);
		renderJson(ids);
	}
	
}


