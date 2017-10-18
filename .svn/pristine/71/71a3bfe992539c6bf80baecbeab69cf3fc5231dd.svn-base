package com.wk.ssp.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类
 */
public class JsonUtils {

//	private final static ObjectMapper mapper = new ObjectMapper();
//
//	static {
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//	}

	/**
	 * 将一个{@link Object}转成{@code Json}格式的{@link String}
	 * 
	 * @param object
	 *            需要转换的数据
	 * @return 返回{@code Json}格式的{@link String}
	 * @throws JsonProcessingException
	 *             抛出异常
	 */
	public static <T> String writeObject2Json(T object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.writeValueAsString(object);
	}

	/**
	 * 将{@code Json}格式的{@link String}转换成一个{@link Class}{@code <T>}类型的实例
	 * 
	 * @param json
	 *            {@code Json}格式的{@link String}
	 * @param clazz
	 *            需要转换成的实例类型
	 * @return 返回{@link Class}{@code <T>}类型的实例
	 * @throws JsonParseException
	 *             抛出异常
	 * @throws JsonMappingException
	 *             抛出异常
	 * @throws IOException
	 *             抛出异常
	 */
	public static <T> T readJson2Object(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, clazz);
	}

	/**
	 * 将{@code Json}格式的{@link String}转换成一个{@link TypeReference}{@code <T>}
	 * 引用类型的实例
	 * 
	 * @param json
	 *            {@code Json}格式的{@link String}
	 * @param typeReference
	 *            要转换成的引用类型
	 * @return 返回{@link TypeReference}{@code <T>}引用类型的实例
	 * @throws JsonParseException
	 *             抛出异常
	 * @throws JsonMappingException
	 *             抛出异常
	 * @throws IOException
	 *             抛出异常
	 */
	public static <T> T readJson2Object(String json, TypeReference<T> typeReference)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, typeReference);
	}
}
