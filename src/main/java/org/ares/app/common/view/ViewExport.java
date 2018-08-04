package org.ares.app.common.view;

public class ViewExport {
	
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Object getExcelObject() {
		return excelObject;
	}
	public void setExcelObject(Object excelObject) {
		this.excelObject = excelObject;
	}
	
	private String filename;
	private String encoding="utf-8";
	private Object excelObject;//POI or JXL
}
