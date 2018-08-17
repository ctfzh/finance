package com.ih2ome.common.utils;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sky
 * create 2018/08/17
 * email sky.li@ixiaoshuidi.com
 **/
public class FtpUtil {
    FtpClient ftpClient;

    /**
     * 连接FTP服务
     *
     * @param url           //IP地址
     * @param port//端口号
     * @param username//用户名
     * @param password//密码
     * @return
     */
    public static FtpClient connectFTP(String url, int port, String username, String password) {
        //创建ftp
        FtpClient ftp = null;
        try {
            //创建地址
            SocketAddress addr = new InetSocketAddress(url, port);
            //连接
            ftp = FtpClient.create();
            ftp.connect(addr);
            //登陆
            ftp.login(username, password.toCharArray());
            ftp.setBinaryType();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 取ftp上的文件内容
     *
     * @param ftpFile
     * @param ftp
     * @return
     */
    public static List<String> download(String ftpFile, FtpClient ftp) {
        List<String> list = new ArrayList<String>();
        String str = "";
        InputStream is = null;
        BufferedReader br = null;
        try {
            // 获取ftp上的文件
            is = ftp.getFileStream(ftpFile);
            //转为字节流
            br = new BufferedReader(new InputStreamReader(is, "gbk"));
            while ((str = br.readLine()) != null) {
                System.out.println(str);
//                list.add(str);
            }
            br.close();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        FtpClient ftp = connectFTP("219.133.104.102", 5021, "pab2biuser", "pab2biuser_123");
        List<String> list = download("PUBLIC/BankCode/SuperBankCode.txt", ftp);
        System.out.println(list);
        System.out.println("test");
    }
}
