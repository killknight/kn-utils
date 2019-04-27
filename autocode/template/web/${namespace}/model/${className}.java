<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dbm.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yusys.weapon.core.BaseEntity;

/**
 * <p> Description:${table.tableAlias} model </p>
 * <p> Copyright: Copyright (c) 2018 </p>
 * <p> Create Date: <#if now??>${now?string('yyyy-MM-dd')}</#if></p>
 * <p> Company: YUSYS </p> 
 *@author:YX-LiAnDong
 *@version:version V1.0
 */
@Table(name = "${table.sqlName}")
public class ${className}  extends BaseEntity{
	
	<#list table.columns as column>
	<#if column.pk>
	
	@Id
    @Column(name = "${column.sqlName}")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	<#else>
	
	@Column(name = "${column.sqlName}")
	</#if>
	private ${column.javaType} ${column.columnNameLower};//${column.columnAlias}
	</#list>

	<#list table.columns as column>
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
	
	public ${className}(){
	}

	
	public String toString() {
		return new StringBuffer()
		<#list table.columns as column>
			.append("${column.columnName}:["+get${column.columnName}()+"]")
		</#list>
			.toString();
	}

}




