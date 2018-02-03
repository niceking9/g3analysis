package cn.yobir.g3.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.yobir.g3.dao.NumbDao;
import cn.yobir.g3.model.Numb;

public class NumbDaoimp implements NumbDao {

	private Connection conn = cn.yobir.g3.util.Dhelper.getConnection();
	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	@Override
	public List<Numb> findSameNumb(int count) throws SQLException {
		String sql = "SELECT * FROM  numb  	WHERE sum REGEXP '[0-9]{2}' LINIT " + count + ";";
		PreparedStatement pstmt = null;
		List list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {

			e.printStackTrace();
			logger.fatal("sql statement is incorrent");
		}
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Numb numb = new Numb();
			numb.setId(rs.getInt("id"));
			numb.setMark(rs.getString("mark"));
			numb.setNum(rs.getString("num"));
			numb.setDate(rs.getString("date"));
			list.add(numb);

		}
		return list;
	}

	@Override
	public List findNumb(int count) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNumb(Numb numb) throws SQLException {
	  String sql="INSERT INTO numb ("+"NULL"+numb.getMark()+","+numb.getNum()+","+numb.getDate()+");";
	 PreparedStatement psmt=conn.prepareStatement(sql);
		return   psmt.execute();
	}
}
