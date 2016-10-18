package by.itechart.phonebook.Servlet;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class AutoSenderEmail {
    private Logger log = Logger.getLogger(AutoSenderEmail.class.getName());

    public void start(){
        log.info("Creating auto sender.");
        JobDetail job = JobBuilder.newJob(SenderBirthdays.class)
                .withIdentity("sendBirthdays", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0 6 * * ?"))
                .forJob(job)
                .build();
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            log.info("Starting auto sender.");
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error(e);
        }

    }
}
