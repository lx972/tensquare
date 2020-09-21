package cn.lx.tensquare.notice.feign;

import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.notice.pojo.Notice;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * cn.lx.tensquare.article.feign
 *
 * @Author Administrator
 * @date 17:07
 */
@FeignClient(name = "notice")
@RequestMapping("/notice")
public interface NoticeFeign {

    /**
     * 新增Notice数据
     * @param notice
     * @return
     */
    @PostMapping
    Result add(@RequestBody @ApiParam(name = "Notice对象",value = "传入JSON数据",required = true) Notice notice);
}
