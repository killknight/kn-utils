<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basepackage}.${rootbasepackage}.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import com.yusys.weapon.web.dbm.model.${className};

/**
 * <p> Description:${table.tableAlias} Mapper </p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Create Date: <#if now??>${now?string('yyyy-MM-dd')}</#if></p>
 * <p> Company: YUSYS </p> 
 *@author:YX-LiAnDong
 *@version:version V1.0
 */                                                                           
public interface ${className}Mapper extends Mapper<${className}>, MySqlMapper<${className}> {
	
	/**
	@Select ("select " + 
		<#list table.columns as column>
		"t.${column.sqlName}, " + 
		</#list>
		"from ${table.sqlName} t " + 
		"where 1=1 limit 100 " )
	public GdPlan getGdPlansByWXUserID(@Param("wxUserID") String wxUserID, @Param("currentDate") String currentDate);3
	*/
	
}
