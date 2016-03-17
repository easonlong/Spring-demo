package com.eason.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eason.spring.mvc.domain.Dataset;
import com.eason.spring.mvc.domain.LineChartData;

@Controller
@RequestMapping(value = "/chart")
public class ChartDataController {
	@RequestMapping(value = "/total", method = RequestMethod.GET)
	@ResponseBody
	public LineChartData total(String path) throws Exception {
		LineChartData lineChartData = new LineChartData();
		lineChartData.setLabels(new String[] { "a", "b", "c", "d", "e" });
		Dataset dataset = Dataset.buildDefaultDataset();
		dataset.setData(new int[] { 1, 2, 3, 4, 5 });
		dataset.setLabel("total");
		lineChartData.setDatasets(new Dataset[] { dataset });
		return lineChartData;
	}

}
