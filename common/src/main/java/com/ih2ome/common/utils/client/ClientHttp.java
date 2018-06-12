package com.ih2ome.common.utils.client;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public abstract class ClientHttp {

	public static CloseableHttpAsyncClient createAsyncClient() throws IOReactorException {
		IOReactorConfig ioReactorConfig = IOReactorConfig.copy(IOReactorConfig.DEFAULT)
		    .setIoThreadCount(Runtime.getRuntime().availableProcessors()).setConnectTimeout(3000)
		    .setSoTimeout(3000).setTcpNoDelay(true).setSoKeepAlive(true).setSoReuseAddress(false)
		    .setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();

		RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
		    .setCookieSpec(CookieSpecs.DEFAULT).setConnectionRequestTimeout(3000)
		    .setConnectTimeout(3000).build();

		DefaultConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
		PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
		cm.setMaxTotal(1000);
		cm.setDefaultMaxPerRoute(1000);
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setConnectionManager(cm)
		    .setDefaultRequestConfig(requestConfig).build();
		return httpclient;
	}

	public static CloseableHttpClient createSyncClient() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		return httpClient;
	}

	// 不需验证证书
	public static CloseableHttpClient createSyncSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			    sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

}
