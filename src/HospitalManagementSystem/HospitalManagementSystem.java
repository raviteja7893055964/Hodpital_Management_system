package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class HospitalManagementSystem {

	private static String url = "jdbc:mysql://localhost:3306/hospital";
	private static String username = "root";
	private static String password = "7893055964";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Patient patient = new Patient(connection, scanner);
			Doctor doctor = new Doctor(connection);

			while (true) {
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");

				System.out.println("1. add patient");

				System.out.println("2. view patients");

				System.out.println("3. view doctors");

				System.out.println("4. book appointment");

				System.out.println("5. exit");
				System.out.println("Enter your choice: ");
				int choice = scanner.nextInt();
				switch (choice) {
					case 1:
						// add patient
						patient.addPatient();
						System.out.println();
						break;

					case 2:
						// view patients
						patient.viewPatient();
						System.out.println();
						break;
					case 3:
						// view doctors
						doctor.viewDoctor();
						System.out.println();
						break;

					case 4:
						// book appointment
						bookAppointment(patient, doctor, connection, scanner);
						System.out.println();
						break;
					case 5:
						System.out
								.println("🧑‍⚕️ >> Thank you for using hospital management system🏥, Stay Healthy...,");
						break;
					default:
						System.out.println("Enter valid choice");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
		System.out.println("Enter Patient id: ");
		int patientId = scanner.nextInt();
		System.out.println("Enter Doctor id: ");
		int doctorId = scanner.nextInt();
		System.out.println("Enter appointment date in YYYY-MM-DD format: ");
		String appointmentDate = scanner.next();

		if (patient.getPatentbyID(patientId) && doctor.getDoctorID(doctorId)) {
			if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {
				String appointQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) values(?, ?, ?)";
				try {
					PreparedStatement preparedstatements = connection.prepareStatement(appointQuery);
					preparedstatements.setInt(1, patientId);
					preparedstatements.setInt(2, doctorId);
					preparedstatements.setString(3, appointmentDate);

					int rowsAffected = preparedstatements.executeUpdate();
					if (rowsAffected > 0) {
						System.out.println("appointment booked successfully.");

					} else {
						System.out.println("Failed to book appointments.");

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Doctor not available on this date...");
			}
		} else {
			System.out.println("Either Doctor or Patient does not exist....");
		}

	}

	public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
		String query = "SELECT COUNT(*) from appointments WHERE doctor_id = ? AND appointment_date = ?";

		try {
			PreparedStatement preparedstatements = connection.prepareStatement(query);
			preparedstatements.setInt(1, doctorId);
			preparedstatements.setString(2, appointmentDate);
			ResultSet resultSet = preparedstatements.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				if (count == 0) {
					return true;
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}
}
