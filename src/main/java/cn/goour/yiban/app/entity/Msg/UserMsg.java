package cn.goour.yiban.app.entity.Msg;

import java.io.Serializable;
import java.util.UUID;

import cn.goour.yiban.app.entity.User;

public class UserMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// {"name":"ybmp","args":[{"access_token":"df71ef17b9076c5a446111cdfa4f52f6","audio_length":0,
	// "avatar":"http://img02.fs.yiban.cn/6746755/avatar/user/88","id":"ed038578-f56b-49e2-9a97-f4f72e2749b0",
	// "order":"MSG","poster":"6746755","text":"1234567890","touser":"6502935","truename":"","type":"1",
	// "userkind":"4","username":""}]}
	private String access_token;
	private int audio_length;
	private String avatar;
	private String id;
	private String order;
	private String poster;
	private String text;
	private String touser;
	private String truename;
	private String type;
	private String userkind;
	private String username;

	public UserMsg() {
		this.access_token = "";
		this.audio_length = 0;
		this.avatar = "";
		this.id = UUID.randomUUID().toString();
		this.order = "MSG";
		this.poster = "";
		this.text = "";
		this.touser = "";
		this.truename = "";
		this.type = "1";
		this.userkind = "4";
		this.username = "";
	}

	public UserMsg(User user,String touserid,String text) {
		this();
		this.access_token = user.getAccess_token();
		this.avatar = user.getPic_s();
		this.poster = user.getUser_id()+"";
		this.touser = touserid;
		this.text = text;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getAudio_length() {
		return audio_length;
	}

	public void setAudio_length(int audio_length) {
		this.audio_length = audio_length;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMsg [");
		if (access_token != null) {
			builder.append("access_token=");
			builder.append(access_token);
			builder.append(", ");
		}
		builder.append("audio_length=");
		builder.append(audio_length);
		builder.append(", ");
		if (avatar != null) {
			builder.append("avatar=");
			builder.append(avatar);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (order != null) {
			builder.append("order=");
			builder.append(order);
			builder.append(", ");
		}
		if (poster != null) {
			builder.append("poster=");
			builder.append(poster);
			builder.append(", ");
		}
		if (text != null) {
			builder.append("text=");
			builder.append(text);
			builder.append(", ");
		}
		if (touser != null) {
			builder.append("touser=");
			builder.append(touser);
			builder.append(", ");
		}
		if (truename != null) {
			builder.append("truename=");
			builder.append(truename);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (userkind != null) {
			builder.append("userkind=");
			builder.append(userkind);
			builder.append(", ");
		}
		if (username != null) {
			builder.append("username=");
			builder.append(username);
		}
		builder.append("]");
		return builder.toString();
	}

}
