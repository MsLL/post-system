package com.upup.demo.postsystem.bss;

import com.upup.demo.postsystem.util.QrcodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @Author tao.li
 * @Date 2021/2/9 15:14
 */

@Controller
@RequestMapping("/qrcode")
@ResponseBody
public class QrCodeController {
    @RequestMapping(method = RequestMethod.GET)
    public void getQrCode(HttpServletResponse response, @RequestParam("content") String content) {
        try {
            byte[] image = QrcodeUtil.generate(content);

            //如果setContentType 在操作OutputStream之后，貌似不work。
            response.setContentType("image/png");
            response.getOutputStream().write(image);
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
