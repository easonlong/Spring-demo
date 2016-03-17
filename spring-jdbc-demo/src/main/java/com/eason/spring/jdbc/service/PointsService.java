package com.eason.spring.jdbc.service;

import com.eason.spring.jdbc.entity.Points;

public interface PointsService {
	public void save(Points points);
	public Points query(String uid);
	public void update(Points points);
}
