<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basepackage}.${rootbasepackage}.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yusys.weapon.web.dbm.mapper.${className}Mapper;
import com.yusys.weapon.web.dbm.model.${className};
import com.yusys.weapon.util.RetMessageVo;
import com.yusys.weapon.util.StringUtil;
/**
 * <p> Description:${table.tableAlias} service </p>
 * <p> Copyright: Copyright (c) 2018 </p>
 * <p> Create Date: <#if now??>${now?string('yyyy-MM-dd')}</#if></p>
 * <p> Company: YUSYS </p> 
 *@author:YX-LiAnDong
 *@version:version V1.0
 */                               
@Service                                            
public class ${className}Service {
	
	@Autowired
    private ${className}Mapper ${classNameLower}Mapper;
	
	/**
	 * <pre>
	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> YX-LiAnDong
	 * 分页查询${table.tableAlias}数据
	 * </pre>
	 * @param ${classNameLower} 参数对象
	 * @return 返回${table.tableAlias}查询结果集合
	 */
	public RetMessageVo queryPageList(${className} ${classNameLower}) { 
		
		RetMessageVo retMessageVo= new RetMessageVo();
		 
		Example example = new Example(${className}.class);
		
		<#list table.columns as column>
		//${column.columnAlias}
    	if(!StringUtil.isEmpty(${classNameLower}.get${column.columnName}())){
    		example.createCriteria().andEqualTo("${column.columnNameLower}",${classNameLower}.get${column.columnName}());
    	} 
    	</#list> 
    	
    	int count = ${classNameLower}Mapper.selectCountByExample(example);
        if (${classNameLower}.getPage() != null && ${classNameLower}.getRows() != null) {
            PageHelper.startPage(${classNameLower}.getPage(), ${classNameLower}.getRows());
        }
        
        List<${className}> list = ${classNameLower}Mapper.selectByExample(example);
        retMessageVo.setCount(count);
        retMessageVo.setContent(list);
        
        return retMessageVo;
	}
	
	/**
	 * <pre>
	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> YX-LiAnDong
	 * 普通查询${table.tableAlias}数据
	 * </pre>
	 * @param ${classNameLower} 参数对象
	 * @return 返回${table.tableAlias}查询结果集合
	 */
	public List<${className}> queryList(${className} ${classNameLower}) { 
		
		Example example = new Example(${className}.class);
		
		<#list table.columns as column>
		//${column.columnAlias}
    	if(!StringUtil.isEmpty(${classNameLower}.get${column.columnName}())){
    		example.createCriteria().andEqualTo("${column.columnName}",${classNameLower}.get${column.columnName}());
    	} 
    	</#list> 
    	
        
        return ${classNameLower}Mapper.selectByExample(example);
	}
	
	/**
	 * <pre>
	 * <#if now??>${now?string('yyyy-MM-dd')}</#if> YX-LiAnDong
	 * 新增或修改${table.tableAlias}数据
	 * </pre>
	 * @param ${classNameLower} 参数对象
	 * @return 返回${table.tableAlias}查询结果集合
	 */
    public Integer saveOrUpdate(${className} ${classNameLower}) {
    	 <#list table.columns as column>
    	    <#if column.pk>
    	 if (${classNameLower}.get${column.columnName}() != null) {    
    		 ${className} ${classNameLower}DB = ${classNameLower}Mapper.selectByPrimaryKey(${classNameLower}.get${column.columnName}());
    		 <#else>
    		 ${classNameLower}DB.set${column.columnName}(${classNameLower}.get${column.columnName}());
	       </#if>
		</#list>
			${classNameLower}Mapper.updateByPrimaryKey(${classNameLower}DB);
	     } else {
	    	${classNameLower}Mapper.insert(${classNameLower});
	     }
    	 <#list table.columns as column>
 	    <#if column.pk>
 	     return ${classNameLower}.get${column.columnName}();
	       </#if>
		</#list>
	   
	 }
	
	/**
	 * @return the ${classNameLower}Mapper
	 */
	public ${className}Mapper get${className}Mapper() {
		return ${classNameLower}Mapper;
	}
	/**
	 * @param ${classNameLower}Mapper the ${classNameLower}Mapper to set
	 */
	public void set${className}Mapper(${className}Mapper ${classNameLower}Mapper) {
		this.${classNameLower}Mapper = ${classNameLower}Mapper;
	}
	
}
