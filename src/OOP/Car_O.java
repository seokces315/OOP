package OOP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class Car_O {

    // enum 타입
    enum StatusType { available, checkedOut, inService, discarded, sold }

    // static 변수
    static Integer[] idList = new Integer[100]; // contains 메서드 자동 언박싱 X
    static int idCnt = 0;
    static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    // 멤버 변수
    Random generator = new Random();
    private int carID; // 1000 ~ 9999
    private StatusType status;
    private Date datePurchased;
    private int mileage;

    // 클래스 생성자
    Car_O(Date d, int m) {
        // carID
        // 반복문을 통해 중복검사
        while(true) {
            // 범위에 맞게 랜덤 생성
            carID = generator.nextInt(9000) + 1000;

            // 탈출 조건
            if(!(Arrays.asList(idList).contains(carID))) {
                idList[idCnt++] = carID; // static 변수 변화시키기
                break;
            }
        }

        // status
        status = StatusType.available;
        // datePurchased
        datePurchased = d;
        // mileage
        mileage = m;
    }

    // setter 메서드
    void setMileage(int x) { mileage = x; }
    void setStatus(StatusType s) { status = s; }

    // 출력하는 메서드
    public void printInfo() {
        System.out.printf("(CarID %d)\n", carID);
        System.out.printf("Status -> %s\n", status.toString());
        System.out.printf("DatePurchased : %s\n", dateformat.format(datePurchased));
        System.out.printf("Mileage = %d\n", mileage);
        System.out.println("-------------------------------------------------------");
    }

    public static void main(String[] args) throws ParseException {

        // 객체 생성
        Car_O c1 = new Car_O(dateformat.parse("2022-04-05"), 100000);
        Car_O c2 = new Car_O(dateformat.parse("2022-11-17"), 3000);
        Car_O c3 = new Car_O(dateformat.parse("2022-11-29"), 50000);
        Car_O c4 = new Car_O(dateformat.parse("2023-01-01"), 0);
        Car_O c5 = new Car_O(dateformat.parse("2023-02-27"), 2000);

        // 배열에 저장하기
        Car_O[] carArray = new Car_O[] {c1, c2, c3, c4, c5};

        // 결과 출력
        System.out.println("< Car Information >");
        System.out.println("-------------------------------------------------------");
        for (Car_O car : carArray)
            car.printInfo();
        System.out.println(); // 줄바꿈
        // 상태 변경후 재출력
        c1.setStatus(StatusType.checkedOut);
        System.out.println("< Car Information >");
        System.out.println("-------------------------------------------------------");
        for (Car_O car : carArray)
            car.printInfo();
        System.out.println(); // 줄바꿈
        // 날짜 데이터를 기준으로 내림차순 정렬후 재출력
        // Comparator 정의
        Comparator<Car_O> comparator = new Comparator<>() {
            @Override
            public int compare(Car_O a, Car_O b) {
                // 내림차순에 활용되도록 리턴
                return b.datePurchased.compareTo(a.datePurchased);
            }
        };
        Arrays.sort(carArray, comparator);
        // comparator를 람다 함수로 대체 가능
        // (a, b) -> a.datePurchased.compareTo(b.datePurchased)
        System.out.println("< Car Information >");
        System.out.println("-------------------------------------------------------");
        for (Car_O car : carArray)
            car.printInfo();

    }

}
