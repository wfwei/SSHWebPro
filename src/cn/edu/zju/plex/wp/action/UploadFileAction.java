package cn.edu.zju.plex.wp.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.edu.zju.plex.wp.dto.FileDTO;
import cn.edu.zju.plex.wp.util.FtpConfig;

import com.opensymphony.xwork2.ActionSupport;

public class UploadFileAction extends ActionSupport {

	private static final long serialVersionUID = 2L;
	private File[] upload;// 实际上传文件
	private String[] uploadContentType; // 文件的内容类型
	private String[] uploadFileName; // 上传文件名
	private List<FileDTO> uploadFiles = new ArrayList<FileDTO>();

	public String execute() throws Exception {
		try {
			String targetDirectory = FtpConfig.getInstance()
					.getConfigItem("uploadFilePath").trim();// 获得路径

			for (int i = 0; i < upload.length; i++) {
				String type = uploadContentType[i];// 文件类型
				String fileName = uploadFileName[i];// 保存的文件名称，使用UUID+后缀进行保存

				File target = new File(targetDirectory, fileName);
				FileUtils.copyFile(upload[i], target);// 上传至服务器的目录，一般都这样操作，
														// 在把路径写入数据库即可
				FileDTO uf = new FileDTO();// 创建文件
				uf.setType(type);
				uf.setLocation(target.getAbsolutePath());
				uf.setName(target.getName());

				uploadFiles.add(uf);// 添加到需要下载文件的List集合中
			}
			ServletActionContext.getRequest().setAttribute("uploadFiles",
					uploadFiles);

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
