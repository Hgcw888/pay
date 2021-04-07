package com.hgcw.pay.dao;

import com.hgcw.pay.pojo.PayInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PayInfoMapper {
    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(PayInfo record);

    /**
     * 选择性添加、添加有数据字段
     * @param record
     * @return
     */
    int insertSelective(PayInfo record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    PayInfo selectByPrimaryKey(Integer id);

    /**
     * 选择性更新、更新有数据的字段或者自己想要更新的字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PayInfo record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(PayInfo record);


    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    PayInfo selectByOrderNo(Long orderNo);

}