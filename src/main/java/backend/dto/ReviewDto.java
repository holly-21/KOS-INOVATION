package backend.dto;

import java.util.Date;

public class ReviewDto {
    private int stationId;
    private String content;
    private int rate;
    private Date createDate;
    private Date fixDate;

    public ReviewDto(){};

    public ReviewDto(int stationId, String content, int rate, Date createDate, Date fixDate) {
        this.stationId = stationId;
        this.content = content;
        this.rate = rate;
        this.createDate = createDate;
        this.fixDate = fixDate;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFixDate() {
        return fixDate;
    }

    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "stationId=" + stationId +
                ", content='" + content + '\'' +
                ", rate=" + rate +
                ", createDate=" + createDate +
                ", fixDate=" + fixDate +
                '}';
    }
}
