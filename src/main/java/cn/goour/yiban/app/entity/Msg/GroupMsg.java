package cn.goour.yiban.app.entity.Msg;

import java.io.Serializable;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

import cn.goour.yiban.app.entity.User;

public class GroupMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// {"name":"ybmp","args":[{"access_token":"df71ef17b9076c5a446111cdfa4f52f6",
	// "avatar":"http://img02.fs.yiban.cn/6746755/avatar/user/88","groupname":"","id":"14a39c7e-db98-48e9-a866-6fff5b3a138d",
	// "order":"MSG","poster":"6746755","text":"0123","togroup":"162061","truename":"","type":"1","userkind":"1","username":""}]}
	private String access_token;
	private String avatar;
	private String groupname;//群名称
	private String id;
	private String order;
	private String poster;
	private String text;
	private String togroup;//群的talkid
	private String truename;//当前用户的昵称
	private String type;
	private String userkind;
	private String username;//当前用户的昵称
	public GroupMsg() {
		this.access_token = "";
		this.avatar = "";
		this.groupname = "";
		this.id = UUID.randomUUID().toString();
		this.order = "MSG";
		this.poster = "";
		this.text = "";
		this.togroup = "";
		this.truename = "";
		this.type = "1";
		this.userkind = "1";
		this.username = "";
	}

	public GroupMsg(User user,JSONObject groupItem,String text) {
		this();
		this.access_token = user.getAccess_token();
		this.avatar = user.getPic_s();
		this.poster = user.getUser_id()+"";
		this.togroup = groupItem.getString("talkid");
		this.groupname = groupItem.getString("name");
		this.text = text;
		this.truename = user.getName();
		this.username = user.getNick();
	}

	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTogroup() {
		return togroup;
	}
	public void setTogroup(String togroup) {
		this.togroup = togroup;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserkind() {
		return userkind;
	}
	public void setUserkind(String userkind) {
		this.userkind = userkind;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
