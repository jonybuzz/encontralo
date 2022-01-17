package com.jonybuzz.encontralo.client.facebook.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostAttachmentFbDto {
    private List<PostAttachmentDataFbDto> data;

    @Data
    public static class PostAttachmentDataFbDto {
        private String description;
        private MediaFbDto media;
    }
}
