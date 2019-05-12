package cn.goour.yiban.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 学院信息，或者说系别信息
 * @author HouKunLin
 *
 */
public class College implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long user_id;
	private String name;
	private String firstL;
	private String pinyin;
	private String img;

	public static List<College> parse(JSONObject jsonObject){
		List<College> list = new ArrayList<College>();
		if (jsonObject.getIntValue("response") == 100) {
			JSONObject data = jsonObject.getJSONObject("data");
			JSONArray list2 = data.getJSONArray("colleges");
			for (Object object : list2) {
				JSONObject json = (JSONObject)object;
				College vo = new College();
				vo.id = json.getLongValue("id");
				vo.user_id = json.getLongValue("user_id");
				vo.name = json.getString("name");
				vo.firstL = json.getString("firstL");
				vo.pinyin = json.getString("pinyin");
				vo.img = json.getString("img");
				
				list.add(vo);
			}
		}
		return list;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstL() {
		return firstL;
	}

	public void setFirstL(String firstL) {
		this.firstL = firstL;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("College [id=");
		builder.append(id);
		builder.append(", user_id=");
		builder.append(user_id);
		builder.append(", ");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (firstL != null) {
			builder.append("firstL=");
			builder.append(firstL);
			builder.append(", ");
		}
		if (pinyin != null) {
			builder.append("pinyin=");
			builder.append(pinyin);
			builder.append(", ");
		}
		if (img != null) {
			builder.append("img=");
			builder.append(img);
		}
		builder.append("]");
		return builder.toString();
	}
}
