package com.AutoRABITFrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyAutoRABIT {
	public Properties FileLoad() throws Throwable {
		File file = new File(
				"C:\\Users\\Siva Koushik\\eclipse-workspace\\AutometionProject\\src\\com\\AutoRABITFrameWork\\PropertyFileAR.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties propertyFile = new Properties();
		propertyFile.load(fis);
		return propertyFile;
	}
}