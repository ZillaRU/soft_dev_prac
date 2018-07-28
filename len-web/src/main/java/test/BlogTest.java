package test;

import com.len.Application;
import com.len.entity.BlogLabel;
import com.len.service.BlogLabelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhuxiaomeng
 * @date 2018/7/28.
 * @email 154040976@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BlogTest {

    @Autowired
    private BlogLabelService blogLabelService;

    /**
     * 新建标签数据
     */
    @Test
    public void insertTestData(){
        String label[]={"java","spring","spring boot","linux","spring cloud",
                "vue","mybatis","git","sql","quartz","centos7","oauth2.0"
        ,"spring security","redis","shiro"};
        List<BlogLabel> blogLabels=new ArrayList<>();
        BlogLabel blogLabel;
        for(int i=0;i<label.length;i++){
            blogLabel=new BlogLabel();
            blogLabel.setId(UUID.randomUUID().toString().replace("-",""));
            blogLabel.setLabelCode(label[i]);
            blogLabel.setLabelName(label[i]);
            blogLabels.add(blogLabel);
        }
       /* boolean isOk = blogLabelService.insertList(blogLabels) > 0;
        System.out.println("录入数据："+isOk);*/

    }
}
