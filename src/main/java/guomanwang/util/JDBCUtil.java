package guomanwang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import guomanwang.domain.Peoplenum;
import guomanwang.exception.BusinessException;
import guomanwang.exception.ErrorType;

public class JDBCUtil {

	
	static String dri="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/cartoon?autoReconnect=true";
	static String user="root";
	static String pwd="123456";

	PreparedStatement psql;
	
	public String[] splitStringBy( String str) {
		String[] result = null;
		if( str != null)
		  result = str.split(";");
		return result;
	}
    public Connection connectDB() {
 	   Connection con=null;
 	   try {
 		   Class.forName(dri);  
 	   }
 	   catch(Exception e) { 
 		   System.out.println(e);
 	   }
 	   try {
 		   con=DriverManager.getConnection(url,user,pwd);
 	   }
 	   catch(SQLException e) {
 		   System.out.println("数据库连接异常");
     }
      return con;
	}
    public String selectAccessById( int accessId) throws Exception {
    	ResultSet rs = null;
    	String str = null;
    	Connection con;
    	con = connectDB();
    	String sql = "select accesspwd from access where id = ?";
    	try {
    		psql = con.prepareStatement(sql);
    		psql.setObject(1, accessId);
    		rs = psql.executeQuery();
    		if(rs.next())
    		   str = rs.getString(1);
    		con.close();
    	}catch (Exception e) {
    		System.out.println(e);
		}
    	return str;
    }
	public void insertPeoplenum( Peoplenum peoplenum) throws Exception {
		String sql;
		Connection con;
		con = connectDB();
		if( peoplenum.getUserid() == null) {
	   	 sql = "insert into peoplenum(accesstime,lefttime,holdtime) VALUES(?,?,?)";
	    }else {
		 sql = "insert into peoplenum(accesstime,lefttime,holdtime,userid) VALUES(?,?,?,?)";
		}
		if(con==null)
			throw new BusinessException(ErrorType.DATABASE_INSERT_ERROR,"peoplenum数据库插入异常");
		try {
			psql=con.prepareStatement(sql);
			psql.setObject(1, peoplenum.getAccesstime());
			psql.setObject(2, peoplenum.getLefttime());
			psql.setDouble(3, peoplenum.getHoldtime());
			if( peoplenum.getUserid() != null) {
				psql.setInt(4, peoplenum.getUserid());
			}
			int rs =psql.executeUpdate();  
			con.close();
		}
		catch(SQLException e) {
			System.out.println(e);
			throw new BusinessException(ErrorType.DATABASE_INSERT_ERROR,"peoplenum数据库插入异常");
			}
	}
	
}
