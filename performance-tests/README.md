# Running performance tests

## Command to run performance tests
```
./gradlew :performance-tests:gatlingRun-net.pydog.performance.DelayServicePerfTest \
-DbaseUrl=<BASE_URL> \
-DdelayValueMs=<DELAY_MS> \
-DstartRequestRate=<START_RATE> \
-DendRequestRate=<END_RATE> \
-DdurationSeconds=<DURATION_SECONDS>
```

`<BASE_URL>` - base URL to reach the service under test. \
`<DELAY_MS>` - delay value in milliseconds requested from the service under test. \
`<START_RATE>` - request rate in requests per second at the beginning of test. \
`<END_RATE>` - request rate in requests per second at the end of test. \
`<DURATION_SECONDS>` - duration of test in seconds. 

## Example

```shell
./gradlew :performance-tests:gatlingRun-net.pydog.performance.DelayServicePerfTest \
-DbaseUrl=http://localhost:8000 \
-DdelayValueMs=200 \
-DstartRequestRate=10 \
-DendRequestRate=500 \
-DdurationSeconds=180
```

## Test report
Open `index.html` found in [performance-tests/build/reports/gatling](performance-tests/build/reports/gatling)
