# java-database-connectivity-via-jdbc-api-with-mysql-server

Create new database
-------------------
```
CREATE DATABASE jdbc_demo;
CREATE USER 'jdbcUser'@'%' IDENTIFIED BY 'jdbcUser';
GRANT ALL ON jdbc_demo.* TO 'jdbcUser'@'%';
```

Create new table
----------------
```
CREATE TABLE person(id int, name VARCHAR(20), age int, city text);
```

Basic SQL query
---------------
```
INSERT INTO person (id, name, age, city) VALUES(103,'Sam',45,'Tornto');
SELECT * FROM person;
SELECT * FROM person WHERE id = 103;
UPDATE person SET age = age + 1;
DELETE FROM person WHERE id = 103;
```

Java code to connect MySQL server
---------------------------------
```
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
```


