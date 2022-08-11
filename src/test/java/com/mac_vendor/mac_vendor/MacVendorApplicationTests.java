package com.mac_vendor.mac_vendor;

import com.mac_vendor.mac_vendor.services.Upload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class MacVendorApplicationTests {

	@Autowired Upload uploadService;

	@Test
	void contextLoads() {
		try
		{
			FileInputStream file = new FileInputStream(new File(new java.io.File(".").getCanonicalPath() +"/stage.xlsx"));
			uploadService.saveDataFromExcelToDatabase(file);
		} catch (FileNotFoundException fileNotFoundException)
		{
			System.out.println(fileNotFoundException);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
