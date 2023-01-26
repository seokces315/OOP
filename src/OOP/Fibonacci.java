package OOP;

import java.util.Scanner;

public class Fibonacci {

    // static 메서드
    static int fibonacci_Num(int n) {

        // n의 값에 따라 조건 분기
        // n이 0 또는 1일 경우 -> 특수
        if(n <= 1) {
            if(n == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else {
            return fibonacci_Num(n - 1) + fibonacci_Num(n - 2);
        }

    }

    public static void main(String[] args) {

        // 사용할 객체 선언
        int n, tmp;
        int sum = 0;
        Scanner sc = new Scanner(System.in);

        // n 입력받기
        System.out.print("Enter the number of Fibonacci numbers to print: ");
        if((n = sc.nextInt()) < 0) {
            System.out.println("잘못된 입력!");
            System.exit(1); // 비정상 종료
        }

        // Fibonacci Number 나열 및 연산
        System.out.printf("Here is the sequence of first %d Fibonacci numbers:\n\t\t\t", n);
        for(int i=0 ; i<n ; i++) {
            tmp = fibonacci_Num(i);

            System.out.printf("%d", tmp);
            if(i == n-1) {
                System.out.println();
            }
            else {
                System.out.print(", ");
            }

            sum += tmp;
        }

        // 원하는 결과 출력
        System.out.printf("The sum of the numbers is %d.\n", sum);

    }

}
