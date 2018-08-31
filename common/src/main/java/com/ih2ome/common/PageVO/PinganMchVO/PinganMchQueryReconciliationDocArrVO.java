package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/31
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryReconciliationDocArrVO {
    //文件名称
    @JSONField(name = "FileName")
    private String FileName;

    //随机密码
    @JSONField(name = "RandomPassword")
    private String RandomPassword;

    //文件路径
    @JSONField(name = "FilePath")
    private String FilePath;

    //提取码
    @JSONField(name = "DrawCode")
    private String DrawCode;
}
