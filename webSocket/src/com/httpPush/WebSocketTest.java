package com.httpPush;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket2")
public class WebSocketTest {
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();

	private static Map<String, WebSocketTest> webSocketMap = new ConcurrentHashMap<String, WebSocketTest>();
	
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private volatile Session session;

	/**
	 * 连接建立成功调用的方法
	 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		webSocketSet.add(this);     //加入set中
		
		webSocketMap.put(session.getId(), this);
		addOnlineCount();           //在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);  //从set中删除
		
		if(this.session!=null){
			webSocketMap.remove(this.session.getId());
		}
		
		subOnlineCount();           //在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@OnMessage
	public void onMessage(String message, Session session){
		System.out.println("来自客户端的消息:" + message);
		
		//群发消息
		/*for(WebSocketTest item: webSocketSet){
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}*/
		//单独发消息
		if(webSocketMap!=null){
			Random random = new Random();
			while (true) {
				if(webSocketMap.get(session.getId())!=null){
					try {
						Thread.sleep(1000);
						
						WebSocketTest item =  webSocketMap.get(session.getId());
						int rodam = random.nextInt(100);
						System.out.println(rodam);
						if(rodam > 25 && rodam < 98){
							item.sendMessage(message+"当前随机数："+rodam+"sessionID:"+session.getId());
						}
						//这里实时获取session中的锁屏时间和自动退出时间
					} catch (Exception e) {
						System.out.println(e.getMessage());
						break;
					}
				}else{
					System.out.println("sssssss");
					break;
				}
			}
		}
	}

	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("发生错误"+error.getMessage());
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message){
		try {
			if(this.session.isOpen() && session.getId()!=null){
				this.session.getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
			
		//this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketTest.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketTest.onlineCount--;
	}
}
