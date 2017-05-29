package yabushan.service.quartz;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class TestTask extends QuartzJobBean{  
    
    Logger log = Logger.getLogger( TestTask.class );  
          
    public void executeAction(){  
        log.info("Hello quartz"+new Date());  
    }  
  
    @Override  
    protected void executeInternal(JobExecutionContext arg0)  
            throws JobExecutionException {  
          
    }  
    
    private static String[] ctx= new String[] { "/applicationContext.xml" };  
    
    public void work()  
       {    
    	log.info("Hello quartz"+new Date());  
       log.error("err>>>>>>>>>>>>>>");
       log.debug("debug>>>>>>>>>>>>>>>>>>>>");
           System.out.println("Quartz的任务调度！！！"+(new Date()).toString());  
       }  
 
    /** 
        * @param args 
        */  
       public static void main(String[] args)  
       {  
           System.out.println("Test start.");  
          // ApplicationContext ctx=new ClassPathXmlApplicationContext(ctx);  
           //如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化  
           //QuartzJob qj=(QuartzJob)ctx.getBean("myJob");  
          // QuartzJobBean qjBean =(QuartzJobBean) ctx.getBean("myJob");
           //qj.work();  
         //  qjBean.wo
           System.out.println("请输入信息：");  
           Scanner input=new Scanner(System.in);  
           int x=input.nextInt();  
           System.out.println(x);  
           System.out.print("Test end..");  
             
 
       }  
}
