package com.integration.mercans.application.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class JobDto implements Serializable {
    private String uuid;
    private String fname;
    private List<String> errors;
    private List<Payload> payload;
}
