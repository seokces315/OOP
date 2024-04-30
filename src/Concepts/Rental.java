package Concepts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

class Car {

    // enum 변수
    enum StatusType { available, checkedOut, inService, discarded, sold }

    // static 변수
    public static ArrayList<Car> carList = new ArrayList<>();
    static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    // 멤버 변수
    private int carID; // 1000 ~ 9999
    private StatusType status;
    private final Date datePurchased;
    private int mileage;

    // 클래스 생성자
    Car(Date d, int m) {
        // carID
        // 지역 변수
        Random generator = new Random();
        boolean flag;

        // do~while문
        do {
            // flag 초기화
            flag = true;
            // 범위에 맞게 랜덤 생성
            carID = generator.nextInt(9000) + 1000;

            // 반복문을 통해 중복검사
            for(Car car : carList) {
                // carID 비교
                if(car.carID == this.carID) {
                    flag = false; // flag 변화시키기
                    break;
                }
            }
        } while(!flag);

        // status
        status = StatusType.available;
        // datePurchased
        datePurchased = d;
        // mileage
        mileage = m;

        // 객체를 배열에 저장
        carList.add(this);
    }

    // setter 메서드
    void setMileage(int x) { mileage = x; }
    void setStatus(StatusType s) { status = s; }

    // 마일리지 누적 메서지
    void addMileage(int x) { mileage += x; }

    // 결과 출력 메서드
    void printInfo() {
        System.out.printf("(CarID %d)\n", carID);
        System.out.printf("Status -> %s\n", status.toString());
        System.out.printf("DatePurchased : %s\n", dateformat.format(datePurchased));
        System.out.printf("Mileage = %d\n", mileage);
        System.out.println("-------------------------------------------------------");
    }

}

class Customer {

    // enum 타입
    enum CustomerStatusType { silver, gold, diamond }

    // static 변수
    public static ArrayList<Customer> customerList = new ArrayList<>();

    // 멤버 변수
    private int customerID; // 10000 ~ 99999
    private final String name;
    private final String driverLicense;
    private double creditPoints;
    private int totalRentalFee;
    private CustomerStatusType cStatus;

    // 클래스 생성자
    Customer(String n, String d) {
        // customerID
        // 지역 변수
        Random generator = new Random();
        boolean flag;

        // do~while문
        do {
            // flag 초기화
            flag = true;
            // 범위에 맞게 랜덤 생성
            customerID = generator.nextInt(90000) + 10000;

            // 반복문을 통해 중복검사
            for(Customer customer : customerList) {
                // customerID 비교
                if(customer.customerID == this.customerID) {
                    flag = false; // flag 변화시키기
                    break;
                }
            }
        } while(!flag);

        // name
        name = n;
        // driverLicense
        driverLicense = d;
        // creditPoints
        creditPoints = 0.0;
        // totalRentalFee
        totalRentalFee = 0;
        // cStatus
        cStatus = CustomerStatusType.silver;

        // 객체를 배열에 저장
        customerList.add(this);
    }

    // getter 메서드
    double getPoints() { return creditPoints; }
    CustomerStatusType getCustomerStatus() { return cStatus; }

    // creditPoints 관리 메서드
    void addPoints(int rentalFee) {
        creditPoints += rentalFee * (cStatus == CustomerStatusType.silver ? 0.05
            :cStatus == CustomerStatusType.gold ? 0.1 : 0.2);

        // rentalFee 누적시키기
        totalRentalFee += rentalFee;
    }
    void reducePoints(int points) { creditPoints -= points; }

    // 회원 등급 관리 메서드
    void promote() {
        // 등급 갱신
        cStatus = totalRentalFee >= 500000 ? CustomerStatusType.diamond
            : totalRentalFee >= 100000 ? CustomerStatusType.gold : CustomerStatusType.silver;
    }

    // 결과 출력 메서드
    void printInfo() {
        System.out.printf("(CustomerID %d)\n", customerID);
        System.out.printf("Name : %s\n", name);
        System.out.printf("DriverLicense = %s\n", driverLicense);
        System.out.printf("CreditPoints = %.1f\n", creditPoints);
        System.out.printf("TotalRentalFee = %d\n", totalRentalFee);
        System.out.printf("CStatus -> %s\n", cStatus.toString());
        System.out.println("-------------------------------------------------------");
    }

}

public class Rental {

    // static 변수
    static int totalRentals;
    public static ArrayList<Rental> rentalList = new ArrayList<>();

    // 멤버 변수
    private int rentalID; // 100000 ~ 999999
    private final Customer customer;
    private final Car car;
    private final Date dateOut;
    private Date dateIn;
    private final int fee;

    // 클래스 생성자
    Rental(Customer customer, Car car, Date out, int fee) {
        // rentalID
        // 지역 변수
        Scanner sc = new Scanner(System.in);
        Random generator = new Random();
        boolean flag;
        int spend;

        // do~while문
        do {
            // flag 초기화
            flag = true;
            // 범위에 맞게 랜덤 생성
            rentalID = generator.nextInt(900000) + 100000;

            // 반복문을 통해 중복검사
            for(Rental rental : rentalList) {
                // rentalID 비교
                if(rental.rentalID == this.rentalID) {
                    flag = false; // flag 변화시키기
                    break;
                }
            }
        } while(!flag);

        // customer
        this.customer = customer;
        // car
        this.car = car;
        // date-out
        dateOut = out;
        // fee
        this.fee = fee;

        // Displaying the accumulated points
        System.out.printf("< Accumulated Points : %.1f >\n", customer.getPoints());
        // Ask about points to use
        System.out.print("How many points would you like to spend?\n=> ");
        while ((spend = sc.nextInt()) > (int) customer.getPoints())
            System.out.print("Invalid range of spend, Try Again!\n=> ");
        System.out.println("-------------------------------------------------------");
        // invokes reducePoints()
        customer.reducePoints(spend);

        // 객체를 배열에 저장
        rentalList.add(this);
        // totalRentals 증가시키기
        totalRentals++;
    }

    // Rental 종료 절차와 관련된 메서드
    void returnCar(Date in, int mileage) {
        // Date of car-returned
        dateIn = in;
        // add mileage incurred during rental
        car.addMileage(mileage);
        // invoke addPoints() to calculate & add points
        customer.addPoints(getFee());
    }

    // getter 메서드
    Customer getCustomer() { return customer; }
    int getFee() {return fee; }

    // 결과 출력 메서드
    void printInfo() {
        System.out.printf("(RentalID %d)\n", rentalID);
        System.out.printf("DateOut : %s\n", Car.dateformat.format(dateOut));
        System.out.printf("Fee = %d\n", fee);
        System.out.println("-------------------------------------------------------");
    }

    public static void main(String[] args) throws ParseException {

        // Customer 객체 생성
        Customer cust1 = new Customer("홍길동", "01-12-234567-78");
        Customer cust2 = new Customer("조석현", "00-00-000000-00");
        // 반복문으로 결과 출력
        System.out.println("< Customer Information >");
        System.out.println("-------------------------------------------------------");
        for(Customer cust : Customer.customerList)
            cust.printInfo();
        System.out.println(); // 줄바꿈

        // Car 객체 생성
        Car c1 = new Car(Car.dateformat.parse("2022-11-29"), 50000);
        Car c2 = new Car(Car.dateformat.parse("2023-01-01"), 0);
        Car c3 = new Car(Car.dateformat.parse("2023-02-27"), 2000);
        // 반복문으로 결과 출력
        System.out.println("< Car Information >");
        System.out.println("-------------------------------------------------------");
        for(Car c : Car.carList)
            c.printInfo();
        System.out.println(); // 줄바꿈

        // Rental 계약 생성
        Rental rent1 = new Rental(cust1, c3, Car.dateformat.parse("2023-12-27"), 500000);
        Rental rent2 = new Rental(cust2, c1, Car.dateformat.parse("2024-02-26"), 2000000);
        // 반복문으로 결과 출력
        System.out.println("< Rental Information >");
        System.out.printf("TotalRentals -> %d\n", Rental.totalRentals);
        System.out.println("-------------------------------------------------------");
        for(Rental rent : Rental.rentalList)
            rent.printInfo();
        System.out.println(); // 줄바꿈

        // Rental 계약 종료 - 차량 반납
        rent1.returnCar(Car.dateformat.parse("2024-03-04"), 2000);
        rent2.returnCar(Car.dateformat.parse("2024-03-04"), 500);

        // change the status of the customers by invoking promote()
        for(Customer cust : Customer.customerList)
            cust.promote();
        // 반복문으로 결과 출력
        System.out.println("########## Car Returned ##########");
        System.out.println("< Modified Customer Information >");
        System.out.println("-------------------------------------------------------");
        for(Customer cust : Customer.customerList)
            cust.printInfo();
        System.out.println(); // 줄바꿈

        // 첫 번째 Customer의 새로운 Rental 생성
        Rental rent3 = new Rental(cust1, c2, Car.dateformat.parse("2024-03-04"), 5000000);
        // Retnal 계약 종료 - 차량 반납
        rent3.returnCar(Car.dateformat.parse("2024-03-04"), 100);
        // 수정된 첫 번째 Customer의 정보 출력
        System.out.println(); // 줄바꿈
        System.out.println("########## Car Returned ##########");
        System.out.println("< Modified Customer Information >");
        System.out.println("-------------------------------------------------------");
        cust1.printInfo();

    }

}
