package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.CollectVO;

public class Excel {

	// 엑셀로 변환해주는 메소드
	public boolean xlsxWriter(List<CollectVO> list, String saveDir) {

		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		// 행 생성
		XSSFRow row = sheet.createRow(0);
		// 셀 생성
		XSSFCell cell;
		// 헤더(타이틀) 정보 구성
		cell = row.createCell(0);
		cell.setCellValue("번호");
		cell = row.createCell(1);
		cell.setCellValue("수금일자");
		cell = row.createCell(2);
		cell.setCellValue("상호명");
		cell = row.createCell(3);
		cell.setCellValue("사업자번호");
		cell = row.createCell(4);
		cell.setCellValue("업태");
		cell = row.createCell(5);
		cell.setCellValue("수금액");

		// 리스트의 size만큼 row를 생성
		CollectVO cVo;

		for (int rowidx = 0; rowidx < list.size(); rowidx++) {

			cVo = list.get(rowidx);

			// 행 생성
			row = sheet.createRow(rowidx + 1);

			cell = row.createCell(0);
			cell.setCellValue(cVo.getC_no());
			cell = row.createCell(1);
			cell.setCellValue(cVo.getC_date());
			cell = row.createCell(2);
			cell.setCellValue(cVo.getC_name());
			cell = row.createCell(3);
			cell.setCellValue(cVo.getA_businessNumber());
			cell = row.createCell(4);
			cell.setCellValue(cVo.getC_business());
			cell = row.createCell(5);
			cell.setCellValue(cVo.getC_collectMoney());

		}

		// 입력된 내용 파일로 쓰기
		String strReportExcelName = "collect_" + System.currentTimeMillis() + ".xlsx"; // 파일명
		File file = new File(saveDir + "\\" + strReportExcelName); // 파일명으로 파일 객체 생성
		FileOutputStream fos = null; // 파일 출력 값

		boolean saveSuccess; // 파일 저장 변수 선언
		saveSuccess = false;

		try {
			fos = new FileOutputStream(file);
			if (fos != null) { // 파일 출력값이 있으면
				workbook.write(fos); // 워크북에 출력한다
				saveSuccess = true; // 파일 저장 성공
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return saveSuccess;

	}

}