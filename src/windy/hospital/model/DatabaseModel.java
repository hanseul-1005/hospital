package windy.hospital.model;

public class DatabaseModel {
	
	private String dbDriver = "org.mariadb.jdbc.Driver";
	//private String jdbcUrl = "jdbc:mariadb://bicycledb:3306/emapdb";
    private String jdbcUrl = "jdbc:mariadb://localhost:3306/emapdb";
    //private String jdbcUrl = "jdbc:mariadb://192.168.0.60:33308/bicycledb";";
	private String user = "kovico";         
	private String password = "kovico@0136";
	
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
