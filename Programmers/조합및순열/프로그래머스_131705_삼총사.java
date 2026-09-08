class Solution {
    static int answer = 0;
    static int[] result = new int[3];

    public int solution(int[] number) {
        combination(0, 0, number);
        return answer;
    }

    static void combination(int start, int depth, int[] number) {
        if (depth == 3) {
            int sum = 0;
            for (int num : result) {
                sum += num;
            }
            if (sum == 0) {
                answer++;
            }
            return;
        }

        for (int i = start; i < number.length; i++) {
            result[depth] = number[i];
            combination(i + 1, depth + 1, number);
        }
    }
}