package org.example;
import java.util.Scanner;

class CustomerInformation {
    private int customerId;
    private String customerName;
    private double billAmount;
    private int noOfTerms;
    private String branchName;


    public CustomerInformation(int customerId, String customerName, double billAmount, int noOfTerms,
                               String branchName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.billAmount = billAmount;
        this.noOfTerms = noOfTerms;
        this.branchName = branchName;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public int getNoOfTerms() {
        return noOfTerms;
    }

    public void setNoOfTerms(int noOfTerms) {
        this.noOfTerms = noOfTerms;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}

class CoustemerInformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the number of customers: ");
        int numberOfCustomers = scanner.nextInt();


        CustomerInformation[] customers = new CustomerInformation[numberOfCustomers];


        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("Enter details for Customer " + (i + 1) + ":");
            System.out.print("Customer ID: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();

            System.out.print("Bill Amount: ");
            double billAmount = scanner.nextDouble();

            System.out.print("Number of Terms: ");
            int noOfTerms = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Branch Name: ");
            String branchName = scanner.nextLine();

            customers[i] = new CustomerInformation(customerId, customerName, billAmount, noOfTerms, branchName);
        }


        System.out.print("Enter the branch name to find average bill: ");
        String branchToFind = scanner.nextLine();
        double avgBill = findAvgBillByBranch(customers, branchToFind);
        System.out.println("Average Bill for " + branchToFind + ": " + avgBill);


        System.out.print("Enter the bill amount for discount: ");
        double discountBillAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter the starting letter of the name for discount: ");
        String startingLetter = scanner.nextLine();

        CustomerInformation discountedCustomer = discountByBillAmount(customers, discountBillAmount, startingLetter);
        if (discountedCustomer != null) {
            System.out.println("Discounted Bill Amount for " + discountedCustomer.getCustomerName() +
                    ": " + discountedCustomer.getBillAmount());
        } else {
            System.out.println("No matching customer found for discount.");
        }

        scanner.close();
    }


    public static double findAvgBillByBranch(CustomerInformation[] customers, String branchName) {
        double totalBill = 0;
        int count = 0;

        for (CustomerInformation customer : customers) {
            if (customer.getBranchName().equalsIgnoreCase(branchName)) {
                totalBill += customer.getBillAmount();
                count++;
            }
        }

        return (count > 0) ? totalBill / count : 0;
    }


    public static CustomerInformation discountByBillAmount(CustomerInformation[] customers, double billAmount,
                                                           String startingLetterOfName) {
        for (CustomerInformation customer : customers) {
            if (customer.getCustomerName().toUpperCase().startsWith(startingLetterOfName.toUpperCase())
                    && customer.getBillAmount() >= billAmount) {

                double discountedAmount = 0.8 * customer.getBillAmount();
                customer.setBillAmount(discountedAmount);
                return customer;
            } else if (customer.getCustomerName().toUpperCase().startsWith(startingLetterOfName.toUpperCase())) {

                double discountedAmount = 0.9 * customer.getBillAmount();
                customer.setBillAmount(discountedAmount);
                return customer;
            }
        }
        return null;
    }
}