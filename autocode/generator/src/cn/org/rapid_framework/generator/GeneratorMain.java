package cn.org.rapid_framework.generator;

import cn.org.rapid_framework.jdbc.sqlgenerator.SqlGenerator;
/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

public class GeneratorMain {
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印数据库中的表名称
	//	String s[] = {"t_gd_book"};
		g.deleteOutRootDir();							//删除生成器的输出目录
		g.generateByTable("trusteeship","template");	//通过数据库表生成文件,template为模板的根目录
//		g.generateByArray(s,"template");
//		g.generateByAllTable("template");	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
//		g.generateByClass(Blog.class,"template_clazz");
		 
//		g.deleteByTable("it_sys_user", "template"); //删除生成的文件
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}
}
