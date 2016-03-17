package com.eason.spring.jdbc.dbutils;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateWrapper {
	private Map<Integer, JdbcTemplate> jdbcTemplates;

	public JdbcTemplate getTemplate(int dsId) {
		JdbcTemplate jdbcTemplate = this.jdbcTemplates.get(dsId);
		if (jdbcTemplate == null) {
			throw new RuntimeException("No jdbcTemplate[" + dsId + "]");
		}
		return jdbcTemplate;
	}

	public Map<Integer, JdbcTemplate> getJdbcTemplates() {
		return jdbcTemplates;
	}

	public void setJdbcTemplates(Map<Integer, JdbcTemplate> jdbcTemplates) {
		this.jdbcTemplates = jdbcTemplates;
	}

	public JdbcTemplate getTemplateByUid(String uid) {
		return getTemplate(ShardHelper.getDsId(uid));
	}
}
