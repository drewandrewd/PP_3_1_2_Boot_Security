package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.services.PdfReportService;
import ru.kata.spring.boot_security.demo.services.ReportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    private final PdfReportService pdfReportService;

    public ReportController(ReportService reportService, PdfReportService pdfReportService) {
        this.reportService = reportService;
        this.pdfReportService = pdfReportService;
    }

    @GetMapping
    public String showReport(@RequestParam(value = "type", required = false) String type,
                             @RequestParam(value = "from", required = false)
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                             @RequestParam(value = "to", required = false)
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                             @RequestParam(value = "groupBy", required = false) List<String> groupBy,
                             Model model) {

        if (type != null) {
            model.addAttribute("reportHeaders", reportService.getHeaders(type));
            model.addAttribute("reportRows", reportService.getReport(type, from, to, groupBy));
        }
        return "reports";
    }

    @GetMapping("/pdf")
    public void exportPdf(@RequestParam("type") String type,
                          @RequestParam(value = "from", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                          @RequestParam(value = "to", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                          @RequestParam(value = "groupBy", required = false) List<String> groupBy,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        List<String> headers = reportService.getHeaders(type);
        List<List<String>> data = reportService.getReport(type, from, to, groupBy);
        pdfReportService.exportStockReport(response, data, headers, groupBy);
    }

}
