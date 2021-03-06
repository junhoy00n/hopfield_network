package deeplearning;

import java.util.Scanner;

public class HopfieldNetworkTest {

	public static void main(String[] args) {
		/*
		 * 학습 패턴 ㄱ
		 *  1  1  1  1
		 * -1 -1 -1  1
		 * -1 -1 -1  1
		 * -1 -1 -1  1
		 * 
		 * 학습 패턴 ㄴ
		 * 1 -1 -1 -1
		 * 1 -1 -1 -1
		 * 1 -1 -1 -1
		 * 1  1  1  1
		 * 
		 * 학습 패턴 ㄷ
		 * 1  1  1  1
		 * 1 -1 -1 -1
		 * 1 -1 -1 -1
		 * 1  1  1  1
		 */
		int[] AstudyPattern = {1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1}; // 학습 패턴 ㄱ
		int[] BstudyPattern = {1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1 , 1, 1, 1}; // 학습 패턴 ㄴ
		int[] CstudyPattern = {1, 1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, 1}; // 학습 패턴 ㄷ
		
		// I 배열
		int[][] Iarr = new int[16][16];
		for(int i=0; i<16; i++) {
			for(int j=0; j<16; j++) {
				if(i == j) {
					Iarr[i][j] = 1;
				}
				else {
					Iarr[i][j] = 0;
				}
			}
		}
		
		// 학습 패턴 ㄱ 가중치 계산
		int[][] Aweight = new int[AstudyPattern.length][AstudyPattern.length];
		for(int i=0; i<AstudyPattern.length; i++) {
			for(int j=0; j<AstudyPattern.length; j++) {
				Aweight[i][j] = AstudyPattern[i] * AstudyPattern[j];
			}
		}
		
		// 학습 패턴 ㄴ 가중치 계산
		int[][] Bweight = new int[BstudyPattern.length][BstudyPattern.length];
		for(int i=0; i<BstudyPattern.length; i++) {
			for(int j=0; j<BstudyPattern.length; j++) {
				Bweight[i][j] = BstudyPattern[i] * BstudyPattern[j];
			}
		}
		
		// 학습 패턴 ㄷ 가중치 계산
		int[][] Cweight = new int[CstudyPattern.length][CstudyPattern.length];
		for(int i=0; i<CstudyPattern.length; i++) {
			for(int j=0; j<CstudyPattern.length; j++) {
				Cweight[i][j] = CstudyPattern[i] * CstudyPattern[j];
			}
		}
		
		// 학습 패턴 ㄱ 가중치 + 학습 패턴 ㄴ 가중치 + 학습 패턴 ㄷ 가중치 - 3 * I 배열
		int[][] Allweight = new int[16][16];
		for(int i=0; i<16; i++) {
			for(int j=0; j<16; j++) {
				Allweight[i][j] = Aweight[i][j] + Bweight[i][j] + Cweight[i][j] - (3 * Iarr[i][j]);
			}
		}
		
		Scanner sc = new Scanner(System.in);
		
		// 배열 입력
		System.out.println("----------------입력 패턴---------------");
		System.out.println("배열을 입력하세요 : ");
		int[] inputPattern = new int[16];
		for(int i=0; i<inputPattern.length; i++) {
			inputPattern[i] = sc.nextInt();
		}
		
		// Net = 입력층 첫 번째 뉴런의 입력 + 초기 출력 * 출력층 첫 번째 뉴런의 강도
		int[] Net = new int[16];
		int[] outputPattern = new int[16];
		int func = 0; // 초기 출력 * 출력층 첫 번째 뉴런의의 강도 계산을 위해 선언
		for(int i=0; i<16; i++) {
			for(int j=0; j<16; j++) {
				func += inputPattern[j] * Allweight[i][j];
			}
			Net[i] = inputPattern[i] + func;
			func = 0;
			
			if(Net[i] > 0) {
				outputPattern[i] = 1;
			}
			
			else if(Net[i] == 0) {
				outputPattern[i] = inputPattern[i];
			}
			
			else if(Net[i] < 0) {
				outputPattern[i] = -1;
			}
			
			if(outputPattern[i] != inputPattern[i]) {
				inputPattern[i] = outputPattern[i];
			}
		}
		
		// 출력의 경우 outputPattern을 1차원 배열로 입력했기 때문에 4 * 4 배열로 출력을 위해 4번째 출력마다 줄바꿈 시행
		System.out.println();
		System.out.println("----------------결과 패턴---------------");
		System.out.println("출력 결과 : ");
		// 출력 결과는 1의 경우 -> ■ / -1인 경우 -> □ 로 출력
		for(int i=0; i<16; i++) {
			if(outputPattern[i] == 1) {
				System.out.print("■ ");
			}
			else if(outputPattern[i] == -1) {
				System.out.print("□ ");
			}
			if(i%4 == 3) {
				System.out.println();
			}
		}
		
	}
}