package com.eason.spring.mvc.config;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {

	private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private DateFormat dateFormat;
	private boolean allowEmpty = true;

	public DateEditor() {
	}

	public DateEditor(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
	}

	/**
	 * 支持特定格式的字符串日期以及时间戳
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else {
			try {
				if (this.dateFormat != null) {
					setValue(this.dateFormat.parse(text));
				} else {
					setValue(TIMEFORMAT.parse(text));
				}
			} catch (ParseException ex) {
				setValue(new DateTime(Long.valueOf(text)).toDate());
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		DateFormat dateFormat = this.dateFormat;
		if (dateFormat == null) {
			dateFormat = TIMEFORMAT;
		}
		return (value != null ? dateFormat.format(value) : "");
	}
}
