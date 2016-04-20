public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*'){
                List<Integer> list1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> list2 = diffWaysToCompute(input.substring(i + 1));
                for (int v1 : list1) {
                    for (int v2 : list2) {
                        if (c == '+') {
                            res.add(v1 + v2);
                        } else  if (c == '-') {
                            res.add(v1 - v2);
                        } else if (c == '*') {
                            res.add(v1 * v2);
                        }
                    }
                }
            }
        }
        
        // 这个逻辑很巧妙，base case在最后判断出来
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        
        return res;
    }
}
