package com.project.shoponline.model.module3;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;
public class UseIdOrGenerate extends IdentityGenerator {

public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
    if (obj == null) throw new HibernateException(new NullPointerException()) ;

//    if ((((Advertiser) obj).getAdvertiser_id()) == null) {
//        Serializable id = super.generate(session, obj) ;
//        return id;
//    } else {
        return ((Advertiser) obj).getAdvertiser_id();

//    }
}
}
