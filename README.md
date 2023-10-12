# Virtual Thread Demonstrator

This repository contains code and deployment scrips for performance test environment that compares the performance
of a web application using classic platform threads.

## Test Environment

The environment consists of the following components.

- Delay Service representing an external to the application under test service that it calls using blocking IO.
- Sync Service is an application under test that can be launched in either platform or virtual thread mode.
  This application accepts requests, forwards them to Delay Service using blocking IO, and forwards back the response.
- Gatling Test Suite implementing a test scenario where a stream of requests with linearly increasing QPS is sent
  to the application under test.

To ensure that no other component of the environment except the application under test represents a bottleneck,
more powerful hardware should be used for Delay Service and Gatling Test Suite. The Delay Service can be deployed
either behind a load balancer or on a machine with significantly better specifications that the one used
for Sync Service.

![Test Environment Diagram](doc/test-environment.png)

## Hardware configuration

## Deployment

## Running performance test
