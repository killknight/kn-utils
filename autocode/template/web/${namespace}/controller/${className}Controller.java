<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basepackage}.dbm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yusys.weapon.core.AppException;
import com.yusys.weapon.core.BaseController;
import ${basepackage}.${rootbasepackage}.model.${className};
import ${basepackage}.${rootbasepackage}.service.${className}Service;
import com.yusys.weapon.util.ConstantErr;
import com.yusys.weapon.util.Constants;
import com.yusys.weapon.util.DateUtil;
import com.yusys.weapon.util.RetMessageVo;
import com.yusys.weapon.util.StringUtil;

/**
 * <p> Description:${table.tableAlias}信息维护 包括：新增、修改、删除  </p>
 * <p> Copyright: Copyright (c) 2018 </p>
 * <p> Create Date: <#if now??>${now?string('yyyy-MM-dd')}</#if></p>
 * <p> Company: YUSYS </p> 
 *@author:YX-LiAnDong
 *@version:version V1.0
 */                                 
@RestController
@RequestMapping("/xxx")
@SessionAttributes(Constants.SESSION_USERID)                                          
public class ${className}Controller extends BaseController{
	
	@Autowired
    private ${className}Service ${classNameLower}Service;
	
    /**
	 * <pre>
	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> 
	 * 分页查询${table.tableAlias}数据   URL为  /
	 * </pre>
	 * @param ${classNameLower} 参数对象
	 * @return 返回${table.tableAlias}查询结果集合
	 */
    @RequestMapping
    public RetMessageVo  getPageList(${className} ${classNameLower}) {
        return ${classNameLower}Service.queryPageList(${classNameLower});
    }
    
    /**
 	 * <pre>
 	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> 
 	 * 查看${table.tableAlias}单笔详情
 	 * </pre>
 	 * @param ${classNameLower} 记录编号
 	 * @return 返回${table.tableAlias}数据对象
 	 */
    <#list table.columns as column>
	<#if column.pk>
	@RequestMapping(value = "/{${column.columnNameLower}}")
	public ${className} getDetail(@PathVariable("${column.columnNameLower}") Integer ${column.columnNameLower}) {
	   return ${classNameLower}Service.get${className}Mapper().selectByPrimaryKey(${column.columnNameLower});
	}
	</#if>
	</#list>
       
	 /**
 	 * <pre>
 	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> 
 	 * 新增或修改${table.tableAlias}记录
 	 * </pre>
 	 * @param ${classNameLower} ${table.tableAlias}对象信息
 	 * @return 返回${table.tableAlias}记录编号
 	 */
    @RequestMapping(value = "/addOrModify")
    public Integer addOrModify(${className} ${classNameLower},@ModelAttribute(Constants.SESSION_USERID) Integer userId ){
    	//拼装固定值
    	<#list table.columns as column>
	    <#if column.pk>
    	if (${classNameLower}.get${column.columnName}() != null) {
	    </#if>
		</#list>
    		${classNameLower}.setUpdateTime(new Date());
    		${classNameLower}.setUpdateUser(userId);
         } else {
        	 //新增 
        	 ${classNameLower}.setCreateTime(new Date());
        	 ${classNameLower}.setCreateUser(userId);
         }
    	return ${classNameLower}Service.saveOrUpdate(${classNameLower});

    }
    
    
    /**
 	 * <pre>
 	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> 
 	 * 删除${table.tableAlias}记录
 	 * </pre>
 	 * @param ${classNameLower} ${table.tableAlias}对象信息
 	 * @return 返回${table.tableAlias}记录编号
 	 */
    <#list table.columns as column>
    <#if column.pk>
	@RequestMapping(value = "/remove/{${column.columnNameLower}}")
    public void remove(@PathVariable("${column.columnNameLower}") Integer ${column.columnNameLower}) {
		${classNameLower}Service.get${className}Mapper().deleteByPrimaryKey(${column.columnNameLower});
	}
    </#if>
	</#list>
		
    
    
    
	
}
