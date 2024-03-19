package org.example.service;

import io.micrometer.tracing.Tracer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
@RestController
@RequestMapping("service")
public class ServiceApplication {
    @Resource
    private Tracer tracer;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @GetMapping
    public void test() {
        final io.micrometer.tracing.TraceContext context = tracer.currentTraceContext().context();
        final String sleuthTraceId = context.traceId();
        final String sleuthSpanId = context.spanId();
        final String skywalkingTraceId = TraceContext.traceId().substring(0, 32);
        log.debug("skywalkingTraceId:{},sleuthTraceId:{},sleuthSpanId:{}", skywalkingTraceId, sleuthTraceId, sleuthSpanId);
        log.info("skywalkingTraceId:{},sleuthTraceId:{},sleuthSpanId:{}", skywalkingTraceId, sleuthTraceId, sleuthSpanId);
        log.warn("skywalkingTraceId:{},sleuthTraceId:{},sleuthSpanId:{}", skywalkingTraceId, sleuthTraceId, sleuthSpanId);
        log.error("skywalkingTraceId:{},sleuthTraceId:{},sleuthSpanId:{}", skywalkingTraceId, sleuthTraceId, sleuthSpanId);
    }
}
