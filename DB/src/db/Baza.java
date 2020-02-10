package db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jdi.connect.spi.Connection;

import beans.User;

public class Baza extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter pw = resp.getWriter();
	resp.setContentType("text/plain;charset=utf-8");
	UsersDAO db=new UsersDAO();
	List<User> all = db.getALL();
	for(User  u : all) {
		pw.println(u.getImie() + "  " + u.getNazwisko() + "\n");
	}
	try {
		db.addUser("wacek", "placek");
		pw.println("dodano" );
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	
	
	

}
}
