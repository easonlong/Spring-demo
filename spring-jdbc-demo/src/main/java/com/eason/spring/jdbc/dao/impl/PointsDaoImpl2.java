package com.eason.spring.jdbc.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.eason.spring.jdbc.dao.PointsDao2;
import com.eason.spring.jdbc.dbutils.JdbcTemplateWrapper;
import com.eason.spring.jdbc.dbutils.PartitionHelper;
import com.eason.spring.jdbc.entity.Points;

public class PointsDaoImpl2 implements PointsDao2 {
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	public void insert(Points points) {
		final String insertSql1 = "insert into points_details (uid,points) values (?,?)";
		this.jdbcTemplateWrapper.getTemplateByUid(points.getUid()).update(insertSql1, points.getUid(), points.getPoints());
	}

	
	public Points query(String uid) {
		List<Points> list= this.jdbcTemplateWrapper.getTemplateByUid(uid).query("select uid,points from " + PartitionHelper.realTable(uid)
		        + " where uid = ?", new Object[] { uid }, new BeanPropertyRowMapper<Points>(Points.class));
		if(CollectionUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
	}


	public JdbcTemplateWrapper getJdbcTemplateWrapper() {
	    return jdbcTemplateWrapper;
    }


	public void setJdbcTemplateWrapper(JdbcTemplateWrapper jdbcTemplateWrapper) {
	    this.jdbcTemplateWrapper = jdbcTemplateWrapper;
    }
}
