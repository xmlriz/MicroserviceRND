package com.capgemini.models;

import lombok.Builder;
import lombok.Data;


@Builder
public record OrderErrorResponse (
     String errorCode,
     String message
    )
{}