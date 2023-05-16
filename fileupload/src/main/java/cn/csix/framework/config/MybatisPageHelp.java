package cn.csix.framework.config;

/**
 * @author: zrd
 * @Date: 2020/4/16 10:28
 * @Description:
 */

import cn.csix.framework.interceptor.MybatisInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class MybatisPageHelp {


    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;



    @PostConstruct
    public void addPageInterceptor() {

        MybatisInterceptor queryByAppInterceptor = new MybatisInterceptor();

        //先把一般方式配置的属性放进去
        //在把特殊配置放进去，由于close-conn 利用上面方式时，属性名就是 close-conn 而不是 closeConn，所以需要额外的一步
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(queryByAppInterceptor);
        }
    }
}

