package org.ares.app.common.view.excel;

/*
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
*/


public abstract class PoiExcelView extends AbsExcelView {
	
	/*public PoiExcelView(HSSFWorkbook wb,String filename){
		this(wb,filename,DEFAULT_ENCODING);
	}
	
	public PoiExcelView(HSSFWorkbook wb,String filename,String encoding){
		super(filename,encoding);
		this.wb=wb;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition", "attachment; filename="  + URLEncoder.encode(getFilename(), getEncoding()));
		response.setContentType(getContentType());
		ServletOutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
	}
	
	private HSSFWorkbook wb;*/
}
