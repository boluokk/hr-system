package org.boluo.hr.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author boluo
 * @date 2023/11/17
 */
@Data
@Accessors(chain = true)
public class AllMenu {
    private List<Menu> allMenu;
}
