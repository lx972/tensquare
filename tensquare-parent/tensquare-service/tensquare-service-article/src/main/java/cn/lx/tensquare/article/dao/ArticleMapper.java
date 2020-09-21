package cn.lx.tensquare.article.dao;

import cn.lx.tensquare.article.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/****
 * @Author:lx
 * @Description:Article的Dao
 * @Date 2020/9/5 10:33
 *****/
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 文章点赞数增加和减少
     * @param id    文章id
     * @param thumbup   点赞数增加的数量
     */
    @Update("update tb_article set thumbup=thumbup+#{thumbup} where id=#{id}")
    void updateThumbup(@Param("id") String id, @Param("thumbup")int thumbup);
}
