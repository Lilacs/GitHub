package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.Service;

public interface ServiceDAO {

	//����һ��ҵ���˺�
	public abstract boolean create(Service service);
	
	//ɾ��һ��ҵ���˺�,ֻ�����ó�2״̬
	public boolean delete(int id) throws Exception;
	
	// ����id��ѯ�����û���Ϣ
	public Service getServiceById(int id) throws Exception;
	
	//����һ����Ϣ
	public boolean update(Service service) throws Exception;
	
	//��ͨҵ���˺�
	public boolean openStatus(int id)throws Exception;
	
	//��ͣҵ���˺�(����״̬Ϊ1,������ͣʱ��)
	public boolean pauseStatus(int id)throws Exception;
		
	//��ҳ��ѯ
	public List<Service> getServiceList(String osUserName, String unixHost,
			String idCardNo, String status, int page, int MAX_PAGE)
			throws Exception;
	
	//����������ѯҳ��
	public int getPageCount(String osUserName, String unixHost,
			String idCardNo, String status, int MAX_PAGE) throws Exception;
}