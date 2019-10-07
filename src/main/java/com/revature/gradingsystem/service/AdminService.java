package com.revature.gradingsystem.service;

import com.revature.gradingsystem.dao.AdminDaoImpl;
import com.revature.gradingsystem.dao.EmployeeDaoImpl;

import java.util.List;

import com.revature.gradingsystem.dao.AdminDao;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.util.MessageConstant;
import com.revature.gradingsystem.validator.EmployeeValidator;

public class AdminService {

	public UserDetails adminLogin(String name, String pwd) throws ServiceException {
		
		AdminDao admindao=new AdminDaoImpl();
		UserDetails userdetail = null;
		
		try {
			userdetail=admindao.findAdminByNamePassword(name, pwd);
			if (userdetail == null) {
				throw new ServiceException(MessageConstant.INVALID_INPUT);
			}
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		return userdetail;
		
	}

	public void updateScoreRangeService(String grade, int min, int max) throws ServiceException {

		AdminDao admindao = new AdminDaoImpl();
		try {
			admindao.updateScoreRange(grade, min, max);
			
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<ScoreRange> viewScoreRangeService() throws ServiceException {

		AdminDao admindao = new AdminDaoImpl();
		List<ScoreRange> list = null;
		
		try {
			list = admindao.viewScoreRange();
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return list;
	}
	
	public void deleteScoreRangeService() throws DBException {

		AdminDao admindao = new AdminDaoImpl();
		
		try {
			admindao.deleteScoreRange();
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public void addEmployeeService(UserDetails user) throws ServiceException {

		EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
		EmployeeValidator employeeValidator = new EmployeeValidator();
		try {
			employeeValidator.addedEmployeeValidation(user);
			employeeDao.addEmployee(user);
			
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
