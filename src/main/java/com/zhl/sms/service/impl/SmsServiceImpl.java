package com.zhl.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.sms.dao.ISmsDao;
import com.zhl.sms.pojo.SmsInfo;
import com.zhl.sms.service.ISmsService;

@Service("smsService")
public class SmsServiceImpl implements ISmsService {
	
	@Resource
	private ISmsDao smsDao;

	@Override
	public void saveSmsInfo(SmsInfo smsInfo) {
		smsDao.saveSmsInfo(smsInfo);
	}

	@Override
	public SmsInfo querySmsInfoByInSmsNoAndPhone(String inSmsNo, String smsPhone) {
		return smsDao.querySmsInfoByInSmsNoAndPhone(inSmsNo, smsPhone);
		
	}

	@Override
	public void updateSmsUserReturn(String smsId, String userReturnContent) {
		smsDao.updateSmsUserReturn(smsId, userReturnContent);
	}

}
