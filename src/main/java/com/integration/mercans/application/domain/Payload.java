package com.integration.mercans.application.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Payload implements Serializable {
    private String employeeCode;
    private String action;
    private Map<?, ?> data;
    private List<PayComponent> payComponents;
}
