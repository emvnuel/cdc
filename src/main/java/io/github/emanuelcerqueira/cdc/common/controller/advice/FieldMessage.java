package io.github.emanuelcerqueira.cdc.common.controller.advice;

import lombok.Value;

@Value
public class FieldMessage {

    String fieldName;
    String message;

}