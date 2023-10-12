package org.boluo.hr.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartRequestBean {
    private Integer parentId;
    private String parentDepPath;
    private Boolean parentIsParent;
    private Integer childrenId;
    private String childrenName;
    private Boolean childrenEnabled;
}
