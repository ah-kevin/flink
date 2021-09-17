package com.bjke.clickhouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * -- create database if not exists werewolf engine =MySQL('47.117.114.66:3306','werewolf','root','890728');
 * show databases;
 * create table player_operation_records engine=Log as select * from mysql('47.117.114.66:3306','werewolf','player_operation_records','root','890728');
 * create table record_details engine=Log as select * from mysql('47.117.114.66:3306','werewolf','record_details','root','890728');
 */
public class ClickHouseJDBCApp {
    public static void main(String[] args) throws Exception {
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        String url = "jdbc:clickhouse://47.117.114.66:8123/werewolf";
        Connection connection = DriverManager.getConnection(url, "default", "890728");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from player_operation_records");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("player_name");
            System.out.println(id + "===>" + name);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
