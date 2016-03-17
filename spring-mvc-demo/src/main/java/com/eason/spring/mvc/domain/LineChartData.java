package com.eason.spring.mvc.domain;

/**
 * @author longyaokun
 *
 */
public class LineChartData {

	private String[] labels;

	private Dataset[] datasets;

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public Dataset[] getDatasets() {
		return datasets;
	}

	public void setDatasets(Dataset[] datasets) {
		this.datasets = datasets;
	}
}
