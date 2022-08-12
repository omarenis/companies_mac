package com.mac_vendor.mac_vendor.services;

import com.mac_vendor.mac_vendor.models.entities.Company;
import com.mac_vendor.mac_vendor.models.entities.Mac;
import com.mac_vendor.mac_vendor.models.repositories.CompanyRepository;
import com.mac_vendor.mac_vendor.models.repositories.MacRepository;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

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
        String companyName = null;
        String addressMac = null;
        String reference = null;
        Company company = null;
        for (Row row : workbook.getSheetAt(0)) {
            if (row.getCell(1).getCellType().equals(CellType.BLANK)) {
                reference = row.getCell(0).getCellType().equals(CellType.STRING) ? row.getCell(0).getStringCellValue() : String.valueOf(row.getCell(0).getNumericCellValue());
                company = companyRepository.findCompanyByName(companyName);
                if(company == null)
                {
                    company = new Company();
                }
                company.setName(companyName);
                companyRepository.saveAndFlush(company);
                Mac mac = macRepository.findByValueContainsAndReferenceContains(addressMac, reference);
                if (mac == null)
                {
                    mac = new Mac();
                    mac.setCompany(company);
                    mac.setValue(addressMac);
                    mac.setReference(reference);
                    companyRepository.saveAndFlush(company);
                    macRepository.saveAndFlush(mac);
                }
            } else {
                companyName = row.getCell(1).getCellType().equals(CellType.STRING) ? row.getCell(1).getStringCellValue() : String.valueOf(row.getCell(1).getNumericCellValue());
                addressMac = row.getCell(0).getStringCellValue().replace(" (hex)", "");
            }
//            else if(row.getCell(1).getCellType().equals(CellType.BLANK))
//            {
//                if (company == null)
//                {
//                    company = new Company();
//                    company.setName(companyName);
//                    companyRepository.saveAndFlush(company);
//                }
//            }
        }
    }
}
