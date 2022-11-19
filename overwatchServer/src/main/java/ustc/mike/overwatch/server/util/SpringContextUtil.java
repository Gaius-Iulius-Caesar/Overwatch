package ustc.mike.overwatch.server.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>类名  :  SpringContextUtil</p>
 * @author YS
 * @date 2019年4月23日
 * @since JDK 1.8
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }
    public static <T>T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }
    public static <T>T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name,clazz);
    }
}

