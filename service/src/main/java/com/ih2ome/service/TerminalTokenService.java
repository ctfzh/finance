package com.ih2ome.service;

import com.ih2ome.model.caspain.TerminalToken;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
public interface TerminalTokenService {
    /**
     * ����token��ѯ�û���Ϣ
     *
     * @param token
     * @return
     */
    TerminalToken findByToken(String token);
}
