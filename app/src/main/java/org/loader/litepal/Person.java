package org.loader.litepal;

import org.litepal.crud.DataSupport;

public class Person extends DataSupport{

	private String name;//名字
	private String time;//时间
	private String age;//年龄
	private String sex;//性别

	public void setName(String name) {
		this.name = name;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}

	public String getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}
}
