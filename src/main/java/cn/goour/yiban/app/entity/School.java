package cn.goour.yiban.app.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 学校信息
 * @author HouKunLin
 *
 */
public class School implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private int school_id;
	private long user_id;
	private String school_name;
	private String brief;
	private String pic_s;
	private String publicName;
	private String pic_b;
	private boolean administrative;
	public School() {
	}
	public School(String jsonStr) {
		this(JSONObject.parseObject(jsonStr));
	}
	public School(JSONObject jsonObject) {
		if (jsonObject == null) {
			return;
		}
		this.brief = jsonObject.getString("brief");
		this.school_id = jsonObject.getIntValue("school_id");
		this.pic_s = jsonObject.getString("pic_s");
		this.user_id = jsonObject.getLongValue("user_id");
		this.publicName = jsonObject.getString("publicName");
		this.pic_b = jsonObject.getString("pic_b");
		this.school_name = jsonObject.getString("school_name");
		this.administrative = jsonObject.getString("isAdministrative").equals("1");
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getPic_s() {
		return pic_s;
	}
	public void setPic_s(String pic_s) {
		this.pic_s = pic_s;
	}
	public String getPublicName() {
		return publicName;
	}
	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}
	public String getPic_b() {
		return pic_b;
	}
	public void setPic_b(String pic_b) {
		this.pic_b = pic_b;
	}
	public boolean isAdministrative() {
		return administrative;
	}
	public void setAdministrative(boolean administrative) {
		this.administrative = administrative;
	}
	@Override
	public String toString() {
		return "School [id=" + id + ", school_id=" + school_id + ", user_id=" + user_id + ", school_name=" + school_name
				+ ", brief=" + brief + ", pic_s=" + pic_s + ", publicName=" + publicName + ", pic_b=" + pic_b
				+ ", administrative=" + administrative + "]";
	}
}
