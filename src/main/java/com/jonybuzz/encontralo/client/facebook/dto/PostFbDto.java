package com.jonybuzz.encontralo.client.facebook.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PostFbDto {
    private String id;
    private String message;
    private String permalinkUrl;
    private ZonedDateTime createdTime;
    private PostAttachmentFbDto attachment;
}
