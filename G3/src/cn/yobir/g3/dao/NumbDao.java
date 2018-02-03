package cn.yobir.g3.dao;

import java.sql.SQLException;
import java.util.List;

import cn.yobir.g3.model.Numb;


public interface NumbDao {
	public List<Numb> findSameNumb(int count)throws SQLException;
	public List findNumb(int count) throws SQLException;
	
	public boolean  addNumb(Numb numb) throws SQLException;
}
