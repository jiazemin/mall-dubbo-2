package com.github.common;

import com.github.common.Const;
import com.github.common.util.GenerateEnumHandler;
import com.github.common.constant.CommonConst;
import org.junit.Test;

public class CommonGenerateEnumHandler {

    @Test
    public void generate() {
        GenerateEnumHandler.generateEnum(getClass(), Const.BASE_PACKAGE, CommonConst.MODULE_NAME);
    }
}
