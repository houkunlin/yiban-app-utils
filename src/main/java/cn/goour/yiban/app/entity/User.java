package cn.goour.yiban.app.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户信息
 * @author HouKunLin
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	// 下面是data属性
	private boolean updateAction;
	private String access_token;// 会话密钥
	private String redirect;
	private boolean doubleCheck;
	private String avatar_server;
	private String voice_app_url;
	private String web_accesstoken_login_url;
	private String oauth2_access_token;
	private String universities_import_time_url;
	private String token;
	
	// 下面是User属性
	private int hiddenPushMsg;
	
	private School schoolInfo;
	
	private String pic_s;
	private int shieldFeeds;
	private String sex;
	private String source;
	private int feedsClose;
	private String nick;
	private String qrIndex;
	private int school_id;
	private String phone;
	private String password;
	private int user_id;
	private int maskedStranger;
	private String pic_b;
	private int authority;
	private String name;
	private int isPublic;
	private int shieldName;
	private int shieldMobile;
	private long provinceOrgId;
	private boolean organization;
	private boolean schoolVerify;
	
	
	// 下面是Friend好友的部分属性
	private String pic_m;
	private String user_name;
	private String remark;
	private String type;
	
	// 下面是一些自定义的属性
	private List<User> friends;
    
	public User() {
	}
	public static void main(String[] args) {
		System.out.println(1);
		User user = new User();
		Class<?> clazz = user.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			field.setAccessible(true);
			
			System.out.print(field.getName()+" text,");
		}
	}
	public User(String phone,String password) {
		this.phone = phone;
		this.password = password;
	}
	public User(String jsonString) {
		this(JSONObject.parseObject(jsonString));
		
	}
	public User(JSONObject jsonObject) {
		if (jsonObject == null) {
			return;
		}
		parseSelf(jsonObject);
	}
	public static List<User> parse(JSONObject jsonObject){
		List<User> list = new ArrayList<User>();
		if (jsonObject.getIntValue("response") == 100) {
			JSONObject data = jsonObject.getJSONObject("data");
			JSONArray list2 = data.getJSONArray("friends");
			for (Object object : list2) {
				JSONObject json = (JSONObject)object;
				User user = new User();
				user.pic_s = json.getString("pic_s");
				user.source = json.getString("source");
				user.user_id = json.getIntValue("user_id");
				user.pic_b = json.getString("pic_b");
				user.name = json.getString("name");
				user.isPublic = json.getIntValue("isPublic");
				user.organization = json.getString("isOrganization").equals("1");
				
				// 好友独有的几个字段
				user.pic_m = json.getString("pic_m");
				user.user_name = json.getString("user_name");
				user.remark = json.getString("remark");
				user.type = json.getString("type");
				list.add(user);
			}
		}
		return list;
	}
	private void parseSelf(JSONObject jsonObject){
		if (jsonObject.getIntValue("response") == 100) {
			JSONObject data = jsonObject.getJSONObject("data");
			String p1 = data.getString("updateAction");
			this.updateAction = p1.equals("true");
			this.access_token = data.getString("access_token");
			this.redirect = data.getString("redirect");
			p1 = data.getString("doubleCheck");
			this.doubleCheck = p1.equals("true");
			this.avatar_server = data.getString("avatar_server");
			this.voice_app_url = data.getString("voice_app_url");
			this.web_accesstoken_login_url = data.getString("web_accesstoken_login_url");
			this.oauth2_access_token = data.getString("oauth2_access_token");
			
			JSONObject user = data.getJSONObject("user");
			this.hiddenPushMsg = user.getIntValue("hiddenPushMsg");
			this.schoolInfo = new School(user.getJSONObject("schoolOrganization"));
			this.pic_s = user.getString("pic_s");
			this.shieldFeeds = user.getIntValue("shieldFeeds");
			this.sex = user.getString("sex");
			this.source = user.getString("source");
			this.feedsClose = user.getIntValue("feedsClose");
			this.nick = user.getString("nick");
			this.qrIndex = user.getString("qrIndex");
			this.school_id = user.getIntValue("school_id");
			this.phone = user.getString("phone");
			this.user_id = user.getIntValue("user_id");
			this.maskedStranger = user.getIntValue("maskedStranger");
			this.pic_b = user.getString("pic_b");
			this.authority = user.getIntValue("authority");
			this.name = user.getString("name");
			this.isPublic = user.getIntValue("isPublic");
			this.shieldName = user.getIntValue("shieldName");
			this.shieldMobile = user.getIntValue("shieldMobile");
			this.provinceOrgId = user.getLongValue("provinceOrgId");
			this.organization = user.getString("isOrganization").equals("1");
			this.schoolVerify = user.getString("isSchoolVerify").equals("1");
			
			this.universities_import_time_url = data.getString("universities_import_time_url");
			this.token = data.getString("token");
		}
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isUpdateAction() {
		return updateAction;
	}

	public void setUpdateAction(boolean updateAction) {
		this.updateAction = updateAction;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public boolean isDoubleCheck() {
		return doubleCheck;
	}

	public void setDoubleCheck(boolean doubleCheck) {
		this.doubleCheck = doubleCheck;
	}

	public String getAvatar_server() {
		return avatar_server;
	}

	public void setAvatar_server(String avatar_server) {
		this.avatar_server = avatar_server;
	}

	public String getVoice_app_url() {
		return voice_app_url;
	}

	public void setVoice_app_url(String voice_app_url) {
		this.voice_app_url = voice_app_url;
	}

	public String getWeb_accesstoken_login_url() {
		return web_accesstoken_login_url;
	}

	public void setWeb_accesstoken_login_url(String web_accesstoken_login_url) {
		this.web_accesstoken_login_url = web_accesstoken_login_url;
	}

	public String getOauth2_access_token() {
		return oauth2_access_token;
	}

	public void setOauth2_access_token(String oauth2_access_token) {
		this.oauth2_access_token = oauth2_access_token;
	}

	public int getHiddenPushMsg() {
		return hiddenPushMsg;
	}

	public void setHiddenPushMsg(int hiddenPushMsg) {
		this.hiddenPushMsg = hiddenPushMsg;
	}

	public School getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(School schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	public String getPic_s() {
		return pic_s;
	}

	public void setPic_s(String pic_s) {
		this.pic_s = pic_s;
	}

	public int getShieldFeeds() {
		return shieldFeeds;
	}

	public void setShieldFeeds(int shieldFeeds) {
		this.shieldFeeds = shieldFeeds;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getFeedsClose() {
		return feedsClose;
	}

	public void setFeedsClose(int feedsClose) {
		this.feedsClose = feedsClose;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getQrIndex() {
		return qrIndex;
	}

	public void setQrIndex(String qrIndex) {
		this.qrIndex = qrIndex;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMaskedStranger() {
		return maskedStranger;
	}

	public void setMaskedStranger(int maskedStranger) {
		this.maskedStranger = maskedStranger;
	}

	public String getPic_b() {
		return pic_b;
	}

	public void setPic_b(String pic_b) {
		this.pic_b = pic_b;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	public int getShieldName() {
		return shieldName;
	}

	public void setShieldName(int shieldName) {
		this.shieldName = shieldName;
	}

	public int getShieldMobile() {
		return shieldMobile;
	}

	public void setShieldMobile(int shieldMobile) {
		this.shieldMobile = shieldMobile;
	}

	public long getProvinceOrgId() {
		return provinceOrgId;
	}

	public void setProvinceOrgId(long provinceOrgId) {
		this.provinceOrgId = provinceOrgId;
	}

	public boolean isOrganization() {
		return organization;
	}

	public void setOrganization(boolean organization) {
		this.organization = organization;
	}

	public boolean isSchoolVerify() {
		return schoolVerify;
	}

	public void setSchoolVerify(boolean schoolVerify) {
		this.schoolVerify = schoolVerify;
	}

	public String getUniversities_import_time_url() {
		return universities_import_time_url;
	}

	public void setUniversities_import_time_url(String universities_import_time_url) {
		this.universities_import_time_url = universities_import_time_url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPic_m() {
		return pic_m;
	}

	public void setPic_m(String pic_m) {
		this.pic_m = pic_m;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", updateAction=");
		builder.append(updateAction);
		builder.append(", ");
		if (access_token != null) {
			builder.append("access_token=");
			builder.append(access_token);
			builder.append(", ");
		}
		if (redirect != null) {
			builder.append("redirect=");
			builder.append(redirect);
			builder.append(", ");
		}
		builder.append("doubleCheck=");
		builder.append(doubleCheck);
		builder.append(", ");
		if (avatar_server != null) {
			builder.append("avatar_server=");
			builder.append(avatar_server);
			builder.append(", ");
		}
		if (voice_app_url != null) {
			builder.append("voice_app_url=");
			builder.append(voice_app_url);
			builder.append(", ");
		}
		if (web_accesstoken_login_url != null) {
			builder.append("web_accesstoken_login_url=");
			builder.append(web_accesstoken_login_url);
			builder.append(", ");
		}
		if (oauth2_access_token != null) {
			builder.append("oauth2_access_token=");
			builder.append(oauth2_access_token);
			builder.append(", ");
		}
		if (universities_import_time_url != null) {
			builder.append("universities_import_time_url=");
			builder.append(universities_import_time_url);
			builder.append(", ");
		}
		if (token != null) {
			builder.append("token=");
			builder.append(token);
			builder.append(", ");
		}
		builder.append("hiddenPushMsg=");
		builder.append(hiddenPushMsg);
		builder.append(", ");
		if (schoolInfo != null) {
			builder.append("schoolInfo=");
			builder.append(schoolInfo);
			builder.append(", ");
		}
		if (pic_s != null) {
			builder.append("pic_s=");
			builder.append(pic_s);
			builder.append(", ");
		}
		builder.append("shieldFeeds=");
		builder.append(shieldFeeds);
		builder.append(", ");
		if (sex != null) {
			builder.append("sex=");
			builder.append(sex);
			builder.append(", ");
		}
		if (source != null) {
			builder.append("source=");
			builder.append(source);
			builder.append(", ");
		}
		builder.append("feedsClose=");
		builder.append(feedsClose);
		builder.append(", ");
		if (nick != null) {
			builder.append("nick=");
			builder.append(nick);
			builder.append(", ");
		}
		if (qrIndex != null) {
			builder.append("qrIndex=");
			builder.append(qrIndex);
			builder.append(", ");
		}
		builder.append("school_id=");
		builder.append(school_id);
		builder.append(", ");
		if (phone != null) {
			builder.append("phone=");
			builder.append(phone);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		builder.append("user_id=");
		builder.append(user_id);
		builder.append(", maskedStranger=");
		builder.append(maskedStranger);
		builder.append(", ");
		if (pic_b != null) {
			builder.append("pic_b=");
			builder.append(pic_b);
			builder.append(", ");
		}
		builder.append("authority=");
		builder.append(authority);
		builder.append(", ");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		builder.append("isPublic=");
		builder.append(isPublic);
		builder.append(", shieldName=");
		builder.append(shieldName);
		builder.append(", shieldMobile=");
		builder.append(shieldMobile);
		builder.append(", provinceOrgId=");
		builder.append(provinceOrgId);
		builder.append(", organization=");
		builder.append(organization);
		builder.append(", schoolVerify=");
		builder.append(schoolVerify);
		builder.append(", ");
		if (pic_m != null) {
			builder.append("pic_m=");
			builder.append(pic_m);
			builder.append(", ");
		}
		if (user_name != null) {
			builder.append("user_name=");
			builder.append(user_name);
			builder.append(", ");
		}
		if (remark != null) {
			builder.append("remark=");
			builder.append(remark);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (friends != null) {
			builder.append("friends=");
			builder.append(friends);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
