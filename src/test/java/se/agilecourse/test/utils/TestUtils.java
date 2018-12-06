package se.agilecourse.test.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
public class TestUtils {

	@SuppressWarnings("rawtypes")
	/*public static List jsonToList(String json, Type token) {
		Gson gson = new Gson();
		return gson.fromJson(json, token.getType());
	}*/

	public static String objectToJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public static <T> T jsonToObject(String json, Class<T> classOf) {
		Gson gson = new Gson();
		return gson.fromJson(json, classOf);
	}

	public static <T> List<T> jsonToListOfObject(String json, Type token) {
		Gson gson = new Gson();
		return gson.fromJson(json, token);
	}
}
