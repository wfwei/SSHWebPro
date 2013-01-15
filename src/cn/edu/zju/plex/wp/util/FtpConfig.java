package cn.edu.zju.plex.wp.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * 动态读取配置文件类
 */
public class FtpConfig {

	/**
	 * 静态工厂方法
	 * 
	 * @return 返回Configuration的单一实例
	 */
	public synchronized static FtpConfig getInstance() {
		return instance;
	}

	/**
	 * 读取一特定的属性项
	 */
	public String getConfigItem(String name, String defaultVal) {
		long newTime = file.lastModified();
		// 检查属性文件是否被修改
		if (newTime == 0) {
			// 属性文件不存在
			if (lastModifiedTime == 0) {
				System.err.println(confile + " file does not exist!");
			} else {
				System.err.println(confile + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > lastModifiedTime) {
			props.clear();
			try {
				props.load(new FileInputStream(getFile()));
			} catch (Exception e) {
				System.err.println("文件重新读取异常");
				e.printStackTrace();
			}
		}
		lastModifiedTime = newTime;
		String val = props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

	/**
	 * 读取一特定的属性项
	 * 
	 * @param name
	 *            属性项的项名
	 * @return 属性项的值（如此项存在）， 空（如此项不存在）
	 */
	public String getConfigItem(String name) {
		return getConfigItem(name, "");
	}

	/**
	 * 私有构造函数
	 * 
	 * @throws URISyntaxException
	 */
	private FtpConfig() {
		try {
			lastModifiedTime = getFile().lastModified();
			if (lastModifiedTime == 0) {
				System.err.println(confile + "file does not exist!");
			}
			props = new Properties();
			props.load(new FileInputStream(getFile()));

		} catch (URISyntaxException e) {
			System.err.println(confile + "文件路径不正确");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(confile + "文件读取异常");
			e.printStackTrace();
		}
	}

	/**
	 * 查找ClassPath路径获取文件
	 * 
	 * @return File对象
	 * @throws URISyntaxException
	 */

	private File getFile() throws URISyntaxException {
		URI fileUri = this.getClass().getClassLoader().getResource(confile)
				.toURI();
		file = new File(fileUri);
		return file;
	}

	/**
	 * 属性文件全名
	 */
	private static String confile = "cn/edu/zju/plex/wp/config/ftp.properties";

	/**
	 * 配置文件路径
	 */
	private URI uri = null;

	/**
	 * 属性文件所对应的属性对象变量
	 */
	private long lastModifiedTime = 0;

	/**
	 * 对应于属性文件的文件对象变量
	 */
	private File file = null;

	/**
	 * 属性文件所对应的属性对象变量
	 */
	private Properties props = null;

	/**
	 * 唯一实例
	 */
	private static FtpConfig instance = new FtpConfig();

	public static void main(String[] args) {
		System.out.println("Ok~");
	}
}
