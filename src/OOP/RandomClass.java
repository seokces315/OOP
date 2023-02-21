package OOP;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RandomClass {

    // static 메서드
    static double std_Cal(int[] arr, double avg) {

        // 지역변수 선언
        double sum = 0.0;

        // 반복문을 통해 표준편차 계산
        for(int i=0 ; i<arr.length ; i++)
            sum += Math.pow(arr[i] - avg, 2);

        // 결과 리턴
        return Math.sqrt(sum / arr.length);

    }

    public static void main(String[] args) {

        // 객체 선언
        Scanner sc = new Scanner(System.in);
        Random generator = new Random();

        // 랜덤하게 생성할 숫자의 개수 입력받기
        // 예외 처리
        int count = 0;
        try {
            System.out.print("Enter the number of random numbers to create : ");
            if((count = sc.nextInt()) < 1) {
                System.out.println("Number is Invalid!");
                System.exit(1); // 비정상 종료
            }
        }
        catch(InputMismatchException e) {
            System.out.println("Number is Invalid!");
            System.exit(1); // 비정상 종료
        }

        // 랜덤하게 생성할 숫자의 범위 입력받기
        System.out.println("Enter the range of the random numbers.");
        System.out.print("lowerBound : ");
        int lowerBound = sc.nextInt();
        System.out.print("upperBound : ");
        int upperBound = sc.nextInt();
        sc.close();

        // 정수형 배열 선언
        int[] numArray = new int[count];

        // 배열에 랜덤하게 생성한 숫자 저장
        for(int i=0 ; i<count ; i++)
            numArray[i] = generator.nextInt(upperBound - lowerBound + 1) + lowerBound;


        // 배열 출력
        System.out.print("The Current Array -> ");
        System.out.println(Arrays.toString(numArray));

        // 배열 Summary
        int max = numArray[0], min = numArray[0], sum = numArray[0];
        for(int i=1 ; i<count ; i++) {
            // max, min 갱신
            if(numArray[i] > max)
                max = numArray[i];
            else if(numArray[i] < min)
                min = numArray[i];

            // 배열의 요소 누적
            sum += numArray[i];
        }
        double avg = (double)sum / count;
        
        // 결과 출력
        System.out.println("-------------------------------------------------------");
        System.out.println("< Summary of Array >");
        System.out.printf("Largest number = %d\n", max);
        System.out.printf("Smallest number = %d\n", min);
        System.out.printf("Average = %.1f\n", avg);
        System.out.printf("Sum = %d\n", sum);
        System.out.printf("Standard Deviation = %.2f\n", std_Cal(numArray, avg));

    }

}
