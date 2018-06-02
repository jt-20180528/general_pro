package com.comba.cxf.service;

import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface="com.comba.cxf.Service",serviceName="Service")
public class ImplServicve implements Service {

	@Override
	public String sayHi(String s) {
		return "Hello"+s;
	}

	@Override
	public String sayToUser(User user) {
		return user.getName();
	}

	@Override
	public String[] sayToUserList(List<User> userList) {
		String[] s=new String[userList.size()];
		for(int i=0;i<userList.size();i++){
			s[i]=userList.get(i).getName();
		}
		return s;
	}

}
