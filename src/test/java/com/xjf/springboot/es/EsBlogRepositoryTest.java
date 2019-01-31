package com.xjf.springboot.es;

import com.xjf.springboot.domain.es.EsBlog;
import com.xjf.springboot.repository.es.EsBlogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author xjf
 * @date 2019/1/31 11:24
 */

/**
 * 测试 EsBlogRepository
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {
    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before
    public void initRepository(){
        //清楚所有数据
        esBlogRepository.deleteAll();

        //初始化数据
        esBlogRepository.save(new EsBlog("登鹳雀楼","王之涣的登鹳雀楼",
                "白日依山尽，黄河入海楼；欲穷千里目，更上一层楼。"));
        esBlogRepository.save(new EsBlog("相思","王维的相思",
                "红豆生南国，春来发几枝；愿君多采撷，此物最相思"));
        esBlogRepository.save(new EsBlog("静夜思","李白的静夜思",
                "床前明月光，疑是地上霜。举头望明月，低头思故乡。"));
    }

    @Test
   public void testFindDistinctByTitleContainingOrSummaryContainingOrContentContaining(){
        Pageable pageable = PageRequest.of(0,20);

        String title = "思";
        String summary = "相思";
        String content = "相思";
        Page<EsBlog> page = esBlogRepository.findDistinctByTitleIsContainingOrSummaryIsContainingOrContentIsContaining(title, summary, content,pageable);
        //断言判断（期望值）
        assertThat(page.getTotalElements()).isEqualTo(2);

        System.out.println("------------start1");
        for (EsBlog esBlog : page.getContent()) {
            System.out.println(esBlog);
        }
        System.out.println("------------end1");


        title = "相思";
        page = esBlogRepository.findDistinctByTitleIsContainingOrSummaryIsContainingOrContentIsContaining(title, "a", "a",pageable);
//        assertThat(page.getTotalElements()).isEqualTo(1);

        System.out.println("------------start2");
        for (EsBlog esBlog : page.getContent()) {
            System.out.println(esBlog);
        }
        System.out.println("------------end2");

    }
}
