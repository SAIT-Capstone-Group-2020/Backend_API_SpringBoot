package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.util.List;

/**
 * This class is a Spring controller which serializes
 * every reporting feature related request handling methods.
 * This controller uses ReportService.
 * This allows the cross origin request from all host
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@CrossOrigin
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * get revenue report list.
     * There are 2 types(period/data) of report based on 'key' value
     * All parameters are required
     *
     * @param key    - period/data
     * @param type   - prod/cate (product or category)
     * @param year   - fiscal year
     * @param term   - week/month
     * @param idList - list of category id or product id
     * @return List of the Mapped object(2 types of Mapped object, it is depend on key's value)
     * @throws ParseException the parse exception
     */
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
