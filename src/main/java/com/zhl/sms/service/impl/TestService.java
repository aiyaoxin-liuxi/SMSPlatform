package com.zhl.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.sms.dao.ITestDao;
import com.zhl.sms.service.ITestService;

@Service("testService")
public class TestService implements ITestService {
	
	@Resource
	private ITestDao testDao;

	@Override
	public void addNewsComment() {
		testDao.addNewsComment();
	}

}
