package com.bit2017.email.test;

import java.util.List;

import com.bit2017.email.dao.EmailDao;
import com.bit2017.email.vo.EmailVo;

public class EmailDaoTest {
	public static void main(String[] args) {
		insertTest();
		
	}

	private static void insertTest() {
		EmailVo emailVo = new EmailVo();
		emailVo.setFirstName("박");
		emailVo.setLastName("해경");
		emailVo.setEmail("haekyung1123@gmail.com");
		
		EmailDao emailDao = new EmailDao();
		emailDao.insert(emailVo);
		
	}

}
