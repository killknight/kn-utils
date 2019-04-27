<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ${rootbasepackage}.util.UtilString;
import ${basepackage}.dao.I${className}Dao;
import ${rootbasepackage}.common.base.BaseDAOImpl;

import ${rootbasepackage}.common.base.PageInfo;
import ${rootbasepackage}.vo.dbvo.${className};

<#include "/java_copyright.include">
@Repository
public class ${className}DaoImpl extends BaseDAOImpl implements I${className}Dao{
	/**
	 * <pre>
	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> ${author}
	 * Description: paging query ${table.tableAlias} collection
	 * </pre>
	 * 
	 * @param ${classNameLower} ${table.tableAlias} object
	 * @param pageInfo paging object for I/O
	 * @throws Exception  
	 */
	public void getPaging${className}List(${className} ${classNameLower},PageInfo pageInfo) throws Exception{
		List<${className}> resultList = new ArrayList<${className}>();
		List<Object> params = new ArrayList<Object>();
		//统计数量SQL
		String count = "SELECT COUNT(*)";
		//查询列表SQL
		StringBuffer hql = new StringBuffer(" FROM ${className} T WHERE 1 = 1 ");
		<#list table.columns as column>
		//${column.columnAlias}
		if(!UtilString.isEmpty(${classNameLower}.get${column.columnName}())){
			hql.append(" AND T.${column.columnNameLower} = ?");
			params.add(${classNameLower}.get${column.columnName}());
		}
		</#list>
	 
		hql.append(" ORDER BY T.${table.idColumn.columnNameLower} DESC");
		int totalCount = this.querySizeByHql(count + hql.toString(),null);
		resultList = this.queryByHql(hql.toString(), params.toArray(),pageInfo.getNumPerPage(), pageInfo.getCurrentPage());
		pageInfo.setTotalCount(totalCount);
		pageInfo.setValueList(resultList);
	}
	
	/**
	 * <pre>
	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> ${author}
	 * Description: select ${table.tableAlias} collection
	 * </pre>
	 * 
	 * @param ${classNameLower} ${table.tableAlias} object 
	 * @result ${className} collection
	 * @throws Exception 异常
	 */
	public List<${className}> get${className}List(${className} ${classNameLower}) throws Exception{
		List<${className}> resultList = new ArrayList<${className}>();
		List<Object> params = new ArrayList<Object>();
		//select HQL
		StringBuffer hql = new StringBuffer(" FROM ${className} T WHERE 1 = 1 ");
		<#list table.columns as column>
		//${column.columnAlias}
		if(!UtilString.isEmpty(${classNameLower}.get${column.columnName}())){
			hql.append(" AND T.${column.columnNameLower} = ?");
			params.add(${classNameLower}.get${column.columnName}());
		}
		</#list>
	 
		hql.append(" ORDER BY T.${table.idColumn.columnNameLower} DESC");
		resultList = this.queryByHql(hql.toString(), params.toArray());
	}

	
}
