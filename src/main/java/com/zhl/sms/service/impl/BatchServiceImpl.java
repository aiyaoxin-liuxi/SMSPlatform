package com.zhl.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.sms.dao.IBatchDao;
import com.zhl.sms.dao.IChannelDao;
import com.zhl.sms.pojo.BatchInfo;
import com.zhl.sms.pojo.ChannelInfo;
import com.zhl.sms.service.IBatchService;
import com.zhl.sms.service.IChannelService;

@Service("batchService")
public class BatchServiceImpl implements IBatchService {
	
	@Resource
	private IBatchDao batchDao;

	@Override
	public void saveBatchInfo(BatchInfo b) {
		batchDao.saveBatchInfo(b);
		
	}

	@Override
	public BatchInfo queryByBatchId(String batchId) {
		return batchDao.queryByBatchId(batchId);
	}
	

}
