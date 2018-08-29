package com.ih2ome.service;

import com.ih2ome.model.lijiang.ZjjzCnapsBanktype;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
public interface ZjjzCnapsBanktypeService {
    /**
     * 查询所有的银行类别
     *
     * @return
     */
    List<ZjjzCnapsBanktype> getBankType();


    /**
     * 读取ftp superBankCode.txt文件，将内容插入数据库
     */
    void insertBankType();

    /**
     * 根据银行名称模糊搜索银行类别
     *
     * @param bankName
     * @return
     */
    List<ZjjzCnapsBanktype> getBankType(String bankName);
}
