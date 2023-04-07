package com.integration.mercans.application.service.impl;


import static com.integration.mercans.jobs.domain.Constants.ENTITY_COMPENSATION;
import static com.integration.mercans.jobs.domain.Constants.ENTITY_CONTRACT;
import static com.integration.mercans.jobs.domain.Constants.ENTITY_PAY;
import static com.integration.mercans.jobs.domain.Constants.ENTITY_WORKER;

import com.integration.mercans.application.domain.JobDto;
import com.integration.mercans.application.domain.PayComponent;
import com.integration.mercans.application.domain.Payload;
import com.integration.mercans.application.service.AbstractJobLauncher;
import com.integration.mercans.application.service.JobLuncher;
import com.integration.mercans.configurations.domain.DynamicConfiguration;
import com.integration.mercans.configurations.domain.DynamicConfigurationField;
import com.integration.mercans.configurations.enums.FieldType;
import com.integration.mercans.configurations.service.DynamicConfigurationService;
import com.integration.mercans.jobs.domain.Job;
import com.integration.mercans.jobs.service.JobsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "integration", name = "launcher", havingValue = "client-a")
public class JobLauncherClientA extends AbstractJobLauncher implements JobLuncher {

  private DynamicConfigurationService<DynamicConfiguration> jsonConfiguration;

  private JobsService<Job, JobDto> csvJobsService;

  public static String CLIENT_DIRECTORY_NAME = "client-a";


  @Autowired
  public JobLauncherClientA(DynamicConfigurationService<DynamicConfiguration> jsonConfiguration,
      JobsService<Job, JobDto> csvJobsService) {
    this.jsonConfiguration = jsonConfiguration;
    this.csvJobsService = csvJobsService;
  }

  @Override
  public JobDto Start() {
//    load Dynamic Json Configuration
    DynamicConfiguration dynamicConfiguration = jsonConfiguration.get();
//    read file name
    String fileName = getFileName(csvJobsService, jsonConfiguration.get(), CLIENT_DIRECTORY_NAME);
//    load Data csv file
    List<Job> jobs = csvJobsService.loadData(fileName);
//    Dynamic mapping
    JobDto jobDto = jobsDtoMapper(jobs, dynamicConfiguration);
//    Print JobDto to Json in log console
    csvJobsService.prettyPrint(jobDto);
    return jobDto;
  }

  /**
   Maps a list of Job objects to a JobDto object using the given DynamicConfiguration.
   @param jobs the list of Job objects to be mapped to JobDto
   @param dynamicConfiguration the DynamicConfiguration object to be used for mapping
   @return a JobDto object that contains the mapped job information
   */
  private JobDto jobsDtoMapper(List<Job> jobs, DynamicConfiguration dynamicConfiguration) {

    JobDto jobDto = new JobDto();
    jobDto.setFname(getFileName(csvJobsService, dynamicConfiguration, CLIENT_DIRECTORY_NAME));
    jobDto.setUuid(String.valueOf(UUID.randomUUID()));
    ArrayList<Payload> payloads = new ArrayList<>();
    ArrayList<PayComponent> payComponents = new ArrayList<>();
    List<String> errors = new ArrayList<>();
    for (Job job : jobs) {
      Payload payload = new Payload();
      PayComponent payComponent = new PayComponent();
      HashMap<String, Object> objectObjectHashMap = new HashMap<>();

      List<String> errorList = validateJob(job, dynamicConfiguration);
//     Validate action mapped accountancy
      String actionCode = getActionCode(dynamicConfiguration, job);

//     Check Errors and add into errorList if data is inconsistent row is discard
      if (errorList.size() > 0 || actionCode == null) {
        String errorMessages = errorList.stream()
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        errorMessages = actionCode == null ? "ACTION, " + errorMessages : errorMessages;
        errors.add(job.getSystemId() + " " + errorMessages + " invalid!");
        continue;
      }
      payload.setAction(actionCode);
      objectObjectHashMap.putAll(job.getCompensation().toMap());
      objectObjectHashMap.putAll(job.getContract().toMap());
      objectObjectHashMap.putAll(job.getWorker().toMap());
      payload.setData(objectObjectHashMap);
      payload.setEmployeeCode(job.getWorker().getPersonalCode());
      payComponent.setAmount(job.getPay().getAmount());
      payComponent.setCurrency(job.getPay().getCurrencyType());
      payComponent.setEndDate(String.valueOf(job.getPay().getEffectiveTo()));
      payComponent.setStartDate(String.valueOf(job.getPay().getEffectiveFrom()));
      payComponents.add(payComponent);
      payload.setPayComponents(List.of(payComponent));
      payloads.add(payload);
    }
    jobDto.setErrors(errors);
    jobDto.setPayload(payloads);
    return jobDto;
  }


  /**
   Retrieves the action code for the given Job object using the provided DynamicConfiguration.
   @param dynamicConfiguration the DynamicConfiguration object to be used for retrieving the action code
   @param job the Job object for which the action code is to be retrieved
   @return a String that represents the action code for the given Job object
   */
  private String getActionCode(DynamicConfiguration dynamicConfiguration, Job job) {
    Optional<DynamicConfigurationField> any = dynamicConfiguration.getFields().stream()
        .filter(
            x -> x.getFieldType().toString().equalsIgnoreCase(FieldType.ActionCode.toString()))
        .findAny();
    return (any.isPresent()) ? dynamicConfiguration.getMappings()
        .get(any.get().getMappingKey()).get(String.valueOf(job.getAction()).toLowerCase()) : "";
  }

  /**

   Validates the provided Job object against the given DynamicConfiguration object and returns a list of error messages
   if the validation fails.
   @param job the Job object to be validated
   @param config the DynamicConfiguration object to be used for validation
   @return a List of String objects containing the error messages generated during validation
   */
  private List<String> validateJob(Job job, DynamicConfiguration config) {
    return Stream.of(
            job.applyBusinessRule(),
            job.getPay().validate(config.getFieldsMap(ENTITY_PAY)),
            job.getWorker().validate(config.getFieldsMap(ENTITY_WORKER)),
            job.getContract().validate(config.getFieldsMap(ENTITY_CONTRACT)),
            job.getCompensation().validate(config.getFieldsMap(ENTITY_COMPENSATION)))
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

}
