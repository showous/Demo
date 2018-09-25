package com.example.demo.mapper;

import com.example.demo.entity.Login;
import com.example.demo.entity.Person;

public interface PersonDao {

	Person selectPersonById(int id);
	int insertPerson(Person person);
	int selectCount();
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
    
    int validLogin(String loginname,String password);
}
