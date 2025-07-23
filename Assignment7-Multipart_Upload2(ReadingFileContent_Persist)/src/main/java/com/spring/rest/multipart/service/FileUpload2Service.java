package com.spring.rest.multipart.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.multipart.exception.FileUpload2GlobalExceptionHandler;
import com.spring.rest.multipart.model.FileUploadJsonModel;
import com.spring.rest.multipart.model.FileUpload_CsvModel;
import com.spring.rest.multipart.model.FileUpload_ExcelModel;
import com.spring.rest.multipart.model.FileUpload_ExcelModelFooter;
import com.spring.rest.multipart.model.FileUpload_ImageModel;
import com.spring.rest.multipart.model.FileUpload_PdfModel;
import com.spring.rest.multipart.model.FileUpload_XmlModel;
import com.spring.rest.multipart.repositorydao.FileUpload2CsvRepository;
import com.spring.rest.multipart.repositorydao.FileUpload2ExcelFooterRepository;
import com.spring.rest.multipart.repositorydao.FileUpload2ExcelRepository;
import com.spring.rest.multipart.repositorydao.FileUpload2ImgRepository;
import com.spring.rest.multipart.repositorydao.FileUpload2JsonRepository;
import com.spring.rest.multipart.repositorydao.FileUpload2PdfRepository;
import com.spring.rest.multipart.repositorydao.FileUpload2XmlRepository;

import jakarta.transaction.Transactional;
import net.sourceforge.tess4j.Tesseract;

@Service
@Component
public class FileUpload2Service {

	@Autowired
	FileUpload2GlobalExceptionHandler exception;
	@Autowired
	FileUpload2JsonRepository jsondao;
	@Autowired
	FileUpload2XmlRepository xmldao;
	@Autowired
	FileUpload2CsvRepository csvdao;
	@Autowired
	FileUpload2ImgRepository imgdao;
	@Autowired
	FileUpload2PdfRepository pdfdao;
	@Autowired
	FileUpload2ExcelRepository excelheaderdao;
	@Autowired
	FileUpload2ExcelFooterRepository excelfooterdao;

	public List<Object> readFileData(String contentType, String myText, MultipartFile file)
			throws IOException, SAXException, ParserConfigurationException, SQLException, InterruptedException {

		System.out.println("Service Layer Initiated");

		if (contentType.equalsIgnoreCase("application/json")) {
			System.out.println("Processing file : " + file.getOriginalFilename());

			List<Object> jsonData = parseJsonContent(file, myText);

			System.out.println("file processing completed : " + file.getOriginalFilename());
			System.out.println("Service Layer Completed");
			return jsonData;
		}

		else if (contentType.equalsIgnoreCase("application/xml")) {
			System.out.println("Processing file : " + file.getOriginalFilename());

			List<Object> xmlData = parseXmlContent(file);

			System.out.println("file processing completed : " + file.getOriginalFilename());
			System.out.println("Service Layer Completed");
			return xmlData;
		}

		else if (contentType.equalsIgnoreCase("text/csv")) {
			System.out.println("Processing file : " + file.getOriginalFilename());

			List<Object> csvData = parseCsvContent(file);

			System.out.println("file processing completed : " + file.getOriginalFilename());
			System.out.println("Service Layer Completed");
			return csvData;
		}

		else if (contentType.equalsIgnoreCase("application/pdf")) {
			System.out.println("Processing file : " + file.getOriginalFilename());

			List<Object> pdfData = parsePdfContent(file);

			System.out.println("file processing completed : " + file.getOriginalFilename());
			System.out.println("Service Layer Completed");
			return pdfData;
		}

		else if (contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/jpg")
				|| contentType.equalsIgnoreCase("image/png")) {
			System.out.println("Processing file : " + file.getOriginalFilename());

			List<Object> imgData = parseImageContent(file);

			System.out.println("file processing completed : " + file.getOriginalFilename());
			System.out.println("Service Layer Completed");
			return imgData;
		}

		else if (contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			System.out.println("Processing file : " + file.getOriginalFilename());

			List<Object> excelData = parseExcelContent(file);

			System.out.println("file processing completed : " + file.getOriginalFilename());
			System.out.println("Service Layer Completed");
			return excelData;
		} else {
			System.out.println("octet - stream file Initiated");
			System.out.println("octet - stream file Completed");
			return null;
		}

	}

	@Transactional
	private List<Object> parseExcelContent(MultipartFile file) {

		System.out.println("parsing Excel Content Initiated");
		List<Object> excelobj = new LinkedList<>();
		List<FileUpload_ExcelModel> excelHeaderList = new LinkedList<>();
		List<FileUpload_ExcelModelFooter> excelFooterList = new LinkedList<>();
		int batchSize = 100;

		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

			Sheet sheet = workbook.getSheetAt(0);
			int totalSheetRow = sheet.getPhysicalNumberOfRows();
			System.out.println("rows filled : " + totalSheetRow);

			for (Row row : sheet) {

				Cell cell1 = row.getCell(0);
				Cell cell2 = row.getCell(1);
				Cell cell3 = row.getCell(2);
				Cell cell4 = row.getCell(3);
				Cell cell5 = row.getCell(4);

				if (row.getRowNum() != 0) {
					if (cell1 != null && cell2 != null && cell3 != null && cell4 != null && cell5 != null) {
						double no = cell1.getNumericCellValue();
						String name = cell2.getStringCellValue();
						String company = cell3.getStringCellValue();
						double exp = cell4.getNumericCellValue();
						double salary = cell5.getNumericCellValue();

						excelHeaderList.add(new FileUpload_ExcelModel(no, name, company, exp, salary));

						if (excelHeaderList.size() == batchSize) {
							batchHeaderInsert(excelHeaderList);
						}

					}
				}

				if (row.getRowNum() != 10 || row.getRowNum() > 10) {
					Cell cell7 = row.getCell(7);
					Cell cell8 = row.getCell(8);

					if (cell7 != null && cell8 != null) {
						String cmplist = row.getCell(7).getStringCellValue();
						double cmpcount = row.getCell(8).getNumericCellValue();

						excelFooterList.add(new FileUpload_ExcelModelFooter(cmplist, cmpcount));

						if (excelFooterList.size() == batchSize) {
							batchFooterInsert(excelFooterList);
						}
					}
				}

			}

		} catch (IOException e) {
			System.out.println("IOException : Failed in service method, File not found !!");
		}

		if (!excelHeaderList.isEmpty())
			batchHeaderInsert(excelHeaderList);

		if (!excelFooterList.isEmpty())
			batchFooterInsert(excelFooterList);

		if (excelHeaderList.size() < 100 || excelFooterList.size() < 100) {
			excelobj.addAll(excelHeaderList);
			excelobj.addAll(excelFooterList);
		} else {
			excelobj.add(new FileUpload_ExcelModel(0, "Large File", "Data is too large", 0, 0));
			excelobj.add(new FileUpload_ExcelModelFooter("Large Content", 0));
		}
		System.out.println("Excel Content Parsing Completed!");
		return excelobj;
	}

	public void batchHeaderInsert(List<FileUpload_ExcelModel> excelHeaderList) {

		System.out.println("Persisting Excel Content in DB");
		excelheaderdao.saveAll(excelHeaderList);
		System.out.println("Data Inserted Successfully into DB!");

	}

	public void batchFooterInsert(List<FileUpload_ExcelModelFooter> excelFooterList) {

		System.out.println("Persisting Excel Content in DB");
		excelfooterdao.saveAll(excelFooterList);
		System.out.println("Data Inserted Successfully into DB!");

	}

	@Transactional
	private List<Object> parseImageContent(MultipartFile file) {

		System.out.println("parsing Image Content Initiated");
		List<Object> imgobj = new ArrayList<>();
		String data = null, name = null, gender = null, id = null, mobile = null, address = null, bank = null,
				type = null, company = null, sign = null, place = null;

		try {

			File file1 = new File(
					"C:\\Local_Disc (D Drive)\\SpringBoot_File\\FileUpload\\FileUpload2 - File Data Reading as Text\\textImage.png");
			BufferedImage bufferedImage = ImageIO.read(file1);
			Tesseract tesseract = new Tesseract();
			tesseract.setLanguage("eng");
			tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
			data = tesseract.doOCR(bufferedImage);

			name = data.substring(6, 15);
			gender = data.substring(23, 27);
			id = data.substring(32, 35);
			mobile = data.substring(44, 53);
			address = data.substring(63, 73);
			bank = data.substring(81, 85);
			type = data.substring(92, 99);
			company = data.substring(109, 120);
			sign = data.substring(131, 137);
			place = data.substring(146, 153);

			System.out.println(name + " " + gender + " " + id + " " + mobile + " " + address + " " + bank + " " + type
					+ " " + company + " " + sign + " " + place);
		} catch (Exception e) {
			List<Object> response = exception.executorException("Exception caught in parse image service method!");
			System.out.println("Exception caught in parse image service method!");
			return response;
		}
		FileUpload_ImageModel imgmdl = new FileUpload_ImageModel(id, name, gender, Long.valueOf(mobile), address, bank,
				type, company, sign, place);

		System.out.println("Persisting Image Content in DB");
		imgdao.save(imgmdl);
		System.out.println("Data Inserted Successfully into DB!");

		imgobj.add(imgmdl);

		System.out.println("Image Content Parsing Completed!");
		return imgobj;
	}

	@Transactional
	private List<Object> parsePdfContent(MultipartFile file) throws IOException {

		System.out.println("parsing PDF Content Initiated");
		List<Object> pdfobj = new ArrayList<>();

		String text = null;

		byte[] bytes = file.getBytes();

		try (PDDocument document = PDDocument.load(bytes)) {

			PDFTextStripper stripper = new PDFTextStripper();
			text = stripper.getText(document);
			System.out.println("PDF Data : \n" + text);

		} catch (Exception e) {
			List<Object> response = exception.executorException("Exception caught in parse pdf service method!");
			System.out.println("Exception caught in parse pdf service method!");
			return response;
		}

		FileUpload_PdfModel objmodel = extractPDFDatas(text);

//		if(text.contains("P R O F I L E")) {
//			String resumedata=text.substring(text.indexOf("P R O F I L E"),text.indexOf("environments.")+"environments.".length());
//		System.out.println(resumedata);
//		}

		try {

			System.out.println("Persisting PDF Content in DB");
			pdfdao.save(objmodel);
			pdfobj.add(objmodel);
			System.out.println("Data Inserted Successfully into DB!");

		} catch (Exception e) {
			List<Object> response = exception.DBException("Exception in DB");
			System.out.println("Exception caught in DB parse pdf service method!");
			return response;
		}

		System.out.println("PDF Content Parsing Completed!");
		return pdfobj;
	}

	@Transactional
	private List<Object> parseCsvContent(MultipartFile file) throws SQLException {

		System.out.println("parsing Csv Content Initiated");
		List<Object> csvobj = new ArrayList<>();
		List<FileUpload_CsvModel> csvModelsList = new ArrayList<>();
		String sid = null, bankName = null, bankAaccNo = null, accType = null, bankLocation = null, company = null;

//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//			String line = null;
//			String[] csvdatas = null;
//			while ((line = reader.readLine()) != null) {
//				csvdatas=line.split(",");
//				
//				if(csvdatas.length < 6) {
//					exception.invalidDataException(file.getOriginalFilename(), file.getSize());
//				}
//				 sid=csvdatas[0];
//				 bankName=csvdatas[1];
//				 bankAaccNo=csvdatas[2];
//				 accType=csvdatas[3];
//				 bankLocation=csvdatas[4];
//				 company=csvdatas[5];
//				
//				System.out.println("Datas : "+sid+" ,"+bankName+" ,"+bankAaccNo+" ,"+accType+" ,"+bankLocation+" ,"+company);
//				
//				try {
//					
//					long id=Long.valueOf(sid);
//					
//					FileUpload_CsvModel csvmdl=new FileUpload_CsvModel(id,bankName,bankAaccNo,accType,bankLocation,company);
//					
//					
//					System.out.println("Persisting File Content in DB");
//					csvdao.save(csvmdl);
//					System.out.println("Data Inserted Successfully into DB!");
//					csvobj.add(csvmdl);
//					}
//					catch(NumberFormatException e) {
//						
//					}
//					
//			}
//			
//			
//			
//			
//		}
//		catch(Exception e) {
//			
//		}
//		finally {
//			
//		}

		try {
			InputStreamReader reader = new InputStreamReader(file.getInputStream());

			CSVFormat format = CSVFormat.DEFAULT.builder().setDelimiter(',').setSkipHeaderRecord(false).setHeader()
					.setIgnoreHeaderCase(true).setTrim(true).build();

			Iterable<CSVRecord> records = format.parse(reader);

			for (CSVRecord record : records) {
				System.out.println("Reading File Content");
				sid = record.get(0);
				bankName = record.get(1);
				bankAaccNo = record.get(2);
				accType = record.get(3);
				bankLocation = record.get(4);
				company = record.get(5);
				System.out.println("Datas : " + sid + " ," + bankName + " ," + bankAaccNo + " ," + accType + " ,"
						+ bankLocation + " ," + company);

				long id = Long.valueOf(sid);

				csvModelsList.add(new FileUpload_CsvModel(id, bankName, bankAaccNo, accType, bankLocation, company));

			}
		} catch (Exception e) {
			List<Object> response = exception.executorException("Exception caught in parse csv service method!");
			System.out.println("Exception caught in parse csv service method!");
			return response;
		}
		try {

			System.out.println("Persisting File Content in DB");

			for (FileUpload_CsvModel objmodel : csvModelsList) {
				csvdao.save(objmodel);
				csvobj.add(objmodel);
			}

			System.out.println("Data Inserted Successfully into DB!");

		} catch (NumberFormatException e) {
			List<Object> response = exception.DBException("Exception caught in parse csv service method!");
			System.out.println("Exception caught in Number Format Exception in DB");
			return response;
		}

		System.out.println("Csv Content Parsing Completed!");
		return csvobj;
	}

	@Transactional
	private List<Object> parseXmlContent(MultipartFile file)
			throws SAXException, IOException, ParserConfigurationException {

		System.out.println("parsing Xml Content Initiated");
		List<Object> xmlobj = new ArrayList<>();
		List<FileUpload_XmlModel> xmlModelsList = new ArrayList<>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		try {
			System.out.println("Reading File Content");
			Document doc = builder.parse(file.getInputStream());
			doc.getDocumentElement().normalize();
			Element root = doc.getDocumentElement();
			NodeList shopList = doc.getElementsByTagName("CoffeeShop");

			for (int i = 0; i < shopList.getLength(); i++) {
				String name = root.getElementsByTagName("Name").item(i).getTextContent();

				String location = root.getElementsByTagName("Location").item(i).getTextContent();

				System.out.println("shops : " + name + " location : " + location);

				xmlModelsList.add(new FileUpload_XmlModel(name, location));

			}

		}

		catch (SAXException e) {
			List<Object> response = new ArrayList<>();
			String Error = "SAXException : Error while parsing xml!!";
			response = exception.parserException(Error);
			return response;
		} catch (IOException e) {
			List<Object> response = new ArrayList<>();
			String Error = "IO : File not found Exception!!";
			response.add(Error);
			return response;
		}

		System.out.println("Persisting File Content in DB");

		for (FileUpload_XmlModel objmodel : xmlModelsList) {
			xmldao.save(objmodel);
			xmlobj.add(objmodel);
		}

		System.out.println("Data Inserted Successfully into DB!");

		System.out.println("Xml Content Parsing Completed!");
		return xmlobj;
	}

	@Transactional
	private List<Object> parseJsonContent(MultipartFile file, String myText) throws IOException, InterruptedException {

		System.out.println("Parsing Json Content Initiated");
		List<Object> jsonobj = new ArrayList<>();
		List<FileUploadJsonModel> jsonModelsList = new ArrayList<>();

		ObjectMapper jsonmapper = new ObjectMapper();

		JsonNode id = jsonmapper.readTree(myText);
		long filegrpid = id.get("fileGrpId").asLong();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			System.out.println("Reading File Content");
			String line;
			while ((line = reader.readLine()) != null) {
				JsonNode node = jsonmapper.readTree(line);

				String name = node.get("Name").asText();
				String processor = node.get("Processor").asText();
				int ram = node.get("RAM").asInt();
				String color = node.get("Color").asText();
				double price = node.get("Price").asDouble();
				System.out
						.println("Data : " + name + " ," + processor + " ," + ram + " ," + color + " ," + price + " .");
				long productid = System.currentTimeMillis();

				jsonModelsList.add(new FileUploadJsonModel(productid, name, processor, ram, color, price, filegrpid));
				Thread.sleep(10);

			}

		}

		try {
			System.out.println("Persisting File Content in DB");

			for (FileUploadJsonModel objmodel : jsonModelsList) {
				jsondao.save(objmodel);
				jsonobj.add(objmodel);
			}

			System.out.println("Data Inserted Successfully into DB!");
		} catch (Exception e) {
			List<Object> response = exception.DBException("Exception in DB");
			System.out.println("Exception caught in DB parse json service method!");
			return response;
		}

		System.out.println("Json Content Parsing Completed!");
		return jsonobj;
	}

	private FileUpload_PdfModel extractPDFDatas(String text) {

		String invoice = null, name = null, empId = null, location = null, productId = null, orderId = null,
				phone = null;
		String address = null, productName = null, price = null, discount = null, gst = null, total = null,
				modeOfPayment = null;

		if (text.contains("Name :")) {
			name = text.split("Name :")[1].split("\n")[0].trim();
		}

		if (text.contains("EmpId :")) {
			empId = text.split("EmpId :")[1].split("\n")[0].trim();
		}

		if (text.contains("Location :")) {
			location = text.split("Location :")[1].split("\n")[0].trim();
		}

		if (text.contains("ProductId :")) {
			productId = text.split("ProductId :")[1].split("\n")[0].trim();
		}

		if (text.contains("OrderId :")) {
			orderId = text.split("OrderId :")[1].split("\n")[0].trim();
		}

		if (text.contains("InvoiceNo :")) {
			invoice = text.split("InvoiceNo :")[1].split("\n")[0].trim();
		}

		if (text.contains("Phone :")) {
			phone = text.split("Phone :")[1].split("\n")[0].trim();
		}

		if (text.contains("Address :")) {
			address = text.split("Address :")[1].split("\n")[0].trim();
		}

		if (text.contains("ProductName :")) {
			productName = text.split("ProductName :")[1].split("\n")[0].trim();
		}

		if (text.contains("Price :")) {
			price = text.split("Price :")[1].split("\n")[0].trim();
			price = price.replaceAll(",", "");
		}

		if (text.contains("Discount :")) {
			discount = text.split("Discount :")[1].split("\n")[0].trim();
		}

		if (text.contains("Gst :")) {
			gst = text.split("Gst :")[1].split("\n")[0].trim();
		}

		if (text.contains("Total :")) {
			total = text.split("Total :")[1].split("\n")[0].trim();
			total = total.replaceAll(",", "");
		}

		if (text.contains("ModeOfPayment :")) {
			modeOfPayment = text.split("ModeOfPayment :")[1].split("\n")[0].trim();
		}

		FileUpload_PdfModel pdfobjmodel = new FileUpload_PdfModel(name, empId, location, Long.valueOf(productId),
				orderId, Long.valueOf(invoice), Long.valueOf(phone), address, productName, Double.valueOf(price),
				discount, gst, Double.valueOf(total), modeOfPayment);

		return pdfobjmodel;
	}

}
