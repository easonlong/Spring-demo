package com.eason.spring.jdbc.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.eason.spring.jdbc.utils.MD5Util;

public class SignatureGenerator {
	private final static String SECRET = "LKJFSDFSD322L23J23";
	private final static String SEPARATOR = "&";

	public static String generator(Map<String, Object> params) {
		Map<String, Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
		treeMap.putAll(params);
		StringBuilder sortedParam = new StringBuilder();
		for (String key : treeMap.keySet()) {
			sortedParam.append(key);
			sortedParam.append("=");
			sortedParam.append(treeMap.get(key));
			sortedParam.append(SignatureGenerator.SEPARATOR);
		}
		sortedParam.deleteCharAt(sortedParam.length() - 1);
		String signature = MD5Util.MD5(sortedParam.toString() + SignatureGenerator.SECRET);
		return signature;
	}

	public Map<String, Object> mockArgs() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("uid", "13761691017");
		args.put("order.id", "9876");
		args.put("order.timestamp", 987654321);
		args.put("payment.total", 98);
		args.put("payment.actual", 72);
		args.put("sid", 1);
		return args;
	}

	public static void main(String[] args) {
		SignatureGenerator sgGenerator = new SignatureGenerator();
		System.out.println(SignatureGenerator.generator(sgGenerator.mockArgs()));
	}
}
