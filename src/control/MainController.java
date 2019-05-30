package control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AccountVO;
import model.CollectVO;
import oracle.net.aso.a;

public class MainController implements Initializable {

	@FXML
	private TabPane mainPane;
	@FXML
	private Tab importion;
	@FXML
	private Tab account;
	@FXML
	private Tab money;
	@FXML
	private Tab buy;
	@FXML
	private Tab product;

	// 메뉴
	@FXML
	private MenuItem m_excel;
	@FXML
	private MenuItem m_pdf;
	@FXML
	private MenuItem menuInfo;
	@FXML
	private MenuItem menuLogout;

	@FXML
	private ImportionTabController importionTabController;
	@FXML
	private AccountTabController accountTabController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					if (newValue == importion) {
						System.out.println("DB 연결 성공");
						try {
							importionTabController.ImportionTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					if (newValue == account) {
						System.out.println("DB 연결 성공");
						try {
							accountTabController.AccountTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == money) {
						try {
							MoneyTabController.CollectTotalList();
							MoneyTabController.PaymentTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == buy) {
						try {
							BuyTabController.BuyTotalList();
							BuyTabController.DealTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == product) {
						try {
							ProductTotalTabController.productTotalList();
							ProductTotalTabController.stockTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

			// 기능 메뉴바 이벤트
			menuLogout.setOnAction(event -> handlerMenuLogoutAction(event));
			menuInfo.setOnAction(event -> handlerMenuInfoAction(event));
			m_pdf.setOnAction(event -> handlerm_pdfFileDirAction(event));
			m_excel.setOnAction(event -> handlerm_excelFileAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그아웃 메뉴 이벤트 핸들러
	public void handlerMenuLogoutAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("육류 유통관리 프로그램");
			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) mainPane.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerMenuInfoAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("도움말");
		alert.setHeaderText("육류 유통관리 프로그램");
		alert.setContentText("beaf distribution Version 0.01");
		alert.setResizable(false);
		alert.showAndWait();
	}

	public void handlerm_excelFileAction(ActionEvent event) {
		String txtSaveFileDirExcel = "D:\\HJtrade\\backup\\Excel";

		CollectTabDAO cDao = new CollectTabDAO();
		boolean saveSuccess;

		ArrayList<CollectVO> list;
		list = cDao.getCollectVOTotalList();
		Excel excelWriter = new Excel();

		// xlsx 파일 쓰기
		saveSuccess = excelWriter.xlsxWriter(list, txtSaveFileDirExcel);
		if (saveSuccess) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("엑셀 파일 생성");
			alert.setHeaderText("매출 목록 엘셀 파일 생성 성공.");
			alert.setContentText("매출 목록 엑셀 파일");
			alert.showAndWait();
		}
	}

	// PDF 파일 생성 메소드
	public void handlerm_pdfFileDirAction(ActionEvent event) {
		String txtSaveFileDir = "D:\\HJtrade\\backup\\PDF";

		try {

			// PDF document 선언
			// (Rectangle pageSize, float marginLeft,float marginRight, float marginTop,
			// float marginBottom)
			Document document = new Document(PageSize.A4, 10, 10, 30, 30);

			// PDF 파일을 저장할 공간을 선언, PDF파일이 생성된다. 그 후 스트림으로 저장
			String strReportPDFName = "매출_" + System.currentTimeMillis() + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(txtSaveFileDir + "\\" + strReportPDFName));

			// document를 열어 PDF문서를 쓸 수 있도록 한다
			document.open();

			// 한글 지원 폰트 설정
			BaseFont bf = BaseFont.createFont("font/MALGUN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 8, Font.NORMAL);
			Font font2 = new Font(bf, 14, Font.BOLD);

			// 타이틀
			Paragraph title = new Paragraph("매출확인표", font2);

			// 중간 정렬
			title.setAlignment(Element.ALIGN_CENTER);

			// 문서에 추가
			document.add(title);
			document.add(new Paragraph("\r\n"));

			// 테이블 생성 Table객체보다 PdfPTable객체가 더 정교하게 테이블을 만들 수 있다
			// 생성자에 컬럼 수를 써준다
			PdfPTable table = new PdfPTable(6);

			// 각각의 컬럼에 width를 정한다
			table.setWidths(new int[] { 30, 100, 50, 30, 30, 30 });

			// 컬럼 타이틀
			PdfPCell header1 = new PdfPCell(new Paragraph("번호", font));
			PdfPCell header2 = new PdfPCell(new Paragraph("수금일자", font));
			PdfPCell header3 = new PdfPCell(new Paragraph("상호명", font));
			PdfPCell header4 = new PdfPCell(new Paragraph("사업자번호", font));
			PdfPCell header5 = new PdfPCell(new Paragraph("업태", font));
			PdfPCell header6 = new PdfPCell(new Paragraph("수금액", font));

			// 가로 정렬
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header5.setHorizontalAlignment(Element.ALIGN_CENTER);
			header6.setHorizontalAlignment(Element.ALIGN_CENTER);

			// 세로 정렬
			header1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header6.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// 테이블에 추가
			table.addCell(header1);
			table.addCell(header2);
			table.addCell(header3);
			table.addCell(header4);
			table.addCell(header5);
			table.addCell(header6);

			// DB 연결 및 리스트 선택
			CollectTabDAO cDao = new CollectTabDAO();

			CollectVO cVo = new CollectVO();
			ArrayList<CollectVO> list;
			list = cDao.getCollectVOTotalList();
			int rowCount = list.size();

			PdfPCell cell1 = null;
			PdfPCell cell2 = null;
			PdfPCell cell3 = null;
			PdfPCell cell4 = null;
			PdfPCell cell5 = null;
			PdfPCell cell6 = null;

			for (int index = 0; index < rowCount; index++) {
				cVo = list.get(index);

				cell1 = new PdfPCell(new Paragraph(cVo.getC_no() + "", font));
				cell2 = new PdfPCell(new Paragraph(cVo.getC_date() + "", font));
				cell3 = new PdfPCell(new Paragraph(cVo.getC_name() + "", font));
				cell4 = new PdfPCell(new Paragraph(cVo.getA_businessNumber() + "", font));
				cell5 = new PdfPCell(new Paragraph(cVo.getC_business() + "", font));
				cell6 = new PdfPCell(new Paragraph(cVo.getC_collectMoney() + "", font));

				// 가로 정렬
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

				// 세로 정렬
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

				// 테이블에 셀 추가
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
			}

			// 문서에 테이블 추가
			document.add(table);
			document.add(new Paragraph("\r\n"));

			// 막대 그래프 이미지 추가
			Paragraph barImageTitle = new Paragraph("학생 성적표 막대 그래프", font);
			barImageTitle.setAlignment(Element.ALIGN_CENTER);
			document.add(barImageTitle);
			document.add(new Paragraph("\r\n"));
			final String barImageUrl = "chartImage/collectBarChart.png";

			// 기존에 javafx.scene.image.Image 객체를 사용하고 있어 충돌이 생겨 아래와 같이 사용함
			com.itextpdf.text.Image barImage;

			try {
				if (com.itextpdf.text.Image.getInstance(barImageUrl) != null) {
					barImage = com.itextpdf.text.Image.getInstance(barImageUrl);
					barImage.setAlignment(Element.ALIGN_CENTER);
					barImage.scalePercent(30f);
					document.add(barImage);
					document.add(new Paragraph("\r\n"));
				}
			} catch (IOException ee) {
			}

			// 문서를 닫는다. 쓰기 종료
			document.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("PDF 파일 생성");
			alert.setHeaderText("학생 목록 PDF 파일 생성 성공");
			alert.setContentText("학생 목록 PDF 파일");
			alert.showAndWait(); // 확인 창 누르기 전까지 대기

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	};

}
