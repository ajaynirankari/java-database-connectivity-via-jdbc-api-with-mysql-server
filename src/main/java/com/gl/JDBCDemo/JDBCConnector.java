package com.gl.JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCConnector {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "jdbcUser", "jdbcUser");
    }

    public static Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public static void save(Person person) throws SQLException {
        getStatement().execute("INSERT INTO person (id,name,age,city) VALUES(" + person.getId() + ",'" + person.getName() + "'," + person.getAge() + ",'" + person.getCity() + "')");
    }

    public static List<Person> findAll() throws SQLException {
        List<Person> personList = new ArrayList<>();
        ResultSet resultSet = getStatement().executeQuery("SELECT * FROM person");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String city = resultSet.getString("city");
            Person person = new Person(id, name, age, city);
            personList.add(person);
        }
        return personList;
    }

    public static Person findById(int id) throws SQLException {
        Person person = null;
        ResultSet resultSet = getStatement().executeQuery("SELECT * FROM person WHERE id = " + id);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String city = resultSet.getString("city");
            person = new Person(id, name, age, city);
        }
        return person;
    }

    public static void deleteById(int id) throws SQLException {
        getStatement().execute("DELETE FROM person WHERE id = " + id);
    }

    public static void update(Person person) throws SQLException {
        getStatement().execute("UPDATE person SET name = '" + person.getName() + "', age = " + person.getAge() + ", city = '" + person.getCity() + "' WHERE id = " + person.getId());
    }
}
