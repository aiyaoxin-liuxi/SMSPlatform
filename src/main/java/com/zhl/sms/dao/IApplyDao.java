package com.zhl.sms.dao;

import com.zhl.sms.pojo.ApplyInfo;

public interface IApplyDao {
	
	public ApplyInfo queryByApplyId(String applyId);
	
	public void deletePojo();

}
