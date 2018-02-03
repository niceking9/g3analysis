package cn.yobir.bizimp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.yobir.g3.biz.MissavgGain;
import cn.yobir.g3.dao.NumbDao;
import cn.yobir.g3.daoimp.NumbDaoimp;
import cn.yobir.g3.model.Numb;
import cn.yobir.g3.util.DoubleAnalysisChartRenderer;

public class MissavgGainimp implements MissavgGain {
	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	private static NumbDao numbdao = new NumbDaoimp();
	private static DoubleAnalysisChartRenderer analysis = new DoubleAnalysisChartRenderer();

	@Override
	public List<Numb> getMissavgList(List<Numb> numbs, int[] gly, int count) {

		List<Numb> rtn = new ArrayList();

		try {
			rtn = analysis.getAnalysisResult(numbdao.findSameNumb(count + gly[2]), gly, count);
		} catch (SQLException e) {
			e.printStackTrace();
		logger.fatal(this.getClass().getMethods().toString()+"is fail");
		}
		return rtn;
	}

}
