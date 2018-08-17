package com.ih2ome.service.impl;

import com.ih2ome.common.utils.FtpUtil;
import com.ih2ome.dao.lijiang.ZjjzCnapsBanktypeDao;
import com.ih2ome.model.lijiang.ZjjzCnapsBanktype;
import com.ih2ome.service.ZjjzCnapsBanktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class ZjjzCnapsBanktypeServiceImpl implements ZjjzCnapsBanktypeService {

    @Autowired
    private ZjjzCnapsBanktypeDao zjjzCnapsBanktypeDao;

    /**
     * 查询所有的银行类别
     *
     * @return
     */
    @Override
    public List<ZjjzCnapsBanktype> getBankType() {
        List<ZjjzCnapsBanktype> banktypes = zjjzCnapsBanktypeDao.selectAll();
        return banktypes;
    }

    /**
     * 读取ftp superBankCode.txt文件，将内容插入数据库
     */
    @Override
    public void insertBankType() {
        String ip = "219.133.104.102";
        Integer port = 5021;
        String username = "pab2biuser";
        String password = "pab2biuser_123";
        String superBankCodeFile = "PUBLIC/BankCode/SuperBankCode.txt";
        FtpClient ftpClient = FtpUtil.connectFTP(ip, port, username, password);
        String str = "";
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        List<ZjjzCnapsBanktype> banktypes = new ArrayList<>();
        try {
            inputStream = ftpClient.getFileStream(superBankCodeFile);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
            //第一行数据不需要。
            str = bufferedReader.readLine();
            while ((str = bufferedReader.readLine()) != null) {
//                System.out.println(str);
                ZjjzCnapsBanktype banktype = new ZjjzCnapsBanktype();
                String[] params = str.split("&");
                banktype.setBankno(params[0]);
                banktype.setStatus(params[1]);
                banktype.setBankclscode(params[2]);
                banktype.setBankname(params[3]);
                banktypes.add(banktype);
            }
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将数据插入数据库
        zjjzCnapsBanktypeDao.insertList(banktypes);
    }
}
