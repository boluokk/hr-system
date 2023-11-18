package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.InsertSalary;
import org.boluo.hr.pojo.Salary;
import org.boluo.hr.pojo.UploadSalary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工资账套 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class SalaryService {

    @Resource
    private SalaryMapper salaryMapper;

    public List<Salary> selectAllSalary() {
        return salaryMapper.selectAll();
    }

    /**
     * 删除工资账套
     *
     * @param id 工资账套id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 新增工资账套
     *
     * @param insertSalary 工资账套信息
     * @return 结果
     */
    public boolean insert(InsertSalary insertSalary) {
        return salaryMapper.insertSalary(insertSalary) == 1;
    }

    /**
     * 修改工资账套
     *
     * @param uploadSalary 工资账套信息
     * @return 结果
     */
    public boolean update(UploadSalary uploadSalary) {
        return salaryMapper.updateByPrimaryKey(uploadSalary) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids 工资账套id数组
     * @return 结果
     */
    public boolean deleteMany(Integer[] ids) {
        return salaryMapper.deleteMany(ids) == ids.length;
    }
}
