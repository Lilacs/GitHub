package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Service;

public interface ServiceDAO {
	public int getPageValues(int rows) throws Exception;

	public List<Service> findAll(int rowPage, int page) throws Exception;

	public int creatUser(Service service) throws Exception;

	/**
	 * �������������Ϣ
	 */
	public List<Service> getList(String osName, String unitHost, String idCard,
			int status,  int pageValue, int MAX_PAGE) throws SQLException;
	/*
	 * ��ѯҳ��
	 */
	public int getpageCount(String osName, String unitHost, String idCard,
			int status, int MAX_PAGE)throws SQLException;
	/**
	 * ����һ��ҵ���˺���Ϣ
	 */
	public boolean createInfo(Service service)throws SQLException;
	
	/*
	 * Ԥ��һ���û�����Ϣ
	 */
	public Service getInfoByid(int id)throws SQLException;
	/*
	 * �����û����ʷ�����
	 */
	public boolean modiCostType(int costType,int id)throws SQLException;
	
	/*
	 * ɾ��һ���û�ҵ����Ϣ(��ʵ��ɾ��,����״̬Ϊ2)
	 */
	public boolean delete(int id)throws SQLException;
	
	/*
	 * ��ͨ
	 */
	public boolean open(int id)throws SQLException;
	
	/*
	 * ��ͣ
	 */
	public boolean pause(int id)throws SQLException;
}
