package cn.goour.yiban.app;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.util.Date;
import java.util.Scanner;

import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.goour.utils.io.FileUtils;
import cn.goour.utils.tools.Console;
import cn.goour.yiban.app.action.IChat;
import cn.goour.yiban.app.action.WebSocketChat;
import cn.goour.yiban.app.action.YiBanRequest;
import cn.goour.yiban.app.entity.User;

public class YiBanRequestTest {
	private static User user;
	private static YiBanRequest action;
	private WebSocketChat client;

	public YiBanRequestTest() throws Exception {
		if (action == null) {
			action = new YiBanRequest();
			File file = new File("data.bat");
			if (file.exists()) {
				Object obj = FileUtils.readObject(file);
				user = (User) obj;
			}

////			 user = new User("13877691346", "hou123456");
			 user = new User("13207725244", "houkunlin0123456");
			 JSONObject json = YiBanRequest.getLogin(user);
			 user = new User(json);
			 FileUtils.saveObject(file, user);
//			YiBanRequest.getAutoLogin(user);
			action.setUser(user);
//			action.getChatStep1();
//			action.getWebJs();
//			action.getFeedsUntouched();
//			action.getUsersConfig();
//			action.getAppHome();
//			action.getUsersFollows(2);
//			action.getOrgUri(6125719);
//			action.getOrgIndex(6125719);
//			action.getOrgRecommendList(5000167, 1,2);
//			action.getOrgIndexApps(5000167, 1, 2);
//			action.getOrgRecommendTeachers(5000167, 1, 2);
//			action.getUsersFollows(2);
//			action.getFeedsUntouched();
//			action.getUsersHome(user.getUser_id());
//			System.out.println(action.getMoneyLog(1));
		}
	}
	@Test
	public void testTest(){
		try {
			System.out.println(1);
			 System.out.println(action.getUsersFriends());
				System.out.println(2);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	@Test
	public void testAction(){
		try {
			// action.getFeedsSelf(0, 1, 20);
//			action.getBlog("内容", "标题1", "1", "1", "关键词", "");
//			// 每日签到
//			JSONObject json = action.getCheckin_Question();
//			boolean isChecked = json.getJSONObject("data").getBooleanValue("isChecked");
//			System.out.println(isChecked);
//			if (!isChecked) {
//				JSONArray json2 = json.getJSONObject("data").getJSONObject("survey").getJSONObject("question").getJSONArray("option");
//				JSONObject json3 = (JSONObject)json2.get(0);
//				action.getCheckin_Answer(json3.get("Question_id"), 1);
//			}
			
			
//			// 获取我的动态首页并同情点赞
//			JSONObject feed = action.getFeedsSelf(0, 1, 10);
//			JSONArray feeds = feed.getJSONObject("data").getJSONArray("list");
//			for (Object object : feeds) {
//				JSONObject item = (JSONObject)object;
//				String upList = item.get("upList").toString();
//				String downList = item.get("downList").toString();
//				if (!upList.contains(user.getUser_id()+"")) {
//					action.getFeedsUps(item.get("id"));
//				}
//				if (!downList.contains(user.getUser_id()+"")) {
//					action.getFeedsDowns(item.get("id"));
//				}
//			}
//			System.out.println(action.getMoneyLog(1));
//			// 访问好友主页，获取好友动态列表，并同情点赞
//			JSONObject friend = action.getUsersFollows(2);
//			System.out.println(friend);
			
//			JSONArray friends = friend.getJSONObject("data").getJSONArray("users");
//			for (Object object : friends) {
//				JSONObject item = (JSONObject)object;
//				action.getUsersInfo(item.get("user_id"));
//				JSONObject fed = action.getFeedsOther(item.get("user_id"), 0, 1, 10);
//				action.getFeedsUntouched();
////				JSONArray feds = fed.getJSONObject("data").getJSONArray("list");
////				for (Object object2 : feds) {
////					JSONObject item2 = (JSONObject)object2;
////					String upList = item2.get("upList").toString();
////					String downList = item2.get("downList").toString();
////					if (!upList.contains(user.getUser_id()+"")) {
////						action.getFeedsUps(item2.get("id"));
////					}
////					if (!downList.contains(user.getUser_id()+"")) {
////						action.getFeedsDowns(item2.get("id"));
////					}
////				}
//			}
			
//			// 获得机构号列表，并访问机构号主页信息
//			JSONObject org = action.getUsersFollows(2);
//			JSONArray orgs = org.getJSONObject("data").getJSONArray("users");
//			for (Object object : orgs) {
//				JSONObject item = (JSONObject)object;
//				action.getOrgIndex(item.get("user_id"));
//			}
			
//			action.getFeedsShares("我来测试一条分享信息，当前时间："+new Date(), "香港特别行政区黄大仙区雙喜街彩虹道体育馆", "22.334948", "114.201888", "爱我就点一下它", "http://stuip.goour.cn", "哈哈，爱我吧", "http://img02.fs.yiban.cn/5000090/avatar/user/200", "爱你的来源");
//			action.getFeedsShares("我来测试一条分享信息，当前时间："+new Date(), "", "", "", "爱我就点一下它", "http://stuip.goour.cn", "哈哈，爱我吧", "http://img02.fs.yiban.cn/5000090/avatar/user/200", "爱你的来源");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testArticleAdd(){
		try {
			String cookie = action.getWebCookies();
			action.setCookie(cookie);
			JSONObject json1 = action.getUsersGroupsLow(2, 1, 5);
			if (json1.getIntValue("response") == 100) {
				JSONObject json2 = (JSONObject)json1.getJSONObject("data").getJSONArray("groups").get(0);
				String groupId = json2.getString("group_id");
				String guserId = json2.getString("user_id");
				JSONObject json3 = action.getArticleChannelInfo(groupId, guserId);
				String channelId = json3.getJSONObject("data").getString("channel_id");
				JSONObject json4 = action.getArticleList(channelId, groupId, guserId, 1, 5);
				System.out.println("groupId="+groupId);
				System.out.println("guserId="+guserId);
				System.out.println("channelId="+channelId);
				action.getArticleADD(channelId, groupId, guserId, "发布一条微社区信息", "<h1>这是信息内容</h2>");
				JSONObject json5 = action.getArticleList(channelId, groupId, guserId, 1, 5);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Ignore
	public void testGetLogin() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetAutoLogin() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetAppHome() {
		Console.printInfo("");
		try {
			action.getAppHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUsersConfig() {
		Console.printInfo("");
		try {
			action.getUsersConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsSelf() {
		Console.printInfo("");
		try {
			action.getFeedsSelf(0, 1, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsUps() {
		Console.printInfo("");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsDowns() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsCommentsAdd() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsCommentsDel() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsADD() {
		Console.printInfo("");
		try {
			action.getFeedsADD("新的一天快要过去，发一条动态纪念一下："+new Date(), "香港特别行政区黄大仙区雙喜街彩虹道体育馆", "22.334948", "114.201888");
			action.getFeedsShares("我来测试一条分享信息", "香港特别行政区黄大仙区雙喜街彩虹道体育馆", "22.334948", "114.201888", "爱我就百度一下", "http://www.baidu.com", "哈哈，爱我吧", "http://img02.fs.yiban.cn/5000090/avatar/user/200", "爱你的来源");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeddsDELETE() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUsersInfo() {
		Console.printInfo("");
		try {
			action.getUsersInfo("6746755");
			action.getUsersInfo("6746743");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsOther() {
		Console.printInfo("");
		try {
			action.getFeedsOther(user.getUser_id(), 0, 1, 10);
			action.getFeedsOther("6746755", 0, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUsersHome() {
		Console.printInfo("");
		try {
			action.getUsersHome(user.getUser_id());
			action.getUsersHome(6746755);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetSchoolVerify() {
		Console.printInfo("");
		try {
			action.getSchoolVerify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetMoneyLog() {
		Console.printInfo("");
		try {
			action.getMoneyLog(1);
			action.getMoneyLog(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUsersQRCard() {
		Console.printInfo("");
		try {
			action.getUsersQRCard(user.getUser_id());
			action.getUsersQRCard(6746755);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFilesSelf() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFilesShareList() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetMyFriends() {
		Console.printInfo("");
		try {
			action.getUsersFriends();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFriendsApply() {
		Console.printInfo("");
		try {
			action.getFriendsApply(1, 10, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetGroupsApply() {
		Console.printInfo("");
		try {
			action.getGroupsApply(1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUsersFollows() {
		Console.printInfo("");
		try {
			action.getUsersFollows(1);
			action.getUsersFollows(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetOrgIndex() {
		Console.printInfo("");
		try {
			action.getOrgIndex(5000120);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetOrgRecommendList() {
		Console.printInfo("");
		try {
			action.getOrgRecommendList(5000120, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetOrgIndexApps() {
		Console.printInfo("");
		try {
			action.getOrgIndexApps(5000120, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetOrgRecommendTeachers() {
		Console.printInfo("");
		try {
			action.getOrgRecommendTeachers(5000120, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetGroups() {
		Console.printInfo("");
		try {
			action.getUsersGroupsAll();
			action.getUsersGroups(50, false);
			action.getUsersGroups(50, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetOrgGroups() {
		Console.printInfo("");
		try {
			action.getOrgGroups(5000120, 1, 10);
			action.getOrgGroups(5000187, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUsersGroups() {
		Console.printInfo("");
		try {
			action.getUsersGroups(10, true);
			action.getUsersGroups(10, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetSearchUsers() {
		Console.printInfo("");
		try {
			action.getSearchUsers("庄菲菲", 1, 1, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetGroupsSearch() {
		Console.printInfo("");
		try {
			action.getGroupsSearch("马克思", 1, 1, true);
			action.getGroupsSearch("马克思", 1, 1, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFriendsRecommends() {
		Console.printInfo("");
		try {
			action.getFriendsRecommends(0);
			action.getFriendsRecommends(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFriendsADD() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetCheckin_Question() {
		Console.printInfo("");
		try {
			action.getCheckin_Question();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetCheckin_Answer() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetYiBanMessage() {
		Console.printInfo("");
		try {
			action.getYiBanMessage( 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetBanner() {
		Console.printInfo("");
		try {
			action.getBanner();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Ignore
//	public void testGetPublicUser() {
//		Console.printInfo("");
//		try {
//			action.getPublicUser(1);
//			action.getPublicUser(2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Ignore
	public void testGetStudentId() {
		Console.printInfo("");
		try {
			action.getStudentId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetHotApp() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetFeedsCheckIn() {
		Console.printInfo("");
		try {
			action.getFeedsCheckIn(0, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetCheckVersion() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetPacketRangeUserLuck() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetPacketRangeUserTop() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetProvinceList() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetLightAppApplicationUser() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetLightAppCategory() {
		Console.printInfo("");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUnGroupFriendsList() {
		Console.printInfo("");
		try {
			action.getUsersUnGroupFriends();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void testGetUserFriendsGroupingList() {
		Console.printInfo("");
		try {
			action.getUsersGroupsLow(1, 1, 10);
			action.getUsersGroupsLow(2, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
