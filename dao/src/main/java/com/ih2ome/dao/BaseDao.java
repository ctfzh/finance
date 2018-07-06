package com.ih2ome.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Sky
 * create 2018/07/06
 * email sky.li@ixiaoshuidi.com
 **/

/**
 * Mapper�ӿڣ�����������ɾ���ġ��鷽��
 * MySqlMapper�����MySQL�Ķ��ⲹ��ӿڣ�֧����������
 */
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
