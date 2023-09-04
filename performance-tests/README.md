# Running performance tests
## Delay service

```shell
./gradlew :performance-tests:gatlingRun-net.pydog.performance.DelayServicePerfTest \
-DbaseUrl=http://localhost:8000 \
-DdelayValueMs=200 \
-DstartRequestRate=10 \
-DendRequestRate=500 \
-DdurationSeconds=180
```
