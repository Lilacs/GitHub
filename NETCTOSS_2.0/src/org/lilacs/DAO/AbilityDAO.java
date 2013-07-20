package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.Ability;

public interface AbilityDAO {

	//得到所有的权限信息
	public abstract List<Ability> getAbilitys();

}