package com.ih2ome.common.test.sdk;import com.dcfs.esb.ftp.client.https.FtpGet;import com.dcfs.esb.ftp.server.msg.FileMsgBean;import com.pabank.sdk.PABankSDK;public class FtpGetTest {	public void test(String remoteFile,String localFile,String privateAuth) {		FtpGet ftpGet = null;		FileMsgBean bean = null;		try {			//初始化配置			PABankSDK.init("conf/config.properties");			//验证开发者			PABankSDK.getInstance().approveDev();						bean = new FileMsgBean();			/** FtpGet类的构造器使用说明			 参数一》 remoteFile：	 服务器文件名，由文件上传方提供，如"/picp/报价单.txt"			 参数二》localFile:		下载文件时，需指定一个本机的绝对路径存放下载后的文件（文件名也要指定），如"D:/down/flieTest/报价单.txt"			 参数三》 scrtFlag ：	是否加密标志，填false			 参数四》 key：			加密密钥，填null			 参数五》 privateAuth：	私密授权码，由文件上传方提供。每个文件都有对应的私密授权码，授权码错误将无法下载文件			 参数六》 bean：消息bean，	用于获取服务器响应的消息			 * */			ftpGet = new FtpGet(remoteFile,localFile,false,null,privateAuth,bean);//			使用指定签约//			ftpGet = new FtpGet(remoteFile,localFile,false,null,privateAuth,bean,"BBB");			ftpGet.doGetFile();		} catch (Exception e) {			//捕获到异常后，可以从FileMsgBean类获取异常信息			System.out.println(bean.getFileRetMsg());			e.printStackTrace();		} finally{			ftpGet.close(true);		}	}	public static void main(String[] args) {		new FtpGetTest().test("/picp/UMF_BPROC_11011344_20171117134404034573394388270001.RQ.zip","D:/FTP/UMF_BPROC_11011344_20171117134404034573394388270001.RQ.zip","37a21b69-bd78-44c7-855c-71f279230884");//		new FtpGetTest().test(args[0], args[1], args[2]);	}}	