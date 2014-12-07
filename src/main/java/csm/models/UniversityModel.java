package csm.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import csm.classes.Course;
import csm.classes.University;
import csm.utilities.DAO;

public class UniversityModel {
	final public static int ID = 1;
	final public static int NAME = 2;;

	public static ArrayList<String> getUniversities() {
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			rs = DAO.getInstance().selectAllFrom("university");
			while (rs.next()) {
				list.add(rs.getString(NAME));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<University> getUniversityList() {
		ResultSet rs = null;
		ArrayList<University> list = new ArrayList<University>();
		try {
			rs = DAO.getInstance().selectAllFrom("university");
			while (rs.next()) {
				list.add(new University(rs.getInt(ID), rs.getString(NAME)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static String getUniversityByID(int id) {
		ResultSet rs = null;
		try {
			rs = DAO.getInstance().selectAllFromWhere("university",
					"id = " + id);
			if (rs.next()) {
				return rs.getString(NAME);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int getUniversityIDByName(String name) {
		ResultSet rs = null;
		try {
			rs = DAO.getInstance().selectAllFromWhere("university",
					"name = \"" + name + "\"");
			if (rs.next()) {
				return rs.getInt(ID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ArrayList<Course> getUniversityCourseList(int id){
		ResultSet rs = null;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			Connection con = DAO.getInstance().getConnection();
			Statement stmt = con.createStatement();
			String sql = "select * from course where id in "
					+ "(select course_id from instructor_teaches natural join "
					+ "university_instructor "
					+ "where university_id = " + id + ");";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				courses.add(new Course(rs.getInt(1), rs.getString(2)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
		
	}
}
