package com.zhl.sms.dao;

import com.zhl.sms.pojo.ChannelInfo;

public interface IChannelDao {
	
	public ChannelInfo queryByChannelId(String channelId);

}
