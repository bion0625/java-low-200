package mySample.stock1.model;

import java.util.Date;

import mySample.stock1.util.FormatUtil;

public class Price {
    
    private Date date;
    private long close;
    private long diff;
    private long open;
    private long high;
    private long low;
    private long volume;

    @Override
    public String toString() {
        FormatUtil formatUtil = new FormatUtil();
        String dateString = formatUtil.dateToString(date);
        return String.format(
            "(date: %s, close: %d, diff: %d, open: %d, high: %d, low: %d, volume: %d)", 
            dateString, close, diff, open, high, low, volume
            );
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return this.date;
    }
    public String getDateString() {
        FormatUtil formatUtil = new FormatUtil();
        return formatUtil.dateToString(this.date);
    }
    public void setClose(long close) {
        this.close = close;
    }
    public long getClose() {
        return this.close;
    }
    public void setDiff(long diff) {
        this.diff = diff;
    }
    public long getDiff() {
        return this.diff;
    }
    public void setOpen(long open) {
        this.open = open;
    }
    public long getOpen() {
        return this.open;
    }
    public void setHigh(long high) {
        this.high = high;
    }
    public long getHigh() {
        return this.high;
    }
    public void setLow(long low) {
        this.low = low;
    }
    public long getLow() {
        return this.low;
    }
    public void setVolume(long volume) {
        this.volume = volume;
    }
    public long getVolume() {
        return this.volume;
    }
}
