package hha.spring.data.dataapi.service;
import hha.spring.data.dataapi.domain.Report;
import hha.spring.data.dataapi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service

@Transactional
public class ReportService {

    @Autowired
    ReportRepository repo;

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
    /*
    public List<List<ReportDto>> reportProd(String type, String year, String term, Integer[] idList) throws ParseException {

        List<List<ReportDto>> returnData = new ArrayList<>();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        int nextYear = Integer.parseInt(year) + 1;

        Calendar c = Calendar.getInstance();

        Date startDate = sdf.parse(year +"-01-01");
        Date dateParam = startDate;
        Date endDate = sdf.parse(String.valueOf(nextYear)+"-01-01");

        if(type.equals("prod")) {

            if (term.equals("week")) {

                for (int i = 0; i < idList.length; i++) {

                    double sum = 0;

                    List<ReportDto> content = new ArrayList<>();
                    int no = 0;

                    while (dateParam.getTime() < endDate.getTime()) {

                        ReportProd con =  prodRepo.prodReportWeekly(endDate, dateParam, idList[i]);
                        content.add(new ReportDto(no, con));

                        c.setTime(dateParam);
                        c.add(Calendar.DATE, 7);
                        Date d = c.getTime();
                        String dd = sdf.format(d);
                        dateParam = sdf.parse(dd);
                        no++;
                    }

                    dateParam = startDate;
                    returnData.add(content);
                }
            }

            else if(term.equals("month")) {

                for (int i = 0; i < idList.length; i++) {

                    List<ReportDto> content = new ArrayList<>();
                    int no = 0;

                    while (dateParam.getTime() < endDate.getTime()) {

                        content.add(new ReportDto(no, prodRepo.prodReportMonthly(endDate, dateParam, idList[i])));

                        c.setTime(dateParam);
                        c.add(Calendar.MONTH, 1);
                        dateParam = c.getTime();

                        no++;
                    }

                    dateParam = startDate;
                    returnData.add(content);
                }
            }
        }

        else if(type.equals("cate")) {

            if (term.equals("week")) {

                for (int i = 0; i < idList.length; i++) {

                    List<ReportDto> content = new ArrayList<>();
                    int no = 0;

                    while (dateParam.getTime() < endDate.getTime()) {

                        content.add(new ReportDto(no, cateRepo.cateReportWeekly(endDate, dateParam, idList[i])));

                        c.setTime(dateParam);
                        c.add(Calendar.DATE, 7);
                        dateParam = c.getTime();

                        no++;
                    }

                    dateParam = startDate;
                    returnData.add(content);
                }
            }

            else if(term.equals("month")) {

                for (int i = 0; i < idList.length; i++) {

                    List<ReportDto> content = new ArrayList<>();
                    int no = 0;

                    while (dateParam.getTime() < endDate.getTime()) {

                        content.add(new ReportDto(no, cateRepo.cateReportMonthly(endDate, dateParam, idList[i])));

                        c.setTime(dateParam);
                        c.add(Calendar.MONTH, 1);
                        dateParam = c.getTime();

                        no++;
                    }

                    dateParam = startDate;
                    returnData.add(content);
                }
            }
        }

        return returnData;
    }
}
*/

