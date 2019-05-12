package cn.goour.yiban.app.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

import cn.goour.utils.security.AEncrypt;
import cn.goour.utils.security.impl.MD5Impl;

public class Test {
	private static String signature = "93748947a03a02269d015af3db858174";
	private static Map<String, Object> map = new HashMap<String, Object>();
	public static int random(int min,int max) {
		Random random = new Random();
		int re = random.nextInt(max);
		return re;
	}
	public static void main(String[] args) throws Exception {
//		String a="648ff1eb-1d2f-428a-88f3-c5199b979e16";
//		String b="N3lcb6YBSignE";
//		System.out.println(md5("000"));
//		System.out.println(md5(md5("0")+"00"));
//		System.out.println(md5(md5(md5("000"))));
//		System.out.println(md5(md5(md5("0")+"0")+"0"));
//		System.out.println(md5(md5(md5(md5("0")+"0")+"0")));
//		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("a", "1");
		System.out.println(jsonObject);
		System.out.println(AEncrypt.byteArrayToHexStr(jsonObject.toJSONString().getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("色".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[喜欢]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[挖鼻]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[色]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[哭]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[气]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[汗]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[嘿嘿]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[可怜]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[呆]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[哼]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[羞]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[得意]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[怒]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[么么哒]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[惊]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[好梦]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[坚定]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[耶]".getBytes()));
		System.out.println(AEncrypt.byteArrayToHexStr("[衰]".getBytes()));

		/*
		System.out.println("开始");
		H1(host, access_token, timeStamp, uuid);
		H2(host, access_token, timeStamp, uuid);
		H3(host, access_token, timeStamp, uuid);
		H4(host, access_token, timeStamp, uuid);
		H5(host, access_token, timeStamp, uuid);
		H6(host, access_token, timeStamp, uuid);

		A1(host, access_token, timeStamp, uuid);
		A2(host, access_token, timeStamp, uuid);
		A3(host, access_token, timeStamp, uuid);
		A4(host, access_token, timeStamp, uuid);
		A5(host, access_token, timeStamp, uuid);
		A6(host, access_token, timeStamp, uuid);

		T1(host, access_token, timeStamp, uuid);
		T2(host, access_token, timeStamp, uuid);
		T3(host, access_token, timeStamp, uuid);
		T4(host, access_token, timeStamp, uuid);
		T5(host, access_token, timeStamp, uuid);
		T6(host, access_token, timeStamp, uuid);

		U1(host, access_token, timeStamp, uuid);
		U2(host, access_token, timeStamp, uuid);
		U3(host, access_token, timeStamp, uuid);
		U4(host, access_token, timeStamp, uuid);
		U5(host, access_token, timeStamp, uuid);
		U6(host, access_token, timeStamp, uuid);

		R1(host, access_token, timeStamp);
		R2(host, access_token, timeStamp);
		R3(host, access_token, timeStamp);
		R4(host, access_token, timeStamp);
		R5(host, access_token, timeStamp);
		R6(host, access_token, timeStamp);

		System.out.println("期待数量：" + (24 * 4 + 6 * 2));
		System.out.println("实际数量：" + map.size());
		System.out.println(ok);*/
	}

	public static void show(String go) {
		// go = go.replace("-", "");
		String re = md5(go);
		map.put(go, re);
		boolean ok = signature.equals(re);
		System.out.println("原始数据\t\t" + go);
		System.out.println("加密后数据\t\t" + re);
		System.out.println("相同\t\t" + ok);
		System.out.println();
		if (ok) {
			ok = true;
			try {
				throw new Exception("成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void H1(String host, String access_token, String timeStamp, String uuid) {
		String go = host + access_token + timeStamp + uuid;
		show(go);
		go = host + access_token.toUpperCase() + timeStamp + uuid;
		show(go);
		go = host + access_token + timeStamp + uuid.toUpperCase();
		show(go);
		go = host + access_token.toUpperCase() + timeStamp + uuid.toUpperCase();
		show(go);
	}

	public static void H2(String host, String access_token, String timeStamp, String uuid) {
		String go = host + access_token + uuid + timeStamp;
		show(go);
		go = host + access_token.toUpperCase() + uuid + timeStamp;
		show(go);
		go = host + access_token + uuid.toUpperCase() + timeStamp;
		show(go);
		go = host + access_token.toUpperCase() + uuid.toUpperCase() + timeStamp;
		show(go);

	}

	public static void H3(String host, String access_token, String timeStamp, String uuid) {
		String go = host + timeStamp + access_token + uuid;
		show(go);
		go = host + timeStamp + access_token.toUpperCase() + uuid;
		show(go);
		go = host + timeStamp + access_token + uuid.toUpperCase();
		show(go);
		go = host + timeStamp + access_token.toUpperCase() + uuid.toUpperCase();
		show(go);
	}

	public static void H4(String host, String access_token, String timeStamp, String uuid) {
		String go = host + timeStamp + uuid + access_token;
		show(go);
		go = host + timeStamp + uuid.toUpperCase() + access_token;
		show(go);
		go = host + timeStamp + uuid + access_token.toUpperCase();
		show(go);
		go = host + timeStamp + uuid.toUpperCase() + access_token.toUpperCase();
		show(go);

	}

	public static void H5(String host, String access_token, String timeStamp, String uuid) {
		String go = host + uuid + access_token + timeStamp;
		show(go);
		go = host + uuid.toUpperCase() + access_token + timeStamp;
		show(go);
		go = host + uuid + access_token.toUpperCase() + timeStamp;
		show(go);
		go = host + uuid.toUpperCase() + access_token.toUpperCase() + timeStamp;
		show(go);

	}

	public static void H6(String host, String access_token, String timeStamp, String uuid) {
		String go = host + uuid + timeStamp + access_token;
		show(go);
		go = host + uuid.toUpperCase() + timeStamp + access_token;
		show(go);
		go = host + uuid + timeStamp + access_token.toUpperCase();
		show(go);
		go = host + uuid.toUpperCase() + timeStamp + access_token.toUpperCase();
		show(go);

	}

	public static void A1(String host, String access_token, String timeStamp, String uuid) {
		String go = access_token + host + timeStamp + uuid;
		show(go);
		go = access_token.toUpperCase() + host + timeStamp + uuid;
		show(go);
		go = access_token + host + timeStamp + uuid.toUpperCase();
		show(go);
		go = access_token.toUpperCase() + host + timeStamp + uuid.toUpperCase();
		show(go);

	}

	public static void A2(String host, String access_token, String timeStamp, String uuid) {
		String go = access_token + host + uuid + timeStamp;
		show(go);
		go = access_token.toUpperCase() + host + uuid + timeStamp;
		show(go);
		go = access_token + host + uuid.toUpperCase() + timeStamp;
		show(go);
		go = access_token.toUpperCase() + host + uuid.toUpperCase() + timeStamp;
		show(go);

	}

	public static void A3(String host, String access_token, String timeStamp, String uuid) {
		String go = access_token + timeStamp + host + uuid;
		show(go);
		go = access_token.toUpperCase() + timeStamp + host + uuid;
		show(go);
		go = access_token + timeStamp + host + uuid.toUpperCase();
		show(go);
		go = access_token.toUpperCase() + timeStamp + host + uuid.toUpperCase();
		show(go);

	}

	public static void A4(String host, String access_token, String timeStamp, String uuid) {
		String go = access_token + timeStamp + uuid + host;
		show(go);
		go = access_token.toUpperCase() + timeStamp + uuid + host;
		show(go);
		go = access_token + timeStamp + uuid.toUpperCase() + host;
		show(go);
		go = access_token.toUpperCase() + timeStamp + uuid.toUpperCase() + host;
		show(go);

	}

	public static void A5(String host, String access_token, String timeStamp, String uuid) {
		String go = access_token + uuid + host + timeStamp;
		show(go);
		go = access_token.toUpperCase() + uuid + host + timeStamp;
		show(go);
		go = access_token + uuid.toUpperCase() + host + timeStamp;
		show(go);
		go = access_token.toUpperCase() + uuid.toUpperCase() + host + timeStamp;
		show(go);

	}

	public static void A6(String host, String access_token, String timeStamp, String uuid) {
		String go = access_token + uuid + timeStamp + host;
		show(go);
		go = access_token.toUpperCase() + uuid + timeStamp + host;
		show(go);
		go = access_token + uuid.toUpperCase() + timeStamp + host;
		show(go);
		go = access_token.toUpperCase() + uuid.toUpperCase() + timeStamp + host;
		show(go);

	}

	public static void T1(String host, String access_token, String timeStamp, String uuid) {
		String go = timeStamp + host + access_token + uuid;
		show(go);
		go = timeStamp + host + access_token.toUpperCase() + uuid;
		show(go);
		go = timeStamp + host + access_token + uuid.toUpperCase();
		show(go);
		go = timeStamp + host + access_token.toUpperCase() + uuid.toUpperCase();
		show(go);

	}

	public static void T2(String host, String access_token, String timeStamp, String uuid) {
		String go = timeStamp + host + uuid + access_token;
		show(go);
		go = timeStamp + host + uuid.toUpperCase() + access_token;
		show(go);
		go = timeStamp + host + uuid + access_token.toUpperCase();
		show(go);
		go = timeStamp + host + uuid.toUpperCase() + access_token.toUpperCase();
		show(go);

	}

	public static void T3(String host, String access_token, String timeStamp, String uuid) {
		String go = timeStamp + access_token + host + uuid;
		show(go);
		go = timeStamp + access_token.toUpperCase() + host + uuid;
		show(go);
		go = timeStamp + access_token + host + uuid.toUpperCase();
		show(go);
		go = timeStamp + access_token.toUpperCase() + host + uuid.toUpperCase();
		show(go);

	}

	public static void T4(String host, String access_token, String timeStamp, String uuid) {
		String go = timeStamp + access_token + uuid + host;
		show(go);
		go = timeStamp + access_token.toUpperCase() + uuid + host;
		show(go);
		go = timeStamp + access_token + uuid.toUpperCase() + host;
		show(go);
		go = timeStamp + access_token.toUpperCase() + uuid.toUpperCase() + host;
		show(go);

	}

	public static void T5(String host, String access_token, String timeStamp, String uuid) {
		String go = timeStamp + uuid + host + access_token;
		show(go);
		go = timeStamp + uuid.toUpperCase() + host + access_token;
		show(go);
		go = timeStamp + uuid + host + access_token.toUpperCase();
		show(go);
		go = timeStamp + uuid.toUpperCase() + host + access_token.toUpperCase();
		show(go);

	}

	public static void T6(String host, String access_token, String timeStamp, String uuid) {
		String go = timeStamp + uuid + access_token + host;
		show(go);
		go = timeStamp + uuid.toUpperCase() + access_token + host;
		show(go);
		go = timeStamp + uuid + access_token.toUpperCase() + host;
		show(go);
		go = timeStamp + uuid.toUpperCase() + access_token.toUpperCase() + host;
		show(go);

	}

	public static void U1(String host, String access_token, String timeStamp, String uuid) {
		String go = uuid + host + access_token + timeStamp;
		show(go);
		go = uuid.toUpperCase() + host + access_token + timeStamp;
		show(go);
		go = uuid + host + access_token.toUpperCase() + timeStamp;
		show(go);
		go = uuid.toUpperCase() + host + access_token.toUpperCase() + timeStamp;
		show(go);

	}

	public static void U2(String host, String access_token, String timeStamp, String uuid) {
		String go = uuid + host + timeStamp + access_token;
		show(go);
		go = uuid.toUpperCase() + host + timeStamp + access_token;
		show(go);
		go = uuid + host + timeStamp + access_token.toUpperCase();
		show(go);
		go = uuid.toUpperCase() + host + timeStamp + access_token.toUpperCase();
		show(go);

	}

	public static void U3(String host, String access_token, String timeStamp, String uuid) {
		String go = uuid + access_token + host + timeStamp;
		show(go);
		go = uuid.toUpperCase() + access_token + host + timeStamp;
		show(go);
		go = uuid + access_token.toUpperCase() + host + timeStamp;
		show(go);
		go = uuid.toUpperCase() + access_token.toUpperCase() + host + timeStamp;
		show(go);

	}

	public static void U4(String host, String access_token, String timeStamp, String uuid) {
		String go = uuid + access_token + timeStamp + host;
		show(go);
		go = uuid.toUpperCase() + access_token + timeStamp + host;
		show(go);
		go = uuid + access_token.toUpperCase() + timeStamp + host;
		show(go);
		go = uuid.toUpperCase() + access_token.toUpperCase() + timeStamp + host;
		show(go);

	}

	public static void U5(String host, String access_token, String timeStamp, String uuid) {
		String go = uuid + timeStamp + host + access_token;
		show(go);
		go = uuid.toUpperCase() + timeStamp + host + access_token;
		show(go);
		go = uuid + timeStamp + host + access_token.toUpperCase();
		show(go);
		go = uuid.toUpperCase() + timeStamp + host + access_token.toUpperCase();
		show(go);
	}

	public static void U6(String host, String access_token, String timeStamp, String uuid) {
		String go = uuid + timeStamp + access_token + host;
		show(go);
		go = uuid.toUpperCase() + timeStamp + access_token + host;
		show(go);
		go = uuid + timeStamp + access_token.toUpperCase() + host;
		show(go);
		go = uuid.toUpperCase() + timeStamp + access_token.toUpperCase() + host;
		show(go);

	}

	public static void R1(String host, String access_token, String timeStamp) {
		String go = host + timeStamp + access_token;
		show(go);
		go = host + timeStamp + access_token.toUpperCase();
		show(go);
	}

	public static void R2(String host, String access_token, String timeStamp) {
		String go = host + access_token + timeStamp;
		show(go);
		go = host + access_token.toUpperCase() + timeStamp;
		show(go);
	}

	public static void R3(String host, String access_token, String timeStamp) {
		String go = access_token + host + timeStamp;
		show(go);
		go = access_token.toUpperCase() + host + timeStamp;
		show(go);
	}

	public static void R4(String host, String access_token, String timeStamp) {
		String go = access_token + timeStamp + host;
		show(go);
		go = access_token.toUpperCase() + timeStamp + host;
		show(go);
	}

	public static void R5(String host, String access_token, String timeStamp) {
		String go = timeStamp + host + access_token;
		show(go);
		go = timeStamp + host + access_token.toUpperCase();
		show(go);
	}

	public static void R6(String host, String access_token, String timeStamp) {
		String go = timeStamp + access_token + host;
		show(go);
		go = timeStamp + access_token.toUpperCase() + host;
		show(go);
	}

	public static String md5(String str) {
		String re = null;
		try {
			re = MD5Impl.getInstance().EncryptionToString(str);
			if ("f166d53cbb8e8b9b687d67bb63367fd1".equals(re)) {
				System.out.println("成功了");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
}
