package com.zhl.sms.service;

import com.zhl.sms.pojo.ChannelInfo;

public interface IChannelService {
	
	public ChannelInfo queryByChannelId(String channelId);

}
