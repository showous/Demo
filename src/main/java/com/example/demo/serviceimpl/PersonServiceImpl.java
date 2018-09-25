package com.example.demo.serviceimpl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.entity.Person;
import com.example.demo.mapper.PersonDao;
import com.example.demo.service.PersonService;


@Service("personService")
public class PersonServiceImpl implements PersonService {

	private PersonDao personDao;
	public PersonDao getStudentDao() {
		return personDao;
	}
    @Resource
	public void setStudentDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public Person selectPersonById(Integer id) {
		
		// TODO 自动生成的方法存根
		return personDao.selectPersonById(id);
	}
	
	public int insertPerson(Person person) {
		// TODO 自动生成的方法存根
		return personDao.insertPerson(person);
	}
	
	public int selectCount() {
		// TODO 自动生成的方法存根
		return personDao.selectCount();
	}

	public int validLogin(String loginname,String password) {
		// TODO 自动生成的方法存根
		return personDao.validLogin(loginname,password);
	}



}
