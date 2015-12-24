package com.cetrinw.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	/**
	 * ��ȡExcel�ļ�,ȡ�ñ������
	 * 
	 * @param fileName
	 *            �ļ�·��
	 * @return List<String>
	 */
	public List<String> readExcel(String fileName) {
		List<String> list = new ArrayList<String>();

		Sheet sheet = getWorkbook(fileName).getSheetAt(0); // ��õ�һ����
		Iterator<Row> rows = sheet.rowIterator(); // ��õ�һ�����ĵ�����
		while (rows.hasNext()) {
			Row row = rows.next(); // ���������
			// System.out.println("Row #" + row.getRowNum()); //����кŴ�0��ʼ
			Iterator<Cell> cells = row.cellIterator(); // ��õ�һ�еĵ�����
			while (cells.hasNext()) {
				Cell cell = cells.next();
				if (cell.getColumnIndex() == 2 && cell.getRowIndex() > 1) {
					list.add(cell.getStringCellValue());
				}
				/**
				 * System.out.println("Cell #" + cell.getColumnIndex()); switch
				 * (cell.getCellType()) { //����cell�е�������������� case
				 * HSSFCell.CELL_TYPE_NUMERIC:
				 * System.out.println(cell.getNumericCellValue()); break; case
				 * HSSFCell.CELL_TYPE_STRING:
				 * System.out.println(cell.getStringCellValue()); break; case
				 * HSSFCell.CELL_TYPE_BOOLEAN:
				 * System.out.println(cell.getBooleanCellValue()); break; case
				 * HSSFCell.CELL_TYPE_FORMULA:
				 * System.out.println(cell.getCellFormula()); break; default:
				 * System.out.println("unsuported sell type"); break; }
				 */
			}
		}

		return list;
	}

	private static Workbook getWorkbook(String fileName) {
		boolean isE2007 = false; // �ж��Ƿ���excel2007��ʽ
		if (fileName.endsWith("xlsx"))
			isE2007 = true;
		InputStream input;
		Workbook wb = null;
		try {
			input = new FileInputStream(fileName);
			if (isE2007)
				wb = new XSSFWorkbook(input);
			else
				wb = new HSSFWorkbook(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// �����ļ���ʽ(2003����2007)����ʼ��
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}
}
