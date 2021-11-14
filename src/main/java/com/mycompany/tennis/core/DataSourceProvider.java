package com.mycompany.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static BasicDataSource datasource;

    public static DataSource getDatesourceProvider(){
        if (datasource==null){
            datasource = new BasicDataSource();
            datasource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            datasource.setUsername("root");
            datasource.setPassword("");

        }
        return datasource;
    }
}
