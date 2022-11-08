package com.overwatch.dataServer;

import com.overwatch.dataServer.dao.RecordMapper;
import com.overwatch.dataServer.dao.ResultMapper;
import com.overwatch.dataServer.utils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ustc.mike.overwatch.common.data.Record;
import ustc.mike.overwatch.common.data.Result;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes=DataServer.class)
public  class DataServerApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	@Autowired
	private ResultMapper resultMapper;
	@Autowired
	private RecordMapper recordMapper;
	@Test
	public void contextLoads() {
		System.out.println(applicationContext);
		resultMapper=applicationContext.getBean(ResultMapper.class);
		recordMapper=applicationContext.getBean(RecordMapper.class);
//		recordService.insertRecord(new Record("123",12.0,"windows",(long)13,8));
		List<Result> results = resultMapper.selectAllUpToDate();
		for(Result result: results)
		{
			System.out.println(result);

		}
//		List<Record> records=recordMapper.selectAll();
//		for(Record record:records)
//		{
//			System.out.println(records);
//		}
	}

}
