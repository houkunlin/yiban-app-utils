package cn.goour.yiban.app.action;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import com.alibaba.fastjson.JSONObject;
import com.lcb123.YBPlatform;

import cn.goour.utils.http.Http;
import cn.goour.utils.http.HttpConfig;
import cn.goour.utils.http.Params;
import cn.goour.utils.security.impl.MD5Impl;
import cn.goour.utils.tools.NullValid;
import cn.goour.yiban.app.entity.User;

/**
 * @author HouKunLin
 *
 */
/**
 * @author HouKunLin
 *
 */
public class YiBanRequest {
	private static Map<String, String> head;
	private User user;
	private String cookie = "";
	private WebSocketChat client;
	static {
		head = new HashMap<String, String>();
		head.put("appVersion", "4.4.3");
	}

	public YiBanRequest() {
	}

	public YiBanRequest(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public synchronized WebSocketChat getChatClient(IChat iChat) throws Exception {
		if (client == null || client.isClosed() || !client.isOpen()) {
			synchronized (YiBanRequest.class) {
				if (client == null || client.isClosed() || !client.isOpen()) {
					byte[] re = this.getChatStep1();// 获取一个令牌信息
					String result = new String(re, "utf-8");
					String[] sp = result.split(":");
					URI uri = new URI("ws://112.65.235.55:4252/socket.io/1/websocket/" + sp[0]);
					client = new WebSocketChat(uri, new Draft_6455(),iChat);
					client.setAction(this);
					client.setConnectionLostTimeout(30000);
					client.setTcpNoDelay(true);
					client.connect();
					while (!client.getReadyState().equals(READYSTATE.OPEN)) {
						// System.out.println("还没有打开1");
					}
				}
			}
		}
		return client;
	}
	public synchronized WebSocketChat getChatClient() throws Exception {
		IChat iChat = new IChat() {
			@Override
			public void send5(String text) {
			}
			@Override
			public void send(String text) {
			}
			@Override
			public void onOpen(ServerHandshake handshakedata) {
			}
			@Override
			public void onMessage(String message) {
			}
			@Override
			public void onClose(int code, String reason, boolean remote) {
			}
		};
		return getChatClient(iChat);
	}
	public static String signature(String userid, String access_token, String timeStamp) {
		return YBPlatform.signature(userid, access_token, timeStamp);
	}

	/**
	 * 通过账号和密码登录
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getLogin(User user) throws Exception {
		// GET
		// /api/v2/passport/login?access_token=2be84b22312d001a4dcd6aa4c3079209&account=13207725244&passwd=houkunlin0123456&ct=2&app=1&v=4.4.3&apn=wifi&identify=99000563812253&sig=d05d577f01daefc3&token=&device=QiKU%3A8676-A01&sversion=22
		String string = MD5Impl.getInstance().EncryptionToString(UUID.randomUUID().toString());
		Params params = new Params();
		params.add("account", user.getPhone());// str1,
		params.add("passwd", URLEncoder.encode(user.getPassword(), "utf-8"));// str2,
		params.add("ct", "2");// String.valueOf(2),
		params.add("app", "1");// String.valueOf(1),
		params.add("v", "58");// String.valueOf(localGlobalSetting.getAppVersionCode()),
		params.add("apn", "wifi");// NetworkUtil.getAPNType(this.this$0.getApplicationContext()),
		params.add("identify", "867581025178170");// localGlobalSetting.getIMEI(),
		params.add("sig", string.substring(0, 16));// YibanApplication.getSig(),
		params.add("token", "");// "",
		params.add("device", URLEncoder.encode("QiKU A8676-A01", "utf-8"));// localGlobalSetting.getDevice(),
		params.add("sversion", "22");// String.valueOf(localGlobalSetting.getSdkVersion())

		String url = "http://mobile.yiban.cn/api/v2/passport/login";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 通过上次登录的access_token自动登录
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getAutoLogin(User user) throws Exception {
		// GET
		// /api/v3/passport/autologin?access_token=2be84b22312d001a4dcd6aa4c3079209
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/passport/autologin";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取APP首页内容，包括banner、APP列表、学校推荐新闻
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAppHome() throws Exception {
		// GET /api/v3/home?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/home";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取首页新闻
	 * 
	 * @param kind
	 *            新闻分类，1=易班推荐，2=学院新闻，3=班级新闻
	 * @param page
	 *            分页
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAppHomeNews(int kind, Object page) throws Exception {
		// GET
		// /api/v3/home/news/yiban?access_token=0a72a27568fdcaed819ada82526b8fda&page=2
		// HTTP/1.1
		// GET
		// /api/v3/home/news/college?access_token=0a72a27568fdcaed819ada82526b8fda
		// HTTP/1.1
		// GET
		// /api/v3/home/news/class?access_token=0a72a27568fdcaed819ada82526b8fda
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		String type = null;
		switch (kind) {
		case 1:
			type = "yiban";
			break;
		case 2:
			type = "college";
			break;
		case 3:
			type = "class";
			break;

		default:
			type = "yiban";
			break;
		}
		String url = "http://mobile.yiban.cn/api/v3/home/news/" + type;
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取当前用户的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUsersConfig() throws Exception {
		// GET
		// /api/v3/users/6746755/config?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/users/" + user.getUser_id() + "/config";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取当前用户的动态列表信息
	 * 
	 * @param lastId
	 *            上次浏览的最后一条动态id
	 * @param page
	 *            动态页数
	 * @param size
	 *            每页的动态条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFeedsSelf(Object lastId, Object page, Object size) throws Exception {
		// GET
		// /api/v3/feeds?access_token=b644f81819de3897d538be4a478ca3a5&direction=1&compareId=0&limit=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("direction", page);
		params.add("compareId", lastId);
		params.add("limit", size);

		String url = "http://mobile.yiban.cn/api/v3/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 给动态点赞或取消点赞
	 * 
	 * @param feedId
	 *            动态id
	 * @return 点赞是返回一个true，取消点赞时返回一个false
	 * @throws Exception
	 */
	public JSONObject getFeedsUps(Object feedId) throws Exception {
		// POST
		// /api/v3/feeds/35270256/ups?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		// params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/feeds/" + feedId + "/ups?access_token=" + user.getAccess_token();
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 同情和取消同情动态
	 * 
	 * @param feedId
	 *            动态id
	 * @return 同情是返回一个true，取消同情时返回一个false
	 * @throws Exception
	 */
	public JSONObject getFeedsDowns(Object feedId) throws Exception {
		// POST
		// /api/v3/feeds/35266045/downs?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		// params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/feeds/" + feedId + "/downs?access_token=" + user.getAccess_token();
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 评论动态
	 * 
	 * @param feedId
	 *            该动态的id
	 * @param content
	 *            评论内容
	 * @return 返回当前评论的id
	 * @throws Exception
	 */
	public JSONObject getFeedsCommentsAdd(Object feedId, String content) throws Exception {
		// POST
		// /api/v3/feeds/35273617/comments?access_token=b644f81819de3897d538be4a478ca3a5&content=%E8%AF%84%E8%AE%BA&toUserId=&toCommentId=
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("content", URLEncoder.encode(content, "utf-8"));
		params.add("toUserId", "");
		params.add("toCommentId", "");

		String url = "http://mobile.yiban.cn/api/v3/feeds/" + feedId + "/comments";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 删除动态评论
	 * 
	 * @param feedId
	 *            评论所属的动态id
	 * @param commentId
	 *            该评论的id
	 * @return 返回一个状态true
	 * @throws Exception
	 */
	public JSONObject getFeedsCommentsDel(Object feedId, Object commentId) throws Exception {
		// DELETE
		// /api/v3/feeds/35273617/comments/3945410?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/feeds/" + feedId + "/comments/" + commentId;
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.delete(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 发布动态
	 * 
	 * @param content
	 *            动态内容
	 * @param address
	 *            地址名称
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @return 返回此动态的id
	 * @throws Exception
	 */
	public JSONObject getFeedsADD(String content, String address, String lat, String lng) throws Exception {
		// POST /api/v3/feeds?access_token=b644f81819de3897d538be4a478ca3a5&
		// content=%E5%8A%A8%E6%80%811&visibleScope=0&artwork=0&toUserIds=&address=&lat=&lng=&
		// share=0&shareTitle=&shareUrl=&shareContent=&shareImage=&shareSource=&kind=1&hiddenAddress=0
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("content", URLEncoder.encode(content, "utf-8"));
		params.add("visibleScope", "0");
		params.add("artwork", "0");
		params.add("toUserIds", "");
		params.add("address", URLEncoder.encode(address, "utf-8"));
		params.add("lat", lat);
		params.add("lng", lng);
		params.add("share", "0");
		params.add("shareTitle", "");
		params.add("shareUrl", "");
		params.add("shareContent", "");
		params.add("shareImage", "");
		params.add("shareSource", "");
		params.add("kind", "1");
		params.add("hiddenAddress", "0");

		String url = "http://mobile.yiban.cn/api/v3/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 发布带图片的动态
	 * 
	 * @param content
	 *            动态内容
	 * @param address
	 *            地址名称
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @param files
	 *            图片文件
	 * @return 返回此动态的id
	 * @throws Exception
	 */
	public JSONObject getFeedsADD_IMG(String content, String address, String lat, String lng, File[] files)
			throws Exception {
		// POST /api/v3/feeds?access_token=b644f81819de3897d538be4a478ca3a5&
		// content=%E5%8A%A8%E6%80%811&visibleScope=0&artwork=0&toUserIds=&address=&lat=&lng=&
		// share=0&shareTitle=&shareUrl=&shareContent=&shareImage=&shareSource=&kind=1&hiddenAddress=0
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("content", URLEncoder.encode(content, "utf-8"));
		params.add("visibleScope", "0");
		params.add("artwork", "0");
		params.add("toUserIds", "");
		params.add("address", URLEncoder.encode(address, "utf-8"));
		params.add("lat", lat);
		params.add("lng", lng);
		params.add("share", "0");
		params.add("shareTitle", "");
		params.add("shareUrl", "");
		params.add("shareContent", "");
		params.add("shareImage", "");
		params.add("shareSource", "");
		params.add("kind", "1");
		params.add("hiddenAddress", "0");
		Map<String, File[]> map = new HashMap<String, File[]>();
		map.put("image[]", files);

		String url = "http://mobile.yiban.cn/api/v3/feeds";

		HttpConfig config = new HttpConfig(url, head);
		byte[] re = Http.post(config, params.getParamsMap(), map);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 发布中职微博
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String getWeiBo(String content) throws Exception {
		String url = "http://vsdh.yiban.cn/weibo/index.php?view=ajax&action=publish";
		String urlFrom = "http://vsdh.yiban.cn/weibo";
		String sendData = null;
		sendData = String.format("pic=%s&content=%s", "0", URLEncoder.encode(content, "utf-8"));
		HttpConfig config = new HttpConfig(url, sendData);
		config.setReferer(urlFrom);
		config.setAjax(true);
		config.setJsonAccept();
		if (NullValid.isNull(cookie)) {
			cookie = getWebCookies();
		}
		String cookie1 = "" + cookie + "login_username=" + user.getNick() + "; LUN=" + user.getNick()
				+ "; POWER=100; isVip=1;timezone=-8; UID=" + user.getUser_id()
				+ "; AREA=43; OS=%B2%E9%BF%B4%B2%A9%CE%C4;";
		config.setHeader("cookie", cookie1);
		byte[] re = Http.post(config);

		String result = new String(re, "utf-8");

		return result;
	}

	/**
	 * 方法不可用
	 * 
	 * @param content
	 * @param title
	 * @param ranges
	 * @param type
	 * @param keywords
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public String getBlog(String content, String title, String ranges, String type, String keywords, String token)
			throws Exception {
		String url = "http://www.yiban.cn/blog/blog/addblog";
		String urlFrom = "http://www.yiban.cn/";
		String sendData = null;
		sendData = String.format("title=%s&content=%s&ranges=%s&type=%s&token=%s&keywords=%s",
				URLEncoder.encode(title, "utf-8"), URLEncoder.encode(content, "utf-8"),
				URLEncoder.encode(ranges, "utf-8"), URLEncoder.encode(type, "utf-8"), URLEncoder.encode(token, "utf-8"),
				URLEncoder.encode(keywords, "utf-8"));
		HttpConfig config = new HttpConfig(url, sendData);
		config.setReferer(urlFrom);
		config.setAjax(true);
		config.setJsonAccept();
		if (NullValid.isNull(cookie)) {
			cookie = getWebCookies();
		}
		config.setHeader("cookie", cookie);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");

		return result;
	}

	/**
	 * 分享一条连接到动态里面
	 * 
	 * @param content
	 *            自己填写的分享内容
	 * @param address
	 *            地址名称
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @param shareTitle
	 *            分享内容的标题
	 * @param shareUrl
	 *            分享内容的url地址
	 * @param shareContent
	 *            分享内容的内容说明
	 * @param shareImage
	 *            分享内容的配图，这是一个相对于对方服务器的路径，例如：/upload/system/201505/150527113726405662.jpg
	 * @param shareSource
	 *            分享来源
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFeedsShares(String content, String address, String lat, String lng, String shareTitle,
			String shareUrl, String shareContent, String shareImage, String shareSource) throws Exception {
		// POST
		// /api/v3/feeds?access_token=0a72a27568fdcaed819ada82526b8fda&content=123456789&
		// visibleScope=0&artwork=0&toUserIds=&address=&lat=&lng=&share=1&
		// shareTitle=%E6%98%8E%E6%98%9F%E7%94%A8%E6%88%B7&
		// shareUrl=http%3A%2F%2Fwww.yiban.cn%2FForum%2FStar%2Findex%3Fv_time%3D150038864394840&
		// shareContent=&shareImage=%2Fupload%2Fsystem%2F201505%2F150527113726405662.jpg&
		// shareSource=&kind=1&hiddenAddress=0 HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("content", URLEncoder.encode(content, "utf-8"));
		params.add("visibleScope", "0");
		params.add("artwork", "0");
		params.add("toUserIds", "");
		params.add("address", URLEncoder.encode(address, "utf-8"));
		params.add("lat", lat);
		params.add("lng", lng);
		params.add("share", "1");
		params.add("shareTitle", URLEncoder.encode(shareTitle, "utf-8"));
		params.add("shareUrl", URLEncoder.encode(shareUrl, "utf-8"));
		params.add("shareContent", URLEncoder.encode(shareContent, "utf-8"));
		params.add("shareImage", URLEncoder.encode(shareImage, "utf-8"));
		params.add("shareSource", URLEncoder.encode(shareSource, "utf-8"));
		params.add("kind", "1");
		params.add("hiddenAddress", "0");

		String url = "http://mobile.yiban.cn/api/v3/feeds";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 删除动态
	 * 
	 * @param feedId
	 *            待删除的动态id
	 * @return 返回一个true的状态
	 * @throws Exception
	 */
	public JSONObject getFeddsDELETE(Object feedId) throws Exception {
		// DELETE
		// /api/v3/feeds/35274370?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/feeds/" + feedId;
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.delete(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取我的动态中与我相关的消息
	 * 
	 * @param lastId
	 *            上一页的最后一条消息
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFeedsMessages(Object lastId, Object page, Object size) throws Exception {
		// GET
		// /api/v3/feeds/messages?access_token=0a72a27568fdcaed819ada82526b8fda&direction=1&compareId=0&limit=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("direction", page);
		params.add("compareId", lastId);
		params.add("limit", size);

		String url = "http://mobile.yiban.cn/api/v3/feeds/messages";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.delete(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取一个用户的首页个人信息
	 * 
	 * @param user_id
	 *            用户id
	 * @return 返回用户信息，用户最近用过的APP信息
	 * @throws Exception
	 */
	public JSONObject getUsersInfo(Object user_id) throws Exception {
		// GET
		// /api/v2/user/9912430?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v2/user/" + user_id;
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回一个具体用户的动态列表信息
	 * 
	 * @param user_id
	 *            用户的id
	 * @param lastId
	 *            该用户上次浏览的最后一条动态id
	 * @param page
	 *            页数
	 * @param size
	 *            每页动态条数
	 * @return 返回该用户的动态列表信息
	 * @throws Exception
	 */
	public JSONObject getFeedsOther(Object user_id, Object lastId, Object page, Object size) throws Exception {
		// GET
		// /api/v3/feeds/user?access_token=b644f81819de3897d538be4a478ca3a5&userId=9912430&direction=1&compareId=0&limit=10
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("userId", user_id);
		params.add("direction", page);
		params.add("compareId", lastId);
		params.add("limit", size);

		String url = "http://mobile.yiban.cn/api/v3/feeds/user";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回当前用户的【我的】窗口页面信息
	 * 
	 * @param user_id
	 *            用户id
	 * @return 返回用户的头像地址、姓名、昵称、网薪
	 * @throws Exception
	 */
	public JSONObject getUsersHome(Object user_id) throws Exception {
		// GET
		// /api/v3/users/6746755?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/users/" + user_id;
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获得当前用户的校方认证信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getSchoolVerify() throws Exception {
		// GET
		// /api/v3/users/6746755/school?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/users/" + user.getUser_id() + "/school";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回当前用户的网薪变化记录
	 * 
	 * @param page
	 *            页数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getMoneyLog(Object page) throws Exception {
		// GET
		// /api/v3/user/money?access_token=b644f81819de3897d538be4a478ca3a5&page=1
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("page", page);

		String url = "http://mobile.yiban.cn/api/v3/user/money";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回用户的二维码信息
	 * 
	 * @param user_id
	 *            用户id
	 * @return 返回携带了二维码信息的字符串
	 * @throws Exception
	 */
	public JSONObject getUsersQRCard(Object user_id) throws Exception {
		// GET
		// /api/v3/users/6746755/card?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/users/" + user_id + "/card";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回当前用户的资料库目录
	 * 
	 * @param path
	 *            路径，需要在路径前面加斜杠/
	 * @param page
	 *            页数
	 * @param size
	 *            每页内容数
	 * @return 文件和目录列表信息
	 * @throws Exception
	 */
	public JSONObject getFilesSelf(String path, Object page, Object size) throws Exception {
		// GET
		// /api/v2/ydfs/directorys?access_token=b644f81819de3897d538be4a478ca3a5&page=1&num=20
		// HTTP/1.1
		// GET
		// /api/v2/ydfs/directorys?access_token=0a72a27568fdcaed819ada82526b8fda&path=%2Fmobile&page=1&num=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", size);
		if (!NullValid.isNull(page)) {
			params.add("path", URLEncoder.encode(path, "utf-8"));
		}

		String url = "http://mobile.yiban.cn/api/v2/ydfs/directorys";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回资料库中别人分享的资料信息
	 * 
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @param by
	 *            不知道是什么，但是=1
	 * @return 返回资料文件列表
	 * @throws Exception
	 */
	public JSONObject getFilesShareList(Object page, Object size, Object by) throws Exception {
		// GET
		// /api/v2/ydfs/shares?access_token=b644f81819de3897d538be4a478ca3a5&page=1&num=20&by=1
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", size);
		params.add("by", by);

		String url = "http://mobile.yiban.cn/api/v2/ydfs/shares";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取我的好友列表信息
	 * 
	 * @return 返回好友列表信息
	 * @throws Exception
	 */
	public JSONObject getUsersFriends() throws Exception {
		// GET
		// /api/v1/user/friends?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v1/user/friends";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取好友申请列表
	 * 
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @param lastId
	 *            上一页的最后一条信息的id
	 * @return 返回好友申请列表
	 * @throws Exception
	 */
	public JSONObject getFriendsApply(Object page, Object size, Object lastId) throws Exception {
		// GET
		// /api/v1/user/friends/apply?access_token=b644f81819de3897d538be4a478ca3a5&page=1&size=20&last_id=0&order=DESC
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("size", size);
		params.add("last_id", lastId);
		params.add("order", "DESC");

		String url = "http://mobile.yiban.cn/api/v1/user/friends/apply";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回群组申请列表
	 * 
	 * @param page
	 *            页数
	 * @param size
	 *            没有条数
	 * @return 返回群组申请列表
	 * @throws Exception
	 */
	public JSONObject getGroupsApply(Object page, Object size) throws Exception {
		// GET
		// /api/v1/groups/apply?access_token=b644f81819de3897d538be4a478ca3a5&page=1&size=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("size", size);

		String url = "http://mobile.yiban.cn/api/v1/groups/apply";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取公共群、机构群主页的基础信息内容
	 * 
	 * @param groupID
	 *            公共群的id
	 * @param user_id
	 *            该公共群所属的用户id，可为空=-1
	 * @return
	 * @throws Exception
	 */
	public JSONObject getGroupsIndex(Object groupID, Object user_id) throws Exception {
		// GET
		// /api/v2/groups/214173?access_token=0a72a27568fdcaed819ada82526b8fda&user_id=-1
		// HTTP/1.1
		// GET
		// /api/v2/groups/245939?access_token=0a72a27568fdcaed819ada82526b8fda&user_id=1514703
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("user_id", user_id);

		String url = "http://mobile.yiban.cn/api/v2/groups/" + groupID;
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回公共群、机构群主页的推荐内容，类似动态
	 * 
	 * @param groupID
	 *            公共群的id
	 * @param user_id
	 *            该公共群所属的用户id，可为空=-1
	 * @return
	 * @throws Exception
	 */
	public JSONObject getGroupsFeeds(Object groupID, Object user_id) throws Exception {
		// GET
		// /api/v2/groups/lastvotecom?access_token=0a72a27568fdcaed819ada82526b8fda&groupid=214173&puserid=-1
		// HTTP/1.1
		// GET
		// /api/v2/groups/lastvotecom?access_token=0a72a27568fdcaed819ada82526b8fda&groupid=245939&puserid=1514703
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("groupid", groupID);
		params.add("puserid", user_id);

		String url = "http://mobile.yiban.cn/api/v2/groups/lastvotecom";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 当kind=1时返回好友列表里面的公众号，当kind=2时返回用户的机构号列表
	 * 
	 * @param kind
	 *            =1 or =2
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUsersFollows(Object kind) throws Exception {
		// GET
		// /api/v2/user/follows?access_token=b644f81819de3897d538be4a478ca3a5&kind=2
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("kind", kind);

		String url = "http://mobile.yiban.cn/api/v2/user/follows";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取机构号主页内容
	 * 
	 * @param orgId
	 *            机构号id
	 * @return 返回机构号的基本信息
	 * @throws Exception
	 */
	public JSONObject getOrgIndex(Object orgId) throws Exception {
		// GET
		// /api/v1/organizations/5000105?access_token=b644f81819de3897d538be4a478ca3a5
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v1/organizations/" + orgId;
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}
	public JSONObject getOrgUri(Object orgId) throws Exception {
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("user_id", orgId);

		String url = "http://mobile.yiban.cn/api/v2/province/orgs/isprovince";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}
	/**
	 * 返回机构号主页的动态内容列表，或者说是推荐阅读列表
	 * 
	 * @param orgId
	 *            机构号id
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getOrgRecommendList(Object orgId, Object page, Object size) throws Exception {
		// GET
		// /api/v1/organization/recommendlist?access_token=b644f81819de3897d538be4a478ca3a5&user_id=5000105&page=1&num=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("user_id", orgId);
		params.add("page", page);
		params.add("num", size);

		String url = "http://mobile.yiban.cn/api/v1/organization/recommendlist";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回机构号主页的APP列表
	 * 
	 * @param orgId
	 *            机构号id
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getOrgIndexApps(Object orgId, Object page, Object size) throws Exception {
		// GET
		// /api/v1/organization/applications?access_token=b644f81819de3897d538be4a478ca3a5&user_id=5000105&page=1&num=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("user_id", orgId);
		params.add("page", page);
		params.add("num", size);

		String url = "http://mobile.yiban.cn/api/v1/organization/applications";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取机构号首页名师推荐的教师列表
	 * 
	 * @param orgId
	 *            机构号id
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getOrgRecommendTeachers(Object orgId, Object page, Object size) throws Exception {
		// GET
		// /api/v1/organization/recommendteachers?access_token=b644f81819de3897d538be4a478ca3a5&user_id=5000105&page=1&num=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("user_id", orgId);
		params.add("page", page);
		params.add("num", size);

		String url = "http://mobile.yiban.cn/api/v1/organization/recommendteachers";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取机构号的机构群列表信息，而且也只能获取到机构群
	 * 
	 * @param orgId
	 *            机构号id
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getOrgGroups(Object orgId, Object page, Object size) throws Exception {
		// GET
		// /api/v1/groups?access_token=b644f81819de3897d538be4a478ca3a5&user_id=5000105&kind=2&page=1&num=20
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("user_id", orgId);
		params.add("page", page);
		params.add("num", size);
		params.add("kind", 2);

		String url = "http://mobile.yiban.cn/api/v1/groups";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取当前用户的群组信息
	 * 
	 * @param size
	 *            条数
	 * @param isOrgGroup
	 *            是否获取机构群，是：获取机构群，否：获取公共群
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUsersGroups(Object size, boolean isOrgGroup) throws Exception {
		// GET
		// /api/v2/groups/mygroups?access_token=b644f81819de3897d538be4a478ca3a5&my_group_type=0&num=5&is_org_group=0
		// HTTP/1.1
		// GET
		// /api/v2/groups/mygroups?access_token=b644f81819de3897d538be4a478ca3a5&my_group_type=0&num=5&is_org_group=1
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("my_group_type", 0);
		params.add("num", size);
		int is_org_group = isOrgGroup ? 1 : 0;
		params.add("is_org_group", is_org_group);

		String url = "http://mobile.yiban.cn/api/v2/groups/mygroups";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回公共群列表信息，只能获取当前用户的所有公共群信息，无论是机构群还是公共群，都一次性获取完。
	 * 相当于调用了getUsersGroups(10,true)并且调用getUsersGroups(10,false)，
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUsersGroupsAll() throws Exception {
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("kind", 1);
		String url = "http://mobile.yiban.cn/api/v1/groups";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回群组列表，一个简化信息的接口，更完善的信息请查看getUsersGroups()
	 * 
	 * @param kind
	 *            公共群=1，机构群=2
	 * @param page
	 *            页数
	 * @param num
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUsersGroupsLow(int kind, int page, int num) throws Exception {
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

		return json;
	}

	/**
	 * 查找用户
	 * 
	 * @param keyword
	 *            查找内容
	 * @param search
	 *            这个不知道什么值，可选123，=1时为查找用户
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public JSONObject getSearchUsers(String keyword, int search, Object page, Object size) throws Exception {
		// GET
		// /api/v1/search/users?access_token=b644f81819de3897d538be4a478ca3a5&keyword=%25E4%25BE%25AF%25E6%25B4%258B&search=1&page=1&num=3
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("keyword", URLEncoder.encode(keyword, "utf-8"));
		params.add("search", search);
		params.add("page", page);
		params.add("num", size);

		String url = "http://mobile.yiban.cn/api/v1/search/users";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 查询群组
	 * 
	 * @param keyword
	 *            查询内容
	 * @param page
	 *            页数
	 * @param size
	 *            每页条数
	 * @param isOrgGroup
	 *            是否查询机构群，是：查询机构群，否：查询公共群
	 * @return
	 * @throws Exception
	 */
	public JSONObject getGroupsSearch(String keyword, Object page, Object size, boolean isOrgGroup) throws Exception {
		// GET
		// /api/v2/recommend/groups/search?access_token=b644f81819de3897d538be4a478ca3a5&
		// isOrganGroups=0&page=1&num=3&keywords=%25E4%25BE%25AF%25E6%25B4%258B&isCountFriend=1
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("keywords", URLEncoder.encode(keyword, "utf-8"));
		params.add("page", page);
		params.add("num", size);
		int isOrganGroups = isOrgGroup ? 1 : 0;
		params.add("isOrganGroups", isOrganGroups);

		String url = "http://mobile.yiban.cn/api/v2/recommend/groups/search";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取推荐好友列表
	 * 
	 * @param user_type
	 *            不知道这是什么值，当=0时获取到推荐普通好友
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFriendsRecommends(int user_type) throws Exception {
		// GET
		// /api/v1/friends/recommend?access_token=b644f81819de3897d538be4a478ca3a5&user_type=0
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("user_type", user_type);

		String url = "http://mobile.yiban.cn/api/v1/friends/recommend";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 添加好友
	 * 
	 * @param user_id
	 *            对方用户id
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFriendsADD(Object user_id) throws Exception {
		// POST
		// /api/v1/user/friends/apply?access_token=b644f81819de3897d538be4a478ca3a5&friend_id=6849317&reason=1
		// HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("friend_id", user_id);
		params.add("reason", 1);

		String url = "http://mobile.yiban.cn/api/v1/user/friends/apply";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取签到问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getCheckin_Question() throws Exception {
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v2/activity/sign/question";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 回答签到问题。<br>
	 * {"data":{"rewards":{"answer":2,"feeds":9},"status":3},"response":100,"message":"请求成功"}<br>
	 * 其中answer答题奖励网薪数，feeds分享动态奖励网薪数
	 * 
	 * @param optionId
	 *            问题选项的id值
	 * @param feeds
	 *            默认=1
	 * @return
	 * @throws Exception
	 */
	public JSONObject getCheckin_Answer(Object optionId, Object feeds) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("optionId", optionId);
		params.add("feeds", feeds);

		String url = "http://mobile.yiban.cn/api/v3/checkin/answer";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取易班消息，就是那个有签到得网薪提醒的消息
	 * 
	 * @param page
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public JSONObject getYiBanMessage(Object page, Object num) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("page", page);
		params.add("num", num);
		String url = "http://mobile.yiban.cn/api/v1/users/" + user.getUser_id() + "/messagebox/list";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获得APP首页的横幅大图和链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getBanner() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v1/home/banner";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回当前用户的认证学号或者工号
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getStudentId() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("id", user.getUser_id());

		String url = "http://mobile.yiban.cn/api/v3/user/student";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取客户端首页的APP列表，其中包括默认的APP和学校自定义的APP列表信息
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public JSONObject getHotApp() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("provinceOrg", "1");

		String url = "http://mobile.yiban.cn/api/v1/home/hotapp";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回了当前用户签到的动态
	 * 
	 * @param lastId
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFeedsCheckIn(Object lastId, Object page, Object size) throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		params.add("direction", page);
		params.add("compareId", lastId);
		params.add("limit", size);
		String url = "http://mobile.yiban.cn/api/v3/feeds/checkin";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回最新版本的APP信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getCheckVersion() throws Exception {
		String url = "http://mobile.yiban.cn/api/v1/version/checkupdate";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 红包排行榜，手气榜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPacketRangeUserLuck() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/global-list-lucky";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 红包排行榜，土豪榜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPacketRangeUserTop() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v3/money/envelopes/global-list-rich";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回地区信息，包括国内省份、特别行政区、海内外
	 * 
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getProvinceList() throws Exception {
		// Params params = new Params();
		// params.add("appVersion", "4.4.3");
		// params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v2/area";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 应用广场中，获得本校的应用
	 * 
	 * @param page
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public JSONObject getLightAppApplicationUser(Object page, Object num) throws Exception {
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

		return json;
	}

	/**
	 * 返回应用广场的分类列表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getLightAppCategory() throws Exception {
		String url = "http://mobile.yiban.cn/api/v2/application/labels";
		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回所有的好友信息，以及机构号
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUsersUnGroupFriends() throws Exception {
		Params params = new Params();
		params.add("appVersion", "4.4.3");
		params.add("access_token", user.getAccess_token());
		String url = "http://mobile.yiban.cn/api/v1/user/ungroupfriends";
		HttpConfig config = new HttpConfig(url, params.getParams());
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 返回服务器节点列表
	 * 
	 * @param userid
	 *            不知何用，检测到默认38
	 * @return
	 * @throws Exception
	 */
	public JSONObject getNode(Object userid) throws Exception {
		// GET /getNode?userid=38 HTTP/1.1
		Params params = new Params();
		// params.add("access_token", user.getAccess_token());
		params.add("userid", userid);

		String url = "http://mobile.yiban.cn:4888/getNode";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取聊天服务器IP信息<br>
	 * {"data":{"port":4261,"host":"112.65.235.55","useSecure":false},"response":100,"message":"请求成功"}<br>
	 * 
	 * this.websocket_url = ("http://" + host + ":" + port +
	 * "/socket.io/1/");<br>
	 * this.websocket_ws = ("ws://" + host + ":" + port +
	 * "/socket.io/1/websocket/");<br>
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public JSONObject getWebSocketAddress() throws Exception {
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/im/server";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 转存别人分享的文件
	 * 
	 * @param path
	 *            文件路径
	 * @param sharesFile_uid
	 *            文件的uid，其实就是分享这个文件的用户id，或者uid
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFilesToSaveMe(String path, Object userid) throws Exception {
		// POST
		// /api/v2/ydfs/transfile?access_token=0a72a27568fdcaed819ada82526b8fda&
		// path=QQ%E5%9B%BE%E7%89%8720170717192546.jpg&uid=8836317 HTTP/1.1
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		params.add("path", URLEncoder.encode(path, "utf-8"));
		params.add("uid", userid);

		String url = "http://mobile.yiban.cn/api/v2/ydfs/transfile";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 下载文件。 起初是打算用来下载资料库别人分享的文件的，分享信息里面存有下载地址，因此可以直接获取下载地址传过来就可以下载文件了
	 * 
	 * @param url
	 *            文件地址
	 * @return
	 * @throws Exception
	 */
	public byte[] download(String url) throws Exception {
		// GET
		// /index.php?a=getFile&userid=8836317&path=QQ%E5%9B%BE%E7%89%8720170717192546.jpg&
		// uid=6502935&sign=a8bf80acc535aedeb5113d8e1c9021c3&time=1500389575&access_token=0a72a27568fdcaed819ada82526b8fda
		// HTTP/1.1

		HttpConfig config = new HttpConfig(url);
		byte[] re = Http.get(config);

		return re;
	}

	/**
	 * 获得微社区的文章列表
	 * 
	 * @param channel_id
	 *            微社区id
	 * @param group_id
	 *            公共群、机构群的群id
	 * @param puid
	 *            该群拥有者（公众号）的用户id
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public JSONObject getArticleList(Object channel_id, Object group_id, Object puid, Object page, Object size)
			throws Exception {
		// POST /forum/article/listAjax HTTP/1.1
		// channel_id=101365&group_id=245939&puid=1514703&page=1&size=6&orderby=updateTime&Sections_id=-1&need_notice=0&my=0
		Params params = new Params();
		params.add("channel_id", channel_id);
		params.add("group_id", group_id);
		params.add("puid", puid);
		params.add("page", page);
		params.add("size", size);
		params.add("orderby", "updateTime");
		params.add("Sections_id", "-1");
		params.add("need_notice", "0");
		params.add("my", "0");

		String url = "http://www.yiban.cn/forum/article/listAjax";
		HttpConfig config = new HttpConfig(url, params.getParams());
		config.setReferer(
				"http://www.yiban.cn/forum/article/list/groupid/" + group_id + "/puserid/" + puid + "?type=topic");
		config.setJsonAccept();
		config.setAjax(true);
		config.setHeader("cookie", cookie);

		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 在微社区发布文章
	 * 
	 * @param channel_id
	 *            微社区id
	 * @param group_id
	 *            公共群、机构群的群id
	 * @param puid
	 *            该群拥有者（公众号）的用户id
	 * @param title
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public JSONObject getArticleADD(Object channel_id, Object group_id, Object puid, String title, String content)
			throws Exception {
		// POST /forum/article/addAjax HTTP/1.1
		// channel_id=101365&puid=1514703&title=1&article_id=&content=%3Cp%3E2%3C%2Fp%3E&isNotice=&pubArea=245939&Sections_id=
		Params params = new Params();
		params.add("channel_id", channel_id);
		params.add("puid", puid);
		params.add("title", URLEncoder.encode(title, "utf-8"));
		params.add("article_id", "");
		params.add("content", URLEncoder.encode(content, "utf-8"));
		params.add("isNotice", "");
		params.add("pubArea", group_id);
		params.add("Sections_id", "0");

		String url = "http://www.yiban.cn/forum/article/addAjax";
		HttpConfig config = new HttpConfig(url, params.getParams());
		config.setReferer(
				"http://www.yiban.cn/forum/article/list/groupid/" + group_id + "/puserid/" + puid + "?type=topic");
		config.setJsonAccept();
		config.setAjax(true);
		config.setHeader("cookie", cookie);

		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	/**
	 * 获取微社区的一些基础信息，其中包括微社区id
	 * 
	 * @param group_id
	 *            公共群、机构群的群id
	 * @param puid
	 *            该群拥有者（公众号）的用户id
	 * @return
	 * @throws Exception
	 */
	public JSONObject getArticleChannelInfo(Object group_id, Object puid) throws Exception {
		// POST /forum/api/getListAjax HTTP/1.1
		// puid=1514703&group_id=245939
		Params params = new Params();
		params.add("puid", puid);
		params.add("group_id", group_id);

		String url = "http://www.yiban.cn/forum/api/getListAjax";
		HttpConfig config = new HttpConfig(url, params.getParams());
		config.setReferer("http://www.yiban.cn/forum/article/list/groupid/" + group_id + "/puserid/" + puid);
		config.setJsonAccept();
		config.setAjax(true);
		config.setHeader("cookie", cookie);

		byte[] re = Http.post(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	public String getWebCookies() throws Exception {
		// http://www.yiban.cn/login/accessTokenLogin?access_token=0a72a27568fdcaed819ada82526b8fda
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://www.yiban.cn/login/accessTokenLogin";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		config.setHeader("X-Requested-With", "com.yiban.app");

		@SuppressWarnings("unused")
		byte[] re = Http.get(config);
		String cookie = config.getBackHeaderCookieString();

		return cookie;
	}

	public byte[] getChatStep1() throws Exception {
		head.put("Authorization", "Bearer " + user.getAccess_token());// Authorization:
																		// Bearer
																		// df71ef17b9076c5a446111cdfa4f52f6

		String url = "http://112.65.235.55:4252/socket.io/1/";
		HttpConfig config = new HttpConfig(url, head);
		byte[] re = Http.get(config);
		
		return re;
	}
	public JSONObject getFeedsUntouched() throws Exception {
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn/api/v3/feeds/untouched";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}

	public String getWebJs() throws Exception {
		Params params = new Params();
		params.add("access_token", user.getAccess_token());
		
		String url = "http://mobile.yiban.cn/api/v1/site/webjs";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");

		return result;
	}
	public JSONObject a() throws Exception {
		Params params = new Params();
		params.add("access_token", user.getAccess_token());

		String url = "http://mobile.yiban.cn";
		HttpConfig config = new HttpConfig(url, params.getParams(), head);
		byte[] re = Http.get(config);
		String result = new String(re, "utf-8");
		JSONObject json = JSONObject.parseObject(result);

		return json;
	}
}
