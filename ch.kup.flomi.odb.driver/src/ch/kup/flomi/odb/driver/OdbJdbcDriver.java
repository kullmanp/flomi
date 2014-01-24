package ch.kup.flomi.odb.driver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.hsqldb.jdbcDriver;

public class OdbJdbcDriver implements Driver {
	private jdbcDriver jdbcDrver = new jdbcDriver(); // The HSQLDB Driver

	public boolean acceptsURL(String url) {
		if (!url.startsWith(":jdbc:odb:"))
			return false;
		try {
			new URL(url.substring(10));
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	/**
	 * expects urls like "jdbc:odb:<url to odb-file>
	 */
	public Connection connect(String url, Properties properties)
			throws SQLException {
		if (!acceptsURL(url))
			throw new SQLException("URL not acceptable");
		URL fileURL = null;
		try {
			fileURL = new URL(url.substring(10));
		} catch (MalformedURLException e) {
			throw new SQLException(e.getLocalizedMessage());
		}
		String tempDBLocation;
		try {
			tempDBLocation = extractTempDBFiles(fileURL);
		} catch (IOException e) {
			throw new SQLException(e.getLocalizedMessage());
		}
		String hsqlDbUrl = "jdbc:hsqldb:" + tempDBLocation;
		return jdbcDrver.connect(hsqlDbUrl, properties);
	}

	private String extractTempDBFiles(URL fileURL) throws IOException {
		ZipFile zip = new ZipFile(fileURL.getPath());
		File f = File.createTempFile("ooTempDatabase", "tmp");
		f.deleteOnExit();
		String tempDir = System.getProperty("java.io.tmpdir");

		Enumeration<? extends ZipEntry> entries = zip.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			if (entry.getName().startsWith("database/")) {
				InputStream in = new BufferedInputStream(
						zip.getInputStream(entry));
				String outFileName = tempDir + "/" + f.getName() + "."
						+ entry.getName().substring(9);
				File outFile = new File(outFileName);
				System.out.println(outFileName);
				outFile.deleteOnExit();
				OutputStream out = new BufferedOutputStream(
						new FileOutputStream(outFile));
				int r;
				while ((r = in.read()) >= 0) {
					out.write(r);
				}
				out.close();
				in.close();
			}
		}
		zip.close();

		return tempDir + "/" + f.getName();
	}

	public boolean equals(Object arg0) {
		return jdbcDrver.equals(arg0);
	}

	public int getMajorVersion() {
		return jdbcDrver.getMajorVersion();
	}

	public int getMinorVersion() {
		return jdbcDrver.getMinorVersion();
	}

	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) {
		return jdbcDrver.getPropertyInfo(arg0, arg1);
	}

	public int hashCode() {
		return jdbcDrver.hashCode();
	}

	public boolean jdbcCompliant() {
		return jdbcDrver.jdbcCompliant();
	}

	public String toString() {
		return "ODB bridge driver for " + jdbcDrver.toString();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new SQLFeatureNotSupportedException();
	}
}
