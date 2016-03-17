package com.eason.spring.mvc.domain;

/**
 * @author longyaokun
 *
 */
public class Dataset {

	private static final String DEFAULT_FILL_COLOR = "rgba(220,220,220,0.2)";

	private static final String DEFAULT_STROKE_COLOR = "rgba(220,220,220,1)";

	private static final String DEFAULT_POINT_COLOR = "rgba(220,220,220,1)";

	private static final String DEFAULT_POINT_STROKE_COLOR = "#fff";

	private static final String DEFAULT_POINT_HIGHLIGHT_FILL = "#fff";

	private static final String DEFAULT_POINT_HIGHLIGHT_STROKE = "rgba(220,220,220,1)";

	private String label;

	private String fillColor;

	private String strokeColor;

	private String pointColor;

	private String pointStrokeColor;

	private String pointHighlightFill;

	private String pointHighlightStroke;

	private int[] data;

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}

	public String getPointColor() {
		return pointColor;
	}

	public void setPointColor(String pointColor) {
		this.pointColor = pointColor;
	}

	public String getPointStrokeColor() {
		return pointStrokeColor;
	}

	public void setPointStrokeColor(String pointStrokeColor) {
		this.pointStrokeColor = pointStrokeColor;
	}

	public String getPointHighlightFill() {
		return pointHighlightFill;
	}

	public void setPointHighlightFill(String pointHighlightFill) {
		this.pointHighlightFill = pointHighlightFill;
	}

	public String getPointHighlightStroke() {
		return pointHighlightStroke;
	}

	public void setPointHighlightStroke(String pointHighlightStroke) {
		this.pointHighlightStroke = pointHighlightStroke;
	}

	public static Dataset buildDefaultDataset() {
		Dataset dataset = new Dataset();
		dataset.setFillColor(DEFAULT_FILL_COLOR);
		dataset.setStrokeColor(DEFAULT_STROKE_COLOR);
		dataset.setPointColor(DEFAULT_POINT_COLOR);
		dataset.setPointStrokeColor(DEFAULT_POINT_STROKE_COLOR);
		dataset.setPointHighlightFill(DEFAULT_POINT_HIGHLIGHT_FILL);
		dataset.setPointHighlightStroke(DEFAULT_POINT_HIGHLIGHT_STROKE);
		return dataset;

	}
	
}
