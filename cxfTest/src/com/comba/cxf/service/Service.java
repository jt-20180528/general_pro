package com.comba.cxf.service;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface Service {

	public String sayHi(String s);
	public String sayToUser(User user);
	public String[] sayToUserList(List<User> user);
}
