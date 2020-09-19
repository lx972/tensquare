package cn.lx.tensquare.zuul.encrypt.filter;

import cn.lx.tensquare.utils.RSAUtil;
import cn.lx.tensquare.zuul.encrypt.service.RsaService;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cn.lx.tensquare.zuul.encrypt.filter
 *  解密请求体中的json数据
 * @Author Administrator
 * @date 9:58
 */
@Component
public class RSAFilter extends ZuulFilter {

    @Autowired
    private RsaService rsaService;

    /**
     * 定义过滤器的类型
     * PRE：在请求被路由之前调用，可以使用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试Log等。
     * ROUTE：将请求路由到对应的微服务，用于构建发送给微服务的请求。
     * POST：在请求被路由到对应的微服务以后执行，可用来为Response添加HTTP Header、将微服务的Response发送给客户端等。
     * ERROR：在其他阶段发生错误时执行该过滤器。
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 指定过滤器的执行顺序
     * DEBUG_FILTER_ORDER = 1;
     * FORM_BODY_WRAPPER_FILTER_ORDER = -1;
     * PRE_DECORATION_FILTER_ORDER = 5;
     * RIBBON_ROUTING_FILTER_ORDER = 10;
     * SEND_ERROR_FILTER_ORDER = 0;
     * SEND_FORWARD_FILTER_ORDER = 500;
     * SEND_RESPONSE_FILTER_ORDER = 1000;
     * SIMPLE_HOST_ROUTING_FILTER_ORDER = 100;
     * SERVLET_30_WRAPPER_FILTER_ORDER = -2;
     * SERVLET_DETECTION_FILTER_ORDER = -3;
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    /**
     * 是否使用过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取上下文环境
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        //解密后的数据
        String decryptDataPEM=null;
        //取得request请求体中加密的json数据
        try {
            ServletInputStream is = request.getInputStream();
            String encodeString = StreamUtils.copyToString(is, Charsets.UTF_8);
            //判断请求体中是否有数据
            if (!Strings.isNullOrEmpty(encodeString)){
                //私钥解密数据
                decryptDataPEM = rsaService.RSADecryptDataPEM(encodeString, RSAUtil.getRsaKey("privateKey.txt"));
            }
            //判断是否解密成功
            if (!Strings.isNullOrEmpty(decryptDataPEM)){
                //将解密后的数据重新放入请求的流中
                String finalDecryptDataPEM = decryptDataPEM;
                currentContext.setRequest(new HttpServletRequestWrapper(request){
                    /**
                     * 获取流
                     * @return
                     * @throws IOException
                     */
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(finalDecryptDataPEM.getBytes()) {
                        };
                    }

                    /**
                     * 流中数据的长度
                     * @return
                     */
                    @Override
                    public int getContentLength() {
                        return finalDecryptDataPEM.getBytes().length;
                    }

                    /**
                     * 流中数据的长度
                     * @return
                     */
                    @Override
                    public long getContentLengthLong() {
                        return finalDecryptDataPEM.getBytes().length;
                    }
                });
                //设置请求头中的数据类型为appliction/json
                currentContext.addZuulRequestHeader("content-type",MediaType.APPLICATION_JSON_UTF8_VALUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
