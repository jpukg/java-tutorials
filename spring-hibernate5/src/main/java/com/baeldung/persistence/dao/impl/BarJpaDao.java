package com.nklkarthi.persistence.dao.impl;

import com.nklkarthi.persistence.dao.IBarDao;
import com.nklkarthi.persistence.dao.common.AbstractJpaDao;
import com.nklkarthi.persistence.model.Bar;
import org.springframework.stereotype.Repository;

@Repository
public class BarJpaDao extends AbstractJpaDao<Bar> implements IBarDao {

    public BarJpaDao() {
        super();

        setClazz(Bar.class);
    }

    // API

}
