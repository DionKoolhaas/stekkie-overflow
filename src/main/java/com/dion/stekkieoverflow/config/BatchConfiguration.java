package com.dion.stekkieoverflow.config;

import com.dion.stekkieoverflow.domain.crawler.Document;
import com.dion.stekkieoverflow.domain.crawler.Link;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    private final EntityManagerFactory entityManagerFactory;


    public BatchConfiguration(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public JpaCursorItemReader<Link> jpaCursorItemReader(EntityManagerFactory entityManagerFactory) {
        JpaCursorItemReader<Link> reader = new JpaCursorItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT l FROM Link l WHERE l.document IS NULL");
        return reader;
    }

    @Bean
    public JpaItemWriter<Document> jpaItemWriter() {
        JpaItemWriter<Document> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Job crawlInternetJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("crawlInternetJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step parseDocumentStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, JpaItemWriter<Document> jpaItemWriter, JpaCursorItemReader<Link> reader, DocumentItemProcessor documentItemProcessor) {
        return new StepBuilder("step1", jobRepository)
                .<Link, Document> chunk(10, transactionManager)
                .reader(reader)
                .processor(documentItemProcessor)
                .writer(jpaItemWriter)
                .build();
    }

}