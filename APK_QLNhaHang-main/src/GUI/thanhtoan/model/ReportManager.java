///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package GUI.thanhtoan.model;
//
//import java.util.HashMap;
//import java.util.Map;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.view.JasperViewer;
//import GUI.thanhtoan.model.Parameter_ThanhToan;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRField;
//
///**
// *
// * @author Admin
// */
//
//public class ReportManager {
//
//    private static ReportManager instance;
//
//    private JasperReport reportPay;
//
//    public static ReportManager getInstance() {
//        if (instance == null) {
//            instance = new ReportManager();
//        }
//        return instance;
//    }
//
//    private ReportManager() {
//    }
//
//    public void compileReport() throws JRException {
//        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("src/GUI/thanhtoan/model/HoaDonThanhToan.jrxml"));
//
//    }
//
//    public void printReportPayment(Parameter_ThanhToan data) throws JRException {
//        Map para = new HashMap();
//        para.put("staff", data.getTenNhanVien());
//        para.put("datePay", data.getNgaythanhtoan());
//        para.put("idHoadon", data.getIdhoadon());
//        para.put("total", data.getTotal());
//        para.put("tenBan", data.getTenBan());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getField());
//        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
//        view(print);
//    }
//
//    private void view(JasperPrint print) throws JRException {
//        JasperViewer.viewReport(print, false);
//    }
//}
package GUI.thanhtoan.model;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportPay;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
        try {
            compileReport();
        } catch (JRException ex) {
            System.err.println("Error compiling report: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void compileReport() throws JRException {
        // Đường dẫn của tệp JRXML
        String jrxmlFilePath = "HoaDonThanhToan.jrxml";
        // Biên dịch tệp JRXML
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream(jrxmlFilePath));
    }

    public void printReportPayment(Parameter_ThanhToan data) throws JRException {
        Map para = new HashMap();
        para.put("staff", data.getTenNhanVien());
        para.put("datePay", data.getNgaythanhtoan());
        para.put("idHoadon", data.getIdhoadon());
        para.put("total", data.getTotal());
        para.put("tenBan", data.getTenBan());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getField());

        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
        view(print);
    }

    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
