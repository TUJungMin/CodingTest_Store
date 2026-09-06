import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        // 아직 다리에 오르지 못하고 대기 중인 트럭들
        Queue <Integer> waiting = new ArrayDeque<>();
        for(int i = 0; i< truck_weights.length; ++i){
            waiting.offer(truck_weights[i]);
        }

        // 다리 위 상태를 나타내는 큐. 크기를 항상 bridge_length로 유지하며,
        // 트럭이 없는 칸은 0으로 채워서 "빈 자리도 같이 흘러가도록" 만든다.
        Queue<Integer> bridge = new ArrayDeque<>();
        for (int i = 0; i < bridge_length; ++i) {
            bridge.offer(0);
        }
        int bridgeWeight = 0; // 현재 다리 위에 있는 트럭들의 무게 합

        // 대기 트럭이 남아있거나, 다리 위에 트럭이 남아있는 동안(=아직 안 끝남) 1초씩 진행
        while (!waiting.isEmpty() || bridgeWeight > 0) {
            answer++; // 1초 경과

            // 다리 맨 앞(= bridge_length초 전에 올라온 트럭)이 다리를 완전히 건너서 빠져나감
            bridgeWeight -= bridge.poll();

            // 대기 트럭이 있고, 그 트럭을 올려도 무게 제한을 넘지 않으면 다리에 올린다
            if (!waiting.isEmpty() && bridgeWeight + waiting.peek() <= weight) {
                int truck = waiting.poll();
                bridgeWeight += truck;
                bridge.offer(truck);
            } else {
                // 올릴 트럭이 없거나 무게 초과면, 빈 자리(0)를 대신 채워 넣어
                // 다리 큐의 길이(bridge_length)를 유지한다
                bridge.offer(0);
            }
        }
        return answer;
    }
}