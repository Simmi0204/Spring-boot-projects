package com.example.springjspdemo.dao.impl;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
	
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.springjspdemo.constants.Constants;
import com.example.springjspdemo.controller.bean.User;
import com.example.springjspdemo.dao.UserDao;
	
import jakarta.annotation.PostConstruct;
@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao{
	
	@Autowired
	DataSource dataSource;
	@PostConstruct
	public void initialize()//based on app.properties
	{
		setDataSource(dataSource);
	}		
	@SuppressWarnings("deprecation")
	@Override
	public User getUserById(String userId) {//actual querying
		String sql="select cast(aes_decrypt(unhex(user_pswd),'secret') as char(50)), role_id from user join user_role on user_role.user_id = user.id where user.user_id=?";
	//String sql = "SELECT CAST(AES_DECRYPT(UNHEX(`user_pswd`), 'secret') AS CHAR(50)) FROM `user` WHERE user_id=?";

	//just a string
//	String sql = "SELECT user_pswd FROM users WHERE user_id=?";
		return getJdbcTemplate().queryForObject(sql, new Object[] { userId }, new RowMapper<User>(){
			@Override
			public User mapRow (ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(userId);
				user.setPassword(rs.getString(1));
		//		user.setPassword(rs.getString("user_pswd"));
				user.setRoleId(rs.getLong(2));
				return user;
				}
		});
	}

	@Override
	public int createNewUser(User user) {
		String sql1 = "insert into user(user_id, user_pswd) values(?,hex(aes_encrypt(?,'secret')))";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
				PreparedStatement ps = con.prepareStatement(sql1, new String[] {"id"});
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());
				return ps;
			}
		},keyHolder);
	
		Long id = keyHolder.getKey().longValue();
		String sql2 = "insert into user_role(role_id, user_id) values(?,?)";
		return getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
				PreparedStatement ps = con.prepareStatement(sql2);
				ps.setInt(1, Constants.ROLE_EMPLOYEE);
				ps.setLong(2, id);
				return ps;
			}
		});
	}
	@Override
	public List<User> getAllUsers() {
		String sql = "select * from user";
		return getJdbcTemplate().query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong(1));
				user.setUserId(rs.getString(2));
				return user;
			}
			
		});
	}
}