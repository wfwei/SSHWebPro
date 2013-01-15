package cn.edu.zju.plex.wp.dto;

public class FileDTO {

	private String name; /* 文件名 */
	private String location; /* 文件地址（包括文件名） */
	private String type; /* 文件类型 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
