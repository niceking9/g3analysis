package cn.yobir.bizimp;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import com.bitagentur.chart.JChartLibLineChart;
import com.bitagentur.data.*;
import com.bitagentur.renderer.JChartLibLinechartRenderer;

import cn.yobir.g3.model.Numb;

public class LineChart {

	public static void main(String[] args) throws IOException {
		JChartLibSerie serie1 = new JChartLibSerie("apple");
		// JChartLibSerie serie2 = new JChartLibSerie("orange");
		// JChartLibSerie serie3 = new JChartLibSerie("banlana");
		serie1.addValue("custom", 1);
		serie1.addValue("custom", 1);
		serie1.addValue("custom", 1);
		serie1.addValue("custom", 1);
		serie1.addValue("custom", 1);
		/*
		 * ddValue("custom", 2);
		 * 
		 * serie3.addValue("custom", 3); serie3.addValue(Date date = new Date();
		 * serie2.addValue(date, 2); serie2.addValue("custom", 2);
		 * serie2.addValue("custom", 2); serie2.addValue("custom", 2);
		 * serie2.a"custom", 3); serie3.addValue("custom", 3);
		 * serie3.addValue("custom", 3); serie3.addValue("custom", 3);
		 */
		JChartLibDataSet dataset = new JChartLibDataSet();
		dataset.addDataSerie(serie1);
		/*
		 * dataset.addDataSerie(serie2); dataset.addDataSerie(serie3);
		 */
		JChartLibLineChart chart = new JChartLibLineChart("G3 display", "X-XAis", "Y-XAis", dataset);

		/*
		 * JChartLibLinechartRenderer renderer = new
		 * JChartLibLinechartRenderer(chart); chart.setRender(renderer);
		 * renderer.addXAxisText("aaa"); renderer.addXAxisText("bbb");
		 * renderer.addXAxisText("ccc"); renderer.addXAxisText("ddd");
		 * renderer.addXAxisText("eee");
		 */
		chart.saveAsJPEG("/home/younger/chart.jpeg", 700, 400);

		System.out.print(" is end");
	}

	
}
