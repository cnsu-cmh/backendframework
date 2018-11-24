package com.xiaoshu.backendframework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xiaoshu.backendframework.dto.GenerateInput;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class TemplateUtil {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	public static String getTemplete(String fileName) {
		return FileUtil.getText(TemplateUtil.class.getClassLoader().getResourceAsStream("generator/" + fileName));
	}


	private static String getPackagePath(String packageName) {
		String packagePath = packageName.replace(".", "/");
		if (!packagePath.endsWith("/")) {
			packagePath = packagePath + "/";
		}

		return packagePath;
	}

	/**
	 * 变量名
	 * @param beanName
	 * @return
	 */
	public static String lowerFirstChar(String beanName) {
		String name = StringUtil.str2hump(beanName);
		String firstChar = name.substring(0, 1);
		name = name.replaceFirst(firstChar, firstChar.toLowerCase());

		return name;
	}

	public static void saveTemplete(GenerateInput input, TemplateType templateType) {

		String text = replacedTemplete(getTemplete(templateType.getFileName()), input);

		String packageName[] = getPackageName(templateType, input);

		FileUtil.saveTextFile(text, input.getPath() + File.separator
				+ getPackagePath(packageName[0]) + packageName[1] + ".java");

		log.debug("生成"+templateType.getTname() + "模板", input.getBeanName());
	}

	public static String[] getPackageName(TemplateType templateType, GenerateInput input) {

		String packageName[] =  new String[2];

		switch (templateType) {
			case CONTROLLER:
				packageName[0] = input.getControllerPkgName();
				packageName[1] = input.getControllerName();
				break;
			case SERVICE:
				packageName[0] = input.getServicePkgName();
				packageName[1] = input.getServiceName();
				break;
			case SERVICEIMPL:
				packageName[0] = input.getServiceImplPkgName();
				packageName[1] = input.getServiceImplName();
				break;
		}

		return packageName;
	}

	private static String replacedTemplete(String text, GenerateInput input) {
		String beanName = input.getBeanName();
		String beanPackageName = input.getBeanPackageName();
		String mapperPackageName = input.getMapperPackageName();
		String mapperName = input.getMapperName();
		text = text.replace("{mapperPackageName}", mapperPackageName);
		text = text.replace("{beanPackageName}", beanPackageName);
		text = text.replace("{mapperName}", mapperName);
		text = text.replace("{daoParamName}", lowerFirstChar(mapperName));
		text = text.replace("{beanName}", beanName);
		text = text.replace("{beanParamName}", lowerFirstChar(beanName));
		text = text.replace("{controllerPkgName}", input.getControllerPkgName());
		text = text.replace("{controllerName}", input.getControllerName());
		return text;
	}

	public static void saveHtmlList(GenerateInput input) {
		String path = input.getPath();
		String beanName = input.getBeanName();
		String beanParamName = lowerFirstChar(beanName);

		String text = getTemplete("htmlList.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{beanName}", beanName);
		List<String> beanFieldNames = input.getBeanFieldName();
		text = text.replace("{columnsDatas}", getHtmlColumnsDatas(beanFieldNames));
		text = text.replace("{ths}", getHtmlThs(beanFieldNames));

		FileUtil.saveTextFile(text, path + File.separator + beanParamName + "List.html");
		log.debug("生成查询页面：{}模板", beanName);

		text = getTemplete("htmlAdd.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
		FileUtil.saveTextFile(text, path + File.separator + "add" + beanName + ".html");
		log.debug("生成添加页面：{}模板", beanName);

		text = getTemplete("htmlUpdate.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
		text = text.replace("{initData}", getInitData(beanFieldNames));
		FileUtil.saveTextFile(text, path + File.separator + "update" + beanName + ".html");
		log.debug("生成修改页面：{}模板", beanName);
	}

	private static CharSequence getInitData(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			builder.append("\t\t\t\t\t\t$('#" + b + "').val(data." + b + ");\n");
		});

		return builder.toString();
	}

	private static String getAddDivs(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			if (!"id".equals(b) && !"createTime".equals(b) && !"updateTime".equals(b)) {
				builder.append("\t\t\t<div class='form-group'>\n");
				builder.append("\t\t\t\t<label class='col-md-2 control-label'>" + b + "</label>\n");
				builder.append("\t\t\t\t<div class='col-md-10'>\n");
				builder.append("\t\t\t\t\t<input class='form-control' placeholder='" + b + "' type='text' name='" + b
						+ "' id='" + b + "' data-bv-notempty='true' data-bv-notempty-message='" + b + " 不能为空'>\n");
				builder.append("\t\t\t\t</div>\n");
				builder.append("\t\t\t</div>\n");
			}
		});
		return builder.toString();
	}

	private static String getHtmlThs(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			builder.append("\t\t\t\t\t\t\t\t\t<th>{beanFieldName}</th>\n".replace("{beanFieldName}", b));
		});
		return builder.toString();
	}

	private static String getHtmlColumnsDatas(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			builder.append("\t\t\t\t{\"data\" : \"{beanFieldName}\", \"defaultContent\" : \"\"},\n"
					.replace("{beanFieldName}", b));
		});

		builder.append("");
		return builder.toString();
	}


	public enum TemplateType {

		CONTROLLER("controller","controller.txt"),
		SERVICE("service","service.txt"),
		SERVICEIMPL("serviceImpl","serviceImpl.txt");

		private String tname;
		private String fileName;

		private TemplateType(String tname, String fileName) {
			this.tname = tname;
			this.fileName = fileName;
		}

		public String getTname() {
			return tname;
		}

		public void setTname(String tname) {
			this.tname = tname;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	}

}
