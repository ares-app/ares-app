package org.ares.app.common.view.excel;

/*import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableWorkbook;*/

public abstract class JxlExcelView extends AbsExcelView {

	public JxlExcelView(String filename){
		super(filename);
	}
	
	public JxlExcelView(String filename,String encoding){
		super(filename,encoding);
	}
	
	/*@Override
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition", "attachment; filename="  + URLEncoder.encode(getFilename(), getEncoding()));
		response.setContentType(getContentType());
		ServletOutputStream out = response.getOutputStream();
		WritableWorkbook wwb=Workbook.createWorkbook(out);
		buildWorkbook(wwb);
		wwb.write();
		wwb.close();
		out.flush();
	}
	
	public abstract void buildWorkbook(WritableWorkbook wb);*/
	
}
