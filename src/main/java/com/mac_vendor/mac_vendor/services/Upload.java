package com.mac_vendor.mac_vendor.services;

import com.mac_vendor.mac_vendor.models.entities.Company;
import com.mac_vendor.mac_vendor.models.entities.Mac;
import com.mac_vendor.mac_vendor.models.repositories.CompanyRepository;
import com.mac_vendor.mac_vendor.models.repositories.MacRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class Upload {
    private final CompanyRepository companyRepository;
    private final MacRepository macRepository;

    public Upload(CompanyRepository companyRepository, MacRepository macRepository) {
        this.companyRepository = companyRepository;
        this.macRepository = macRepository;
    }

    public void saveDataFromExcelToDatabase(FileInputStream fileInputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        String companyName;
        String addressMac;
        Company company;
        Mac mac;
        for (Row row : workbook.getSheetAt(0)) {
                companyName = row.getCell(1).getCellType().equals(CellType.STRING) ? row.getCell(1).getStringCellValue() : String.valueOf(row.getCell(1).getNumericCellValue());
                addressMac = row.getCell(0).getStringCellValue().replace(" (hex)", "").replace("-", "");
                System.out.println("company name = " + companyName);
                System.out.println("mac address = " + addressMac);
                company = companyRepository.findCompanyByName(companyName);
                mac = macRepository.findByValueContains(addressMac);
                System.out.println("mac id = " + mac);
                if(company == null)
                {
                    company = new Company(companyName);
                    companyRepository.saveAndFlush(company);
                }
                if (mac == null)
                {
                    mac = new Mac(addressMac, company);
                    macRepository.saveAndFlush(mac);
                }
                else if (! mac.getCompany().getName().equals(companyName))
                {
                    mac.setCompany(company);
                    macRepository.saveAndFlush(mac);
                }
        }
    }
}
