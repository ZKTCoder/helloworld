package com.example.hello.util;

import com.example.hello.quartz.PrintTimeJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
//
//    @Bean
//    public JobDetail printTimeJobDetail() {
//        return JobBuilder.newJob(PrintTimeJob.class)
//                .withIdentity("PrintTimeJob")
//                .usingJobData("msg", "Hello World")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public Trigger printTimeJobTrigger() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
//        return TriggerBuilder.newTrigger()
//                .forJob(printTimeJobDetail())
//                .withIdentity("printTimeJobTrigger")
//                .withSchedule(cronScheduleBuilder)
//                .build();
//    }

}
