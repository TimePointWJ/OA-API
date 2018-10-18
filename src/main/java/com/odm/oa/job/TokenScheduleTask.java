package com.odm.oa.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TokenScheduleTask extends Thread {

	protected static Logger logger = LogManager.getLogger(TokenScheduleTask.class.getName());

	public void initMethod() {
		this.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				// 获取token
				this.tokenTask(0);

				Thread.sleep(7100 * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tokenTask(int retry) throws InterruptedException {
		try {
			if (retry >= 3) {
				logger.error("获取token异常重试次数以达上线");
				return;
			}
//			TokenWRLock.setToken(BusinessUtils.getToken());
		} catch (Exception e) {
			retry++;
			logger.error("获取token异常:", e);
			Thread.sleep(1 * 1000);
			this.tokenTask(retry);
		}
	}
}
