package com.edianxun.adp.dao;

import com.edianxun.adp.pojo.GateCard;
import com.edianxun.adp.pojo.Manager;

import java.util.List;

/**
 * 管理员操作接口
 * @author lhr
 * @ date 2014-4-21
 */
public interface GateDao extends BaseDao<GateCard> {

    List<GateCard> findAllOrderbyDay();

}
