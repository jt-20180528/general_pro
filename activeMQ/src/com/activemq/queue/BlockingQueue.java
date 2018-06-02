package com.activemq.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueue {

	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(5);
		LinkedBlockingQueue<String> bq1 = new LinkedBlockingQueue<String>();
		for(int i=1;i<=10;i++){
			bq.offer(i);
		}
		
		for(int i=1;i<=100000;i++){
			bq1.offer("a"+i);
		}
		System.out.println(bq.size()+"--"+bq1.size());
		
		for(int i=1;i<=bq1.size();i++){
			try {
				System.out.println(bq1.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
