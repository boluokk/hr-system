package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 政治面貌 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticsStatus{
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;

}