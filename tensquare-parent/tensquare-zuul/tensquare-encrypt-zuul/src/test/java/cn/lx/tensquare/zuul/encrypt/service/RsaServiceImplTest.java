package cn.lx.tensquare.zuul.encrypt.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.Charsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * cn.lx.tensquare.zuul.encrypt.service
 *
 * @Author Administrator
 * @date 10:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RsaServiceImplTest {

    @Autowired
    private RsaService rsaService;

    /**
     * 加密
     * @throws Exception
     */
    @Test
    public void encode() throws Exception {
        //读取到公钥
        ClassPathResource classPathResource=new ClassPathResource("publicKey.txt");
        InputStream is = classPathResource.getInputStream();
        String publicKey = StreamUtils.copyToString(is, Charsets.UTF_8);
        //json数据加密
        /*Map<String,Object> data=new HashMap<>();
        data.put("name","lx");
        data.put("age","123");*/
        String data="  {\n" +
                "    \"id\": \"242353534653\",\n" +
                "    \"columnid\": \"1\",\n" +
                "    \"userid\": \"12\",\n" +
                "    \"title\": \"东方华府\",\n" +
                "    \"content\": \"给法国恢复\",\n" +
                "    \"image\": \"ddhfhfghfg\",\n" +
                "    \"createtime\": null,\n" +
                "    \"updatetime\": null,\n" +
                "    \"ispublic\": null,\n" +
                "    \"istop\": null,\n" +
                "    \"visits\": null,\n" +
                "    \"thumbup\": null,\n" +
                "    \"comment\": null,\n" +
                "    \"state\": null,\n" +
                "    \"channelid\": null,\n" +
                "    \"url\": null,\n" +
                "    \"type\": null\n" +
                "}";
        String encrypt = rsaService.RSAEncryptDataPEM(data, publicKey);
        System.out.println("encrypt:"+encrypt);

    }


    /**
     * 解密
     * @throws Exception
     */
    @Test
    public void dencode() throws Exception {
        //读取到私钥
        ClassPathResource classPathResource=new ClassPathResource("privateKey.txt");
        InputStream is = classPathResource.getInputStream();
        String privateKey = StreamUtils.copyToString(is, Charsets.UTF_8);
        String s="fSD+ch7irL0AbKKSdF05fKFPpH4CI67rAq6JBDf+xi+dMjK8Dtx59se+j8wSgr5eIh+teJ+D8brkXhYdXqrHW0ZEoCGICmXTVZ6P2jh/61tH8WOORIrMsYVH9No6/2bHCrBgq3Rpbim+jnUL+FNV4eg6SFBSMXNqqyptsOZcPSGdzdXNvf+fbxT5C3a7/moRp4/jouXIyLkNeyMlqTpz30dA/BIxve9vdBskRjYM34bQ5+D/Avh1K+1yDregOeaSc57v9tWo6Lpt3j5TTuvAVukhaSEATHUEeu1SDoCDF9Pq+Ht3H7OgtxXy3KU4DhRyMNbp4/HRYJNBtxmQ+4WePnrVHXylnuKtWp2/DsE8ttA1Gt4nz684IVrZB6+57nnQk8SEZZ5OES0g8tr+R2onuBAQjYHGDPjotT0MYWlmbA53rRD3cezQAt73uy4CqM0rnQtUgWRE6YrTBM3Ge8eXK5jMFed4rvcNilumURgDGHv8nisj5Glj7AZh9KPniEeLMScB0Qg+Iqwvf2FKqUHTbqKW1AJ7oeBWnp2wv7mdOZ4MHbaYibwtXxiG/azKITp64D2v6Wql9OZ4KcX+2/izcfpNwsu/HnIoC2yPd7YoiOYlqEVbeNf4V4At9dFEqJaLK1zVkBM+JAhoFomaM+r/lwaRLNb2YKYs75Rt3uRttTg=";
        String decryptDataPEM = rsaService.RSADecryptDataPEM(s, privateKey);
        System.out.println("decryptDataPEM:"+decryptDataPEM);

    }

}
