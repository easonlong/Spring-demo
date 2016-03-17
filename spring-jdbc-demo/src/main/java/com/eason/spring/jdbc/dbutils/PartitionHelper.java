package com.eason.spring.jdbc.dbutils;

public class PartitionHelper {
	public static String realTable(String uid) {
		return "points_details" + (Integer.valueOf(uid) % 2 + 1);
	}
}
