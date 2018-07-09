package com.ih2ome.service.impl;

import com.ih2ome.dao.caspain.TerminalTokenDao;
import com.ih2ome.model.caspain.TerminalToken;
import com.ih2ome.service.TerminalTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class TerminalTokenServiceImpl implements TerminalTokenService {

    @Autowired
    private TerminalTokenDao terminalTokenDao;

    @Override
    public TerminalToken findByToken(String token) {
        TerminalToken terminalToken = terminalTokenDao.selectByPrimaryKey(token);
        return terminalToken;
    }
}
