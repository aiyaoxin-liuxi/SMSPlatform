package com.zhl.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.sms.dao.IChannelDao;
import com.zhl.sms.pojo.ChannelInfo;
import com.zhl.sms.service.IChannelService;

@Service("channelService")
public class ChannelServiceImpl implements IChannelService {
	
	@Resource
	private IChannelDao channelDao;

	@Override
	public ChannelInfo queryByChannelId(String channelId) {
		return channelDao.queryByChannelId(channelId);
	}


}
