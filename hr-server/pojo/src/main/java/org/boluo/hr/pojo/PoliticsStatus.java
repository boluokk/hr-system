package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 政治面貌 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticsStatus implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;

}