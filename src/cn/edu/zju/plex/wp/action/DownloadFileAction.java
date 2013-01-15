package cn.edu.zju.plex.wp.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFileAction extends ActionSupport {
	private static final long serialVersionUID = 6329383258366253255L;
	private String fileName;
	private String fileLocation;

	/**
	 * 设定文件名，同时设定文件的地址
	 */
	public void setFileName() {
		String fname = ServletActionContext.getRequest().getParameter(
				"fileName");
		String flocation = ServletActionContext.getRequest().getParameter(
				"fileLocation");
		try {
			fname = new String(fname.getBytes("ISO-8859-1"), "UTF-8");
			flocation = new String(flocation.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.fileName = fname;
		this.fileLocation = flocation;
	}

	/**
	 * 提供文件下载流
	 * <p>
	 * 方法名在struts中限定 <param name="inputName">downloadFile</param>
	 */
	public InputStream getDownloadFile() {
		this.setFileName();
		try {
			// return ServletActionContext.getServletContext()
			// .getResourceAsStream(this.getFileName());

			ServletActionContext.getResponse().setHeader(
					"Content-Disposition",
					"attachment;fileName="
							+ java.net.URLEncoder.encode(fileName, "utf-8"));

			System.out.println("下载文件：\t" + this.getFileLocation());
			return new FileInputStream(this.getFileLocation());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

}
