package com.zhl.sms.util;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SmsHttpUtil {
	
	// 应用
	public static String httpsPost(String url, String uuid,
			Map<String, Object> map) {
		String ret = null;
		try {
			CloseableHttpClient httpClient = createSSLClientDefault();
			List<NameValuePair> pairs = null;
			if (map != null && !map.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(map.size());
				for (Entry<String, Object> entry : map.entrySet()) {
					Object value = entry.getValue();
					if (entry.getKey() != null && value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
			}
			HttpPost httpPost = new HttpPost(new URI(url));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :"
						+ statusCode);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ret = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
		} catch (Exception e) {
		}
		return ret;
	}
	public static CloseableHttpClient createSSLClientDefault() throws Exception {

		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
				new TrustStrategy() {
					// 信任所有
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslContext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
	
	/**
	 * 易信通使用
	 * @param url
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public static String postJson(String url, String jsonStr) throws Exception{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonStr, "utf-8");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity  httpEntity = response.getEntity();
		//if (httpEntity != null) {
		//post.abort();
	  //}
		if(statusCode == 200){
			ByteArrayOutputStream resHolder = new ByteArrayOutputStream();
			httpEntity.writeTo(resHolder);
			resHolder.flush();
	    	return resHolder.toString("utf-8");
		}
		else {
			throw new Exception("服务异常");
		}
	}

}
