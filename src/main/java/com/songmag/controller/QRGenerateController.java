package com.songmag.controller;

import com.google.zxing.WriterException;
import com.songmag.service.QRGenerate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
public class QRGenerateController {

    private final QRGenerate qrGenerate;

    @GetMapping("/generate")
    public String generateQRCode(
            @RequestParam("data") String data) throws IOException, WriterException {
        return imageToBase64(
                qrGenerate.generateQRCode(data, 300, 300),
                "png"
        );
    }


    public static String imageToBase64(BufferedImage image, String formatName) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, formatName, baos);
        byte[] imageBytes = baos.toByteArray();

        return Base64.getEncoder().encodeToString(imageBytes);
    }

}
