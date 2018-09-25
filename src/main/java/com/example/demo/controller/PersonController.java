package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;



@RestController
@EnableAutoConfiguration
@RequestMapping("/person")
@Api(tags = "PersonController", description = "用户信息")
@CrossOrigin
public class PersonController {
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	@Autowired
	PersonService personService;
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	@ApiOperation(value="获取用户详细信息", notes="根据Id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "id={id}", method = RequestMethod.GET)
	public ResponseData<Person> getPersonById (@PathVariable(value = "id") Integer id){
		ResponseData<Person> data =new  ResponseData<Person>();
		try
		{
			Person person = personService.selectPersonById(id);
			data.setData(person);
		}catch(Exception e)
		{
			data.setErrormessage(e.getMessage());
			logger.error(e.getMessage());
		}
		return data;
	}

	@ApiOperation(value="录入用户详细信息", notes="添加新用户")
	@RequestMapping(value = "insertPerson", method = RequestMethod.POST)
	public  ResponseData<Boolean> insertPerson (@RequestBody  Person person){
		ResponseData<Boolean> data =new ResponseData<Boolean>();
		int personAdd=-1;
		try
		{
			System.out.println(person.getName());
			 personAdd = personService.insertPerson(person);
			 if(personAdd==1)
			 {
				 data.setData(true);
			 }else
			 {
				 data.setData(false);
			 }
			
		}catch(Exception e)
		{
			data.setErrormessage(e.getMessage());
			logger.error(e.getMessage());
		}
		return data;
	}
	
	@ApiOperation(value="用户总数", notes="用户")
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public ResponseData<Integer> selectCount (){
		ResponseData<Integer> data =new ResponseData<Integer>();
		try
		{
		int personCount = personService.selectCount();
		data.setData(personCount);
		data.setErrorcode(-1);
		data.setErrormessage("none");
		}catch(Exception e)
		{
			data.setErrormessage("错误："+e.getMessage());
			logger.error(e.getMessage());
		}
		return data;
	}
	@ApiOperation(value="用户合法性", notes="数据库对比")
	@RequestMapping(value = "validLogin", method = RequestMethod.GET)
	public ResponseData<Integer> validLogin (
			@RequestParam(value = "loginname", required = false) String loginname,
			@RequestParam(value = "password", required = false) String password){
		ResponseData<Integer> data =new ResponseData<Integer>();
		try
		{
			int obj = personService.validLogin(loginname,password);
		    data.setData(obj);
		    data.setErrorcode(-1);
		    data.setErrormessage("none");
		}catch(Exception e)
		{
			data.setErrormessage(e.getMessage());
			logger.error(e.getMessage());
		}
		return data;
	}
}