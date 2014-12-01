package csm.models;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.google.common.base.Joiner;

import csm.utilities.DAO;

public class StudentModel extends AbstractTableModel {
  
  final static String displayFields[] = {"id", "first_name", "last_name", "email", "street_num", "street_name", "city", "state", "zip", "telephone", "status", "year", "birth_date"};
  final static String selectSql = "person left join university_student on person.id = university_student.student_id natural join student";
  final static String fields[] = {"first_name", "last_name", "email", "street_num",
                                  "street_name", "city", "state", "zip", "telephone"};
  
  final static String university_student_fields[] = {"university_id", "student_id", "status", "year"};
  
  final public static int FIRST_NAME = 2;
  final public static int LAST_NAME = 3;
  final public static int EMAIL = 4;
  final public static int STREET_NUM = 5;
  final public static int STREET_NAME = 6;
  final public static int CITY = 7;
  final public static int STATE = 8;
  final public static int ZIP = 9;
  final public static int TELEPHONE = 10;
  final public static int UNIVERSITY_ID = 11;
  final public static int STUDENT_ID = 12;
  final public static int STATUS = 13;
  final public static int YEAR = 14;
  final public static int BIRTH_DATE = 15;
  
  
  public static final int ID = 0;
  
  public StudentModel(){
    super();
  }

  public int getColumnCount() {
    int count = 0;
    try {
      DAO db = DAO.getInstance();
      ResultSet rs = db.selectSomeFrom(selectSql, displayFields);
      count = rs.getMetaData().getColumnCount();
      rs.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return count;
  }

  public int getRowCount() {
    int count = 0;
    try {
      DAO db = DAO.getInstance();
      ResultSet rs = db.selectSomeFrom(selectSql, displayFields);
      while(rs.next()){
        count++;
      }
      rs.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return count;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    DAO db = DAO.getInstance();
    try {
      ResultSet rs = db.selectSomeFrom(selectSql, displayFields);
      for(int i = 0; rs.next(); i++){
        if(i == rowIndex){
          return rs.getObject(columnIndex+1);
        }
      }
      rs.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }
  
  public String getColumnName(int column){
    DAO db = DAO.getInstance();
    try {
      ResultSet rs = db.selectSomeFrom(selectSql, displayFields);
      return db.getColumns(rs).get(column);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static void deleteStudent(int id){
    DAO db = DAO.getInstance();
    try {
      db.deleteFromWhere("person", "id = " + id);
    } catch (SQLException e){
      e.printStackTrace();
    }
  }
  
  public static ResultSet getStudent(int id) throws SQLException{
    return DAO.getInstance().selectAllFromWhere(selectSql, "id = " + id);
  }
  
  public static void insertStudent(String[] values, String birthDate, String[] universityValues, ArrayList<Integer> majors) throws SQLException{
    Connection con = DAO.getInstance().getConnection();
    try {
      Statement stmt = null;
      stmt = con.createStatement();
      Joiner fJoin = Joiner.on(", ");
      Joiner vJoin = Joiner.on("\", \"");
      String sql = "INSERT INTO person(" + fJoin.join(fields) 
          + ") VALUES (\"" + vJoin.join(values) + "\");";
      stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet rs = stmt.getGeneratedKeys();
      int studentId = 0;
      if(rs.next()){
        studentId = rs.getInt(1);
      }
      
      stmt = con.createStatement();
      sql = "INSERT INTO student(id, birth_date) VALUES(LAST_INSERT_ID(), \"" + birthDate
          + "\");";
      stmt.executeUpdate(sql);
      
      stmt = con.createStatement();
      sql = "INSERT INTO university_student(" + fJoin.join(university_student_fields) 
          + ") VALUES (\"" + universityValues[0] +"\", LAST_INSERT_ID(), \""+ universityValues[2] +"\", \"" 
          + universityValues[3] + "\");";
      stmt.executeUpdate(sql);
      
      stmt = con.createStatement();
      sql = "DELETE FROM student_major where student_id = " + studentId + ";";
      stmt.executeUpdate(sql);
      
      for(Integer major: majors){
        stmt = con.createStatement();
        sql = "INSERT INTO student_major(student_id, major_id)"
            + " VALUES(\"" + studentId + "\",\"" + major + "\");";
        stmt.executeUpdate(sql);
      }
      
      con.commit();
    } catch (SQLException e) {
      con.rollback();
      e.printStackTrace();
    }
    
  }
  
  public static void updateStudent(int studentId, String[] values, String birthDate, String[] universityValues, ArrayList<Integer> majors) throws SQLException{
    Connection con = DAO.getInstance().getConnection();
    try{
      Statement stmt = null;
      stmt = con.createStatement();
      
      String sql = "UPDATE person SET ";
      for(int i = 0; i < values.length; i++){
        sql += fields[i] + "= \"" + values[i] + "\"";
        if((i+1) < values.length){
          sql += ", ";
        }
      }
      sql += " where id = " + studentId + ";";
      stmt.executeUpdate(sql);
      
      stmt = con.createStatement();
      sql = "UPDATE student SET birth_date = \"" + birthDate + "\" where id = " + studentId + ";";
      stmt.executeUpdate(sql);
      
      stmt = con.createStatement();
      sql = "UPDATE university_student SET ";
      for(int i = 0; i < universityValues.length; i++){
        sql += university_student_fields[i] + "= \"" + universityValues[i] + "\"";
        if((i+1) < universityValues.length){
          sql += ", ";
        }
      }
      sql += " where student_id = " + studentId + ";"; 
      stmt.executeUpdate(sql);
      
      stmt = con.createStatement();
      sql = "DELETE FROM student_major where student_id = " + studentId + ";";
      stmt.executeUpdate(sql);
      
      for(Integer major: majors){
        stmt = con.createStatement();
        sql = "INSERT INTO student_major(student_id, major_id)"
            + " VALUES(\"" + studentId + "\",\"" + major + "\");";
        stmt.executeUpdate(sql);
      }
      
      con.commit();
    }catch (SQLException e){
      con.rollback();
      e.printStackTrace();
    }
  }
  
  public static ArrayList<String> getStudentMajors(int studentId){
    ResultSet rs;
    ArrayList<String> list = new ArrayList<String>();
    try {
      rs = DAO.getInstance().selectAllFromWhere("student_major full join major on major_id = major.id", "student_id = " + studentId);
      while(rs.next()){
        list.add(rs.getString(4));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public static ArrayList<String> getAllMajors(){
    ResultSet rs;
    ArrayList<String> list = new ArrayList<String>();
    try {
      rs = DAO.getInstance().selectAllFrom("major ORDER BY name ASC");
      while(rs.next()){
        list.add(rs.getString(2));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public static int[] getStudentMajorIndices(int studentId){
    ArrayList<String> all = getAllMajors();
    ArrayList<String> some = getStudentMajors(studentId);
    ArrayList<Integer> indicesList = new ArrayList<Integer>();
    for(String major: some){
      indicesList.add(all.indexOf(major));
    }
    int[] indices = new int[indicesList.size()];
    for(int i=0; i < indices.length; i++){
      indices[i] = indicesList.get(i);
    }
    return indices;
  }
  
  public static int getMajorIdByName(String name){
    int retVal = -1;
    try {
      Connection con = DAO.getInstance().getConnection();
      Statement stmt = null;
      stmt = con.createStatement();
      String sql = "SELECT id from major where name = \"" + name + "\";";
      ResultSet rs = stmt.executeQuery(sql);
      if(rs.next()){
        retVal = rs.getInt(1); 
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return retVal;
  }
  
}
