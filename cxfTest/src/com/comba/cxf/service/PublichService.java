package com.comba.cxf.service;

import javax.xml.ws.Endpoint;

public class PublichService {

	public static void main(String[] args) {
		ImplServicve imp = new ImplServicve();
		String endPoint="http://localhost:8080/cxfTest/service";
		Endpoint.publish(endPoint, imp);
		System.out.println("web service start!");
	}

}
