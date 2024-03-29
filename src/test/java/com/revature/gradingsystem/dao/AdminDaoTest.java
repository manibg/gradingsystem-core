package com.revature.gradingsystem.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.exception.DBException;

public class AdminDaoTest {

	@Test
	public void testValidLogin() {

		String name = "Mani";
		String password = "pass123";
		AdminDao admindao = new AdminDaoImpl();
		UserDetails userdetail = null;
		try {
			userdetail = admindao.findAdminByNamePassword(name, password);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(userdetail);
	}
	
	@Test
	public void testInValidLogin() {

		String name = "";
		String password = "";
		AdminDao admindao = new AdminDaoImpl();
		UserDetails admin = null;
		try {
			admin = admindao.findAdminByNamePassword(name, password);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNull(admin);
	}
	@Test
	public void testInValidUpdateScoreRange() throws SQLException {

		String grade="";
		int min = 0;
		int max = 0;
		AdminDao admindao = new AdminDaoImpl();
		int rows = 0;
		try {
			rows = admindao.updateScoreRange(grade, min, max);
			assertEquals(0, rows);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testValidViewRange() {
		
		AdminDao admindao = new AdminDaoImpl();
		List<ScoreRange> list = null;
		try {
			list = admindao.viewScoreRange();
			
			assertNotNull(list);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}
		
	
}