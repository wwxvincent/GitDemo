package com.vincent.quartzdemo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 9/4/24
 * @Description:
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello Quartz! The job is executing at: " + new java.util.Date());
    }
}
