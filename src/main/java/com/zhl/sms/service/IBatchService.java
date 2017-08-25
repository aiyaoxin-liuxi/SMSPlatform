package com.zhl.sms.service;

import com.zhl.sms.pojo.BatchInfo;


public interface IBatchService {
	
	/**
	 * 写入批量手机号记录表
	 * @param b
	 */
	public void saveBatchInfo(BatchInfo b);
	
	/**
	 * 获取批量手机号记录表信息
	 * @param batchId
	 * @return
	 */
	public BatchInfo queryByBatchId(String batchId);

}
