package com.upup.demo.postsystem.bss.qrcode;

import com.upup.demo.postsystem.bss.qrcode.QrcodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author tao.li
 * @Date 2021/2/9 15:14
 */

@Controller
@RequestMapping("/qrcode")
@ResponseBody
public class QrCodeResource {
    @RequestMapping(method = RequestMethod.GET)
    public void getQrCode(HttpServletResponse response, @RequestParam("content") String content) {
        try {
            byte[] image = QrcodeUtil.generate(content);

            //NOTE-UPUP 2021/3/18 上午12:04 : 如果setContentType 在操作OutputStream之后，貌似不work。
            response.setContentType("image/png");
            response.getOutputStream().write(image);
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
