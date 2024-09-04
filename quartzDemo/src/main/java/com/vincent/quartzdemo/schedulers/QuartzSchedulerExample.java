package com.vincent.quartzdemo.schedulers;

import com.vincent.quartzdemo.jobs.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 9/4/24
 * @Description:
 */
public class QuartzSchedulerExample {

    public static void main(String[] args) throws SchedulerException {
        // 创建作业实例
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("helloJob", "group1").build();

        // 创建trigger，每五秒执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("simpleTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // 获取调度器实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 启动调度器
        scheduler.start();

        // 调度作业
        scheduler.scheduleJob(job, trigger);
    }
}
