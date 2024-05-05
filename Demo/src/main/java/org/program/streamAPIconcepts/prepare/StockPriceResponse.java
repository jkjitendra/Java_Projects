package org.program.streamAPIconcepts.prepare;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class StockPriceResponse {

    @JsonProperty("Time Series (5min)")
    private Map<String, Map<String, String>> timeSeries;

    public Map<String, Map<String, String>> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(Map<String, Map<String, String>> timeSeries) {
        this.timeSeries = timeSeries;
    }
}