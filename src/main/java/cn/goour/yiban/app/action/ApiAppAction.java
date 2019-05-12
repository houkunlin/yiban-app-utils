package cn.goour.yiban.app.action;

import java.net.URLEncoder;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

import cn.goour.utils.http.Http;
import cn.goour.utils.http.HttpConfig;
import cn.goour.utils.http.Params;
import cn.goour.utils.security.impl.MD5Impl;
import cn.goour.utils.tools.NullValid;
import cn.goour.yiban.app.entity.User;
@Deprecated
public class ApiAppAction {
	private User user;
	private Object appVersion;

	public ApiAppAction() {
	}

	public ApiAppAction(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 未知作用
	 * @param notice_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject API_Already_Read(String notice_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("notice_id", notice_id);
		String url = "http://mobile.yiban.cn/api/v1/notices/response";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public JSONObject API_FeedBack(String content) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", content);
		String url = "http://mobile.yiban.cn/api/v1/feedback";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AcceptJoinGroup(String record_id, String status) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("record_id", record_id);
		params.add("status", status);
		String url = "http://mobile.yiban.cn/api/v1/groups/accept";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AddComments(String content, String toUserId, String toCommentId, String id)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", content);
		params.add("toUserId", toUserId);
		params.add("toCommentId", toCommentId);
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/comments".replace("{id}", id);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 发布动态
	 * @param content 发布内容
	 * @param privacy 公开=0时to_userids为空，部分好友可见=1时to_userids为好友的id列表，用逗号隔开
	 * @param kind kind=2
	 * @param to_userids 
	 * @param address 发布地址名称
	 * @param artwork 要么0要么1
	 * @param lat 纬度
	 * @param lng 经度
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_AddDiscover(String content, int privacy, int kind, String to_userids, String address,
			String artwork, String lat, String lng) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", URLEncoder.encode(content, "UTF-8"));
		params.add("visibleScope", privacy);
		params.add("kind", kind);
		params.add("to_userids", to_userids);
		params.add("address", address);
		params.add("artwork", artwork);
		params.add("lat", lat);
		params.add("lng", lng);
		String url = "http://mobile.yiban.cn/api/v3/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 发布动态
	 * @param content 发布内容
	 * @param privacy 公开=0时to_userids为空，部分好友可见=1时to_userids为好友的id列表，用逗号隔开
	 * @param to_userids 
	 * @param address 发布地址名称
	 * @param artwork 要么0要么1
	 * @param lat 纬度
	 * @param lng 经度
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_AddDiscover(String content, int privacy, String to_userids, String address, String artwork,
			String lat, String lng) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", content);
		params.add("visibleScope", privacy);
		params.add("to_userids", to_userids);
		params.add("address", address);
		params.add("artwork", artwork);
		params.add("lat", lat);
		params.add("lng", lng);
		String url = "http://mobile.yiban.cn/api/v3/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AddDowns(String id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/downs".replace("{id}", id);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AddLightApp(int app_ids) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("app_ids", app_ids);
		String url = "http://mobile.yiban.cn/api/v2/application/user";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AddMemberToGroups(String group_ids, String user_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_ids", group_ids);
		params.add("user_id", user_id);
		String url = "http://mobile.yiban.cn/api/v1/user/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，无法post，当get的时候，返回了一个动态列表中他人分享的内容，待确定
	 * @param content
	 * @param privacy
	 * @param to_userids
	 * @param address
	 * @param share
	 * @param shareTitle
	 * @param shareUrl
	 * @param shareContent
	 * @param shareImage
	 * @param shareSource
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_AddShareDiscover(String content, int privacy, String to_userids, String address, int share,
			String shareTitle, String shareUrl, String shareContent, String shareImage, String shareSource)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", content);
		params.add("privacy", privacy);
		params.add("to_userids", to_userids);
		params.add("address", address);
		params.add("share", share);
		params.add("shareTitle", shareTitle);
		params.add("shareUrl", shareUrl);
		params.add("shareContent", shareContent);
		params.add("shareImage", shareImage);
		params.add("shareSource", shareSource);
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail".replace("{id}", ""+user.getUser_id());
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AddUps(String id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/ups".replace("{id}", id);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AddUserLightAppList(String app_ids, String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("app_ids", app_ids);
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/user/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_AllKind(String type, String page, String pagesize, String keywords) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("type", type);
		params.add("page", page);
		params.add("pagesize", pagesize);
		if ((keywords != null) && (!"".equals(keywords))) {
			params.add("keywords", keywords);
		}
		String url = "http://mobile.yiban.cn/api/v1/home/recommendapp";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 获得APP首页的横幅大图和链接
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_Banner() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/home/banner";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，返回空的列表信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_BlackList() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/blacks";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 初步判断是一个删除好友的接口，传入非好友id提示删除好友失败或非好友关系
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_BlackListAdd(String user_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		String url = "http://mobile.yiban.cn/api/v1/user/blacks";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，传入参数服务器端会报不存在方法
	 * @param paramString
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_BlackListDelete(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/blacks/" + paramString;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_CheckCode(String passwd, String phone, String code, String identify) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("passwd", passwd);
		params.add("phone", phone);
		params.add("code", code);
		params.add("identify", identify);
		params.add("ct", "2");
		String url = "http://mobile.yiban.cn/api/v1/passport/doublecheck";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 似乎返回了当前用户的动态，但是测试中只获得3条动态
	 * @param direction 未知作用
	 * @param compareId 未知作用
	 * @param limit 貌似是一个获取长度，类似size
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_CheckInDiscoverList(String direction, String compareId, int limit) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("direction", direction);
		params.add("compareId", compareId);
		params.add("limit", limit);
		String url = "http://mobile.yiban.cn/api/v3/feeds/checkin";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，无法post
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_CheckPhone(String phone) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("phone", phone);
		String url = "http://mobile.yiban.cn/api/v1/passport/checkphone";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回最新版本的APP信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_CheckVersion() throws Exception {
		String url = "http://mobile.yiban.cn/api/v1/version/checkupdate";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ClickRedPacket(int id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/{id}/info".replace("{id}", id + "");
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_CollegesClasses(String group_id, String school_id, String keyWord, String page, String num,
			String isAdministrative) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", group_id);
		params.add("school_id", school_id);
		params.add("keyWord", keyWord);
		params.add("page", page);
		params.add("num", num);
		params.add("isAdministrative", isAdministrative);// =1 or =2 , true=1
															// false=2
		String url = "http://mobile.yiban.cn/api/v2/colleges/classes";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，无法post
	 * @param type 默认=2
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_CommentAboutMe(String type, String page) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("type", type);
		params.add("page", page);
		String url = "http://mobile.yiban.cn/api/v1/feeds/message";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，无法post，可能参数错误
	 * @param lastid
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_CommentMessageList(int lastid, int count) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("lastid", lastid);
		params.add("count", count);
		String url = "http://mobile.yiban.cn/api/v1/feeds/message";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DeleteComments(String id, String cid) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/comments/{cid}".replace("{id}", id).replace("{cid}",
				cid);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DeleteDynamic(String id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}".replace("{id}", id);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DeleteGroupLightAppList(int paramInt1, int group_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", group_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/applications/" + paramInt1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DeleteUserLightAppList(String paramString1, String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/user/applications/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Delete_Album_Or_Image(String path) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("path", path);
		String url = "http://mobile.yiban.cn/api/v2/gallerys";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，一直提示接口不存在，仿佛是获取某条动态的评论列表的
	 * @param id
	 * @param content
	 * @param to_userid
	 * @param to_comment
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_DiscoverComments(int id, String content, int to_userid, int to_comment) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", content);
		if ((to_comment != 0) && (to_userid != 0)) {
			params.add("to_userid", to_userid);
			params.add("to_comment", to_comment);
		}
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail/".replace("{id}", id+"");// + id + "/comments/";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，仿佛是删除动态的
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_DiscoverDelete(int id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail/".replace("{id}", id+"");
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DiscoverDetail(int id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail".replace("{id}", id + "");
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DiscoverList(int user_id, int lastid, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (user_id != 0) {
			params.add("user_id", user_id);
		}
		params.add("lastid", lastid);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail".replace("{id}", user_id+ "");
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回我的好友动态列表
	 * @param direction 页数
	 * @param compareId 最后一条动态的id
	 * @param limit 数量
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_DiscoverListNewVersion(String direction, String compareId, int limit) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("direction", direction);
		params.add("compareId", compareId);
		params.add("limit", limit);
		String url = "http://mobile.yiban.cn/api/v3/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DiscoverList_Old(int user_id, int lastid, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (user_id != 0) {
			params.add("user_id", user_id);
		}
		params.add("lastid", lastid);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DiscoverReViewDelete(int id, int paramInt2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail/" + id + "/comments/" + paramInt2;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DiscoverSadFace(int id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("id", id);
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail/down";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DiscoverUp(int id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("id", id);
		String url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail/up".replace("{id}",id+ "");
		url = "http://mobile.yiban.cn/api/v3/feeds/{id}/detail/up";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_DynamicAboutMe(int direction, int compareId, int limit) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("direction", direction);
		params.add("compareId", compareId);
		params.add("limit", limit);
		String url = "http://mobile.yiban.cn/api/v3/feeds/messages";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用
	 * 返回了一个易班内置的uri地址信息<br>
	 * {"data":{"url":"yiban://organizations/6502935?type=1","status":"0"},"response":"100","message":"请求成功"}
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_EduOrganizationStatus(int user_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		String url = "http://mobile.yiban.cn/api/v2/province/orgs/isprovince";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_FriendGroupsList(int user_id, String kind, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("kind", kind);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用
	 * @param user_id
	 * @param kind =1 or =2
	 * @param page
	 * @param num
	 * @param isAdministrative =1 or =2
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_FriendGroupsList(int user_id, String kind, int page, int num, String isAdministrative)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("kind", kind);
		params.add("page", page);
		params.add("num", num);
		params.add("isAdministrative", isAdministrative);// =1 or =2 ,true=1
															// false=2
		String url = "http://mobile.yiban.cn/api/v1/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetAllUser(String keyword, int search, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("keyword", keyword);
		params.add("search", search);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/search/users";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetClassList(String group_id, String school_id, String keyWord, String page, String num)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", group_id);
		if (school_id != null) {
			params.add("school_id", school_id);
		}
		if (keyWord != null) {
			params.add("keyWord", keyWord);
		}
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/colleges/classes";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回这个学校的机构群列表信息
	 * @param school_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetCollegeList(String school_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("school_id", school_id);
		String url = "http://mobile.yiban.cn/api/v1/school/colleges";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetFindPasswordMailCode(String email, String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("email", email);
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v3/passport/email-code";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetGroupFromTalkId(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString + "/group/";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，貌似是一个被抛弃的api
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public JSONObject ApiApp_GetGroupsAccept(String page, String size) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("size", size);
		String url = "http://mobile.yiban.cn/api/v1/groups/accept";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetLogin(String mobile, String password, String imei, String app, String apn, String token,
			String device, String sversion, String latitude, String longitude) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", mobile);
		params.add("password", password);
		params.add("imei", imei);
		params.add("app", app);
		params.add("apn", apn);
		params.add("token", token);
		params.add("device", device);
		params.add("sversion", sversion);
		params.add("latitude", latitude);
		params.add("longitude", longitude);
		String url = "http://mobile.yiban.cn/api/v3/passport/login";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetPacketGroupRich(int groupId, int groupType) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("groupId", groupId);
		params.add("groupType", groupType);
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/group-list-rich";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetPacketGroupStingy(int groupId, int groupType) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("groupId", groupId);
		params.add("groupType", groupType);
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/group-list-poor";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 红包排行榜，手气榜单
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetPacketRangeUserLuck() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/global-list-lucky";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 红包排行榜，土豪榜单
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetPacketRangeUserTop() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/global-list-rich";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetPacketReceivedCondition(String year, int page, int limit) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("year", year);
		params.add("page", page);
		params.add("limit", limit);
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/user-received";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetPacketSendCondition(String year, int page, int limit) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("year", year);
		params.add("page", page);
		params.add("limit", limit);
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/user-packed";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetPhoneNumberAvailable(String mobile) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", mobile);
		String url = "http://mobile.yiban.cn/api/v3/passport/mobile-bind-info";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回地区信息，包括国内省份、特别行政区、海内外
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetProvinceList() throws Exception {
//		Params params = new Params();
//		params.add("appVersion", "4.4.3");
//		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/area";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 当kind=1时返回好友列表里面的公众号，当kind=2时返回用户的机构号列表
	 * @param kind =1 or =2
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetPublicUser(String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v2/user/follows";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetRecommend_GroupList(int isOrganGroups, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("isOrganGroups", isOrganGroups);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/recommend/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetRecommend_GroupListSearch(int isOrganGroups, int page, int num, String keywords)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("isOrganGroups", isOrganGroups);
		params.add("page", page);
		params.add("num", num);
		params.add("keywords", keywords);
		params.add("isCountFriend", "1");
		String url = "http://mobile.yiban.cn/api/v2/recommend/groups/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetRedPacketConfig(int toId, int type) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("toId", toId);
		params.add("type", type);
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/config";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetRedPacketDetail(int id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/{id}/detail".replace("{id}", id + "");
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetSchoolList(String provinceId, String cityID, String schoolId, String name)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("provinceId", provinceId);
		params.add("cityID", cityID);
		params.add("schoolId", schoolId);
		params.add("name", name);
		String url = "http://mobile.yiban.cn/api/v1/school/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 查找用户，通过输入关键字，如姓名等查找用户
	 * @param account 建议姓名
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetSingleUserInfo(String account) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("account", account);
		String url = "http://mobile.yiban.cn/api/v1/user/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回当前用户的认证学号
	 * @param id 参数好像无用
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetStudentId(int id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("id", id);
		String url = "http://mobile.yiban.cn/api/v3/user/student";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetTransfer_Authority(int to) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("to", to);
		String url = "http://mobile.yiban.cn/api/v3/money/transfer/check";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GetTransfer_Config(int to) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("to", to);
		String url = "http://mobile.yiban.cn/api/v3/money/transfer/config";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 我的网薪，获取我的网薪变化记录信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetTransfer_MineAssets(String page) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		String url = "http://mobile.yiban.cn/api/v3/user/money";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回了当前用户的：昵称、姓名、网薪、头像地址
	 * @param user_id 传入任意用户id
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetUser_Profile_Info(String user_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/users/" + user_id;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回了当前用户的：昵称、姓名、id、头像地址、学校名称、以及一个二维码的特殊字符串
	 * @param id 任意用户id
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GetUser_QrCode_Info(String id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/users/{id}/card".replace("{id}", id);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 获取文件基本信息
	 * @param user_id 只能传入当前用户id，传入其他用户id会提示获取文件基本信息失败
	 * @param page
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_Get_Albums(String user_id, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/gallerys";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，提示参数错误，无法post
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_Get_Checkin_Bonus() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/activity/sign/callback";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Get_Checkin_Feeds(int last_id, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("last_id", last_id);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/feeds/sign";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Get_Checkin_Question() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/activity/sign/question";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Get_Checkin_Question2() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/checkin/question/";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Get_Checkin_Question_New() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/checkin/question";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Get_Guide_Advertising() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/home/launchad";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Get_Images_Of_Album(String path, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("path", path);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/gallery/images";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Get_Report_Category(int kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v2/report/category";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 获取易班消息，就是那个有签到得网薪提醒的消息
	 * @param userid 必须当前用户的id
	 * @param page
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_Get_YiBanMessage(String userid, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/users/{userid}/messagebox/list".replace("{userid}", userid);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupAvatar(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString + "/avatar";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupLightAppList(int user_id, int group_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("group_id", group_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupMemberSearch(String paramString1, String keyword, String page, String num)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("keyword", keyword);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString1 + "/members/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupingMemberList(int group_id, int last_id, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", group_id);
		params.add("last_id", last_id);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/groups/friends";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用
	 * @param record_id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GroupsAccept(String record_id, String status) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("record_id", record_id);
		params.add("status", status);
		String url = "http://mobile.yiban.cn/api/v1/groups/accept";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsApply(String group_id, String g_userid, String reason) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", group_id);
		params.add("g_userid", g_userid);
		params.add("reason", reason);
		String url = "http://mobile.yiban.cn/api/v1/groups/apply";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsApplyGroupRecordings(String id, String page, String size) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("size", size);
		params.add("id", id);
		String url = "http://mobile.yiban.cn/api/v1/groups/accept";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 未知作用，post和get均测试过
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GroupsApplyMyRecordings(String page, String size) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("size", size);
		String url = "http://mobile.yiban.cn/api/v1/groups/apply";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsCreate(String name, String brief, String kind, String auth, String sort)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("name", name);
		params.add("brief", brief);
		params.add("kind", kind);
		params.add("auth", auth);
		params.add("sort", sort);
		String url = "http://mobile.yiban.cn/api/v1/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsDelete(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsDetail(String paramString1, String user_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsDetail2(String paramString1, String user_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		String url = "http://mobile.yiban.cn/api/v2/groups/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsDynamic(String groupid, String puserid) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("groupid", groupid);
		params.add("puserid", puserid);
		String url = "http://mobile.yiban.cn/api/v2/groups/lastvotecom";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsFriendList(String userId, String page, String num, String keywords)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", num);
		if ((keywords != null) && (!"".equals(keywords))) {
			params.add("keywords", keywords);
		}
		String url = "http://mobile.yiban.cn/api/v2/groups/mycreategroupfriends";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回公共群列表信息
	 * @param kind 1=公共群
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GroupsList(String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回公共群列表信息
	 * @param kind 1=公共群
	 * @param page
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_GroupsList(String kind, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsMemberAdd(String paramString1, String user_id, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString1 + "/members/" + paramString3;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsMemberDelete(String paramString1, String user_id, String member_id)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("member_id", member_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString1 + "/members/kick";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsMemberDeletePost(String paramString1, String user_id, String member_id)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("member_id", member_id);
		params.add("user_id", user_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString1 + "/members/kick";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsMemberList(String paramString1, String user_id, String last_id, String num)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("last_id", last_id);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString1 + "/members";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_GroupsModify(String paramString1, String user_id, String name, String brief, String kind,
			String auth, String sort) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("name", name);
		params.add("brief", brief);
		params.add("kind", kind);
		params.add("auth", auth);
		params.add("sort", sort);
		String url = "http://mobile.yiban.cn/api/v1/groups/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 客户端首页推荐新闻
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_Home_App(String modules) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (!NullValid.isNull(modules)) {
			params.add("modules", modules);
		}
		String url = "http://mobile.yiban.cn/api/v3/home";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 获取客户端首页的APP列表，其中包括默认的APP和学校自定义的APP列表信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_Hot_App() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("provinceOrg", "1");
		String url = "http://mobile.yiban.cn/api/v1/home/hotapp";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_IsDeveloper() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("isAndroid", "1");
		String url = "http://mobile.yiban.cn/api/v2/user/developer";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_JoinGroups(String kind, String page, String num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/user/joinGroups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 应用广场中，获得本校的应用
	 * @param page
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_LightAppApplicationUser(int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/application/user";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppBanner() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/application/banners";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回应用广场的分类列表信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_LightAppCategory() throws Exception {
		String url = "http://mobile.yiban.cn/api/v2/application/labels";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppDeletApp(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/application/user/" + paramInt;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppDetail(int appID) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("appID", appID);
		String url = "http://mobile.yiban.cn/api/v2/application/detail";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppGroupAdd(String app_ids, String group_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("app_ids", app_ids);
		params.add("group_id", group_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppGroupList(String app_id, String page, String num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("app_id", app_id);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/user/application/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppList(int labelID, int page, int num, String deviation) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (labelID != -1) {
			params.add("labelID", labelID);
		}
		params.add("page", page);
		params.add("num", num);
		params.add("deviation", deviation);
		String url = "http://mobile.yiban.cn/api/v2/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppList(String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppList(String kind, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppListWithGroup(String user_id, String group_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("group_id", group_id);
		String url = "http://mobile.yiban.cn/api/v1/groups/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppRecommend(int item, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("item", item);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/application/recommends";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppRecommend(int item, int deviation, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("item", item);
		params.add("deviation", deviation);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/application/recommends";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppRecommendList(String group_id, String kind, String sort, String page, String num,
			String last_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", group_id);
		params.add("kind", kind);
		params.add("sort", sort);
		params.add("page", page);
		params.add("num", num);
		params.add("last_id", last_id);
		String url = "http://mobile.yiban.cn/api/v1/application/recommend";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppSearch(int item, String keyword, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("item", item);
		params.add("keyword", keyword);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/application/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppSearch(String keyword, String group_id, String beenAdded, String kind, String page,
			String num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("keyword", keyword);
		params.add("group_id", group_id);
		params.add("beenAdded", beenAdded);
		params.add("kind", kind);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/application/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppSearchDevApp(String item, String keyword) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("item", item);
		params.add("keyword", keyword);
		String url = "http://mobile.yiban.cn/api/v2/application/dev";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppSearchDevApp(String item, String keyword, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("item", item);
		params.add("keyword", keyword);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/application/dev";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppTopic(int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/application/topics";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_LightAppTopicApps(int topicID, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("topicID", topicID);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/application/topic/apps";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Logs_Position(String position, String content, String url1, int paramInt)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (!NullValid.isNull(url1)) {
			params.add("url", url1);
		}
		params.add("content", content);
		params.add("position", position);
		String url = "http://mobile.yiban.cn/api/v3/logs/position";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Logs_Reg(String deviceId) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("deviceId", deviceId);
		String url = "http://mobile.yiban.cn/api/v2/logs/reg";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Logs_Share(int shareType, String shareUrl) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("shareType", shareType);
		params.add("shareUrl", shareUrl);
		String url = "http://mobile.yiban.cn/api/v2/logs/share";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Logs_Share_Third(int shareType, String shareUrl) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("shareType", shareType);
		params.add("shareUrl", shareUrl);
		String url = "http://mobile.yiban.cn/api/v2/logs/share/sharethird";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_MessageAudioUpload(int time) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("app", "mobile");
		params.add("time", time);
		String url = "http://mobile.yiban.cn/api/v1/message/audios";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_MessageImageUpload(String artwork) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("artwork", artwork);
		String url = "http://mobile.yiban.cn/api/v1/message/images";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_MessageMenuDetail(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/message/menu/" + paramInt;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_MessageMenuUpdate(long createTime) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("createTime", createTime);
		String url = "http://mobile.yiban.cn/api/v1/message/menu";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_MessageMoreImageUpload(String m_tag, String chatID, String chatName, String chatKind,
			String isGroup, String artwork) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("m_tag", m_tag);
		params.add("chatID", chatID);
		params.add("chatName", chatName);
		params.add("chatKind", chatKind);
		params.add("isGroup", isGroup);
		params.add("artwork", artwork);
		String url = "http://mobile.yiban.cn/api/v2/message/images";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ModifyDevice(String identify) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("identify", identify);
		String url = "http://mobile.yiban.cn/api/v2/passport/modefiydevice";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Modify_Album(String path, String name, int paramInt, String password) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("path", path);
		params.add("name", name);
		params.add("private", paramInt);
		params.add("password", password);
		String url = "http://mobile.yiban.cn/api/v2/gallery/modify";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_MyDiscoverList(String userId, String direction, String compareId, int limit)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("userId", userId);
		params.add("direction", direction);
		params.add("compareId", compareId);
		params.add("limit", limit);
		String url = "http://mobile.yiban.cn/api/v3/feeds/user";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_MyRelatedGroupList(int my_group_type, int num, int is_org_group) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("my_group_type", my_group_type);
		params.add("num", num);
		params.add("is_org_group", is_org_group);
		String url = "http://mobile.yiban.cn/api/v2/groups/mygroups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_NewDiscover() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/feeds/untouched";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_NoticeFeedback(String paramString1, String hasrecieved, String unrecieved)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("hasrecieved", hasrecieved);
		params.add("unrecieved", unrecieved);
		String url = "http://mobile.yiban.cn/api/v1/groups/notice/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_NoticeFeedbackUsers(String paramString1, String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/groups/notice/" + paramString1 + "/users";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_NoticeMyNoticeList(int last_id, int size) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("last_id", last_id);
		params.add("size", size);
		String url = "http://mobile.yiban.cn/api/v1/groups/notice";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_NoticeSend(String content, String group_ids, String kind, String location, String address,
			String artwork) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", content);
		params.add("group_ids", group_ids);
		params.add("kind", kind);
		params.add("location", location);
		params.add("address", address);
		params.add("artwork", artwork);
		String url = "http://mobile.yiban.cn/api/v1/groups/notice";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_NoticesSendStatus(int notice_id, int kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("notice_id", notice_id);
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/notices/users";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_OrganizatDynamic(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/organization/recommendlist/" + paramInt;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_OrganizatDynamic(int user_id, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/organization/recommendlist";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_OrganizationAppList(int user_id, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/organization/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_OrganizationGroupList(int user_id, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/organization/list";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_OrganizationInfo(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/organizations/" + paramInt;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_OrganizationTeacherRecommendedList(int user_id, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/organization/recommendteachers";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_OrgsRecommend(int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v2/recommend/orgs";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Out_Of_All(String page, String pagesize) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("pagesize", pagesize);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/recommend/totalstation";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Out_Of_Edit_Recommend() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/ydfs/recommend/list";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Out_Of_My_Group(String page, String pagesize) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("pagesize", pagesize);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/recommend/group";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Out_Of_Transfile(String uid, String path, String desPath) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("uid", uid);
		params.add("path", path);
		params.add("desPath", desPath);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/transfile";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportAuthcode(String phone, String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("phone", phone);
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/passport/authcode";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportAutologin(String ct, String app, String v, String apn, String identify, String sig,
			String token, String device, String sversion, String latitude, String longitude, String address,
			String authCode) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("ct", ct);
		params.add("app", app);
		params.add("v", v);
		params.add("apn", apn);
		params.add("identify", identify);
		params.add("sig", sig);
		params.add("token", token);
		params.add("device", device);
		params.add("sversion", sversion);
		params.add("latitude", latitude);
		params.add("longitude", longitude);
		params.add("address", address);
		params.add("authCode", authCode);
		String url = "http://mobile.yiban.cn/api/v1/passport/autologin";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportBindPhone(String phone, String code) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("phone", phone);
		params.add("code", code);
		String url = "http://mobile.yiban.cn/api/v2/passport/bindphone";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportBinding(String account, String phone, String code) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("account", account);
		params.add("phone", phone);
		params.add("code", code);
		String url = "http://mobile.yiban.cn/api/v1/passport/binding";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportCertification(String studentKey, String studentName, String school_id)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("studentKey", studentKey);
		params.add("studentName", studentName);
		params.add("school_id", school_id);
		String url = "http://mobile.yiban.cn/api/v1/passport/certification";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportCheckPhone(String phone) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("phone", phone);
		String url = "http://mobile.yiban.cn/api/v1/passport/checkphone";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportCheckcode(String account, String code, String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("account", account);
		params.add("code", code);
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/passport/checkcode";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportEditPwd(String phone, String email, String newPassword) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("phone", phone);
		params.add("email", email);
		params.add("newPassword", newPassword);
		String url = "http://mobile.yiban.cn/api/v1/passport/editPwd";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportEditPwd(String phone, String newPassword, String code, boolean paramBoolean)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (paramBoolean) {
			params.add("phone", phone);
			params.add("newPassword", URLEncoder.encode(newPassword, "utf-8"));
			params.add("code", code);
			params.add("email", phone);
			String url = "http://mobile.yiban.cn/api/v1/passport/editpwd";
			HttpConfig config = new HttpConfig(url, params.getParams());
			byte[] re = Http.get(config);
			String result = new String(re, "utf-8");
			JSONObject json = JSONObject.parseObject(result);
			System.out.println("结果：" + result);
			System.out.println("格式化：" + json);
			return json;
		}
		return null;
	}

	public JSONObject ApiApp_PassportEmail(String email, String kind) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("email", email);
		params.add("kind", kind);
		String url = "http://mobile.yiban.cn/api/v1/passport/email";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public static JSONObject ApiApp_PassportLogin(User user) throws Exception {
		String string = MD5Impl.getInstance().EncryptionToString(UUID.randomUUID().toString());
		Params params = new Params();
		params.add("account", user.getPhone());// str1,
		params.add("passwd", URLEncoder.encode(user.getPassword(),"utf-8"));// str2,
		params.add("ct", 2);// String.valueOf(2),
		params.add("app", 1);// String.valueOf(1),
		params.add("v", 58);// String.valueOf(localGlobalSetting.getAppVersionCode()),
		params.add("apn", "");// NetworkUtil.getAPNType(this.this$0.getApplicationContext()),
		params.add("identify", "867581025178170");// localGlobalSetting.getIMEI(),
		params.add("sig", string.substring(0, 16));// YibanApplication.getSig(),
		params.add("token", "");// "",
		params.add("device", 21);// localGlobalSetting.getDevice(),
		params.add("sversion", 21);// String.valueOf(localGlobalSetting.getSdkVersion())
		String url = "http://mobile.yiban.cn/api/v2/passport/login";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportLogin2(String account, String passwd, String ct, String app, String v, String apn,
			String identify, String sig, String token, String device, String sversion, String authCode)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("account", account);
		params.add("passwd", URLEncoder.encode(passwd, "utf-8"));
		params.add("ct", ct);
		params.add("app", app);
		params.add("v", v);
		params.add("apn", apn);
		params.add("identify", identify);
		params.add("sig", sig);
		params.add("token", token);
		params.add("device", device);
		params.add("sversion", sversion);
		if (!NullValid.isNull(authCode)) {
			params.add("authCode", authCode);
		}
		String url = "http://mobile.yiban.cn/api/v2/passport/login";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportPerfectionInfo(String nick, String school_id, String join_year, String college_id)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("nick", nick);
		params.add("school_id", school_id);
		params.add("join_year", join_year);
		params.add("college_id", college_id);
		String url = "http://mobile.yiban.cn/api/v2/passport/registerinfo";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportReg(String code, String phone, String passwd, String nick, String school_id,
			String join_year, String college_id, String authCode) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("code", code);
		params.add("phone", phone);
		params.add("passwd", passwd);
		params.add("nick", nick);
		params.add("school_id", school_id);
		params.add("join_year", join_year);
		params.add("college_id", college_id);
		params.add("authCode", authCode);
		String url = "http://mobile.yiban.cn/api/v1/passport/reg";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportReg_new(String paramString1, String paramString2, String paramString3,
			String paramString4) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("code", paramString1);
		params.add("phone", paramString2);
		params.add("passwd", paramString3);
		params.add("authCode", paramString4);
		String url = "http://mobile.yiban.cn/api/v2/passport/register";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PassportVoicecode(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("phone", paramString);
		String url = "http://mobile.yiban.cn/api/v1/passport/voipcode";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Passport_Logout() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/passport/logout";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostChangePassword(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("old", paramString1);
		params.add("new", paramString2);
		params.add("mobile", paramString3);
		String url = "http://mobile.yiban.cn/api/v3/passport/change-password";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostChangePhone(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", paramString1);
		params.add("password", paramString2);
		params.add("code", paramString3);
		String url = "http://mobile.yiban.cn/api/v3/passport/change-mobile";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostChangePhoneNumberMessageCode(String paramString1, String paramString2,
			String paramString3) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", paramString1);
		params.add("kind", paramString2);
		params.add("captcha", paramString3);
		String url = "http://mobile.yiban.cn/api/v3/passport/sms-code";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostChangePhoneNumberVoiceCode(String paramString1, String paramString2,
			String paramString3) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", paramString1);
		params.add("kind", paramString2);
		params.add("captcha", paramString3);
		String url = "http://mobile.yiban.cn/api/v3/passport/voip-code";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostFindPasswordPhoneCheckCode(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("account", paramString1);
		params.add("code", paramString2);
		String url = "http://mobile.yiban.cn/api/v3/passport/check-code";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostResetPassword(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("password", paramString1);
		params.add("mobile", paramString2);
		params.add("email", paramString3);
		String url = "http://mobile.yiban.cn/api/v3/passport/reset-password";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostTransfer_Commit(int paramInt1, int paramInt2, String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("to", paramInt1);
		params.add("amount", paramInt2);
		params.add("remark", paramString);
		String url = "http://mobile.yiban.cn/api/v3/money/transfer/process";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PostUserRegister(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", paramString1);
		params.add("password", paramString2);
		params.add("code", paramString3);
		String url = "http://mobile.yiban.cn/api/v3/passport/register";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Post_Checkin_Answer(String paramString, int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("optionId", paramString);
		if (paramInt == 1) {
			params.add("feeds", "" + paramInt);
		}
		String url = "http://mobile.yiban.cn/api/v3/checkin/answer";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ProvinceOfficeOrganizationManagedSchoolList(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramInt1);
		params.add("getVerifySum", paramInt2);
		params.add("page", paramInt3);
		params.add("num", paramInt4);
		String url = "http://mobile.yiban.cn/api/v2/province/orgs/schools";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ProvinceOfficeOrganizationSchoolActiveRankList(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramInt);
		String url = "http://mobile.yiban.cn/api/v2/province/orgs/provinceorgtop";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ProvinceOfficeOrganizationSchoolActiveRankList2(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramInt);
		String url = "http://mobile.yiban.cn/api/v2/province/orgs/provinceorgtop2";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ProvinceOrganizationApps(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramInt);
		String url = "http://mobile.yiban.cn/api/v2/province/orgs/provinceorgapps";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ProvinceOrganizationTopNews(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramInt);
		String url = "http://mobile.yiban.cn/api/v2/province/orgs/provinceorgtopnews";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PublicMembers(String paramString1, String paramString2, String paramString3,
			String paramString4, String isAdministrative) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", paramString1);
		params.add("school_id", paramString2);
		params.add("page", paramString3);
		params.add("num", paramString4);
		params.add("isAdministrative", isAdministrative);// =1 or
															// =2,true=1,false=2

		String url = "http://mobile.yiban.cn/api/v2/colleges/publicmembers";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PublishDynamic(String paramString1, int paramInt1, int paramInt2, String paramString2,
			String paramString3, String paramString4, String paramString5, int paramInt3, String paramString6,
			String paramString7, String paramString8, String paramString9, String paramString10, int paramInt4,
			int paramInt5) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", paramString1);
		params.add("visibleScope", paramInt1);
		params.add("artwork", paramInt2);
		params.add("toUserIds", paramString2);
		params.add("address", paramString3);
		params.add("lat", paramString4);
		params.add("lng", paramString5);
		params.add("share", paramInt3);
		params.add("shareTitle", paramString6);
		params.add("shareUrl", paramString7);
		params.add("shareContent", paramString8);
		params.add("shareImage", paramString9);
		params.add("shareSource", paramString10);
		params.add("kind", paramInt4);
		params.add("hiddenAddress", paramInt5);
		String url = "http://mobile.yiban.cn/api/v3/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Push(String paramString1, String paramString2, String paramString3) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("userid", paramString1);
		params.add("content", paramString2);
		params.add("alert", paramString3);
		String url = "http://mobile.yiban.cn/api/v1/push";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PushShieldForGroup(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", paramString1);
		params.add("type", paramString2);
		params.add("action", paramString3);
		String url = "http://mobile.yiban.cn/api/v1/push/shield";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_PushShieldForUser(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramString1);
		params.add("type", paramString2);
		params.add("action", paramString3);
		String url = "http://mobile.yiban.cn/api/v1/push/shield";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_QuitGroup(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", paramString);
		String url = "http://mobile.yiban.cn/api/v1/groups/retreat";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ReceiptsRedPacket(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (!NullValid.isNull(paramString2)) {
			params.add("answer", URLEncoder.encode(paramString2, "utf-8"));
		}
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/{id}/receipts".replace("{id}", paramString1);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_RemindMessage() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/feeds/message/boxcount";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Report_URL(int paramInt, String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("category_id", paramInt);
		params.add("url", paramString1);
		params.add("content", paramString2);
		String url = "http://mobile.yiban.cn/api/v2/report/url";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Report_USER(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("category_id", paramString1);
		params.add("to_uid", paramString2);
		params.add("content", paramString3);
		String url = "http://mobile.yiban.cn/api/v2/report/user";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SchoolList() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/schools";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SearchOrgsRecommend(String paramString, int paramInt1, int paramInt2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("keywords", paramString);
		if (paramInt1 > 0) {
			params.add("page", paramInt1);
		}
		if (paramInt2 > 0) {
			params.add("num", paramInt2);
		}
		String url = "http://mobile.yiban.cn/api/v2/recommend/orgs/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SectionGroups(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("num", paramString);
		String url = "http://mobile.yiban.cn/api/v2/user/sectionGroups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SendForMoreRedPacket(long paramLong, int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("amount", paramLong);
		params.add("dividend", paramInt1);
		params.add("groupType", paramInt2);
		params.add("groupId", paramInt3);
		params.add("type", paramInt4);
		params.add("message", URLEncoder.encode(paramString, "utf-8"));
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/forcrowd";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SendForOneRedPacket(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("amount", paramString1);
		params.add("toUserId", paramString2);
		params.add("message", URLEncoder.encode(paramString3, "utf-8"));
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/forone";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 在传入空值的情况下，用get方法获取到了当前用户的个人主页内容
	 * @param school_id
	 * @param group_id
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_SetStudentInfo(String school_id, String group_id, String classId)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (school_id != null) {
			params.add("school_id", school_id);
		}
		if (group_id != null) {
			params.add("group_id", group_id);
		}
		if (classId != null) {
			params.add("classId", classId);
		}
		String url = "http://mobile.yiban.cn/api/v2/user/studentinfo";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Share_To_Prepare(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("webURL", paramString);
		String url = "http://mobile.yiban.cn/api/v3/share/prepare";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ShowFriendToContactList(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("friend_type", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/user/friends/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SinaAuth() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("ct", 2);
		params.add("v", appVersion);
		String url = "http://mobile.yiban.cn/api/v1/thirdauth/weibo/sinaurl";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SinaBind(String paramString1, String paramString2, String paramString3) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("code", paramString1);
		params.add("phone", paramString2);
		params.add("passwd", paramString3);
		params.add("ct", 2);
		params.add("v", appVersion);
		String url = "http://mobile.yiban.cn/api/v1/thirdauth/weibo/bind";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_SpecailAppDiscover() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/site/menu";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回当前用户的基础信息，实际上学校信息更多
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_StudentInfo() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/passport/studentinfo";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksCreate(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("name", paramString1);
		params.add("members", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/talks";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksDelete(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksDetail(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksEdit(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("name", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksMemberList(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", paramString2);
		params.add("last_id", paramString3);
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString1 + "/members";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksMembersAdd(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("id", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString1 + "/members";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksMembersDelete(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString1 + "/members/" + paramString2;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TalksUpdateMembers(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("name", paramString2);
		params.add("members", paramString3);
		String url = "http://mobile.yiban.cn/api/v1/talks/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ThinAppMsgListGetting(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/users/" + paramInt + "/appmsgsetting";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ThinAppMsgSetting(int paramInt1, int paramInt2, int paramInt3) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("set", paramInt3);
		String url = "http://mobile.yiban.cn/api/v1/users/" + paramInt1 + "/appmsgsetting" + "/" + paramInt2;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TopicDiscoverList(String paramString1, String paramString2, String paramString3,
			int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("content", paramString1);
		params.add("direction", paramString2);
		params.add("compareId", paramString3);
		params.add("limit", paramInt);
		String url = "http://mobile.yiban.cn/api/v3/feeds/topic";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_TransferDetail(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/money/logs/{id}".replace("{id}", paramString);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UisSwitch() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/passport/uisswitch";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回的数据有：公众号、机构号、好友
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UnGroupFriendsList() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/ungroupfriends";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_Upload_Images(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("path", paramString);
		String url = "http://mobile.yiban.cn/api/v2/gallery/images";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserAction(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("deviceId", paramString);
		String url = "http://mobile.yiban.cn/api/v1/useraction";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserApplications(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", "1");
		params.add("user_id", paramString1);
		params.add("page", paramString2);
		params.add("num", paramString3);
		String url = "http://mobile.yiban.cn/api/v1/user/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserApplications(String paramString1, String paramString2, String paramString3,
			String paramString4) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", paramString2);
		params.add("user_id", paramString1);
		params.add("page", paramString3);
		params.add("num", paramString4);
		String url = "http://mobile.yiban.cn/api/v1/user/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserAvatar() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/avatar";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserBackground() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/background";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserContacts() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/contacts";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserFollows(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", paramString);
		String url = "http://mobile.yiban.cn/api/v1/user/follows";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserFriendPermit() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/friends/permit";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserFriendPermit(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("addFriend", paramInt);
		String url = "http://mobile.yiban.cn/api/v1/user/friends/permit";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 2017-17-18 00:00:00 用get方法返回了一串看不懂的json字符串信息，仿佛是聊天列表的用户信息<br>
	 * 2017-17-18 00:36:00 用get方法这个应该是返回了申请列表的好友申请列表，其中包括已通过的、未通过的都在这里<br>
	 * 用post方法则是通过用户id添加一个好友，如果用户id不存在则返回不存在该用户
	 * @param paramString1
	 * @param paramString2
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsAdd(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("friend_id", paramString1);
		params.add("reason", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/user/friends/apply";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserFriendsAddAccept(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mid", paramString1);
		params.add("status", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/user/friends/accept";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 2017-17-18 00:36:00 用get方法这个应该是返回了申请列表的好友申请列表，其中包括已通过的、未通过的都在这里<br>
	 * 应该是通过分页技术来获取到更多的申请列表信息，只能用get方法
	 * @param page
	 * @param size
	 * @param last_id
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsAddList(int page, int size, int last_id, String order)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("size", size);
		params.add("last_id", last_id);
		params.add("order", order);
		String url = "http://mobile.yiban.cn/api/v1/user/friends/apply";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 传入空值，用get方法，返回了好友列表。
	 * 传入用户id则返回不存在此方法。
	 * @param paramString
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsDelete(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/friends/" + paramString;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 无论如何传值和get或者post都报错误链接
	 * @param paramString1
	 * @param paramString2
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsEdit(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("remark", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/user/friends/" + paramString1;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回群组列表
	 * @param kind 公共群=1，机构群=2
	 * @param page 页数
	 * @param num 每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsGroupingList(int kind, int page, int num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/user/joinGroups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回用户的好友列表（联系人列表）
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsList() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/friends";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回推荐好友列表
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsRecommend() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/friends/recommend";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回推荐用户列表
	 * @param user_type 猜猜：普通用户=0，公共号=1
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserFriendsRecommend_newVersion(int user_type) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_type", user_type);
		String url = "http://mobile.yiban.cn/api/v1/friends/recommend";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 返回用户个人主页信息
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserInfo(int user_id) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/" + user_id;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	/**
	 * 无法post，只能get，get返回个人主页信息
	 * @param user_id
	 * @param nick
	 * @param signature 默认为空
	 * @param birth
	 * @param sex 男=M 女=F
	 * @param brief
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserInfo(int user_id, String nick, String signature, String birth,
			String sex, String brief) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());

		if (!NullValid.isNull(signature)) {
			params.add("signature", signature);
		}
		if (!NullValid.isNull(birth)) {
			params.add("birth", birth);
		}
		if (!NullValid.isNull(sex)) {
			params.add("sex", sex);
		}
		if (!NullValid.isNull(nick)) {
			params.add("nick", URLEncoder.encode(nick, "UTF-8"));
		}
		if (!NullValid.isNull(brief)) {
			params.add("brief", brief);
		}
		String url = "http://mobile.yiban.cn/api/v1/user/" + user_id;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;

	}

	/**
	 * 无法post，只能get，get返回个人主页信息
	 * @param user_id
	 * @param introduction
	 * @param birth
	 * @param sex
	 * @param signature
	 * @param nick
	 * @param biref
	 * @return
	 * @throws Exception
	 */
	public JSONObject ApiApp_UserInfo(int user_id, String introduction, String birth, String sex,
			String signature, String nick, String biref) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("introduction", introduction);
		params.add("birth", birth);
		params.add("sex", sex);
		params.add("signature", signature);
		params.add("nick", nick);
		params.add("biref", biref);
		String url = "http://mobile.yiban.cn/api/v1/user/" + user_id;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserInfoV2(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/user/" + paramInt;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserLightAppList(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("kind", paramString);
		String url = "http://mobile.yiban.cn/api/v1/user/applications";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserMenu(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramInt);
		String url = "http://mobile.yiban.cn/api/v1/user/menu";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserRelation(int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("user_id", paramInt);
		String url = "http://mobile.yiban.cn/api/v1/user/relation";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserSetting(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("behaviour", paramString1);
		params.add("action", paramString2);
		String url = "http://mobile.yiban.cn/api/v1/user/setting";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UserTalksList(String paramString1, String paramString2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (!NullValid.isNull(paramString2)) {
			params.add("page", paramString2);
		}
		String url = "http://mobile.yiban.cn/api/v1/users/" + paramString1 + "/talks";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UsersDetail(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/users/" + paramString;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_UsersDetail2(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/" + paramString;
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_V3PassportAutologin()
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/passport/autologin";
		HttpConfig config = new HttpConfig(url,params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_V3PersonalConfig(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/users/{id}/config".replace("{id}", paramString);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_ValidateNick(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("nick", paramString);
		String url = "http://mobile.yiban.cn/api/v2/passport/checknick";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_WebJS() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/site/webjs";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_WebNewsSearch(String paramString, int paramInt1, int paramInt2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("keywords", paramString);
		params.add("page", paramInt1);
		params.add("num", paramInt2);
		String url = "http://mobile.yiban.cn/api/v2/web/news/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_YDFS_DIR(String paramString, int paramInt1, int paramInt2) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (paramString != null) {
			params.add("path", paramString);
		}
		params.add("page", paramInt1);
		params.add("num", paramInt2);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/directorys";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_YDFS_DIR_SEARCH(String paramString1, String paramString2, int paramInt1, int paramInt2)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("path", paramString1);
		params.add("name", paramString2);
		params.add("page", paramInt1);
		params.add("num", paramInt2);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/directory/search";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_YDFS_GroupResourceList(String paramString1, String paramString2, String paramString3,
			String paramString4, String paramString5, String paramString6) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("group_id", paramString1);
		params.add("kind", paramString2);
		if (paramString3 != null) {
			params.add("name", paramString3);
		}
		params.add("by", paramString4);
		params.add("page", paramString5);
		params.add("num", paramString6);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/groups";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_YDFS_REMOVE_FILE(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("path", paramString);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/removefile";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_YDFS_SHARE(String paramString, int paramInt1, int paramInt2, int paramInt3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		if (paramString != null) {
			params.add("name", paramString);
		}
		params.add("page", paramInt1);
		params.add("num", paramInt2);
		params.add("by", paramInt3);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/shares";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_getAllContact(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("contacts", paramString);
		String url = "http://mobile.yiban.cn/api/v2/recommend/contacts/classification";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_v1_Captcha(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", paramString);
		String url = "http://mobile.yiban.cn/api/v1/passport/captcha";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject ApiApp_v1_CheckCaptcha(String paramString1, String paramString2, String paramString3)
			throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("mobile", paramString1);
		params.add("captcha", paramString2);
		params.add("kind", paramString3);
		String url = "http://mobile.yiban.cn/api/v1/passport/checkcaptcha";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	// public JSONObject ApiApp_v3(String paramString) {
	// Uri localUri = Uri.parse(paramString);
	// return createFullUrlByPath(localUri.getPath(),
	// UrlUtils.getUrlParams(localUri.getQuery()));
	// }

	public JSONObject ApiApp_v3_SchoolVerify(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/users/{id}/school".replace("{id}", paramString);
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject apiApp_YDFS_DumpFile(String paramString) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("file_id", paramString);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/dumpfile";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	public JSONObject apiApp_YDFS_DumpFile(String paramString, int paramInt) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("path", paramString);
		params.add("uid", paramInt);
		String url = "http://mobile.yiban.cn/api/v2/ydfs/transfile";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);
		System.out.println("结果：" + result);
		System.out.println("格式化：" + json);
		return json;
	}

	// private JSONObject createFullUrl(String paramString1, String
	// paramString2, String paramString3, List<NameValuePair> paramList)
	// {
	// String str1;
	// if ((!NullValid.isNull(paramString3) && (paramList != null)) {
	//
	// Params params = new Params();params.add("appVersion",
	// "4.4.3");params.add("access_token", user.getAccess_token());
	// String str2 =
	// user.getAccess_token();//JsonResponse.loadSession(YibanApplication.getContext());
	// if (!NullValid.isNull(str2)) {
	// params.add("access_token", str2);
	// }
	// localArrayList.addAll(paramList);
	// if (paramString1.equals(com.yiban.app.utils.ServerUtil.SERVER_DOMAIN[(-1
	// + com.yiban.app.utils.ServerUtil.SERVER_DOMAIN.length)])){}
	// URI localURI;
	// for (Object localObject = URIUtils.createURI("https", paramString1, -1,
	// paramString2 + paramString3, URLEncodedUtils.format(localArrayList,
	// "UTF-8"), null);; localObject = localURI)
	// {
	// LogManager.getInstance().d(TAG, ((URI)localObject).toString();
	// str1 = ((URI)localObject).toString();
	// break;
	// localURI = URIUtils.createURI("http", paramString1, -1, paramString2 +
	// paramString3, URLEncodedUtils.format(localArrayList, "UTF-8"), null);
	// }
	// str1 = null;
	//
	// }
	// return str1;
	// }

	// public JSONObject createFullUrl(String paramString,
	// List<NameValuePair> paramList) throws Exception {
	// return createFullUrl(GlobalSetting.getInstance().getServerDomain(),
	// "/api/", paramString, paramList);
	// }

	// public JSONObject createFullUrlByPath(String paramString,
	// List<NameValuePair> paramList) throws Exception {
	// return createFullUrl(GlobalSetting.getInstance().getServerDomain(), "",
	// paramString, paramList);
	// }

	// private JSONObject createFullUrlOverSSL(String paramString1,
	// String paramString2, String paramString3, List<NameValuePair> paramList)
	// {
	// if ((!NullValid.isNull(paramString3) && (paramList != null) throws
	// Exception{}
	// for (;;)
	// {
	//
	// Params params = new Params();params.add("appVersion",
	// "4.4.3");params.add("access_token", user.getAccess_token());
	// String str2 = JsonResponse.loadSession(YibanApplication.getContext();
	// if (!NullValid.isNull(str2) throws Exception{
	// params.add("access_token", str2);
	// }
	// localArrayList.addAll(paramList);
	// URI localURI = URIUtils.createURI("https", paramString1, -1, paramString2
	// + paramString3, URLEncodedUtils.format(localArrayList, "UTF-8"), null);
	// LogManager.getInstance().d(TAG, localURI.toString();
	// String str3 = localURI.toString();
	// str1 = str3;
	// return str1;
	//
	// String str1 = null;
	// }
	// }

	// public JSONObject createFullUrlOverSSL(String paramString,
	// List<NameValuePair> paramList) throws Exception {
	// return
	// createFullUrlOverSSL(GlobalSetting.getInstance().getServerDomain(),
	// "/api/", paramString, paramList);
	// }

}
