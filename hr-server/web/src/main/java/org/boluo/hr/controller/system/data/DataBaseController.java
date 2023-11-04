package org.boluo.hr.controller.system.data;

import cn.hutool.core.io.FileUtil;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.annotation.uploadFileCheck;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.DataBaseService;
import org.boluo.hr.service.util.CustomFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 数据库备份
 *
 * @author 🍍
 * @date 2023/11/02
 */

@RestController
@RequestMapping("/sys/data")
public class DataBaseController {

    private final DataBaseService dataBaseService;
    private final CustomFileUtil customFileUtil;

    @Autowired
    public DataBaseController(DataBaseService dataBaseService, CustomFileUtil customFileUtil) {
        this.dataBaseService = dataBaseService;
        this.customFileUtil = customFileUtil;
    }

    /**
     * 获取所有表信息
     */
    @GetMapping("/tables/info")
    @Log("获取所有表信息")
    public RespBean findTablesInfo() {
        return RespBean.ok(dataBaseService.selectAllTables());
    }

    /**
     * 备份数据库
     */
    @PostMapping("/backup")
    @Log("备份数据库")
    public ResponseEntity<byte[]> backup() {
        return dataBaseService.backup();
    }

    /**
     * 初始化数据库
     */
    @GetMapping("/init/database")
    @Log("初始化数据库")
    public RespBean initDatabase() {
        if (dataBaseService.truncateTable()) {
            return RespBean.ok();
        }
        return RespBean.error("初始化失败");
    }

    /**
     * 获取备份版本
     */
    @GetMapping("/backup/version")
    @Log("获取备份版本")
    public RespBean getBackupVersion() {
        return RespBean.ok(dataBaseService.getBackupVersion());
    }


    /**
     * 选择备份版本恢复
     */
    @GetMapping("restore/version/{version}")
    @Log("选择备份版本恢复")
    public RespBean selectVersionRestore(@PathVariable("version") String version) {
        String pre = "/history/";
        if (!FileUtil.exist(customFileUtil.getEnhanceServerBackupFilePath(pre + version))) {
            return RespBean.error("文件不存在");
        }
        if (dataBaseService.restoreByVersion(version)) {
            return RespBean.ok("恢复成功", true);
        }
        return RespBean.error("恢复失败");
    }

    /**
     * 上传备份文件
     */
    @PostMapping("/upload")
    @Log("上传备份文件")
    @uploadFileCheck(checkType = uploadFileCheck.checkType.SUFFIX, supportTypeStr = {"sql"})
    public RespBean uploadBackupFile(MultipartFile file) {
        if (!customFileUtil.saveFile(customFileUtil.getEnhanceServerBackupFilePath("/upload/"), file)) {
            return RespBean.error("文件保存失败");
        }
        if (dataBaseService.restoreByUpload(file.getOriginalFilename())) {
            return RespBean.ok("恢复成功", true);
        }
        return RespBean.error("恢复失败");
    }
}
