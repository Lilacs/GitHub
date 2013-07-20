package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Role_info;

public interface Role_infoDAO {
	//������ɫ
	public boolean create_Role_info(Role_info info,int[] value) throws SQLException;
	//Ajax��ѯ��ɫ�����Ƿ����
	public boolean find_Role_info_name(String name) throws SQLException;
	//�޸Ľ�ɫ��Ϣ
	public boolean modify_Role_info(String newName,String oldName,int[] num) throws SQLException;
	//ɾ����ɫ��Ϣ
	public boolean delete_Role_info(int id) throws SQLException;
	//��ʾ��ɫ
	public List<Role_info> getList(int pageValue, int MAX_PAGE)throws SQLException;
	//��ѯһ������ҳ
	public int howManyPage(int MAX_PAGE)throws SQLException;
	//����id���ҽ�ɫ����
	public String getName(int id)throws SQLException;
	//�������еĽ�ɫ��
	public List<Role_info> getAllInfo()throws SQLException;
	//���ݽ�ɫ���Ʋ�ѯid
	public int getID(String name)throws SQLException;
	/*
	 * ���ݴ����Ľ�ɫ�����ж��Ƿ��Ѿ�����,�ų����Լ�������,�û�������Ϣ
	 */
	public boolean modiName(String name)throws SQLException;

}	

