package com.example.demo.service;

import com.example.demo.entity.Login;
import com.example.demo.entity.Person;

/**
 * @author Administrator 用户管理服务接口
 *
 */
public interface PersonService {

	/**
	 * @param id
	 *            通过用户ID查询用户信息
	 * @return
	 */
	Person selectPersonById(Integer id);

	/**
	 * 
	 * @param person
	 *            添加新用户信息
	 * @return
	 */
	int insertPerson(Person person);

	/**
	 * 
	 * @return 查询总记录数
	 */
	int selectCount();

	/***
	 * 
	 * @param login
	 *            检查登录用户合法性
	 * @return
	 */
	int validLogin(String loginname,String password);
}