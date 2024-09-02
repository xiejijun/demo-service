package com.wisenable.dms.exception;

import com.wisenable.dms.constants.ExceptionConstants;
import org.slf4j.Logger;

public class DMSException {

    public static void readFail(Logger logger, Exception e) {
        logger.error("异常码[{}],异常提示[{}],异常[{}]",
                ExceptionConstants.DATA_READ_FAIL_CODE, ExceptionConstants.DATA_READ_FAIL_MSG,e);
        throw new BusinessRunTimeException(ExceptionConstants.DATA_READ_FAIL_CODE,
                ExceptionConstants.DATA_READ_FAIL_MSG);
    }

    public static void writeFail(Logger logger, Exception e) {
        logger.error("异常码[{}],异常提示[{}],异常[{}]",
                ExceptionConstants.DATA_WRITE_FAIL_CODE,ExceptionConstants.DATA_WRITE_FAIL_MSG,e);
        throw new BusinessRunTimeException(ExceptionConstants.DATA_WRITE_FAIL_CODE,
                ExceptionConstants.DATA_WRITE_FAIL_MSG);
    }


}
