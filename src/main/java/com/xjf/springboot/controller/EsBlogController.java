package com.xjf.springboot.controller;

import com.xjf.springboot.domain.es.EsBlog;
import com.xjf.springboot.repository.es.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xjf
 * @date 2019/1/31 17:53
 */
//测试查询
@RestController
@RequestMapping("/blogs")
public class EsBlogController {

    @Autowired
    private EsBlogRepository esBlogRepository;

    /**
     * 通过条件查询博客
     * @param title
     * @param summary
     * @param content
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping
    public List<EsBlog> select(@RequestParam("title") String title,
                               @RequestParam("summary") String summary,
                               @RequestParam("content") String content,
                               @RequestParam(value = "pageIndex",defaultValue = "0") int pageIndex,
                               @RequestParam(value = "pageSize",defaultValue = "10") int pageSize
                               ){
        Pageable pageable = PageRequest.of(pageIndex,pageSize);

        Page<EsBlog> page = esBlogRepository.findDistinctByTitleIsContainingOrSummaryIsContainingOrContentIsContaining(title, summary, content,pageable);

        return page.getContent();
    }
}
