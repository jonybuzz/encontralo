package com.jonybuzz.encontralo.client.facebook.dto;

import lombok.Data;

@Data
public class MediaFbDto {
    private ImageFbDto image;

    @Data
    public static class ImageFbDto {
        private String src;
    }
}
