Before going to run project follow the below steps:

setup sql driver to build path in your IDE
create database and change database name in url which is in HospitalManagementSystem class
after that create tables as follow to ensure smooth configuration
appointments, doctors, patients
appointments 
+------------------+------+------+-----+---------+----------------+
| Field            | Type | Null | Key | Default | Extra          |
+------------------+------+------+-----+---------+----------------+
| id               | int  | NO   | PRI | NULL    | auto_increment |
| patient_id       | int  | NO   | MUL | NULL    |                |
| doctor_id        | int  | NO   | MUL | NULL    |                |
| appointment_date | date | NO   |     | NULL    |                |
+------------------+------+------+-----+---------+----------------+
doctors
+----------------+--------------+------+-----+---------+----------------+
| Field          | Type         | Null | Key | Default | Extra          |
+----------------+--------------+------+-----+---------+----------------+
| id             | int          | NO   | PRI | NULL    | auto_increment |
| name           | varchar(255) | NO   |     | NULL    |                |
| specialization | varchar(255) | NO   |     | NULL    |                |
+----------------+--------------+------+-----+---------+----------------+
patients
+--------+--------------+------+-----+---------+----------------+
| Field  | Type         | Null | Key | Default | Extra          |
+--------+--------------+------+-----+---------+----------------+
| id     | int          | NO   | PRI | NULL    | auto_increment |
| name   | varchar(255) | NO   |     | NULL    |                |
| age    | int          | NO   |     | NULL    |                |
| gender | varchar(10)  | NO   |     | NULL    |                |
+--------+--------------+------+-----+---------+----------------+

after this all setup done, use your username and password of database


