package com.sunz.demo01;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

import static javax.servlet.http.HttpServlet.METHOD_OPTIONS;

@SpringBootApplication
public class Demo01Application {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {


        //获取context BeanFactory->Application->ConfigurableApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(Demo01Application.class, args);
        //beanFactoryTest(context);

        /**
         * ApplicationContext 相对 BeanFactory 增加的功能
         */

        /**
         * 1.国际化 MessageSource
         */
        //messageSourceTest(context);

        /**
         * 2.获取资源 Resource -> ResourcePatternResolver
         */
        //resourcePatternResolverTest(context);

        /**
         * 3. 环境变量 EnvironmentCapable  java_home 、获取 application.properties
         */
        //environmentCapableTest(context);
        /**
         * 4. 发布事件 ApplicationEventPublisher  注册发送短信  实现组件之间的解耦
         */
        applicationEventPublisherTest(context);

    }
    private static void beanFactoryTest(ConfigurableApplicationContext context) throws NoSuchFieldException, IllegalAccessException {
        /**
         * BeanFactory 功能  实现类defaultListableBeanFactory
         * DefaultSingletonBeanRegistry 父类  DefaultListableBeanFactory 子类
         *
         */
        System.out.println(context);
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String,Object> singletonMap = (Map<String,Object>)singletonObjects.get(beanFactory);
//        singletonMap.forEach((k,v)->{
//            System.out.println("键:  " + k + " = " + "值 " + v);
//        });
        singletonMap.entrySet().stream().filter(e->e.getKey().startsWith("component"))
                .forEach(e->{
                    System.out.println("键:  " + e.getKey() + " = " + "值 " + e.getValue());
                });
    }

    private static void messageSourceTest(ConfigurableApplicationContext context){
        System.out.println(context.getMessage("hi", null, Locale.CHINESE));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
    }
    private static void resourcePatternResolverTest(ConfigurableApplicationContext context) throws IOException {
        Resource[] resources = context.getResources("classpath:application.properties");
        for (Resource resource : resources){
            System.out.println(resource);
        }
        Resource[] resources1 = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources1){
            System.out.println(resource);
        }
    }
    private static void environmentCapableTest(ConfigurableApplicationContext context){
        ConfigurableEnvironment environment = context.getEnvironment();
        System.out.println(environment.getProperty("java_home"));
        //TODO:获取配置文件中值
        //System.out.println(environment.getProperty("classpath:application.properties"));
    }
    private static void applicationEventPublisherTest(ConfigurableApplicationContext context){
        context.getBean(Component01.class).register();
        context.getBean(Component001.class).register();
    }

}
