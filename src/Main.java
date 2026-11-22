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

    public static void main(String[] args) {ArrayList<Invoice> invoices = new ArrayList<>();
        ArrayList<Payment> payments = new ArrayList<>();

        seedData(invoices, payments);

        System.out.println("Invoices: " + invoices.size());
        System.out.println("Payments: " + payments.size());
    }


    public static void seedData(ArrayList<Invoice> invoices, ArrayList<Payment> payments) {

        Random random = new Random();
        int nextPaymentId = 1;

        String[] suppliers = {
                "Papadakis",
                "Nikoloudakis",
                "Papas",
                "Kritikos",
                "Manousakis"
        };

        for (int i = 0; i < 500; i++) {

            int invoiceId = i + 1;
            BigDecimal amount = BigDecimal.valueOf(random.nextInt(50000) + 1);
            String invoiceNumber = "A-" + invoiceId;
            LocalDate issueDate = LocalDate.of(2026, 10, 15);

            int randomIndex = random.nextInt(suppliers.length);
            String supplierName = suppliers[randomIndex];

            Invoice newInvoice = new Invoice(
                    amount,
                    invoiceNumber,
                    issueDate,
                    supplierName,
                    invoiceId
            );

            addInvoice(newInvoice, invoices);

            // Δημιουργία 1–3 πληρωμών
            int numberOfPayments = random.nextInt(3) + 1;

            for (int j = 0; j < numberOfPayments; j++) {

                BigDecimal remainingBalance =
                        calculateRemainingBalance(newInvoice, payments);

                if (remainingBalance.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }

                int maxCents =
                        remainingBalance.movePointRight(2).intValueExact();

                int paymentsLeft = numberOfPayments - j - 1;
                int maxPaymentCents = maxCents - paymentsLeft;

                int randomCents;

                if (paymentsLeft == 0) {
                    randomCents = maxCents;
                } else {
                    randomCents = random.nextInt(maxPaymentCents) + 1;
                }

                BigDecimal paymentAmount =
                        BigDecimal.valueOf(randomCents, 2);

                LocalDate paymentDate =
                        issueDate.plusDays(random.nextInt(31));

                Payment newPayment = new Payment(
                        nextPaymentId,
                        invoiceId,
                        paymentAmount,
                        paymentDate,
                        "Test payment"
                );

                addPayment(newPayment, payments, newInvoice);

                nextPaymentId++;
            }
        }
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




















