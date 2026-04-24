package gcash.app.model;
import java.math.BigDecimal;

import java.util.UUID;

public class Balance {
    Users user = new Users();

    private final UUID uuid = user.getUuid();
    private final String phoneNumber = user.getPhoneNumber();
    private BigDecimal amount = BigDecimal.ZERO;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
