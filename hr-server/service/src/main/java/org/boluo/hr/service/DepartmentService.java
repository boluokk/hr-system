package org.boluo.hr.service;

import org.boluo.hr.exception.BusinessException;
import org.boluo.hr.mapper.DepartmentMapper;
import org.boluo.hr.pojo.DeleteRequestDepartment;
import org.boluo.hr.pojo.Department;
import org.boluo.hr.pojo.InsertRequestDepartment;
import org.boluo.hr.pojo.UploadDepartment;
import org.boluo.hr.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 通过父部门id 返回所有部门
     *
     * @param parentId 部门id
     * @return 部门集合
     */
    public List<Department> selectAllDepart(Integer parentId) {
        return departmentMapper.selectByParentId(parentId);
    }


    /**
     * 新增部门
     *
     * @param department 部门信息
     * @return 结果
     */
    public boolean insert(Department department) {
        return departmentMapper.insertDepartment(department) == 1;
    }


    /**
     * 更新部门
     *
     * @param uploadDepartment 部门信息
     * @return 结果
     */
    public boolean update(UploadDepartment uploadDepartment) {
        return departmentMapper.updateByPrimaryKey(uploadDepartment) == 1;
    }

    /**
     * 最后一次插入部门id
     *
     * @return 部门id
     */
    public int lastInsertId() {
        return departmentMapper.lastInsertId();
    }


    /**
     * 删除部门
     *
     * @param id 部门id
     * @return 结果
     */
    public int delete(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 没有子部门
     *
     * @param id 父部门id
     * @return 结果
     */
    public boolean noChildren(Integer id) {
        return departmentMapper.selectCountByParentId(id) != 0;
    }

    /**
     * 所有开启部门
     *
     * @return 部门集合
     */
    public List<Department> selectAllDepWithDisabled() {
        return departmentMapper.selectAllDepWithDisabled();
    }

    /**
     * 通过部门路径 删除部门
     *
     * @param depPath 部门路径
     * @return 结果
     */
    public boolean deleteByDepPath(String depPath) {
        return departmentMapper.deleteByDepPath(depPath) >= 1;
    }

    /**
     * 通过部门名称 查询部门
     *
     * @param name 部门名称
     * @return 部门集合
     */
    public List<Department> selectByName(String name) {
        return departmentMapper.selectByName(name);
    }

    /**
     * 返回所有部门
     *
     * @return 部门集合
     */
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }


    /**
     * 添加部门
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addDepartment(InsertRequestDepartment insertDepartment) {
        if (CheckUtil.isNull(insertDepartment.getParentIsParent()) ||
                !insertDepartment.getParentIsParent()) {
            UploadDepartment department = new UploadDepartment()
                    .setId(insertDepartment.getParentId())
                    .setIsParent(true);
            if (!update(department)) {
                throw new BusinessException("更新部门失败");
            }
        }
        Department department = new Department()
                .setName(insertDepartment.getChildrenName())
                .setParentId(insertDepartment.getParentId())
                .setEnabled(insertDepartment.getChildrenEnabled())
                .setIsParent(false);
        if (!insert(department)) {
            throw new BusinessException("添加失败");
        }
        int lastInsertId = lastInsertId();
        if (lastInsertId == 0) {
            throw new BusinessException("新插入部门id不能为0");
        }
        UploadDepartment newDepartment = new UploadDepartment()
                .setId(lastInsertId)
                // 拼接部门路径
                .setDepPath(insertDepartment.getParentDepPath() + "." + lastInsertId);
        if (!update(newDepartment)) {
            throw new BusinessException("更新部门失败！");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDepartment(DeleteRequestDepartment deleteRequestDepartment) {
        if (!deleteByDepPath(deleteRequestDepartment.getParentDepPath())) {
            throw new BusinessException("删除路径失败");
        }
        if (!noChildren(deleteRequestDepartment.getParentId())) {
            if (!update(new UploadDepartment()
                    .setId(deleteRequestDepartment.getParentId()).setIsParent(false))) {
                throw new BusinessException("部门更新失败");
            }
            return true;
        }
        throw new BusinessException("部门更新失败");
    }

}
