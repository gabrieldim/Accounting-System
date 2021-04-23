package accountingsystem.main.service;


import accountingsystem.main.model.Turnover;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TestPDFExporter {
    private List<Turnover> turnoverList;
    private Long totalProcurementPrice;
    private Long totalSellingPrice;
    private Double totalTaxed;

    public TestPDFExporter(List<Turnover> turnoverList){
        this.turnoverList=turnoverList;
        this.totalProcurementPrice= Long.valueOf(0);
        this.totalSellingPrice=Long.valueOf(0);
        this.totalTaxed=Double.valueOf(0);
    }

    public TestPDFExporter() {
        this.totalProcurementPrice= Long.valueOf(0);
        this.totalSellingPrice=Long.valueOf(0);
        this.totalTaxed=Double.valueOf(0);
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);


        Paragraph title=new Paragraph("Monthly turnover report",font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        Paragraph info=new Paragraph(new Phrase("Total products sold: "+1));
        info.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(info);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);

        PdfPTable tableSummary=new PdfPTable(3);
        tableSummary.setWidthPercentage(100f);
        tableSummary.setWidths(new float[] {1.5f, 3.5f, 3.0f});
        tableSummary.setSpacingBefore(10);

        writeTableSummaryHeaders(tableSummary);
        WriteTableSummaryData(tableSummary);
        document.add(tableSummary);

        document.close();

    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLUE);

        cell.setPhrase(new Phrase("Product", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Procurement price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Selling price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Profit", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Taxes", font));
        table.addCell(cell);



    }

    private void writeTableSummaryHeaders(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setPadding(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLUE);



        cell.setPhrase(new Phrase("Total Procurement Value", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total Selling Value", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total Taxed", font));
        table.addCell(cell);
    }

    private void WriteTableSummaryData(PdfPTable table){
        table.addCell(String.valueOf(String.valueOf(totalProcurementPrice)));
        table.addCell(String.valueOf(String.valueOf(totalSellingPrice)));
        table.addCell(String.valueOf(String.valueOf(totalTaxed)));
    }
    private void writeTableData(PdfPTable table) {
       /* for (Turnover turnover : turnoverList) {
            List<Product> products=turnover.getProductList();
            for(Product product: products){
                table.addCell(product.getName());
                table.addCell(product.getProcurementPrice().toString());
                table.addCell(product.getPrice().toString());
                table.addCell(product.getProfit().toString());
                Double calculation=product.getProcurementPrice()+product.getProcurementPrice()*0.5+product.getProfit();
                table.addCell(String.valueOf(calculation)); // the formula

                this.totalProcurementPrice+=product.getProcurementPrice();
                this.totalTaxed+=calculation;
                this.totalSellingPrice+=product.getPrice();
            }

        }
        Remove comment for functionality.
        */


        table.addCell("Diesel");
        table.addCell("15");
        table.addCell("16");
        table.addCell("1");
        table.addCell("25");

    }


}
