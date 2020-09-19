package cn.lx.tensquare.utils;

import com.google.common.base.Charsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * cn.lx.tensquare.utils
 *
 * @Author Administrator
 * @date 11:58
 */
public class RSAUtil {

    /**
     * 读取公钥私钥
     * @param keyName
     * @return
     * @throws IOException
     */
    public static String getRsaKey(String keyName) throws IOException {
        //读取到公钥
        ClassPathResource classPathResource=new ClassPathResource(keyName);
        InputStream is = classPathResource.getInputStream();
        return StreamUtils.copyToString(is, Charsets.UTF_8);
    }
}
