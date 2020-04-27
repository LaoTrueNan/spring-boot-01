package gzq.upc.exception;

import gzq.upc.enums.ResultEnum;

public class SellException extends RuntimeException {

    private  Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
