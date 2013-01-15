package cn.edu.zju.plex.wp.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.edu.zju.plex.wp.dto.FileDTO;
import cn.edu.zju.plex.wp.util.FtpConfig;


public class ListFilesAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<FileDTO> uploadFiles = new ArrayList<FileDTO>();

	public String execute() throws Exception {
		try {
			String dir = FtpConfig.getInstance().getConfigItem("FtpPath")
					.trim();
			File folder = new File(dir);
			File[] fileList = folder.listFiles();

			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				FileDTO fd = new FileDTO();
				fd.setLocation(file.getCanonicalPath());
				fd.setName(file.getName());
				uploadFiles.add(fd);
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
}
