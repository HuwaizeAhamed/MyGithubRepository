package com.spring.rest.multipart.filedownload.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.multipart.filedownload.repositorydao.MultipartFileDownload_Repository;

@Service
public class MultipartFileDownload_Service {

	@Autowired
	MultipartFileDownload_Repository dao;

	public byte[] generatePdfFileResponse(String location) throws IOException, ClassNotFoundException, SQLException{

		String filetype="pdf";
		List<String> DBResponse = dao.getAllDatas(location,filetype);
		if(DBResponse.size() == 0) {
			throw new SQLException();
		}
		try (PDDocument doc = new PDDocument(); 
				ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			PDPage page = new PDPage(PDRectangle.A4);
			doc.addPage(page);

			PDPageContentStream contentStream = new PDPageContentStream(doc, page);
			contentStream.setFont(PDType1Font.HELVETICA, 12);

			float margin = 50;
			float yStart = page.getMediaBox().getHeight() - margin;
			float yPosition = yStart;
			float lineHeight = 15;

			for (String line : DBResponse) {
				if (yPosition <= margin) {
					contentStream.beginText();
					contentStream.endText();
					contentStream.close();
					page = new PDPage(PDRectangle.A4);
					doc.addPage(page);
					contentStream = new PDPageContentStream(doc, page);
					contentStream.setFont(PDType1Font.HELVETICA, 12);
					yPosition = yStart;
				}
				contentStream.beginText();
				contentStream.newLineAtOffset(margin, yPosition);
				contentStream.showText(line);
				contentStream.endText();
				yPosition -= lineHeight;
			}

			contentStream.close();
			doc.save(baos);

			return baos.toByteArray();

		} 
	}

	
	
	public byte[] generateCsvFileResponse(String location) throws ClassNotFoundException, SQLException {
		
		String filetype="csv";
		
		List<String> DBResponse = dao.getAllDatas(location,filetype);
		if(DBResponse.size() == 0) {
			throw new SQLException();
		}
		return DBResponse.toString().replaceAll(", ", "").replace("[", "").replace("]", "").getBytes();
	}

	
	
	public byte[] generateTxtFileResponse(String textMessage) {
		
		byte[] response = textMessage.getBytes(StandardCharsets.UTF_8);
		
		return response;
	}



	public byte[] generateExcelFileResponse(String location) throws ClassNotFoundException, SQLException, IOException {
		
		String filetype="xlsx";
		
		List<String> DBResponse = dao.getAllDatas(location,filetype);
		
		if(DBResponse.size() != 0) {
		try (Workbook workbook = new XSSFWorkbook(); 
				ByteArrayOutputStream response = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("EmployeeDetails - "+location);
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Name", "Age", "Company", "Phone", "Department", "Salary", "Location"};
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            
            int rowIdx = 1;
            for (String line : DBResponse) {
                String[] rowData = line.split(",");
                Row row = sheet.createRow(rowIdx++);
                for (int col = 0; col < rowData.length; col++) {
                    row.createCell(col).setCellValue(rowData[col]);
                }
            }
            workbook.write(response);
            return response.toByteArray();
		}
		}
		else {			
			throw new SQLException();
		}	
	}
}
