package gcash.app.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Balance {
    LocalDateTime nowTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    double amount;
    String updatedAt = nowTime.format(formatter);

    //setters
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public void setNowTime(LocalDateTime nowTime) {
        this.nowTime = nowTime;
    }

}
