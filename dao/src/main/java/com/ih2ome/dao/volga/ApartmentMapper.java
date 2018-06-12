package com.ih2ome.dao.volga;

import com.ih2ome.model.volga.Apartment;
import com.ih2ome.model.volga.ApartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentMapper {
    int countByExample(ApartmentExample example);

    int deleteByExample(ApartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Apartment record);

    int insertSelective(Apartment record);

    List<Apartment> selectByExampleWithBLOBs(ApartmentExample example);

    List<Apartment> selectByExample(ApartmentExample example);

    Apartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Apartment record, @Param("example") ApartmentExample example);

    int updateByExampleWithBLOBs(@Param("record") Apartment record, @Param("example") ApartmentExample example);

    int updateByExample(@Param("record") Apartment record, @Param("example") ApartmentExample example);

    int updateByPrimaryKeySelective(Apartment record);

    int updateByPrimaryKeyWithBLOBs(Apartment record);

    int updateByPrimaryKey(Apartment record);
}