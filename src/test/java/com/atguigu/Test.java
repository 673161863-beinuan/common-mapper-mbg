package com.atguigu;

import com.atguigu.shop.entities.Employee;
import com.atguigu.shop.mappers.EmployeeMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.InputStream;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession session = factory.openSession();

        //***********************
        //按照Java方式整合通用Mapper的特殊设置
        //i.创建MapperHelper对象
        MapperHelper mapperHelper = new MapperHelper();

        //ii.通过MapperHelper对象对MyBatis原生的Configuration对象进行处理
        Configuration configuration = session.getConfiguration();
        mapperHelper.processConfiguration(configuration);

        //***********************
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

        List<Employee> employees = mapper.selectAll();
        for (Employee employee : employees) {
            System.out.println(employee);

        }
    }
}
