package com.zhl.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.sms.dao.IApplyDao;
import com.zhl.sms.dao.IBatchDao;
import com.zhl.sms.dao.IChannelDao;
import com.zhl.sms.dao.ISmsDao;
import com.zhl.sms.dao.ITestDao;
import com.zhl.sms.service.ITestService;

@Service("testService")
public class TestService implements ITestService {
	
	@Resource
	private ITestDao testDao;
	
	@Resource
    private IApplyDao applyDao;
	@Resource
    private IBatchDao batchDao;
	@Resource
    private IChannelDao channelDao;
	@Resource
    private ISmsDao smsDao;

	@Override
	public void addNewsComment() {
		testDao.addNewsComment();
	}

    @Override
    public void deletePojo() {
        applyDao.deletePojo();
        batchDao.deletePojo();
        channelDao.deletePojo();
        smsDao.deletePojo();
    }

}
