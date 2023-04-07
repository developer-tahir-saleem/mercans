# Mercans Integration Tools
These Java integration tools can help you to easily parse and generate data files in various formats, making it easier to integrate with other systems and technologies.

## Authors
Tahir Saleem  
[linkedin/developer.tahir.saleem](https://www.linkedin.com/in/developer-tahir-saleem/)

## Getting Started
To get started with the Spring Boot Java Integration Tool, you will need to add the relevant configuration and Data file into resource folder.
Once you have added the resources, you can start using the integration features by configuring the relevant integration components in your application context.
### Executing program
* How to run the program
  - Run tool from command line mode show the output result in console
```
$ ./mvnw spring-boot:run -D"spring-boot.run.profiles"=console
```
  - Run tool from web application show the output result in web browser 
```
$ ./mvnw spring-boot:run -D"spring-boot.run.profiles"=web
```
  - Click this API endpoint [link](http://localhost:8080/jobs/client-a)

### Result Output 
  - Also put the result.json file on project root
```json
{
  "uuid": "90a5de48-5435-413e-8617-31f893c45644",
  "fname": "client-a/input_01.csv",
  "errors": [
    "02EDC235D00C4FF1A7245550E4EC3216 compensation_amount, compensation_effectiveFrom, compensation_effectiveTo invalid!",
    "98CBACE8E0694049AFF768B747441F89 contract_workStartDate, compensation_amount, compensation_effectiveFrom, compensation_effectiveTo invalid!",
    "91E58776B2CC43CDB4C12E02EE7D0169 ACTION, contract_workerId, compensation_amount, compensation_effectiveFrom, compensation_effectiveTo invalid!",
    "65F7F49843B04AE6B871ABBBAB03B8EC TERMINATE_RULE worker_personCode or contract_endDate, compensation_amount, compensation_effectiveFrom, compensation_effectiveTo invalid!",
    "AE14B9E3D40941D7B24119215BBE2F6C pay_amount, pay_effectiveFrom, pay_effectiveTo, contract_workStartDate, compensation_amount, compensation_effectiveFrom, compensation_effectiveTo invalid!",
    "3BCCBF9330164EF79D86F239F9875C32 pay_amount, pay_effectiveFrom, pay_effectiveTo, compensation_amount, compensation_effectiveFrom, compensation_effectiveTo invalid!",
    "6DC7CCC5F48543E5BE6243957EED0A14 pay_amount, contract_workerId, compensation_amount, compensation_effectiveFrom, compensation_effectiveTo invalid!"
  ],
  "payload": [
    {
      "employeeCode": "660831ZXFFPDZS80WU1KTYSTXJAG",
      "action": "change",
      "data": {
        "worker_motherMaidenName": "Vaughn",
        "worker_grandmotherMaidenName": "Lynn",
        "contract_endDate": null,
        "compensation_effectiveTo": "311221",
        "compensation_currencyType": "EUR",
        "contract_workStartDate": "60998",
        "worker_name": "Hazel Webster",
        "contract_signatureDate": "170692",
        "worker_numberOfKids": 2,
        "compensation_type": "health",
        "contract_type": "indefinite",
        "worker_genderType": "female",
        "compensation_effectiveFrom": "10121",
        "compensation_amount": 500,
        "worker_personalCode": "660831ZXFFPDZS80WU1KTYSTXJAG",
        "contract_workerId": "660831F5"
      },
      "payComponents": [
        {
          "amount": 4400,
          "currency": "EUR",
          "startDate": "10122",
          "endDate": "310122"
        }
      ]
    },
    {
      "employeeCode": "310513E0",
      "action": "hire",
      "data": {
        "worker_motherMaidenName": "Ellis",
        "worker_grandmotherMaidenName": "Keller",
        "contract_endDate": null,
        "compensation_effectiveTo": "310122",
        "compensation_currencyType": "EUR",
        "contract_workStartDate": "310513",
        "worker_name": "Brock Salazar",
        "contract_signatureDate": "210692",
        "worker_numberOfKids": 0,
        "compensation_type": "health",
        "contract_type": "indefinite",
        "worker_genderType": "male",
        "compensation_effectiveFrom": "10122",
        "compensation_amount": 100,
        "worker_personalCode": "310513E0",
        "contract_workerId": "80072480"
      },
      "payComponents": [
        {
          "amount": 1400,
          "currency": "EUR",
          "startDate": "10122",
          "endDate": "310122"
        }
      ]
    },
    {
      "employeeCode": "900405O7OLBUVB90WAYHVYKUXKGQ",
      "action": "change",
      "data": {
        "worker_motherMaidenName": "Woodward",
        "worker_grandmotherMaidenName": "Roberts",
        "contract_endDate": null,
        "compensation_effectiveTo": "10722",
        "compensation_currencyType": "EUR",
        "contract_workStartDate": "310515",
        "worker_name": "Cole Sloan",
        "contract_signatureDate": "220692",
        "worker_numberOfKids": 0,
        "compensation_type": "discount",
        "contract_type": "indefinite",
        "worker_genderType": "male",
        "compensation_effectiveFrom": "10122",
        "compensation_amount": 50,
        "worker_personalCode": "900405O7OLBUVB90WAYHVYKUXKGQ",
        "contract_workerId": "90040543"
      },
      "payComponents": [
        {
          "amount": 3000,
          "currency": "EUR",
          "startDate": "10122",
          "endDate": "310122"
        }
      ]
    }
  ]
}
````
### The following listing shows a typical layout:

````text
│   │   │   └───com
│   │   │       └───integration
│   │   │           └───mercans
│   │   │               ├───application
│   │   │               │   ├───controller
│   │   │               │   ├───domain
│   │   │               │   └───service
│   │   │               │       └───impl
│   │   │               ├───configurations
│   │   │               │   ├───domain
│   │   │               │   ├───enums
│   │   │               │   └───service
│   │   │               │       └───impl
│   │   │               ├───jobs
│   │   │               │   ├───domain
│   │   │               │   │   └───exception
│   │   │               │   ├───enums
│   │   │               │   └───service
│   │   │               │       └───impl
│   │   │               └───parsers
│   │   │                   ├───config
│   │   │                   ├───enums
│   │   │                   ├───factory
│   │   │                   └───service
│   │   │                       └───impl
│   │   └───resources
│   │       └───client-a

````

### Reference journey for finding the solutions
Inspiration, code snippets, etc. 
* [Simple flatmapper](https://simpleflatmapper.org/)
* [Java Generics](https://www.baeldung.com/java-generics)
* [Service locator Pattern](https://springframework.guru/service-locator-pattern-in-spring/)
* [Spring Profiles](https://docs.spring.io/spring-boot/docs/1.0.1.RELEASE/reference/html/howto-properties-and-configuration.html)
* [Spring Boot Banner Generator](https://devops.datenkollektiv.de/banner.txt/index.html)
  