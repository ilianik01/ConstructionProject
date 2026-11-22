import java.time.LocalDate;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Random;

public class Main {

    public static void printInvoiceDetails(Invoice invoice) {
        System.out.println(invoice.getAmount());
        System.out.println(invoice.getinvoiceNumber());
        System.out.println(invoice.getSupplierName());
        System.out.println(invoice.getInvoiceId());
        System.out.println(invoice.getIssueDate());
    }

    public static void main(String[] args) {
        Invoice firstInvoice = new Invoice(new BigDecimal("1500.50"), " A-125", LocalDate.of(2026, 10, 15), "Papadakis", 1);
        Invoice secondInvoice = new Invoice(new BigDecimal("2000"), "A-126", LocalDate.of(2026, 10, 15), "Nikoloudakis", 2);
        Invoice thirdInvoice = new Invoice(new BigDecimal("3000"), "A-127", LocalDate.of(2026, 1, 15), "Papas", 3);


        ArrayList<Invoice> invoices = new ArrayList<>();
        addInvoice(firstInvoice, invoices);
        addInvoice(secondInvoice, invoices);
        addInvoice(thirdInvoice, invoices);













        System.out.println(invoices.size());

        System.out.println(invoices.get(1).

    getSupplierName());

        for(
    int i = 0; i<invoices.size();i++)

    {
        System.out.println(invoices.get(i).getInvoiceId());
    }






        for(
    int i = 0; i<invoices.size();i++)

    {
        printInvoiceDetails(invoices.get(i));
    }

        System.out.println(invoices.size());


    Payment firstPayment = new Payment(1, 1, new BigDecimal("1000"), LocalDate.of(2026, 10, 15), "Papadakis Payment 2909", "concrete payment");



    ArrayList<Payment> payments = new ArrayList<>();
        addPayment(firstPayment, payments, firstInvoice);


    Payment secondPayment = new Payment(2, 1, new BigDecimal("400"), LocalDate.of(2026, 10, 16), "secondtry", "lets");
        addPayment(secondPayment, payments, firstInvoice);

        BigDecimal remainingBalance = calculateRemainingBalance(firstInvoice, payments);
        System.out.println(remainingBalance);




    }

    public static void addPayment(
            Payment newPayment,
            ArrayList<Payment> payments,
            Invoice invoice) {

        if (newPayment.getInvoiceId() != invoice.getInvoiceId()) {
            throw new IllegalArgumentException("IDs do not match");
        }

        BigDecimal remainingBalance =
                calculateRemainingBalance(invoice, payments);

        if (newPayment.getPaymentAmount().compareTo(remainingBalance) > 0) {
            throw new IllegalArgumentException("Payment exceeds the remaining amount");
        }

        payments.add(newPayment);
    }


    public static BigDecimal calculateRemainingBalance(
            Invoice invoice,
            ArrayList<Payment> payments) {

        BigDecimal totalPaid = BigDecimal.ZERO;

        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getInvoiceId() == invoice.getInvoiceId()) {
                totalPaid = totalPaid.add(payments.get(i).getPaymentAmount());
            }
        }

        BigDecimal remainingBalance = invoice.getAmount().subtract(totalPaid);

        return remainingBalance;
    }








    public static void validateUniqueInvoiceId(
            Invoice newInvoice,
            ArrayList<Invoice> invoices) {

        for (int i = 0; i < invoices.size(); i++) {
            if (invoices.get(i).getInvoiceId() == newInvoice.getInvoiceId()) {
                throw new IllegalArgumentException("Duplicate invoice ID");
            }
        }
    }

    public static void addInvoice(
            Invoice newInvoice,
            ArrayList<Invoice> invoices) {

        validateUniqueInvoiceId(newInvoice, invoices);

        invoices.add(newInvoice);
    }



    }




















