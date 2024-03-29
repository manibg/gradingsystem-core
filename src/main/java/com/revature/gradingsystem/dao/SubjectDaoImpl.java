package com.revature.gradingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.Subject;
import com.revature.gradingsystem.util.ConnectionUtil;
import com.revature.gradingsystem.util.MessageConstant;

public class SubjectDaoImpl {

	public List<Subject> findAll() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Subject> list = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id, sub_code, subject_name from subject";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<Subject>();
			while (rs.next()) {

				list.add(new Subject(rs.getInt("id"), rs.getString("sub_code"), rs.getString("subject_name")));

			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_SUBJECTS, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return list;
	}

}
