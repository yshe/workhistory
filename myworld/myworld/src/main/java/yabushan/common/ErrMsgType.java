package yabushan.common;

import org.junit.internal.runners.statements.Fail;
import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 * Created by vincent on 2015/1/3.
 */
public enum  ErrMsgType {
	
	DEPLOY_SUCCESS(1,"部署成功！"),
	DEPLOY_FAIL(-1,"部署失败！"),
	
	START_FAIL(-1,"启动失败！"),
	START_SUCCESS(1,"启动成功！"),
	START_USER_NULL(-1,"启动时传入的处理人为空！"),
	START_BUSINESS_NULL(-1,"启动时传入的业务主键为空"),
	
	TASK_NOT_FOUND(-1,"任务没有找到"),
	
	SUCCESS(1,"成功！"),
	Fail(-1,"失败！"),
	
	END(10000,"没意思");
	

    Integer errcode;
    String errmsg;

    private ErrMsgType(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }
}
