package com.nkrasnovoronka.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactory {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    public static SessionFactory getSessionFactory() {
        loggerInfo.info("Getting session factory");
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            return configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
