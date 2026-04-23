package gcash.app.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Balance {

    private final UUID uuid = new Users().getUuid();
    private final LocalDateTime nowTime = LocalDateTime.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public Balance(double amount) {
        this.amount = amount;
    }
    private final String updatedAt = nowTime.format(formatter);
    private double amount;

    //getters
    public UUID getUuid() {
        return uuid;
    }
    public LocalDateTime getNowTime() {
        return nowTime;
    }
    public DateTimeFormatter getFormatter() {
        return formatter;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
