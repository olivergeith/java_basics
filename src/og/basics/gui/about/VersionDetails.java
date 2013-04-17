package og.basics.gui.about;

import javax.swing.ImageIcon;

/**
 * 
 * Datenklasse zum Verwalten/Speichern von VersionsDetails
 * 
 * @author Oliver
 * 
 */
public class VersionDetails {
	private String				version			= "";
	private String				copyright		= "";
	private ImageIcon			logo			= null;
	private String				company			= "";
	private String				date			= "";
	private String				applicationname	= "";
	private String				description		= "";

	final public static String	javaVersion		= System.getProperty("java.version");
	final public static String	javaVendor		= System.getProperty("java.vendor");
	final public static String	osName			= System.getProperty("os.name");
	final public static String	osArch			= System.getProperty("os.arch");

	public VersionDetails() {

	}

	public String getCompany() {
		return company;
	}

	public void setCompany(final String company) {
		this.company = company;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(final String copyright) {
		this.copyright = copyright;
	}

	public ImageIcon getLogo() {
		return logo;
	}

	public void setLogo(final ImageIcon logo) {
		this.logo = logo;
	}

	public String getApplicationname() {
		return applicationname;
	}

	public void setApplicationname(final String applicationname) {
		this.applicationname = applicationname;
	}

	/**
	 * The description can be a long multiline String. Use the html-tag "br" for
	 * new lines!
	 * 
	 * @param description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
