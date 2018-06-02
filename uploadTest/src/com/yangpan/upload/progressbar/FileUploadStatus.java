package com.yangpan.upload.progressbar;


/**
 * 
 * 上传状态类
 * 
 * @author 妞见妞爱
 *
 */
public class FileUploadStatus {
	
	private String statusMsg = "";
	private long readedBytes = 0L;
	private long totalBytes = 0L;
	private int currentItem = 0;
	// 1 ： 错误  0 ： 正常  2:完成
	private String error = "0";
	
	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public long getReadedBytes() {
		return readedBytes;
	}

	public void setReadedBytes(long readedBytes) {
		this.readedBytes = readedBytes;
	}

	public long getTotalBytes() {
		return totalBytes;
	}

	public void setTotalBytes(long totalBytes) {
		this.totalBytes = totalBytes;
	}

	public int getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(int currentItem) {
		this.currentItem = currentItem;
	}
 
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String toJSon() {
		StringBuffer strJSon = new StringBuffer();
		strJSon.append("{");
		strJSon.append("error:'").append(error).append("',");
		strJSon.append("statusMsg:'").append(statusMsg).append("',");
		strJSon.append("readedBytes:'").append(readedBytes).append("',");
		strJSon.append("totalBytes:'").append(totalBytes).append("',");
		strJSon.append("currentItem:'").append(currentItem).append("'");
		strJSon.append("}");
		return strJSon.toString();
	}	
}
