package cn.edu.nju.model;

import android.graphics.Bitmap;

public class DelicacyBrief {
	
	private int did;
	private String name;
	private Bitmap image;
	
	public int getDid() {
		return did;
	}
	
	public void setDid(int did) {
		this.did = did;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap map) {
		this.image = map;
	}
	
}
