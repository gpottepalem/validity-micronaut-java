# Overview
[![Build Status](https://travis-ci.org/gpottepalem/validity-micronaut-java.svg?branch=master)](https://travis-ci.org/gpottepalem/validity-micronaut-java)

This is a simple Micronaut microservice with one controller (`CustomerController`) with one action (`list`) 
which loads a csv file with customer data, processes it and lists potential duplicates and non-duplicates found.

This is the same Spring Boot app https://github.com/gpottepalem/validity-springboot-web written in Java with Spring Boot for 
comparing efforts and learning curve.

## Installation
Nothing really is needed. All you need is Java 8.

## Running the app
Simply run the following command from the main project directory:   
`./gradlew run`

## Test the end-point
**End-point: `customer`**  
List customers data processed (html): `curl http://localhost:8080/customer`  

## Tests
### Running
Simply run the following command from the main project directory to run all unit/integration/functional tests:   
`./gradlew clean test`
### Test Reports
Simply run the following command from the main project directory or point your browser to the following
`build/reports/...` directory:  
`open ./build/reports/tests/test/index.html`  