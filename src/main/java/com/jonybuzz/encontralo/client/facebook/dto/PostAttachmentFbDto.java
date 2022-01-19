package com.jonybuzz.encontralo.client.facebook.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostAttachmentFbDto {
    private List<PostAttachmentDataFbDto> data;

    @Data
    public static class PostAttachmentDataFbDto {
        private String description; // only available if type photo
        private String type; // album|photo|share
        private MediaFbDto media;
        private PostSubattachmentFbDto subattachments; // if type album, this contains photos

        @Data
        public static class PostSubattachmentFbDto {
            private List<PostAttachmentDataFbDto> data;
        }

    }

}
