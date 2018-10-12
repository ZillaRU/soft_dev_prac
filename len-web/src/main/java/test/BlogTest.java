package test;

import com.len.Application;
import com.len.entity.BlogCategory;
import com.len.service.ArticleCategoryService;
import com.len.service.BlogCategoryService;
import com.len.service.BlogTagService;
import com.len.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
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
    private BlogTagService blogLabelService;

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BlogTagService tagService;

    /**
     * 新建标签数据
     */
    @Test
    public void insertTestData() {
       /* String label[]={"java","spring","spring boot","linux","spring cloud",
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
        }*/
       /* boolean isOk = blogLabelService.insertList(blogLabels) > 0;
        System.out.println("录入数据："+isOk);*/

    }

    @Test
    public void insertCategory() {
        String[] cate = {"Java", "Linux", "架构", "其他"};
        List<BlogCategory> categories = new ArrayList<>();
        int i = 0;
        for (String aCate : cate) {
            BlogCategory category = new BlogCategory();
            category.setId(UUID.randomUUID().toString().replace("-", ""));
            category.setSequence((byte) ++i);
            category.setName(aCate);
            category.setCode(aCate);
            category.setCreateDate(new Date());
            category.setCreateBy("zxm");
            category.setParentId("0");
            categories.add(category);

        }
        categoryService.insertList(categories);
    }
}
