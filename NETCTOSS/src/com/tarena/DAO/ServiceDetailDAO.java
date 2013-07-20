package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.ServiceDetail;

public interface ServiceDetailDAO {

	public int createDetail(ServiceDetail serviceDetail) throws Exception;
	public void createDetail(List<ServiceDetail> list) throws Exception;
	public List<ServiceDetail> findAll()throws Exception;
}
