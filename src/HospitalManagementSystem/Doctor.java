package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	private Connection connection;
	private Scanner scanner;
	
	
	public Doctor(Connection connection){
		this.connection = connection;
	}
	
	/*public  void addPatient() {
		System.out.println("Enter patient NAME: ");
		String name = scanner.next();
		System.out.println("Enter patient AGE: ");
		int age = scanner.nextInt();
		System.out.println("Enter patient GENDER: ");
		int gender = scanner.nextInt();
		
		try {
			String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
			PreparedStatement preparedstatements = connection.prepareStatement(query);
			preparedstatements.setString(1, name);
			preparedstatements.setInt(1, age);
			preparedstatements.setInt(1, gender);
			int affectdRows = preparedstatements.executeUpdate();
				if(affectdRows>0) 
					System.out.println("patient added successfully");
				
				
				else 
					System.out.println("failed to add patient");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}*/
	//
	public void viewDoctor (){
		String query = "SELECT * from doctors";

		try {
			PreparedStatement preparedstatements = connection.prepareStatement(query);
			ResultSet resultSet = preparedstatements.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+-----------+--------+--------------+");
			System.out.println("|doctor id  |name    |specialization|");
			System.out.println("+-----------+--------+--------------+");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String specialization = resultSet.getString("specialization");

				System.out.printf("|%-11s|%-8s|%-15s|\n", id, name, specialization);

				System.out.println("+-----------+--------+--------------+");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getDoctorID(int id) {
		String query = "SELECT * FROM DOCTORS WHERE id = ?";
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
