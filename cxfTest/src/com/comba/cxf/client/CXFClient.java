package com.comba.cxf.client;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import com.comba.cxf.service.Client;

public class CXFClient {

	public void getWeb(String url,String oparetionName,Map<String, Object> paramMap){
		try {
			JaxWsDynamicClientFactory clientFactory=JaxWsDynamicClientFactory.newInstance();
			Client client=(Client) clientFactory.createClient(url);
			Object[] result=((org.apache.cxf.endpoint.Client) client).invoke(oparetionName,paramMap.get("s"));
			System.out.println(result[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mian(){
		String url="http://127.0.0.1:8080/cxfTest/service?wsdl";
		String oparetionName="sayHi";
		Map<String, Object> paramMap=new LinkedHashMap<String, Object>();
		paramMap.put("s", "大军");
		getWeb(url, oparetionName, paramMap);
	}
	
	@Test
	public void aa(){
		System.out.println("aaa");
	}
}
