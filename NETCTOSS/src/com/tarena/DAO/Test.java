package com.tarena.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.po.Role_info;

public class Test {
	public static void main(String[] args) {
		Role_infoDAOimpl ri = new Role_infoDAOimpl();
		try {
			System.out.println(ri.howManyPage(10));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
