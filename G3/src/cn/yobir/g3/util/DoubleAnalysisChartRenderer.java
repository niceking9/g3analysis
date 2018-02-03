
package cn.yobir.g3.util;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import com.bitagentur.chart.JChartLibLineChart;
import com.bitagentur.data.JChartLibDataSet;
import com.bitagentur.data.JChartLibSerie;
import com.bitagentur.renderer.JChartLibLinechartRenderer;

import cn.yobir.g3.model.Numb;

public class DoubleAnalysisChartRenderer {
	@SuppressWarnings("unchecked")
	public List<Numb> getAnalysisMissavgResult(List<Numb> numbs, int[] gly, int count) {
		analysisMissavg(numbs, gly[0]);
		analysisMissavg(numbs, gly[1]);
		analysisMissavg(numbs, gly[2]);
		analysisMiss(numbs);

		if (count + gly[2] < numbs.size()) {
			return numbs.subList(numbs.size() - count, numbs.size());
		} else
			return numbs;

	}

	public void analysisMissavg(List<Numb> numbs, int gly) {

		if (numbs.size() > gly) {
			for (ListIterator ltr = numbs.listIterator(); ltr.hasPrevious();) {
				int endIndex = ltr.previousIndex();
				if (gly < endIndex) {
					Numb endObj = numbs.get(endIndex);
					int endId = endObj.getId();
					int startId = numbs.get(endId - gly).getId();
					String oldStr = endObj.getMissavg();
					String newStr = (endId - startId - gly) / gly + "";
					if (oldStr != null) {
						endObj.setMissavg(oldStr + "," + ((endId - startId - gly) / gly));
					} else {

						endObj.setMissavg("" + ((endId - startId - gly) / gly));
					}
					numbs.set(endIndex, endObj);
				} else
					return;
			}
		}

	}

	public void analysisMiss(List<Numb> numbs) {
		if (numbs.size() > 1) {
			for (ListIterator ltr = numbs.listIterator(); ltr.hasPrevious();) {
				int endIndex = ltr.previousIndex();
				if (1 < endIndex) {
					Numb endObj = numbs.get(endIndex);
					int endId = endObj.getId();
					int startId = numbs.get(endId - 1).getId();

					endObj.setMiss("" + (endIndex - startId));
					numbs.set(endIndex, endObj);
				} else
					return;
			}

		}

	}

	public JChartLibDataSet makeDataset(List<Numb> numbs, int[] gly) {
		JChartLibSerie serie_gly0 = new JChartLibSerie("MISSAVG-" + gly[0]);
		JChartLibSerie serie_gly1 = new JChartLibSerie("MISSAVG-" + gly[1]);
		JChartLibSerie serie_gly2 = new JChartLibSerie("MISSAVG-" + gly[2]);
		JChartLibDataSet dataset = new JChartLibDataSet();
		for (ListIterator<Numb> ltr = numbs.listIterator(); ltr.hasNext();) {
			Numb numb = ltr.next();

			serie_gly0.addValue(Integer.parseInt(numb.getMissavg().charAt(0) + ""));
			serie_gly1.addValue(Integer.parseInt(numb.getMissavg().charAt(2) + ""));
			serie_gly2.addValue(Integer.parseInt(numb.getMissavg().charAt(4) + ""));
		}
		dataset.addDataSerie(serie_gly0);
		dataset.addDataSerie(serie_gly1);
		dataset.addDataSerie(serie_gly2);

		return dataset;

	}

	public JChartLibLinechartRenderer makeRenderer(JChartLibLineChart chart, List<Numb> numbs) {

		JChartLibLinechartRenderer renderer = new JChartLibLinechartRenderer(chart);
		chart.setRender(renderer);
		renderer.setDrawdots(true);
		renderer.setShowminmax(true);
		for (ListIterator ltr = numbs.listIterator(); ltr.hasNext();) {
			Numb numb = new Numb();
			renderer.addXAxisText(numb.getDate());

		}

		return renderer;

	}

	public void makeJPEG(List<Numb> numbs, int[] gly, int count) throws IOException {
		
		JChartLibDataSet dataset = makeDataset(numbs, gly);
		JChartLibLineChart chart = new JChartLibLineChart("G3 Analysis", "X-XAis", "Y-XAis", dataset);

		makeRenderer(chart, numbs);

		chart.saveAsJPEG("analysis.jpeg", 1000, 600);
	}

}
