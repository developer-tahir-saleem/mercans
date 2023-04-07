package com.integration.mercans.application.domain;

import com.integration.mercans.jobs.enums.CurrencyType;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayComponent {
    private BigDecimal amount;
    private CurrencyType currency;
    private String startDate;
    private String endDate;
}
