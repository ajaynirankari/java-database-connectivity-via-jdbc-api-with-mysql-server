package com.gl.JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class JdbcDemoApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Start the Testing of MySQL from Core Java via the JDBC Driver");

        /**
         * Basic steps to set up MySQL for Testing of the JDBC Driver
         * ----------------------------------------------------------
         * CREATE DATABASE jdbc_demo;
         * CREATE USER 'jdbcUser'@'%' IDENTIFIED BY 'jdbcUser';
         * GRANT ALL ON jdbc_demo.* TO 'jdbcUser'@'%';
         * ----------------------------------------------------------
         * CREATE TABLE person(id int, name VARCHAR(20), age int, city text);
         * INSERT INTO person (id, name, age, city) VALUES(100,'Sam',45,'Toronto');
         * SELECT * FROM person;
         * SELECT * FROM person WHERE id = 103;
         * UPDATE person SET age = age + 1;
         * DELETE FROM person WHERE id = 103;
         */

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "jdbcUser", "jdbcUser")) {

            Statement statement = connection.createStatement();

            System.out.println("-------------------------------------------------------------");

            statement.execute("INSERT INTO person (id, name, age, city) VALUES (101,'Marry',50,'Canada')");
            statement.execute("INSERT INTO person (id, name, age, city) VALUES (102,'John',35,'India')");
            statement.execute("INSERT INTO person (id, name, age, city) VALUES (103,'Tim',46,'USA')");

            System.out.println("-------------------------------------------------------------");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city");
                System.out.println(id + ", " + name + ", " + age + ", " + city);
            }
            System.out.println("-------------------------------------------------------------");

            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM person WHERE id = 103");
            while (resultSet1.next()) {
                int id = resultSet1.getInt("id");
                String name = resultSet1.getString("name");
                int age = resultSet1.getInt("age");
                String city = resultSet1.getString("city");
                System.out.println(id + ", " + name + ", " + age + ", " + city);
            }
            System.out.println("-------------------------------------------------------------");

            statement.execute("UPDATE person SET age = age + 2 WHERE id = 103");

            System.out.println("-------------------------------------------------------------");
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM person WHERE id = 103");
            while (resultSet2.next()) {
                int id = resultSet2.getInt("id");
                String name = resultSet2.getString("name");
                int age = resultSet2.getInt("age");
                String city = resultSet2.getString("city");
                System.out.println(id + ", " + name + ", " + age + ", " + city);
            }
            System.out.println("-------------------------------------------------------------");

            statement.execute("DELETE FROM person WHERE id = 103");

            System.out.println("-------------------------------------------------------------");
        }

        JDBCConnector.deleteById(101);
        JDBCConnector.deleteById(102);
        System.out.println("-------------------------------------------------------------");

        Person person1 = new Person(301, "Reran", 24, "Canada");
        Person person2 = new Person(302, "Samra", 45, "India");
        Person person3 = new Person(303, "Marry", 56, "USA");

        JDBCConnector.save(person1);
        JDBCConnector.save(person2);
        JDBCConnector.save(person3);
        System.out.println("-------------------------------------------------------------");

        List<Person> personList = JDBCConnector.findAll();
        personList.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------");

        Person person = JDBCConnector.findById(302);
        System.out.println("person = " + person);
        System.out.println("-------------------------------------------------------------");

        person.setName(person.getName() + "_UpdatedName");
        person.setAge(person.getAge() + 1);
        person.setCity(person.getCity() + "_UpdatedCity");
        JDBCConnector.update(person);

        person = JDBCConnector.findById(302);
        System.out.println("updated person = " + person);
        System.out.println("-------------------------------------------------------------");

        System.out.println("JDBCConnector.findAll().size() = " + JDBCConnector.findAll().size());
        JDBCConnector.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------------------");

        JDBCConnector.deleteById(301);
        JDBCConnector.deleteById(302);
        JDBCConnector.deleteById(303);
        System.out.println("-------------------------------------------------------------");

        System.out.println("JDBCConnector.findAll().size() = " + JDBCConnector.findAll().size());
        JDBCConnector.findAll().forEach(System.out::println);
    }
}
