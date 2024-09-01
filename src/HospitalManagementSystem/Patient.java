package HospitalManagementSystem;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class Patient {

	
	private Connection connection;
	private Scanner scanner;
	
	
	public Patient(Connection connection, Scanner scanner){
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public  void addPatient() {
		System.out.println("Enter patient NAME: ");
		String name = scanner.next();
		System.out.println("Enter patient AGE: ");
		int age = scanner.nextInt();
		System.out.println("Enter patient GENDER: ");
		String gender = scanner.next();
		
		try {
			String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
			PreparedStatement preparedstatements = connection.prepareStatement(query);
			preparedstatements.setString(1, name);
			preparedstatements.setInt(2, age);
			preparedstatements.setString(3, gender);
			int affectdRows = preparedstatements.executeUpdate();
				if(affectdRows>0) 
					System.out.println("patient added successfully");
				
				
				else 
					System.out.println("failed to add patient");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void viewPatient (){
		String query = "SELECT * from patients";

		try {
			PreparedStatement preparedstatements = connection.prepareStatement(query);
			ResultSet resultSet = preparedstatements.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+----+--------+-----+--------+");
			System.out.println("|id  |name    |age  |gender  |");
			System.out.println("+----+--------+-----+--------+");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				System.out.printf("|%-4s|%-8s|%-5s|%-8s|\n", id, name, age, gender);

				System.out.println("+----+--------+-----+--------+");

				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getPatentbyID(int id) {
		String query = "SELECT * FROM PATIENTS WHERE id = ?";
		try {
			PreparedStatement preparedstatements = connection.prepareStatement(query);
			preparedstatements.setInt(1, id);
			ResultSet resultSet = preparedstatements.executeQuery();
			if(resultSet.next())
				return true;
			else
				return false;
			
			} catch (Exception e) {
				e.printStackTrace();
		}
		return false;
	}

}
 