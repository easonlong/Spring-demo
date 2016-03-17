package com.eason.spring.jdbc.dbutils;

public class ShardHelper {
	public static int getDsId(String uid) {
		return Integer.valueOf(uid) >100?1:2;
	}
}
