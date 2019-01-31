package com.xjf.springboot.domain.es;

/**
 * @author xjf
 * @date 2019/1/31 11:14
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 日志实体类,实现序列化接口
 */
@Document(indexName = "blog",type = "blog")
public class EsBlog implements Serializable {
    private static final long serialVersionUID = 3252682885088888680L;

    //主键
    @Id
    private String id;

    private String title;

    private String summary;

    private String content;

    //JPA规范，改为protected，防止被使用
    protected EsBlog() {
    }

    public EsBlog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EsBlog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
