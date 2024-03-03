package com.dion.stekkieoverflow.config;

import com.dion.stekkieoverflow.domain.crawler.Document;
import com.dion.stekkieoverflow.repository.crawler.DocumentRepository;
import com.dion.stekkieoverflow.repository.crawler.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final DocumentRepository documentRepository;
    private final LinkRepository linkRepository;

    @Autowired
    public JobCompletionNotificationListener(DocumentRepository documentRepository,
                                             LinkRepository linkRepository) {
        this.documentRepository = documentRepository;
        this.linkRepository = linkRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");
            documentRepository.findAll().forEach(document -> LOGGER.info("Found < {} > in the database.", document));
            linkRepository.findAll().forEach(link -> LOGGER.info("Found < {} > in the database.", link));
        }
    }
}