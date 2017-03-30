package entity;

import java.util.Date;

/**
 * 交接单类
 *
 * Created by 59480 on 2017/3/19.
 */
public class DeliveryReceipt {
    private int SealNumber;
    private String TheArticleNumberList;
    private String PostingTo;
    private String TransitionTo;
    private Date DRTime;
    private String TCarNo;
    private String Carrier;
    private String Recipient;
    private int CarId;
    private String TheStartingPointLine;
    private String LineAtTheEndOf;
    private int SealNumber1;


    public DeliveryReceipt(int sealNumber, int carId, String theStartingPointLine, String lineAtTheEndOf) {
        SealNumber = sealNumber;
        CarId = carId;
        TheStartingPointLine = theStartingPointLine;
        LineAtTheEndOf = lineAtTheEndOf;
    }

    public DeliveryReceipt(String theArticleNumberList, String postingTo, String transitionTo, Date DRTime, String TCarNo, String carrier, String recipient, int carId, String theStartingPointLine, String lineAtTheEndOf) {
        TheArticleNumberList = theArticleNumberList;
        PostingTo = postingTo;
        TransitionTo = transitionTo;
        this.DRTime = DRTime;
        this.TCarNo = TCarNo;
        Carrier = carrier;
        Recipient = recipient;
        CarId = carId;
        TheStartingPointLine = theStartingPointLine;
        LineAtTheEndOf = lineAtTheEndOf;
    }

    public DeliveryReceipt(int sealNumber, String theArticleNumberList, String postingTo, String transitionTo, Date DRTime, String TCarNo, String carrier, String recipient) {
        SealNumber = sealNumber;
        TheArticleNumberList = theArticleNumberList;
        PostingTo = postingTo;
        TransitionTo = transitionTo;
        this.DRTime = DRTime;
        this.TCarNo = TCarNo;
        Carrier = carrier;
        Recipient = recipient;
    }

    public DeliveryReceipt(int sealNumber, String theArticleNumberList, String postingTo, String transitionTo, Date DRTime, String TCarNo, String carrier, String recipient, int carId, String theStartingPointLine, String lineAtTheEndOf) {
        SealNumber = sealNumber;
        TheArticleNumberList = theArticleNumberList;
        PostingTo = postingTo;
        TransitionTo = transitionTo;
        this.DRTime = DRTime;
        this.TCarNo = TCarNo;
        Carrier = carrier;
        Recipient = recipient;
        CarId = carId;
        TheStartingPointLine = theStartingPointLine;
        LineAtTheEndOf = lineAtTheEndOf;
    }

    public int getSealNumber() {
        return SealNumber;
    }

    public void setSealNumber(int sealNumber) {
        SealNumber = sealNumber;
    }

    public String getTheArticleNumberList() {
        return TheArticleNumberList;
    }

    public void setTheArticleNumberList(String theArticleNumberList) {
        TheArticleNumberList = theArticleNumberList;
    }

    public String getPostingTo() {
        return PostingTo;
    }

    public void setPostingTo(String postingTo) {
        PostingTo = postingTo;
    }

    public String getTransitionTo() {
        return TransitionTo;
    }

    public void setTransitionTo(String transitionTo) {
        TransitionTo = transitionTo;
    }

    public Date getDRTime() {
        return DRTime;
    }

    public void setDRTime(Date DRTime) {
        this.DRTime = DRTime;
    }

    public String getTCarNo() {
        return TCarNo;
    }

    public void setTCarNo(String TCarNo) {
        this.TCarNo = TCarNo;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public int getCarId() {
        return CarId;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    public String getTheStartingPointLine() {
        return TheStartingPointLine;
    }

    public void setTheStartingPointLine(String theStartingPointLine) {
        TheStartingPointLine = theStartingPointLine;
    }

    public String getLineAtTheEndOf() {
        return LineAtTheEndOf;
    }

    public void setLineAtTheEndOf(String lineAtTheEndOf) {
        LineAtTheEndOf = lineAtTheEndOf;
    }

    public int getSealNumber1() {
        return SealNumber1;
    }

    public void setSealNumber1(int sealNumber1) {
        SealNumber1 = sealNumber1;
    }
}
