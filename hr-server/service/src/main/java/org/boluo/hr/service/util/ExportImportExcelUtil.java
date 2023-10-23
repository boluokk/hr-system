package org.boluo.hr.service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.boluo.hr.pojo.*;
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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 表单导出
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Slf4j
public class ExportImportExcelUtil {
    /**
     * 导出员工信息
     */
    public static ResponseEntity<byte[]> exportEmployeeData(List<Employee> employeeList) throws IOException {
        // 创建工作簿
        ByteArrayOutputStream bass;
        HttpHeaders headers;
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            String[] titles = {"id", "name", "gender", "birthday", "idCard", "wedlock",
                    "nationId", "nativePlace", "politicId", "email", "phone", "address", "departmentId",
                    "jobLevelId", "posId", "engageForm", "tiptopDegree", "specialty", "school", "beginDate",
                    "workId", "contractTerm", "conversionTime", "beginContract", "endContract"};
            // 创建sheet表单
            Sheet sheet = workbook.createSheet();
            for (int i = 0; i < titles.length; i++) {
                sheet.setColumnWidth(i, 5 * 256);
            }
            Row row = sheet.createRow(0);
            AtomicInteger atomicInteger = new AtomicInteger();
            for (String title : titles) {
                Cell cell = row.createCell(atomicInteger.getAndIncrement());
                cell.setCellValue(title);
            }

            AtomicInteger atomicInteger1 = new AtomicInteger(1);
            XSSFCellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

            // 开始插入值
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
                cell5.setCellValue(employee.getIdCard());
                Cell cell6 = row1.createCell(5);
                cell6.setCellValue(employee.getWedlock());
                Cell cell7 = row1.createCell(6);
                // nation
                cell7.setCellValue(employee.getNation().getName());
                Cell cell8 = row1.createCell(7);
                cell8.setCellValue(employee.getNativePlace());
                Cell cell9 = row1.createCell(8);
                // politic
                cell9.setCellValue(employee.getPoliticsStatus().getName());
                Cell cell10 = row1.createCell(9);
                cell10.setCellValue(employee.getEmail());
                Cell cell11 = row1.createCell(10);
                cell11.setCellValue(employee.getPhone());
                Cell cell12 = row1.createCell(11);
                cell12.setCellValue(employee.getAddress());
                Cell cell13 = row1.createCell(12);
                // department
                cell13.setCellValue(employee.getDepartment().getName());
                Cell cell14 = row1.createCell(13);
                cell14.setCellValue(employee.getJobLevel().getName());
                Cell cell15 = row1.createCell(14);
                // position
                cell15.setCellValue(employee.getPosition().getName());
                Cell cell16 = row1.createCell(15);
                cell16.setCellValue(employee.getEngageForm());
                Cell cell17 = row1.createCell(16);
                cell17.setCellValue(employee.getTiptopDegree());
                Cell cell18 = row1.createCell(17);
                cell18.setCellValue(employee.getSpecialty());
                Cell cell19 = row1.createCell(18);
                cell19.setCellValue(employee.getSchool());
                Cell cell20 = row1.createCell(19);
                cell20.setCellValue(employee.getBeginDate());
                cell20.setCellStyle(dataStyle);
                Cell cell21 = row1.createCell(20);
                cell21.setCellValue(employee.getWorkId());
                cell21.setCellStyle(dataStyle);
                Cell cell22 = row1.createCell(21);
                if (employee.getContractTerm() == null) {
                    cell22.setCellValue(0);
                } else {
                    cell22.setCellValue(employee.getContractTerm());
                }
                Cell cell23 = row1.createCell(22);
                cell23.setCellValue(employee.getConversionTime());
                cell23.setCellStyle(dataStyle);
                Cell cell24 = row1.createCell(23);
                cell24.setCellValue(employee.getBeginContract());
                cell24.setCellStyle(dataStyle);
                Cell cell25 = row1.createCell(24);
                cell25.setCellValue(employee.getEndContract());
                cell25.setCellStyle(dataStyle);
            }
            bass = new ByteArrayOutputStream();
            headers = new HttpHeaders();
            try {
                headers.setContentDispositionFormData("attachment",
                        new String("员工表.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                workbook.write(bass);
            } catch (IOException e) {
                log.debug("导出数据异常: {}", e.getMessage());
            }
        }
        return new ResponseEntity<>(bass.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     * 导入员工信息
     */
    public static List<Employee> importEmployeeData(MultipartFile file, List<Nation> allNation, List<Department> allDepInfo, List<PoliticsStatus> allPolitic, List<JobLevel> allJobLevel, List<Position> allPosition) throws IOException {
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
                    if (Objects.requireNonNull(cellType) == CellType.STRING) {
                        String cellValue = cell.getStringCellValue();
                        switch (m) {
                            case 1:
                                employee.setName(cellValue);
                                break;
                            case 2:
                                employee.setGender(cellValue);
                                break;
                            case 4:
                                employee.setIdCard(cellValue);
                                break;
                            case 5:
                                employee.setWedlock(cellValue);
                                break;
                            case 6:
                                Nation nation = new Nation();
                                nation.setName(cellValue);
                                int i1 = allNation.indexOf(nation);
                                employee.setNationId(allNation.get(i1).getId());
                                break;
                            case 7:
                                employee.setNativePlace(cellValue);
                                break;
                            case 8:
                                PoliticsStatus politicsstatus = new PoliticsStatus();
                                politicsstatus.setName(cellValue);
                                int i2 = allPolitic.indexOf(politicsstatus);
                                employee.setPoliticId(allPolitic.get(i2).getId());
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
                                employee.setDepartmentId(allDepInfo.get(i3).getId());
                                break;
                            case 13:
                                JobLevel jobLevel = new JobLevel();
                                jobLevel.setName(cellValue);
                                int i4 = allJobLevel.indexOf(jobLevel);
                                employee.setJobLevelId(allJobLevel.get(i4).getId());
                                break;
                            case 14:
                                Position position = new Position();
                                position.setName(cellValue);
                                int i5 = allPosition.indexOf(position);
                                employee.setPosId(allPosition.get(i5).getId());
                                break;
                            case 15:
                                employee.setEngageForm(cellValue);
                                break;
                            case 16:
                                employee.setTiptopDegree(cellValue);
                                break;
                            case 17:
                                employee.setSpecialty(cellValue);
                                break;
                            case 18:
                                employee.setSchool(cellValue);
                                break;
                            case 20:
                                employee.setWorkId(cellValue);
                                break;
                            default:
                                break;
                        }
                    } else {
                        switch (m) {
                            case 3:
                                employee.setBirthday(cell.getDateCellValue());
                                break;
                            case 19:
                                employee.setBeginDate(cell.getDateCellValue());
                                break;
                            case 21:
                                employee.setContractTerm(cell.getNumericCellValue());
                                break;
                            case 22:
                                employee.setConversionTime(cell.getDateCellValue());
                                break;
                            case 23:
                                employee.setBeginContract(cell.getDateCellValue());
                                break;
                            case 24:
                                employee.setEndContract(cell.getDateCellValue());
                                break;
                        }
                    }

                }
                list.add(employee);
            }
        }
        return list;
    }

    /**
     * 导出员工工资表信息
     */
    public static ResponseEntity<byte[]> exportSalaryTable(List<SalaryTableView> salaryTableView) throws IOException {
        ByteArrayOutputStream bass;
        HttpHeaders headers;
        String[] titles = {
                "员工名", "员工号", "部门名", "职称名", "工资", "罚款", "奖励",
                "奖金", "午餐补助", "交通补助", "个人所得税", "实发工资",
        };
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // 创建sheet表单
            Sheet sheet = workbook.createSheet();
            for (int i = 0; i < titles.length; i++) {
                sheet.setColumnWidth(i, 5 * 256 * 2);
            }
            Row titleRow = sheet.createRow(0);
            AtomicInteger atomicInteger = new AtomicInteger();
            for (String title : titles) {
                Cell cell = titleRow.createCell(atomicInteger.getAndIncrement());
                cell.setCellValue(title);
            }

            XSSFCellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            // 开始插入值
            for (int i = 0; i < salaryTableView.size(); i++) {
                SalaryTableView currentSalaryTableView = salaryTableView.get(i);
                Salary salary = currentSalaryTableView.getSalary();
                Row valueRow = sheet.createRow(i + 1);
                valueRow.createCell(0).setCellValue(currentSalaryTableView.getEmployeeName());
                valueRow.createCell(1).setCellValue(currentSalaryTableView.getWorkId());
                valueRow.createCell(2).setCellValue(currentSalaryTableView.getDepartmentName());
                valueRow.createCell(3).setCellValue(currentSalaryTableView.getJobLevelName());
                valueRow.createCell(4).setCellValue(salary.getAllSalary());
                valueRow.createCell(5).setCellValue(currentSalaryTableView.getPunishment());
                valueRow.createCell(6).setCellValue(currentSalaryTableView.getReward());
                valueRow.createCell(7).setCellValue(salary.getBonus());
                valueRow.createCell(8).setCellValue(salary.getLunchSalary());
                valueRow.createCell(9).setCellValue(salary.getTrafficSalary());
                valueRow.createCell(10).setCellValue(currentSalaryTableView.getTax());
                valueRow.createCell(11).setCellValue(currentSalaryTableView.getWagesPayable());
            }

            bass = new ByteArrayOutputStream();
            headers = new HttpHeaders();
            try {
                headers.setContentDispositionFormData("attachment",
                        new String("工资表.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                workbook.write(bass);
            } catch (IOException e) {
                log.debug("导出数据异常: {}", e.getMessage());
            }
        }
        return new ResponseEntity<>(bass.toByteArray(), headers, HttpStatus.CREATED);
    }
}
