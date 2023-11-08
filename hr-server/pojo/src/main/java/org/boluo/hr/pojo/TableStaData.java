package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 表的统计数据
 *
 * @author boluo
 * @date 2023/11/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableStaData<T> {
    private List<T> data;
}
