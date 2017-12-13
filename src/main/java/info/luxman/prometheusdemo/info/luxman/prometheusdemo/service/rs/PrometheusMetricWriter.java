package info.luxman.prometheusdemo.info.luxman.prometheusdemo.service.rs;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PrometheusMetricWriter implements MetricWriter {


    @Override
    public void increment(Delta<?> delta) {

    }

    @Override
    public void reset(String s) {

    }

    @Override
    public void set(Metric<?> metric) {

    }
}
