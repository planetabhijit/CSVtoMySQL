package com.batch.config;

import javax.sql.DataSource;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.batch.model.User;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	Logger logger = org.slf4j.LoggerFactory.getLogger(BatchConfig.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public FlatFileItemReader<User> reader(){
		FlatFileItemReader<User> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("EmployeeDataCSV.csv"));
		reader.setLineMapper(getLineMapper());
		reader.setLinesToSkip(1);
		
		return reader;
		
	}

	private LineMapper<User> getLineMapper() {
		// TODO Auto-generated method stub
		
		DefaultLineMapper<User> lineMapper = new  DefaultLineMapper<>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] {"jobTitle","department","unit","gender"});
		logger.info(lineTokenizer+"linetoken");

		lineTokenizer.setIncludedFields(new int[] {0,1,2,3});
		logger.info(lineTokenizer+"setinvc");

		BeanWrapperFieldSetMapper<User> fieldSetter = new BeanWrapperFieldSetMapper<>();
		fieldSetter.setTargetType(User.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetter);
		logger.info(lineMapper+"linemap");

		return lineMapper;
	}
	
	@Bean
	public UserItemProcessor processor() {
		
		return new UserItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<User> writer(){
		
		JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
		writer.setSql("insert into user(jobTitle,department,unit,gender) values(:jobTitle,:department,:unit,:gender)");
		logger.info(writer+"writerquery");
		writer.setDataSource(this.dataSource);
		
		return writer;
	}
	
	@Bean
	public Job importUserJob() {
	
		return this.jobBuilderFactory.get("USER-IMPORT-JOB").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
	
	@Bean
	public Step step1() {
		
		return this.stepBuilderFactory.get("step1").<User,User>chunk(10).reader(reader()).processor(processor()).writer(writer()).build();
	}
	
}
