package cn.yobir.bizimp;

import java.io.IOException;
import java.util.List;

import cn.yobir.g3.model.Numb;

public class DisplayChart {
	private static LineChart lineChart=new LineChart();
	private static  MissavgGainimp missavgGain=new MissavgGainimp();
	public void doChart(List<Numb> numbs, int[] gly, int count ) throws IOException{
		missavgGain.getMissavgList(numbs, gly, count);
		lineChart.makeJPEG(numbs, gly);

		
	}
	
	

}
