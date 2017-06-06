package com.edianxun.adp.dao.impl;

import com.edianxun.adp.dao.GateDao;
import com.edianxun.adp.dao.ManagerDao;
import com.edianxun.adp.pojo.GateCard;
import com.edianxun.adp.pojo.Manager;

import java.util.List;

/**
 * @author lhr
 * @ date 2014-4-21
 */
public class GateDaoImpl extends BaseDaoImpl<GateCard> implements GateDao {

    @Override
    public List<GateCard> findAllOrderbyDay() {
        List gateCards = find("from GateCard order by day desc,name asc");
        if (gateCards != null) {
            return (List<GateCard>) gateCards;
        }
        return null;
    }

}
