/**
 * 
 */
package cn.com.taiji.sample.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.taiji.common.manager.pub.AbstractHelper;
import cn.com.taiji.common.pub.AssertUtil;

/**
 * @author ltx
 * @date 2016年6月2日
 * @E_mail lantx@mail.taiji.com.cn
 */
public abstract class BaseDownloadController extends BaseLogController {

	protected void download(final HttpServletRequest request,HttpServletResponse response,final File file
			,String displayName) throws IOException {
		download(request, response, new FileInputStream(file), displayName);
	}
	
	protected void download(final HttpServletRequest request,HttpServletResponse response,final String content
			,String displayName) throws IOException {
		download(request, response, new ByteArrayInputStream(content.getBytes()), displayName);
	}
	
	protected void download(final HttpServletRequest request,HttpServletResponse response,final InputStream stream,
			String displayName) throws IOException {
		AssertUtil.notNull(stream, "被下载的数据流不能为空");
		AssertUtil.hasText(displayName, "显示名称必须指定");
		
		String ua = request.getHeader("user-agent");//获取终端类型
		if(ua == null)
        {
            ua = " User-Agent: Mozilla/4.0 (compatible; MSIE 6.0;) ";
        }
		
		response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/octet-stream;charset=GBK");
        if(ua.toLowerCase().contains("msie") || ua.toLowerCase().contains("trident")
                || ua.toLowerCase().contains("edge"))
        {
            response.setContentType("application/x-download");// 设置为下载application/x-download
        }

        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(displayName.getBytes("GBK"), "ISO8859-1"));
        response.setHeader("taiji_note", "success");
        
        OutputStream outputStream = null;
        
        InputStream fis = null;
        
        try{
	        response.setContentLength(stream.available());
	        outputStream = response.getOutputStream();
	        fis = stream;
	        byte[] b = new byte[AbstractHelper.BUFFER];
	        int i=0;
	        while((i=fis.read(b))>0){
	        	outputStream.write(b, 0, i);
	        }
	        outputStream.flush();
        }catch(Exception e){
        	
        }
        finally{
        	if(fis!=null){
        		fis.close();
        		fis=null;
        	}
        	if(outputStream!=null){
        		outputStream.close();
        		outputStream=null;
        	}
        }
	}
}
