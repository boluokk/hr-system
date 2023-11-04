package org.boluo.hr.aop;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.boluo.hr.annotation.UploadFileCheck;
import org.boluo.hr.enums.uploadFileEnum;
import org.boluo.hr.exception.BusinessException;
import org.boluo.hr.util.CheckUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 上传文件AOP
 *
 * @author 🍍
 * @date 2023/11/03
 */

@Component
@Slf4j
@Aspect
public class UploadFileCheckAspect {

    @Before("@annotation(uploadFileCheck)")
    public void checkFile(JoinPoint joinPoint, UploadFileCheck uploadFileCheck) {
        String message = uploadFileCheck.message();
        UploadFileCheck.checkType checkType = uploadFileCheck.checkType();
        uploadFileEnum[] supportType = uploadFileCheck.supportType();
        Set<String> supportTypeStr = new HashSet<>(Arrays.asList(uploadFileCheck.supportTypeStr()));
        if (CheckUtil.isEmpty(supportType) &&
                supportTypeStr.isEmpty()) {
            return;
        }
        MultipartFile file = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof MultipartFile) {
                file = (MultipartFile) arg;
            }
        }
        if (CheckUtil.isNull(file)) {
            return;
        }
        doCheck(file, message, checkType, supportType, supportTypeStr);
    }

    private void doCheck(MultipartFile file, String message,
                         UploadFileCheck.checkType checkType,
                         uploadFileEnum[] supportType,
                         Set<String> supportTypeStr) {
        if (checkType == UploadFileCheck.checkType.SUFFIX) {
            checkSuffix(file, message, supportType, supportTypeStr);
        } else if (checkType == UploadFileCheck.checkType.MAGIC_NUMBER) {
            checkMagicNumber(file, message, supportType);
        } else if (checkType == UploadFileCheck.checkType.SUFFIX_MAGIC_NUMBER) {
            checkSuffix(file, message, supportType, supportTypeStr);
            checkMagicNumber(file, message, supportType);
        }
    }

    private void checkMagicNumber(MultipartFile file, String message, uploadFileEnum[] supportType) {
        String magicNumber = getMagicNumber(file);
        for (uploadFileEnum type : supportType) {
            if (type.getMagic().equalsIgnoreCase(magicNumber)) {
                return;
            }
        }
        throw new BusinessException(message);
    }

    private void checkSuffix(MultipartFile file, String message, uploadFileEnum[] supportType, Set<String> supportTypeStr) {
        String suffixName = FileUtil.extName(file.getOriginalFilename());
        for (uploadFileEnum type : supportType) {
            if (type.getType().equalsIgnoreCase(suffixName)) {
                return;
            }
        }
        if (supportTypeStr.contains(suffixName)) {
            return;
        }

        throw new BusinessException(message);
    }

    private String getMagicNumber(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            StringBuilder magicNumber = new StringBuilder();
            byte[] bytes = new byte[4];
            inputStream.read(bytes);
            for (byte aByte : bytes) {
                magicNumber.append(Integer.toHexString(aByte & 0xFF));
            }
            return magicNumber.toString();
        } catch (IOException e) {
            throw new BusinessException("文件异常");
        } finally {
            if (CheckUtil.isNotNull(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭文件流异常", e);
                }
            }
        }
    }


}
