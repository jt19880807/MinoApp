package com.minoapp.common.exception;

import com.minoapp.base.BaseException;

/**
 * Created by Devin on 2017/6/27.
 */

public class ApiException extends BaseException {
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
