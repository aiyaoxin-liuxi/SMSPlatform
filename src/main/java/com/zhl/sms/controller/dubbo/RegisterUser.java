package com.zhl.sms.controller.dubbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
@Component("iUserService")
@Path("users")
public class RegisterUser implements IUserService{
	@POST
	@Path("register")
	@Consumes({ContentType.TEXT_XML_UTF_8})
//	@Consumes({ContentType.APPLICATION_JSON_UTF_8})
	@Produces({ContentType.APPLICATION_JSON_UTF_8})
	public Object registerUser(String username) {
		
		System.out.println(username);
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("1111");
		System.out.println("1111");
		System.out.println("1111");
//		System.out.println(str);
//		str = "222222222222222222222";
		map.put("name", "Hello " + username);
		return map;
//		try {
//			System.out.println(testPojo.getName());
////			System.out.println(URLDecoder.decode(name,"utf-8"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		 List list = new ArrayList();
//	        list.add(testPojo.getName());
//	        return list;
	}


}
