package org.ares.app.common.view.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public abstract class AbsExcelView extends AbstractView {

	public AbsExcelView(){
		this(DEFAULT_FILE_NAME);
	}
	
	public AbsExcelView(String filename){
		this(filename,DEFAULT_ENCODING);
	}
	
	public AbsExcelView(String filename,String encoding){
		setContentType(CONTENT_TYPE);
		if(filename!=null&&!filename.endsWith(EXTENSION))
			filename=filename+EXTENSION;
		this.filename=filename;
		this.encoding=encoding;
	}
	
	@Override
	abstract protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)throws Exception ;
	
	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}
	
	public String getFilename() {
		return filename;
	}

	public String getEncoding() {
		return encoding;
	}

	private static final String CONTENT_TYPE = "application/vnd.ms-excel";
	protected static final String DEFAULT_ENCODING="UTF-8";
	private static final String DEFAULT_FILE_NAME="ns_excel.xls";
	static final String EXTENSION = ".xls";
	
	private String filename;
	private String encoding;
}
