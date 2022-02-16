package com.base.exchange.entity;

import com.base.exchange.util.Utils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExchangeInfo {
    private ZonedDateTime Date;
    private ZonedDateTime PreviousDate;
    private String PreviousURL;
    private ZonedDateTime Timestamp;
    private Object Valute;
    private List<ExchangeRate> rateList;

    public List<ExchangeRate> getRateList() {
        return rateList;
    }

    public void setRateList() {
        this.rateList = Arrays.asList(Utils.getGson().fromJson(Utils.arrayNormalize(getValute().toString()), ExchangeRate[].class));
    }

    public ExchangeInfo(ZonedDateTime date, ZonedDateTime previousDate, String previousURL, ZonedDateTime timestamp, Object valute) {
        Date = date;
        PreviousDate = previousDate;
        PreviousURL = previousURL;
        Timestamp = timestamp;
        Valute = valute;
    }

    public ZonedDateTime getDate() {
        return Date;
    }

    public void setDate(ZonedDateTime date) {
        Date = date;
    }

    public ZonedDateTime getPreviousDate() {
        return PreviousDate;
    }

    public void setPreviousDate(ZonedDateTime previousDate) {
        PreviousDate = previousDate;
    }

    public String getPreviousURL() {
        return PreviousURL;
    }

    public void setPreviousURL(String previousURL) {
        PreviousURL = previousURL;
    }

    public ZonedDateTime getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        Timestamp = timestamp;
    }

    public Object getValute() {
        return Valute;
    }

    public void setValute(Object valute) {
        Valute = valute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeInfo that = (ExchangeInfo) o;
        return Date.equals(that.Date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Date);
    }

    @Override
    public String toString() {
        return "ExchangeInfo{" +
                "Date=" + Date +
                ", PreviousDate=" + PreviousDate +
                ", PreviousURL='" + PreviousURL + '\'' +
                ", Timestamp=" + Timestamp +
                '}';
    }
}
