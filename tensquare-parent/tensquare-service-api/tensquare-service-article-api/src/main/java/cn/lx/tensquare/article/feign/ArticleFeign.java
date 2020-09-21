package cn.lx.tensquare.article.feign;

import cn.lx.tensquare.article.pojo.Article;
import cn.lx.tensquare.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * cn.lx.tensquare.article.feign
 *
 * @Author Administrator
 * @date 17:07
 */
@FeignClient(name = "article")
@RequestMapping("/article")
public interface ArticleFeign {

    /**
     * 根据ID查询Article数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Article> findById(@PathVariable String id);
}
