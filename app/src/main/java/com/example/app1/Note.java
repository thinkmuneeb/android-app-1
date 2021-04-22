package com.example.app1;

import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.UUID;
import java.io.Serializable;

public class Note implements Serializable{
	
	private String id;
	private String content;
	private boolean important;
	private Date creationDateTime;
	private int aheadTime;

	public Note(){
		init();
	}
	
	public Note(String content){
		init();
		this.content = content;
	}
	public Note(String content, boolean isImportant, int aheadTime){
		init();
		this.content = content;
		this.important = isImportant;
		this.aheadTime = aheadTime;
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	public Date getTime() {
		return addHoursToJavaUtilDate(creationDateTime, aheadTime);
	}

	private void init(){
		this.id = UUID.randomUUID().toString();		
		this.creationDateTime = new Date();
		this.important = false;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setImportance(boolean value){
		important = value;
	}
	
	public boolean isImportant(){
		return important;
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	public Date addHoursToJavaUtilDate(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}
}
