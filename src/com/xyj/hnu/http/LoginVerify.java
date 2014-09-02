package com.xyj.hnu.http;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.xyj.hnu.tools.Configs;

/**
 * µÇÂ½ÑéÖ¤
 * 
 * @author xyj
 * 
 */
public class LoginVerify {
	public static void verify(final String username, final String password) {
		StringRequest request = new StringRequest(Request.Method.POST,
				Configs.loginUrl, new Listener<String>() {
					@Override
					public void onResponse(String response) {
						Configs.loginResult = response.equals("success")?true:false;
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Configs.loginResult=false;
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", username);
				params.put("password", password);
				return params;
			}
		};
		Configs.queue.add(request);
	}
}
