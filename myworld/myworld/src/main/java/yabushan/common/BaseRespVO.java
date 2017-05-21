package yabushan.common;

/**
 * Created by yabushan
 */
public class BaseRespVO {

    private  Integer code;
    private String message;

    public BaseRespVO() {
    	
    }

    public BaseRespVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
