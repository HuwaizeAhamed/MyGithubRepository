package com.spring.rest.multipart.filedownload.repositorydao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MultipartFileDownload_Repository {

	public List<String> getAllDatas(String location, String filetype) throws ClassNotFoundException, SQLException {

		String response = null;
		List<String> daoResponse = new LinkedList<>();
		String sql = "select * from filecontentconsumer where location = '" + location + "'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} finally {
			System.out.println("RepoDao final");
		}

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/local_mysqldb", "root",
				"Huwaize@123#")) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String age = rs.getString("age");
				String company = rs.getString("company");
				String phone = rs.getString("phone");
				String department = rs.getString("department");
				String salary = rs.getString("salary");
				String empLocation = rs.getString("location");

				response = String.join(",", id, name, age, company, phone, department, salary, empLocation);
				daoResponse.add(response);
				if (filetype.equalsIgnoreCase("csv")) {
					daoResponse.add(System.lineSeparator());
				}
			}
		}

		System.out.println(daoResponse);
		return daoResponse;
	}

}
