package com.boluo.hr.service.util;

import com.boluo.hr.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/16 - 22:19
 */

public class ExportAndImortExcelUtil {
    public static ResponseEntity<byte[]> exportData(List<Employee> employeeList) {
//        创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
//        够着sheet表单
        String[] titles = {"id", "name", "gender", "birthday", "idcard", "wedlock",
                "nationid", "nativeplace", "politicid", "email", "phone", "address", "departmentid"
                , "joblevelid", "posid", "engageform", "tiptopdegree", "specialty", "school", "begindate",
                "workid", "contractterm", "conversiontime", "begincontract", "endcontract"};
        Sheet sheet = workbook.createSheet();
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 10 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 16 * 256);
        sheet.setColumnWidth(13, 14 * 256);
        sheet.setColumnWidth(14, 14 * 256);
        sheet.setColumnWidth(15, 12 * 256);
        sheet.setColumnWidth(16, 8 * 256);
        sheet.setColumnWidth(17, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 8 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        sheet.setColumnWidth(22, 14 * 256);
        sheet.setColumnWidth(23, 15 * 256);
        sheet.setColumnWidth(24, 15 * 256);
        Row row = sheet.createRow(0);
        AtomicInteger atomicInteger = new AtomicInteger();
        for (String title : titles) {
            Cell cell = row.createCell(atomicInteger.getAndIncrement());
            cell.setCellValue(title);
        }

        AtomicInteger atomicInteger1 = new AtomicInteger(1);
        XSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

//        开始插入值
        for (Employee employee : employeeList) {
            Row row1 = sheet.createRow(atomicInteger1.getAndIncrement());
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue(employee.getId());
            Cell cell2 = row1.createCell(1);
            cell2.setCellValue(employee.getName());
            Cell cell3 = row1.createCell(2);
            cell3.setCellValue(employee.getGender());
            Cell cell4 = row1.createCell(3);
            cell4.setCellValue(employee.getBirthday());
            cell4.setCellStyle(dataStyle);
            Cell cell5 = row1.createCell(4);
            cell5.setCellValue(employee.getIdcard());
            Cell cell6 = row1.createCell(5);
            cell6.setCellValue(employee.getWedlock());
            Cell cell7 = row1.createCell(6);
            // nation
            cell7.setCellValue(employee.getNation().getName());
            Cell cell8 = row1.createCell(7);
            cell8.setCellValue(employee.getNativeplace());
            Cell cell9 = row1.createCell(8);
//           politic
            cell9.setCellValue(employee.getPoliticsstatus().getName());
            Cell cell10 = row1.createCell(9);
            cell10.setCellValue(employee.getEmail());
            Cell cell11 = row1.createCell(10);
            cell11.setCellValue(employee.getPhone());
            Cell cell12 = row1.createCell(11);
            cell12.setCellValue(employee.getAddress());
            Cell cell13 = row1.createCell(12);
//            department
            cell13.setCellValue(employee.getDepartment().getName());
            Cell cell14 = row1.createCell(13);
            cell14.setCellValue(employee.getJoblevel().getName());
            Cell cell15 = row1.createCell(14);
//            postion
            cell15.setCellValue(employee.getPosition().getName());
            Cell cell16 = row1.createCell(15);
            cell16.setCellValue(employee.getEngageform());
            Cell cell17 = row1.createCell(16);
            cell17.setCellValue(employee.getTiptopdegree());
            Cell cell18 = row1.createCell(17);
            cell18.setCellValue(employee.getSpecialty());
            Cell cell19 = row1.createCell(18);
            cell19.setCellValue(employee.getSchool());
            Cell cell20 = row1.createCell(19);
            cell20.setCellValue(employee.getBegindate());
            cell20.setCellStyle(dataStyle);

            Cell cell21 = row1.createCell(20);
            cell21.setCellValue(employee.getWorkid());
            cell21.setCellStyle(dataStyle);
            Cell cell22 = row1.createCell(21);
//              ----------------
            if (employee.getContractterm() == null) {
                cell22.setCellValue(0);
            } else {

                cell22.setCellValue(employee.getContractterm());
            }
            Cell cell23 = row1.createCell(22);
            cell23.setCellValue(employee.getConversiontime());
            cell23.setCellStyle(dataStyle);
            Cell cell24 = row1.createCell(23);
            cell24.setCellValue(employee.getBegincontract());
            cell24.setCellStyle(dataStyle);
            Cell cell25 = row1.createCell(24);
            cell25.setCellValue(employee.getEndcontract());
            cell25.setCellStyle(dataStyle);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment",
                    new String("员工表.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static List<Employee> importData(MultipartFile file, List<Nation> allNation, List<Department> allDepInfo, List<Politicsstatus> allPolitic, List<Joblevel> allJobLevel, List<Position> allPostion) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        int numberOfSheets = workbook.getNumberOfSheets();
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < physicalNumberOfRows; j++) {
                if (j == 0) {
                    continue;
                }
                XSSFRow row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }

                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                Employee employee = new Employee();

                for (int m = 0; m < physicalNumberOfCells; m++) {
                    XSSFCell cell = row.getCell(m);
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            String cellValue = cell.getStringCellValue();
//                            第一行是id
                            switch (m) {
                                case 1:
                                    employee.setName(cellValue);
                                    break;
                                case 2:
                                    employee.setGender(cellValue);
                                    break;
                                case 4:
                                    employee.setIdcard(cellValue);
                                    break;
                                case 5:
                                    employee.setWedlock(cellValue);
                                    break;
                                case 6:
                                    Nation nation = new Nation();
                                    nation.setName(cellValue);
                                    int i1 = allNation.indexOf(nation);
                                    employee.setNationid(allNation.get(i1).getId());
                                    break;
                                case 7:
                                    employee.setNativeplace(cellValue);
                                    break;
                                case 8:
                                    Politicsstatus politicsstatus = new Politicsstatus();
                                    politicsstatus.setName(cellValue);
                                    int i2 = allPolitic.indexOf(politicsstatus);
                                    employee.setPoliticid(allPolitic.get(i2).getId());
                                    break;
                                case 9:
                                    employee.setEmail(cellValue);
                                    break;
                                case 10:
                                    employee.setPhone(cellValue);
                                    break;
                                case 11:
                                    employee.setAddress(cellValue);
                                    break;
                                case 12:
                                    Department department = new Department();
                                    department.setName(cellValue);
                                    int i3 = allDepInfo.indexOf(department);
                                    employee.setDepartmentid(allDepInfo.get(i3).getId());
                                    break;
                                case 13:
                                    Joblevel joblevel = new Joblevel();
                                    joblevel.setName(cellValue);
                                    int i4 = allJobLevel.indexOf(joblevel);
                                    employee.setJoblevelid(allJobLevel.get(i4).getId());
                                    break;
                                case 14:
                                    Position position = new Position();
                                    position.setName(cellValue);
                                    int i5 = allPostion.indexOf(position);
                                    employee.setPosid(allPostion.get(i5).getId());
                                    break;
                                case 15:
                                    employee.setEngageform(cellValue);
                                    break;
                                case 16:
                                    employee.setTiptopdegree(cellValue);
                                    break;
                                case 17:
                                    employee.setSpecialty(cellValue);
                                    break;
                                case 18:
                                    employee.setSchool(cellValue);
                                    break;
                                case 20:
                                    employee.setWorkid(cellValue);
                                    break;
                            }
                            break;
                        default: {
                            switch (m) {
                                case 3:
                                    employee.setBirthday(cell.getDateCellValue());
                                    break;
                                case 19:
                                    employee.setBegindate(cell.getDateCellValue());
                                    break;
                                case 21:
                                    employee.setContractterm(cell.getNumericCellValue());
                                    break;
                                case 22:
                                    employee.setConversiontime(cell.getDateCellValue());
                                    break;
                                case 23:
                                    employee.setBegincontract(cell.getDateCellValue());
                                    break;
                                case 24:
                                    employee.setEndcontract(cell.getDateCellValue());
                                    break;
                            }
                        }
                    }

                }
                list.add(employee);
            }
        }
        return list;
    }
}
