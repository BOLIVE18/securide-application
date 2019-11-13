package com.securide.securide.back;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class SimpleHttpResponse<T> {
    @NonNull
    private int code;
    private T body;
}
