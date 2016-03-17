package com.eason.spring.jdbc.service.impl;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.eason.spring.jdbc.dao.PointsDao;
import com.eason.spring.jdbc.dao.PointsDao2;
import com.eason.spring.jdbc.dbutils.TransactionTemplateWrapper;
import com.eason.spring.jdbc.entity.Points;
import com.eason.spring.jdbc.service.PointsService;

public class PointsServiceImpl implements PointsService {

	private PointsDao pointsDao;
	private PointsDao2 pointsDao2;
	private TransactionTemplateWrapper txTemplateWrapper;
	public void save(final Points points) {
		try {
	        Thread.sleep(3000);
        } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		this.txTemplateWrapper.getTemplateByUid(points.getUid()).execute(new TransactionCallbackWithoutResult(){
			@Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				pointsDao.insert(points);
				/*points.setUid("16");
				pointsDao2.insert(points);*/
				int a=1/0;
	            
            }});
		
	}

	public void update(Points points){
		
	}
	
	public Points query(String uid) {
		return this.pointsDao.query(uid);
	}

	public PointsDao getPointsDao() {
		return pointsDao;
	}

	public void setPointsDao(PointsDao pointsDao) {
		this.pointsDao = pointsDao;
	}

	public TransactionTemplateWrapper getTxTemplateWrapper() {
		return txTemplateWrapper;
	}

	public void setTxTemplateWrapper(TransactionTemplateWrapper txTemplateWrapper) {
		this.txTemplateWrapper = txTemplateWrapper;
	}

	public PointsDao2 getPointsDao2() {
	    return pointsDao2;
    }

	public void setPointsDao2(PointsDao2 pointsDao2) {
	    this.pointsDao2 = pointsDao2;
    }

}
