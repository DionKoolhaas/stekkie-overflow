package com.dion.stekkieoverflow.controller.crawler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
public class BatchJobController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    /**
     * Endpoint to start the crawlInternetJob
     * @return
     * @throws Exception
     */
    @PostMapping("/startjob")
    public ResponseEntity<String> handle() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(job, jobParameters);

        return ResponseEntity.ok("Job gestart");
    }
}
