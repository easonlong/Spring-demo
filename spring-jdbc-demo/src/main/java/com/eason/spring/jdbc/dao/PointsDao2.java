package com.eason.spring.jdbc.dao;

import com.eason.spring.jdbc.entity.Points;

public interface PointsDao2 {
	public void insert(Points points);
	public Points query(String uid);
}
