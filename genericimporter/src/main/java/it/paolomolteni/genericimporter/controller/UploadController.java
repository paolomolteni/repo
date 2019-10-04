package it.paolomolteni.genericimporter.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.monitorjbl.xlsx.impl.StreamingWorkbook;
import com.monitorjbl.xlsx.impl.StreamingWorkbookReader;

import it.paolomolteni.genericimporter.service.ImporterService;

@Controller
public class UploadController {
	
	/**
	 * 
	 */
	@Autowired
	private ImporterService importerService;
	
	/**
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		
		try {
			doImport(file.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
    }
	
	/**
	 * @param inputStream
	 */
	private void doImport(InputStream inputStream)  {
		
		StreamingWorkbookReader streamingWorkbookReader = new StreamingWorkbookReader(com.monitorjbl.xlsx.StreamingReader.builder().rowCacheSize(100).bufferSize(4096));
		streamingWorkbookReader.init(inputStream);
		
		StreamingWorkbook streamingWorkbook = new StreamingWorkbook(streamingWorkbookReader);
		Sheet sheet = streamingWorkbook.getSheetAt(0);
		
		Map<Integer, String> mapFieldPosition = new HashMap<Integer, String>();
		List<Map<String, Cell>> rows = new ArrayList<Map<String,Cell>>();
		
		int index = 0;
		int numCells = 0;
		Cell cell = null;
		Map<String, Cell> mapRow = null;
		
		for(Row row : sheet) {
			index++;
			if(index == 1) {
				numCells = row.getLastCellNum();
				
				for(int indexCell = 0; indexCell < numCells; indexCell++) {
					cell = row.getCell(indexCell);
					mapFieldPosition.put(indexCell, cell.getStringCellValue());
				}
				
			}
			else {
				mapRow = new HashMap<String, Cell>();
				for(int indexCell = 0; indexCell < numCells; indexCell++) {
					mapRow.put(mapFieldPosition.get(indexCell), row.getCell(indexCell));
				}
				rows.add(mapRow);
			}
			
		}
		
		try {
			importerService.doImport(rows);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
