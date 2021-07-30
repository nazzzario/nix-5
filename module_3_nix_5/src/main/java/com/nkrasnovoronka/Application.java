package com.nkrasnovoronka;

import com.nkrasnovoronka.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
            }catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }

        }

//        Map<String, String> properties = new HashMap<>();
//        properties.put("login", args[0]);//Login to BD
//        properties.put("pass", args[1]);// Password BD
//        properties.put("email", args[2]);// Email, search in script.sql
//        properties.put("variant", args[3]);// "1" - JPA, "2" - JDBC
//
//        if (properties.get("variant").equals("1")) {
//            Configuration configuration = new Configuration().configure()
//                    .setProperty("hibernate.connection.username", properties.get("login"))
//                    .setProperty("hibernate.connection.password", properties.get("pass"));
//
//            try (SessionFactory sessionFactory = configuration.buildSessionFactory();
//                 Session session = sessionFactory.openSession()) {
//
//                JPAController firstVariant = new JPAController(session, properties.get("email"));
//                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
//                    firstVariant.run(bufferedReader);
//                } catch (Exception e) {
//                    logger.error(e.getMessage());
//                    System.out.println(e.getMessage());
//                }
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                System.out.println(e.getMessage());
//            }
//        } else if (properties.get("variant").equals("2")) {
//            String fileName = "operations.csv";
//            Properties props = loadProperties();
//            String url = props.getProperty("url");
//            try (Connection connection = DriverManager.getConnection(url, properties.get("login"), properties.get("pass"));
//                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
//                JDBController secondVariant = new JDBController(connection, properties.get("email"), fileName);
//                secondVariant.run(bufferedReader);
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                System.out.println(e.getMessage());
//            }
//        } else {
//            logger.info("Incorrect variant of task");
//            System.exit(0);
//        }

    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = Application.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }
}
