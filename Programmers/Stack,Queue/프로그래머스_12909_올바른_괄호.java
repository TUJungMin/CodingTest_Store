import java.util.*;

class Solution {
    boolean solution(String s) {
        // 질문자님이 선언하셨던 스택을 그대로 사용합니다.
        Stack<Character> st = new Stack<>();
         
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // 열린 괄호는 스택에 담아둡니다.
                st.push(c);
            } else {
                // 닫힌 괄호를 만났는데 스택이 비어있다면 짝이 안 맞는 것이므로 즉시 false
                if (st.isEmpty()) {
                    return false;
                }
                // 스택이 비어있지 않다면 맨 위 열린 괄호 하나를 꺼내어 짝을 맞춰 제거합니다.
                st.pop();
            }
        }
    
        // 모든 반복이 끝난 후 스택이 깨끗하게 비어있어야만 올바른 괄호 쌍입니다.
        return st.isEmpty();
    }
}
