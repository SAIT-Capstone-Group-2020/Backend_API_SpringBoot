package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/api/admin/report")
    public List<List<?>> reportProd(
            @RequestParam(name="key", required = true) String key,
            @RequestParam(name="type", required = true) String type,
            @RequestParam(name="year", required = true) String year,
            @RequestParam(name="term", required = true) String term,
            @RequestParam(name="idList", required = true) Integer[] idList
    ) throws ParseException {

        if(key.equals("period")) {
            return reportService.reportPeriod(type, year, term, idList);
        }

       return reportService.reportProd(type, year, term, idList);
    }
}
