package cn.edu.nju.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.nju.model.DelicacyBrief;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SearchManager {

	private String baseUrl;

	public SearchManager() {
		setBaseUrl("http://shaynee.vicp.cc/");
	}

	public ArrayList<DelicacyBrief> getDelicacyList() {
		ArrayList<DelicacyBrief> list = new ArrayList<DelicacyBrief>();
		
		URL myFileUrl = null;
		try {
			myFileUrl = new URL(baseUrl + "service/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6000);
			conn.setDoInput(true);
			conn.connect();
			is = conn.getInputStream();
			try {
				JSONArray json = new JSONArray(new BufferedReader(new InputStreamReader(is)).readLine());
				for (int i = 0; i < json.length(); i++) {
					DelicacyBrief brief = new DelicacyBrief();
					JSONObject obj = json.getJSONObject(i);
					brief.setDid(Integer.parseInt(obj.getString("did")));
					brief.setName(obj.getString("name"));
					brief.setImage(getBitMap(obj.getString("image")));
					list.add(brief);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			is.close();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private static Bitmap getBitMap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection conn;
		InputStream is = null;
		try {
			conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6000);
			conn.setDoInput(true);
			conn.connect();
			is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
