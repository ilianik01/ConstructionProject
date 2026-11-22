import java.time.LocalDate;
import java.math.BigDecimal;

public class Payment {
    private int paymentId;
    private int invoiceId;
    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
    private String transactionReference;
    private String paymentDescription;

    Payment(int paymentId, int invoiceId, BigDecimal paymentAmount, LocalDate paymentDate, String transactionReference, String paymentDescription) {
        if (paymentId <= 0) {
            throw new IllegalArgumentException("Invalid payment Id");

        }

        if (invoiceId <= 0) {
            throw new IllegalArgumentException("Invalid invoice  Id");

        }

        if (paymentAmount == null) {
            throw new IllegalArgumentException("Invalid payment amount");
        }
        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }
        if (paymentDate == null || paymentDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid payment date");
        }

        if (transactionReference == null || transactionReference.isBlank()) {
            throw new IllegalArgumentException("Invalid Reference");

        }

        this.paymentId = paymentId;
        this.invoiceId = invoiceId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.transactionReference = transactionReference;
        this.paymentDescription = paymentDescription;


    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }


    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getTransactionReference() {
        return transactionReference;


    }

    public String getPaymentDescription() {
        return paymentDescription;
    }


}









