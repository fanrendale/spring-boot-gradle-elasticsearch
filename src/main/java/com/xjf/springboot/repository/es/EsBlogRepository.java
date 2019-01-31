package com.xjf.springboot.repository.es;

import com.xjf.springboot.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author xjf
 * @date 2019/1/31 11:24
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
    /**
     * 分页查询博客（去重）
     * @param title
     * @param summary
     * @param content
     * @return
     */
    Page<EsBlog> findDistinctByTitleIsContainingOrSummaryIsContainingOrContentIsContaining(String title, String summary, String content, Pageable pageable);
}
