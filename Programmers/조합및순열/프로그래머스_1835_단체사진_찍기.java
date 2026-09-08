public class Solution {
    static char[] people = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] visited = new boolean[8];
    static char[] output = new char[8];
    static int answer = 0;
    static String[] data;

    public int solution(int n, String[] data) {
        this.data = data;
        permutation(0);
        return answer;
    }

    static void permutation(int depth) {
        if (depth == 8) {              // 8명을 다 세웠으면
            if (isValid()) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = people[i];   // depth번째 자리에 사람 배치
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isValid() {
        for (String condition : data) {
            char p1 = condition.charAt(0);   // 조건 제시자
            char p2 = condition.charAt(2);   // 상대방
            char op = condition.charAt(3);   // 연산자
            int value = condition.charAt(4) - '0';  // 원하는 간격

            int pos1 = indexOf(p1);
            int pos2 = indexOf(p2);
            int gap = Math.abs(pos1 - pos2) - 1;    // 실제 간격

            if (op == '=' && gap != value) return false;
            if (op == '<' && !(gap < value)) return false;
            if (op == '>' && !(gap > value)) return false;
        }
        return true;   // 모든 조건 통과
    }

    static int indexOf(char c) {
        for (int i = 0; i < 8; i++) {
            if (output[i] == c) return i;
        }
        return -1;
    }
}public class Solution {
    static char[] people = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] visited = new boolean[8];
    static char[] output = new char[8];
    static int answer = 0;
    static String[] data;

    public int solution(int n, String[] data) {
        this.data = data;
        permutation(0);
        return answer;
    }

    static void permutation(int depth) {
        if (depth == 8) {              // 8명을 다 세웠으면
            if (isValid()) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = people[i];   // depth번째 자리에 사람 배치
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isValid() {
        for (String condition : data) {
            char p1 = condition.charAt(0);   // 조건 제시자
            char p2 = condition.charAt(2);   // 상대방
            char op = condition.charAt(3);   // 연산자
            int value = condition.charAt(4) - '0';  // 원하는 간격

            int pos1 = indexOf(p1);
            int pos2 = indexOf(p2);
            int gap = Math.abs(pos1 - pos2) - 1;    // 실제 간격

            if (op == '=' && gap != value) return false;
            if (op == '<' && !(gap < value)) return false;
            if (op == '>' && !(gap > value)) return false;
        }
        return true;   // 모든 조건 통과
    }

    static int indexOf(char c) {
        for (int i = 0; i < 8; i++) {
            if (output[i] == c) return i;
        }
        return -1;
    }
}