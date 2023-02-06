package com.sci.bpm.controller.po;

import com.sci.bpm.command.mi.ComponentDescription;
import com.sci.bpm.command.mi.DeliveryChallanDTO;
import com.sci.bpm.command.mi.RawComponentDesc;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.servlet.ServletContextURIResolver;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.List;
import java.util.Properties;

public class DeliveryChallanPDFServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeliveryChallanPDFServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	private FopFactory fopFactory = FopFactory.newInstance();
	private TransformerFactory tFactory = TransformerFactory.newInstance();
	protected URIResolver uriResolver;
	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 //Setup a buffer to obtain the content length
		try {

			DeliveryChallanDTO dto = new DeliveryChallanDTO();
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream("template.properties");
			Properties props = new Properties();
			props.load(stream);

			File file = new File("C:\\tools\\deliverychallan.xlsx");   //creating a new file instance
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
			//creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
			int rows = sheet.getPhysicalNumberOfRows();//3
			int cols = sheet.getRow(0).getPhysicalNumberOfCells();//2

			for(String key:props.stringPropertyNames()) {
				String values =  props.getProperty(key);
				String[] position = StringUtils.tokenizeToStringArray(values,",");
				Integer rownum = Integer.parseInt(position[0]);
				Integer colnum = Integer.parseInt(position[1]);
				XSSFCell type = sheet.getRow(rownum).getCell(colnum);
				System.out.println(rownum+ "  "+ colnum + " "+ key);



				switch (key) {
					case "DC_NO":
						sheet.getRow(rownum).getCell(colnum).setCellValue(Double.parseDouble(dto.getDeliveryChellanNo()));
						break;
					case "DC_DATE":
						sheet.getRow(rownum).getCell(colnum).setCellValue(dto.getDcDate());
						break;
					case "PO_NO":
						sheet.getRow(rownum).getCell(colnum).setCellValue(dto.getPurchaseNo());
						break;
					case "WO_NO":
						sheet.getRow(rownum).getCell(colnum).setCellValue(dto.getWorkOrderNo());
						break;
					case "VEND_DETAILS":
						sheet.getRow(rownum).getCell(colnum).setCellValue(dto.getVendorName()+"\n"+dto.getVendorAddress());
						break;
				}

			}

			stream = this.getClass().getClassLoader().getResourceAsStream("midetails.properties");
			props = new Properties();
			props.load(stream);

			rows = sheet.getPhysicalNumberOfRows();//3
			cols = sheet.getRow(0).getPhysicalNumberOfCells();//2

			int idx = 0;
			for(ComponentDescription desc: dto.getComponentDescriptionList()) {
				for (String key : props.stringPropertyNames()) {
					String values = props.getProperty(key);
					String[] position = StringUtils.tokenizeToStringArray(values, ",");
					Integer rownum = Integer.parseInt(position[0]);
					Integer colnum = Integer.parseInt(position[1]);
					List<ComponentDescription> descriptionList = dto.getComponentDescriptionList();

					rownum = rownum + idx;
					System.out.println(rownum+ "  "+ colnum + " "+ key);
					XSSFCell type = sheet.getRow(rownum).getCell(colnum);
					System.out.println(type);
					switch (key) {
						case "MI_NO":

							type.setCellValue(desc.getFinishMI());
							break;
						case "MI_DESC":
							type.setCellValue(desc.getDescription());
							break;
						case "MI_DIMEN":
							type.setCellValue(desc.getDimension());
							break;
						case "MI_QUANT":
							type.setCellValue(desc.getQuantity());
							break;
						case "MI_TOTAL_PRICE":
							type.setCellValue(desc.getTotalPrice());
							break;
						case "MI_UNIT_PRICE":
							type.setCellValue(desc.getUnitPrice());
							break;
						case "MI_DRAW_REF":
							type.setCellValue(desc.getMiDrawingReference());
							break;
						case "MI_DUE_DATE":
							type.setCellValue(desc.getMiDeliveryDate());
							break;
					}

				}
				idx = idx + 1;
			}


			stream = this.getClass().getClassLoader().getResourceAsStream("rawmidetails.properties");
			props = new Properties();
			props.load(stream);


			rows = sheet.getPhysicalNumberOfRows();//3
			cols = sheet.getRow(0).getPhysicalNumberOfCells();//2
			idx = 0;
			for(RawComponentDesc desc: dto.getRawComponentDescList()) {
				for (String key : props.stringPropertyNames()) {
					String values = props.getProperty(key);
					String[] position = StringUtils.tokenizeToStringArray(values, ",");
					Integer rownum = Integer.parseInt(position[0]);
					Integer colnum = Integer.parseInt(position[1]);
					List<ComponentDescription> descriptionList = dto.getComponentDescriptionList();

					rownum = rownum + idx;
					System.out.println(rownum+ "  "+ colnum + " "+ key);
					XSSFCell type = sheet.getRow(rownum).getCell(colnum);
					System.out.println(type);
					switch (key) {
						case "RAWMI_MINO":

							type.setCellValue(desc.getRawMatMi());
							break;
						case "RAWMI_MATCAT":
							type.setCellValue(desc.getRawMatType());
							break;
						case "RAWMI_MATDESC":
							type.setCellValue(desc.getRawMatDesc());
							break;

					}

				}
				idx = idx + 1;
			}



  /*          for (int i = 0; i < rows; i++) {
                Iterator<Cell> cellIterator = sheet.getRow(i).cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    //will iterate over the Merged cells
                    for (int idx = 0; idx < sheet.getNumMergedRegions(); idx++) {

                        CellRangeAddress region = sheet.getMergedRegion(idx); //Region of merged cells

                        int colIndex = region.getFirstColumn(); //number of columns merged
                        int rowNum = region.getFirstRow();      //number of rows merged
                        //check first cell of the region
                        if (rowNum == cell.getRowIndex() && colIndex == cell.getColumnIndex() && cell.getCellType() == CellType.STRING) {
                            System.out.println("");
                            System.out.print(" colindex-" + colIndex);
                            System.out.print(" rowindex-" + rowNum);
                            //.getRow(rowNum).getCell(colIndex).setCellValue("HI");
                            System.out.print("  "+sheet.getRow(rowNum).getCell(colIndex).getStringCellValue());

                        }
                    }
                    //the data in merge cells is always present on the first cell. All other cells(in merged region) are considered blank
                    if (cell.getCellType() == CellType.BLANK || cell == null) {
                        continue;
                    }
                    //System.out.println(cell.getStringCellValue());
                }

            }*/

			File outfile = new File("C:\\tools\\delivery_output.xlsx");   //creating a new file instance
			FileOutputStream fileOut = new FileOutputStream(outfile);
			wb.write(fileOut);
			fileOut.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println(getServletContext().getRealPath("/"));
		  this.uriResolver = new ServletContextURIResolver(getServletContext());
	}

}
