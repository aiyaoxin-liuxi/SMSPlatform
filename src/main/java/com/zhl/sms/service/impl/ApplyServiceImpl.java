package com.zhl.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.sms.dao.IApplyDao;
import com.zhl.sms.pojo.ApplyInfo;
import com.zhl.sms.service.IApplyService;

@Service("applyService")
public class ApplyServiceImpl implements IApplyService {
	
	@Resource
	private IApplyDao applyDao;

	@Override
	public ApplyInfo queryByApplyId(String applyId) {
		return applyDao.queryByApplyId(applyId);
	}


}
