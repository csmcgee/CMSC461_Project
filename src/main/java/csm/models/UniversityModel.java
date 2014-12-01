package csm.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import csm.utilities.DAO;

public class UniversityModel {
  final public static int ID = 1;
  final public static int NAME = 2;;
  public static ArrayList<String> getUniversities(){
    ResultSet rs = null;
    ArrayList<String> list = new ArrayList<String>();
    try {
      rs = DAO.getInstance().selectAllFrom("university");
      while(rs.next()){
        list.add(rs.getString(NAME));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return list;
  }
  
  public static String getUniversityByID(int id){
    ResultSet rs = null;
    try {
      rs = DAO.getInstance().selectAllFromWhere("university", "id = " + id);
      if(rs.next()){
        return rs.getString(NAME);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public static int getUniversityIDByName(String name){
    ResultSet rs = null;
    try {
      rs = DAO.getInstance().selectAllFromWhere("university", "name = \"" + name + "\"");
      if(rs.next()){
        return rs.getInt(ID);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return -1;
  }

}
