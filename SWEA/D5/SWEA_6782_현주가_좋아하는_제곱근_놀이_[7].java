import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_6782_곽정민 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine().trim());
            long count = 0;

            // N이 2가 될 때까지, "가장 가까운 완전제곱수까지 채운 뒤 제곱근" 을 반복 (그리디)
            while (N != 2) {
                long k = ceilSqrt(N); // N 이상인 최소의 정수 k (k*k >= N)

                count += (k * k - N); // k*k까지 채우는 증가 연산 횟수
                count += 1;            // 제곱근을 취하는 연산 1회

                N = k;
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb);
    }

    // N 이상인 최소의 정수 k (즉 k*k >= N 을 만족하는 가장 작은 k) 를 반환
    // Math.sqrt의 부동소수점 오차를 보정하기 위해 +-1 범위를 직접 검증한다
    static long ceilSqrt(long N) {
        long k = (long) Math.sqrt((double) N);

        while (k * k < N) k++;             // 혹시 오차로 인해 모자라면 올려주고
        while (k > 1 && (k - 1) * (k - 1) >= N) k--; // 혹시 오차로 인해 너무 크면 내려준다

        return k;
    }
}