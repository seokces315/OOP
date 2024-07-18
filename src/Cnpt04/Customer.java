package Cnpt04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

class Car {

    // enum 변수
    public enum StatusType { available, checkedOut, inService, discarded, sold }

    // static 변수
    public static ArrayList<Car> carList = new ArrayList<>();
    static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    // 멤버 변수
    private int carID; // 1000 ~ 9999
    private StatusType status;
    private Date datePurchased;
    private int mileage;

    // 클래스 생성자
    Car(Date d, int m) {
        // carID
        // 지역 변수
        Random generator = new Random();
        boolean flag;
        // while문
        while (true) {
            // 범위에 맞게 랜덤 생성
            carID = generator.nextInt(9000) + 1000;

            // 반복문을 통해 중복검사
            flag = false;
            for (Car car : carList) {
                // customerID 비교
                if (car.carID == carID) {
                    flag = true; // flag 변화시키기
                    break;
                }
            }

            // flag에 의해 분기
            if(!flag) break;
        }

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

public class Customer {

    // enum 타입
    public enum CustomerStatusType { silver, gold, diamond }

    // static 변수
    public static ArrayList<Customer> cumstomerList = new ArrayList<>();

    // 멤버 변수
    private int customerID; // 10000 ~ 99999
    private String name;
    private String driverLicense;
    private double creditPoints;
    private int totalRentalFee;
    private CustomerStatusType cStatus;

    // 클래스 생성자
    Customer(String n, String d) {
        // customerID
        // 지역 변수
        Random generator = new Random();
        boolean flag;
        // while문
        while(true) {
            // 범위에 맞게 랜덤 생성
            customerID = generator.nextInt(90000) + 10000;

            // 반복문을 통해 중복검사
            flag = false;
            for (Customer customer : cumstomerList) {
                // customerID 비교
                if (customer.customerID == customerID) {
                    flag = true; // flag 변화시키기
                    break;
                }
            }

            // flag에 의해 분기
            if(!flag) break;
        }

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
        cumstomerList.add(this);
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
        System.out.printf("CStatus -> %s\n", cStatus.toString());
        System.out.println("-------------------------------------------------------");
    }

    public static void main(String[] args) throws ParseException {

        // Customer 객체 생성
        Customer cust1 = new Customer("홍길동", "01-12-234567-78");
        Customer cust2 = new Customer("조석현", "00-00-000000-00");
        // 반복문으로 결과 출력
        System.out.println("< Customer Information >");
        System.out.println("-------------------------------------------------------");
        for(Customer customer : cumstomerList)
            customer.printInfo();
        System.out.println(); // 줄바꿈

        // Car 객체 생성
        Car c3 = new Car(Car.dateformat.parse("2022-11-29"), 50000);
        Car c4 = new Car(Car.dateformat.parse("2023-01-01"), 0);
        Car c5 = new Car(Car.dateformat.parse("2023-02-27"), 2000);
        // 반복문으로 결과 출력
        System.out.println("< Car Information >");
        System.out.println("-------------------------------------------------------");
        for(Car car : Car.carList)
            car.printInfo();
        System.out.println(); // 줄바꿈

        // creditPoints 증가시키기
        cust1.addPoints(300000);
        cust1.promote();
        // 반복문으로 결과 출력
        System.out.println("< Customer Information >");
        System.out.println("-------------------------------------------------------");
        for(Customer customer : cumstomerList)
            customer.printInfo();

    }

}
