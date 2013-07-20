package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.Host;

public interface HostDAO {
	public List<Host> getAll() throws Exception;
}