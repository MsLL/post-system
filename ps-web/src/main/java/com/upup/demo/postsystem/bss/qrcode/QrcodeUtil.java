package com.upup.demo.postsystem.bss.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import javax.naming.OperationNotSupportedException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author tao.li
 * @Date 2021/2/8 22:58
 * <p>
 * https://segmentfault.com/a/1190000023951144
 */
public class QrcodeUtil {
    /**
     * @return png格式的图片数据
     */
    public static byte[] generate(String content) {

        // 设置字符集编码
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            //1.生成qr code数据，主要是涉及编码什么的
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);

            //2。用二维码数据去绘制图片，只有黑(255.0.0.0)白点(0.0.0.0)   16进制的rgb值
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            //3.把图片raw data以png的格式写到输出流(字节数组)
            //https://stackoverflow.com/questions/15414259/java-bufferedimage-to-byte-array-and-back
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();

            //4.返回图片数据
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    ;

    /**
     * 貌似不需要这个功能
     */
    public static byte[] parse(byte[] data) {
        throw new RuntimeException(new OperationNotSupportedException());
    }

    ;
}
