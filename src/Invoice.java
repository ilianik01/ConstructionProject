import java.time.LocalDate;
import java.math.BigDecimal;

public class Invoice {
    private BigDecimal amount;
    private String invoiceNumber;
    private LocalDate issueDate;
    private String supplierName;
    private int invoiceId;





    Invoice(BigDecimal amount, String invoiceNumber, LocalDate issueDate, String supplierName, int invoiceId) {

        if (amount == null) {
            throw new IllegalArgumentException("Invalid amount");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if (invoiceNumber == null || invoiceNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid value");
        }


        if (supplierName == null ||supplierName.isBlank()) {
            throw new IllegalArgumentException("Invalid name");
        }


        if (issueDate == null) {
            throw new IllegalArgumentException("Invalid date");
        }

        if (invoiceId <= 0) {
            throw new IllegalArgumentException("Invalid Id");

        }


        this.amount = amount;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.supplierName = supplierName;
        this.invoiceId = invoiceId;





    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getinvoiceNumber() {
        return invoiceNumber;

    }

    public LocalDate getIssueDate(){
        return issueDate;
    }

    public String getSupplierName(){
        return supplierName;
    }

    public int getInvoiceId(){
        return invoiceId;
    }

}
