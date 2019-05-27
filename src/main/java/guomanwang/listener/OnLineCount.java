package guomanwang.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import guomanwang.domain.Peoplenum;
import guomanwang.domain.User;
import guomanwang.exception.BusinessException;
import guomanwang.exception.ErrorType;

/**
 * Application Lifecycle Listener implementation class OnLineCount
 *  在线人数统计，将值存放到application范围，
 *
 */
@Component
@WebListener
public class OnLineCount extends Object implements HttpSessionListener {
       
    /**
     * @see Object#Object()
     */
	static String dri="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/cartoon";
	static String user="root";
	static String pwd="123456";
	
	private int realtimecount = 0;//实时在线量
	long startTime;
	private Peoplenum peoplenum = new Peoplenum();
    public OnLineCount() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @Override
	public void sessionCreated(HttpSessionEvent se)  { 
		 startTime = 0;
		 realtimecount++;
		 se.getSession().getServletContext().setAttribute("onlinecount", realtimecount);
		 startTime = System.currentTimeMillis();
		 peoplenum.setAccesstime( new java.util.Date());
		 System.out.println("peopelnum -----------====================================create" + realtimecount + peoplenum.getAccesstime());
		 
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    @Override
	public void sessionDestroyed(HttpSessionEvent se)  { 
    	 realtimecount--;
    	 long endTime = System.currentTimeMillis();
         se.getSession().getServletContext().setAttribute("onlinecount", realtimecount);
         peoplenum.setLefttime( new java.util.Date());
         double holdTime = (endTime - startTime)/1000;
         peoplenum.setHoldtime( holdTime);
         if( se.getSession().getAttribute("user") != null) {
        	 User person = (User)se.getSession().getAttribute("user");
        	 peoplenum.setUserid( person.getUserid());
         }
         try {
			insertPeoplenum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         System.out.println("peopelnum -----------====================================destory" + realtimecount + peoplenum.getAccesstime());
    }
    public static Connection connectDB() {
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
    public void insertPeoplenum() throws Exception {
    	Connection con;
		PreparedStatement psql;
		String sql;
		con=OnLineCount.connectDB();
		if( peoplenum.getUserid() == null) {
       	 sql = "insert into peoplenum(accesstime,lefttime,holdtime) VALUES(?,?,?)";
        }else {
       	 System.out.println("user==============" + peoplenum.getUserid());
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
			System.out.println(rs + "插入peoplenum表的结果");
		}
		catch(SQLException e) {
			System.out.println(e);
			throw new BusinessException(ErrorType.DATABASE_INSERT_ERROR,"peoplenum数据库插入异常");
		}
    }

}
