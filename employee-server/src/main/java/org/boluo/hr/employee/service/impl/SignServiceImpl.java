package org.boluo.hr.employee.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.boluo.hr.employee.mapper.EmployeeSignMapper;
import org.boluo.hr.employee.pojo.EmployeeSign;
import org.boluo.hr.employee.service.SignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author boluo
 * @date 2024/02/09
 */
@Service
public class SignServiceImpl implements SignService {

    @Resource
    private EmployeeSignMapper employeeSignMapper;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private static final byte[] BYTES = new byte[31];

    @Override
    public boolean sign(int employeeId) {
        if (ObjectUtil.isNotNull(employeeSignMapper.findSign(employeeId, SIMPLE_DATE_FORMAT.format(new Date())))) {
            return true;
        }
        EmployeeSign employeeSign = new EmployeeSign();
        employeeSign.setEmployeeId(employeeId);
        employeeSign.setSignTime(new Date());
        return employeeSignMapper.insert(employeeSign) == 1;
    }

    public boolean signOut(int employeeId) {
        return employeeSignMapper.updateSignOut(employeeId, SIMPLE_DATE_FORMAT.format(new Date())) == 1;
    }

    @Override
    public int getCurrentMonthCount(int employeeId) {
        return employeeSignMapper.selectCurMonCnt(employeeId);
    }

    public EmployeeSign getOne(int employeeId) {
        return employeeSignMapper.findSign(employeeId, SIMPLE_DATE_FORMAT.format(new Date()));
    }
}
