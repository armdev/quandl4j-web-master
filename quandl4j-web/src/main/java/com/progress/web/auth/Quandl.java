package com.progress.web.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author armen.arzumanyan@gmail.com
 */
public class Quandl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private String ex_Dividend;
    private String splitRatio;
    private String adjOpen;
    private String adjHigh;
    private String adjLow;
    private String adjClose;
    private String adjVolume;
    private String symbol;

    public Quandl() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEx_Dividend() {
        return ex_Dividend;
    }

    public void setEx_Dividend(String ex_Dividend) {
        this.ex_Dividend = ex_Dividend;
    }

    public String getSplitRatio() {
        return splitRatio;
    }

    public void setSplitRatio(String splitRatio) {
        this.splitRatio = splitRatio;
    }

    public String getAdjOpen() {
        return adjOpen;
    }

    public void setAdjOpen(String adjOpen) {
        this.adjOpen = adjOpen;
    }

    public String getAdjHigh() {
        return adjHigh;
    }

    public void setAdjHigh(String adjHigh) {
        this.adjHigh = adjHigh;
    }

    public String getAdjLow() {
        return adjLow;
    }

    public void setAdjLow(String adjLow) {
        this.adjLow = adjLow;
    }

    public String getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(String adjClose) {
        this.adjClose = adjClose;
    }

    public String getAdjVolume() {
        return adjVolume;
    }

    public void setAdjVolume(String adjVolume) {
        this.adjVolume = adjVolume;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.open);
        hash = 97 * hash + Objects.hashCode(this.high);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quandl other = (Quandl) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.open, other.open)) {
            return false;
        }
        return Objects.equals(this.high, other.high);
    }

    @Override
    public String toString() {
        return "Quandl" + "id=" + id + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume + ", ex_Dividend=" + ex_Dividend + ", splitRatio=" + splitRatio + ", adjOpen=" + adjOpen + ", adjHigh=" + adjHigh + ", adjLow=" + adjLow + ", adjClose=" + adjClose + ", adjVolume=" + adjVolume + '}';
    }

}
