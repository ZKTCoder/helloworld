package com.example.hello.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintTimeJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String msg = context.getJobDetail().getJobDataMap().get("msg").toString();

        System.out.println("current time is : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-------" + msg);

    }
}
