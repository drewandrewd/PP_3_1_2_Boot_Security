package ru.kata.spring.boot_security.demo.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfReportService {

    public void exportStockReport(HttpServletResponse response,
                                  List<List<String>> data,
                                  List<String> headers,
                                  List<String> groupBy) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        BaseFont baseFont = BaseFont.createFont(
                "src/main/resources/fonts/ofont.ru_AA Stetica.ttf",
                BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED
        );
        Font fontHeader = new Font(baseFont, 12, Font.BOLD);
        Font fontCell = new Font(baseFont, 11);
        Font fontGroup = new Font(baseFont, 11, Font.BOLDITALIC);

        document.add(new Paragraph("Отчет", fontHeader));
        document.add(Chunk.NEWLINE);

        // Если задана группировка
        if (groupBy != null && !groupBy.isEmpty() && headers.contains("Категория")) {
            Comparator<List<String>> comparator = Comparator.comparing(row -> "");
            if (groupBy.contains("category")) {
                comparator = comparator.thenComparing(row -> row.get(2)); // категория
            }
            if (groupBy.contains("location")) {
                comparator = comparator.thenComparing(row -> row.get(4)); // местоположение
            }
            data = data.stream().sorted(comparator).collect(Collectors.toList());

            String currentGroup = "";
            for (List<String> row : data) {
                String groupKey = "";
                if (groupBy.contains("category")) groupKey += row.get(2);
                if (groupBy.contains("location")) groupKey += " / " + row.get(4);

                if (!groupKey.equals(currentGroup)) {
                    currentGroup = groupKey;
                    document.add(new Paragraph("Группа: " + currentGroup, fontGroup));
                    PdfPTable table = new PdfPTable(headers.size());
                    table.setWidthPercentage(100);
                    for (String header : headers) {
                        table.addCell(new PdfPCell(new Phrase(header, fontHeader)));
                    }
                    for (String cell : row) {
                        table.addCell(new Phrase(cell, fontCell));
                    }
                    document.add(table);
                } else {
                    PdfPTable table = new PdfPTable(headers.size());
                    table.setWidthPercentage(100);
                    for (String cell : row) {
                        table.addCell(new Phrase(cell, fontCell));
                    }
                    document.add(table);
                }
            }
        } else {
            // Без группировки
            PdfPTable table = new PdfPTable(headers.size());
            table.setWidthPercentage(100);
            for (String header : headers) {
                table.addCell(new PdfPCell(new Phrase(header, fontHeader)));
            }
            for (List<String> row : data) {
                for (String cell : row) {
                    table.addCell(new Phrase(cell, fontCell));
                }
            }
            document.add(table);
        }

        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Руководитель склада __________________   __________________", fontCell));
        document.add(new Paragraph("                            Подпись                                 Расшифровка", fontCell));
        document.close();
    }

}
