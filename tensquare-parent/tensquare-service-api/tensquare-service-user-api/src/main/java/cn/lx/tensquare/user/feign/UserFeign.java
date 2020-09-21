package cn.lx.tensquare.user.feign;

import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.user.pojo.User;
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
@FeignClient(name = "user")
@RequestMapping("/user")
public interface UserFeign {

    /**
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<User> findById(@PathVariable String id);
}
