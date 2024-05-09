package mySample.stock1.model;

import java.util.Date;
import java.util.List;

public class Stock {
    private String name; // 종목명
    private String code; // 종목코드
    private int totalPage; // 네이버에서 가져올 전체 페이지
    private List<Price> prices; // 가격정보 리스트

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return this.totalPage;
    }
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
    public List<Price> getPrices() {
        return this.prices;
    }

    class Price {
        private Date date;
        private long close;
        private long diff;
        private long open;
        private long high;
        private long low;
        private long volume;

        public void setDate(Date date) {
            this.date = date;
        }
        public Date getDate() {
            return this.date;
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
}
