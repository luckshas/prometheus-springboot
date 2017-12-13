package info.luxman.prometheusdemo.info.luxman.prometheusdemo.service.rs;

import info.luxman.prometheusdemo.ServiceA;
import info.luxman.prometheusdemo.exception.CustomErrorType;
import info.luxman.prometheusdemo.model.WOrder;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class Service {


    @Autowired
    private ServiceA serviceA;

    static final Counter postOrderRequests = Counter.build()
            .name("post_order_requests_total").help("Total requests.").register();
    static final Counter getOrderRequests = Counter.build()
            .name("get_order_requests_total").help("Total requests.").register();
    static final Counter getOrderFailedRequests = Counter.build()
            .name("get_order_failed_requests_total").help("Total requests.").register();
    static final Counter requests = Counter.build()
            .name("requests_total").help("Total requests.").register();


    static final Summary requestLatency = Summary.build()
            .name("requests_latency_seconds").help("Request latency in seconds.").register();

    static final Histogram requestLatencyH = Histogram.build()
            .name("requests_latency_seconds_historgam").help("Request latency in seconds.").register();


    @RequestMapping(value="/orders/",method = RequestMethod.POST)
    public WOrder createOrder(@RequestBody WOrder wOrder){
        postOrderRequests.inc();
        requests.inc();
        Summary.Timer timer = requestLatency.startTimer();
        Histogram.Timer timerH =requestLatencyH.startTimer();
        try {
            return serviceA.createWorder(wOrder);
        }
        finally{
            timer.observeDuration();
            timerH.observeDuration();
        }

    }

    @RequestMapping(value="/orders/{id}",method=RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable("id") String id) {
        getOrderRequests.inc();requests.inc();
        Summary.Timer timer = requestLatency.startTimer();
        Histogram.Timer timerH =requestLatencyH.startTimer();
        try {
        WOrder order = serviceA.getWOrder(id);
        if(order!=null) {
            return new ResponseEntity<WOrder>(order, HttpStatus.OK);
        }
        getOrderFailedRequests.inc();
        return new ResponseEntity(new CustomErrorType("Unable to get. order with id " + id + " not found."),
                HttpStatus.NOT_FOUND);
        }
        finally{
            timer.observeDuration();
            timerH.observeDuration();

        }
    }
}
