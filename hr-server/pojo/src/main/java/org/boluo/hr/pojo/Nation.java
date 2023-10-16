package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 民族 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nation implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;

}