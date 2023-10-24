package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.BaseHr;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.UploadHr;

import java.util.List;

public interface HrMapper {

    /**
     * 删除人事
     *
     * @param id 人事id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增人事
     *
     * @param hr 人事信息
     * @return 结果
     */
    int insertHr(UploadHr uploadHr);


    /**
     * 通过人事id 查询人事
     *
     * @param id 人事id
     * @return 人事信息
     */
    Hr selectByPrimaryKey(Integer id);

    /**
     * 修改人事
     *
     * @param hr 人事信息
     * @return 结果
     */
    int updateByPrimaryKey(UploadHr hr);

    /**
     * 通过人事账号 查询人事
     *
     * @param username 账号
     * @return 人事
     */
    Hr loadByUserName(String username);

    /**
     * 通过人事id 查询带角色的人事
     *
     * @param id 人事id
     * @return 带角色人事集合
     */
    List<Hr> selectRoleWithHrById(Integer id);

    /**
     * 查询除当前人事的其他人事
     *
     * @param id 当前人事id
     * @return 人事集合
     */
    List<BaseHr> selectAllExceptCurrentHr(@Param("id") Integer id);

    /**
     * 查询所有人事
     *
     * @return 人事集合
     */
    List<Hr> selectAll();

    /**
     * 通过人事名字查询
     *
     * @param hrName 人事名称
     * @return 人事集合
     */
    List<Hr> selectHrByName(@Param("hrName") String hrName);

    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return 人事信息
     */
    Hr selectByUsername(@Param("username") String username);
}