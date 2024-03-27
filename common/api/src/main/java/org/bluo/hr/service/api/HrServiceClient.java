package org.bluo.hr.service.api;

import org.bluo.global.pojo.RespBean;
import org.bluo.hr.service.api.pojo.SalaryTableSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/09
 */
@FeignClient(value = "hr-service")
public interface HrServiceClient {
    @PostMapping("/sal/table/{pageNum}/{pageSize}")
    RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                      @PathVariable("pageSize") Integer pageSize,
                      @Valid @RequestBody SalaryTableSearch salaryTableSearch);
}
