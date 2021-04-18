package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is a business logic to manage report data object
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Service
@Transactional
public class ReportService {

    /**
     * The Repo.
     */
    @Autowired
    ReportRepository repo;

    /**
     * Report prod list.
     *
     * @param type   the type
     * @param year   the year
     * @param term   the term
     * @param idList the id list
     * @return the list
     * @throws ParseException the parse exception
     */
    public List<List<?>> reportProd(String type, String year, String term, Integer[] idList) throws ParseException {

        List<List<?>> returnData = new ArrayList<>();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        int nextYear = Integer.parseInt(year) + 1;

        Date startDate = sdf.parse(year + "-01-01");
        Date endDate = sdf.parse(String.valueOf(nextYear) + "-01-01");
        Date endWeek = sdf.parse(year + "-12-31");

        if (type.equals("prod")) {

            if (term.equals("week")) {

                for (int i = 0; i < idList.length; i++) {

                    List<Map<String, Object>> prodList = repo.prodReport(startDate, endWeek, idList[i]);

                    String check = String.valueOf(prodList.get(0).get("paid_date"));
                    check = check.substring(0, 4);

                    if (!check.equals(year)) {
                        prodList.remove(0);
                    }

                    returnData.add(prodList);
                }
            } else if (term.equals("month")) {

                for (int i = 0; i < idList.length; i++) {

                    List<Map<String, Object>> prodList = repo.prodReportMonth(startDate, endDate, idList[i]);
                    returnData.add(prodList);
                }
            }

        } else if (type.equals("cate")) {

            if (term.equals("week")) {

                for (int i = 0; i < idList.length; i++) {

                    List<Map<String, Object>> prodList = repo.cateReport(startDate, endDate, idList[i]);
                    returnData.add(prodList);
                }
            } else if (term.equals("month")) {

                for (int i = 0; i < idList.length; i++) {

                    List<Map<String, Object>> prodList = repo.cateReportMonth(startDate, endDate, idList[i]);
                    returnData.add(prodList);
                }
            }

        }

        return returnData;
    }

    /**
     * Report period list.
     *
     * @param type   the type
     * @param year   the year
     * @param term   the term
     * @param idList the id list
     * @return the list
     * @throws ParseException the parse exception
     */
    public List<List<?>> reportPeriod(String type, String year, String term, Integer[] idList) throws ParseException {

        List<List<?>> returnData = new ArrayList<>();

        int firstWeek = Integer.parseInt(year + "01");
        int firstMonth = 1;

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        int nextYear = Integer.parseInt(year) + 1;

        Date startDate = sdf.parse(year + "-01-01");
        Date endDate = sdf.parse(String.valueOf(nextYear) + "-01-01");

        if (type.equals("prod")) {

            if (term.equals("week")) {

                for (int w = 0; w < 52; w++) {

                    List<Map<String, Object>> content = new ArrayList<>();

                    for (int i = 0; i < idList.length; i++) {
                        Map<String, Object> prodList = repo.periodReportProdWeek(String.valueOf(firstWeek), idList[i]);
                        content.add(prodList);
                    }
                    returnData.add(content);
                    firstWeek++;
                }
            }

            else if (term.equals("month")) {

                for (int w = 0; w < 12; w++) {

                    List<Map<String, Object>> content = new ArrayList<>();

                    for (int i = 0; i < idList.length; i++) {
                        Map<String, Object> prodList = repo.periodReportProdMonth(startDate, endDate, String.valueOf(firstMonth), idList[i]);
                        content.add(prodList);
                    }
                    returnData.add(content);
                    firstMonth++;
                }
            }
        }

        else if (type.equals("cate")) {

            if (term.equals("week")) {

                for (int w = 0; w < 52; w++) {

                    List<Map<String, Object>> content = new ArrayList<>();

                    for (int i = 0; i < idList.length; i++) {
                        Map<String, Object> prodList = repo.periodCateReport(String.valueOf(firstWeek), idList[i]);
                        content.add(prodList);
                    }
                    returnData.add(content);
                    firstWeek++;
                }
            }

            else if (term.equals("month")) {

                for (int w = 0; w < 12; w++) {

                    List<Map<String, Object>> content = new ArrayList<>();

                    for (int i = 0; i < idList.length; i++) {
                        Map<String, Object> prodList = repo.periodReportCateMonth(startDate, endDate, String.valueOf(firstMonth), idList[i]);
                        content.add(prodList);
                    }
                    returnData.add(content);
                    firstMonth++;
                }
            }
        }
        return returnData;
    }
}