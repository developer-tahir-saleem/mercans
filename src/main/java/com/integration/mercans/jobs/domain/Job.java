package com.integration.mercans.jobs.domain;

import static com.integration.mercans.jobs.domain.Constants.JOB_ACTION_CHANGE;
import static com.integration.mercans.jobs.domain.Constants.JOB_ACTION_HIRE;
import static com.integration.mercans.jobs.domain.Constants.JOB_ACTION_TERMINATE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.simpleflatmapper.map.annotation.Column;

@Getter
@Setter
@ToString
public class Job extends JobRules implements Serializable {

  @Column("SystemId")
  private String systemId;

  @Column("ACTION")
  private String action;

  @Column("worker")
  private Worker worker;

  @Column("contract")
  private Contract contract;

  @Column("pay")
  private Pay pay;

  @Column("compensation")
  private Compensation compensation;

  public List<String> applyBusinessRule() {
    return Stream.of(applyHireRule(), applyChangeRule(), applyTerminateRule())
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  private List<String> applyTerminateRule() {
    List<String> terminateRules = new ArrayList<>();
    if (action != null && action.equalsIgnoreCase(JOB_ACTION_TERMINATE)) {
      if (worker.getPersonalCode() == null || fieldValidDate(contract.getEndDate())) {
        terminateRules.add("TERMINATE_RULE worker_personCode or contract_endDate");
      }
    }
    return terminateRules;
  }

  private List<String> applyChangeRule() {
    List<String> changeRules = new ArrayList<>();
    if (action != null && action.equalsIgnoreCase(JOB_ACTION_CHANGE)) {
      if (worker.getPersonalCode() == null) {
        changeRules.add("CHANGE_RULE worker_personCode");
      }
    }
    return changeRules;
  }

  private List<String> applyHireRule() {
    List<String> hireRules = new ArrayList<>();
    if (action != null && action.equalsIgnoreCase(JOB_ACTION_HIRE)) {
      if (worker.getPersonalCode() == null && !fieldValidDate(contract.getWorkStartDate())) {
        hireRules.add("HIRE_RULE worker_personCode and contract_workStartDate");
      } else {
        if (fieldValidDate(contract.getWorkStartDate())) {
          worker.setPersonalCode(createPersonalCode(contract.getWorkStartDate()));
        }
      }
    }
    return hireRules;
  }

}
