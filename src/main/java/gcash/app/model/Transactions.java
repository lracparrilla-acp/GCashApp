package gcash.app.model;

import gcash.app.utils.security.ReferenceNumberGenerator;

import java.math.BigDecimal;
import java.sql.Ref;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class Transactions {
    Users senderUser = new Users();
    Users recipientUser = new Users();
    private final LocalDateTime nowTime = LocalDateTime.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    private String refNum = ReferenceNumberGenerator.generateReferenceNumber();
    private BigDecimal amount;
    private UUID senderUuid = senderUser.getUuid();
    private UUID recipientUuid = recipientUser.getUuid();
    private String updatedAt = nowTime.format(formatter);
    private String[] types = {"cash-in", "send", "receive"};

    //SETTERS
    public void setSenderUser(Users senderUser) {
        this.senderUser = senderUser;
    }

    public void setRecipientUser(Users recipientUser) {
        this.recipientUser = recipientUser;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setSenderUuid(UUID senderUuid) {
        this.senderUuid = senderUuid;
    }

    public void setRecipientUuid(UUID recipientUuid) {
        this.recipientUuid = recipientUuid;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    //GETTERS
    public Users getSenderUser() {
        return senderUser;
    }

    public Users getRecipientUser() {
        return recipientUser;
    }

    public String getRefNum() {
        return refNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UUID getSenderUuid() {
        return senderUuid;
    }

    public UUID getRecipientUuid() {
        return recipientUuid;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Transactions() {
    }
}


