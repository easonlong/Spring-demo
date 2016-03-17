package com.eason.spring.jdbc.dbutils;

import java.util.Map;

import org.springframework.transaction.support.TransactionTemplate;

public class TransactionTemplateWrapper {
	Map<Integer, TransactionTemplate> transactionTemplates;

	public TransactionTemplate getTemplate(int dsId) {
		TransactionTemplate template = this.transactionTemplates.get(dsId);
		if (template == null) {
			throw new RuntimeException("No transactionTemplate[" + dsId + "]");
		}
		return template;
	}

	public Map<Integer, TransactionTemplate> getTransactionTemplates() {
		return transactionTemplates;
	}

	public void setTransactionTemplates(Map<Integer, TransactionTemplate> transactionTemplates) {
		this.transactionTemplates = transactionTemplates;
	}

	public TransactionTemplate getTemplateByUid(String uid) {
		return this.getTemplate(ShardHelper.getDsId(uid));
	}
}
