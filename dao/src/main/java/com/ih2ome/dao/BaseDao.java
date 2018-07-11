package com.ih2ome.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Sky
 * create 2018/07/06
 * email sky.li@ixiaoshuidi.com
 **/

/**
 * Mapper接口：基本的增、删、改、查方法
 * MySqlMapper：针对MySQL的额外补充接口，支持批量插入
 */
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
