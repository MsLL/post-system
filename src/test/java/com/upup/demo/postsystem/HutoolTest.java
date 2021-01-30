package com.upup.demo.postsystem;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.DESede;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.junit.jupiter.api.Test;

/**
 * @Author tao.li
 * @Date 2021/1/29 下午8:48
 */
public class HutoolTest {

    @Test
    public  void testMd5() {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digest = md5.digestHex("password_for_hfvj");
        System.out.println(digest);
    }

    @Test
    public void testMd52(){
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digest = md5.digestHex("tbRXq5979d697cc06f903246c2d2d70e07d030--md5(password_for_hfvj)");
        System.out.println(digest);
    }

    @Test
    public  void test3DES() throws UnsupportedEncodingException {
        //1.通过api生成一个秘钥（字节数组）。(秘钥是用一定的算法生成的，不是我们随便给的一个值)。我们要保存这个值，就把它base64编码一下，以字符串的形式保存，我们需要用的时候再解码一下字符串，就得到原秘钥字节数组了。
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
        String base64Str= Base64.getEncoder().encodeToString(key);
        //
        System.out.println(base64Str);


        DESede deSede=SecureUtil.desede(Base64.getDecoder().decode(base64Str));

        byte[] encrypted=deSede.encrypt("xxxxxxxxxxxxxxxxxxxx".getBytes("utf-8"));
        //String encryptHex=

        String encryptHex=deSede.encryptHex("xxxxxxxxxxxxxxxxxxxx");
        System.out.println(encryptHex);

        String deencrypt=new String(deSede.decrypt(HexUtil.decodeHex(encryptHex)));
        System.out.println(deencrypt);
    }
}
