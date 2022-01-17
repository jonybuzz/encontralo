package com.jonybuzz.encontralo.client.facebook.dto;

import lombok.Data;

@Data
public class PagingFbDto {
    private PagingCursorFbDto cursors;

    @Data
    public static class PagingCursorFbDto {
        private String before;
        private String after;
    }

}
