// https://leetcode.com/problems/remove-invalid-parentheses/
// 301. Remove Invalid Parentheses 

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
		Queue<String> q = new LinkedList<String>();
		Set<String> vis = new HashSet<String>();
		q.add(s);
		while (res.isEmpty() && !q.isEmpty()) { // level order
			int qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				s = q.remove();
				if (isBalanced(s)) {
					res.add(s);
				}
				for (int j = 0; j < s.length(); j++) {
					char c = s.charAt(j);
					if (c != '(' && c != ')') {
						continue;
					}
					String tmp = new StringBuilder(s).deleteCharAt(j).toString();
					if (!vis.contains(tmp)) {
						q.add(tmp);
						vis.add(tmp);
					}
				}
			}
		}
		
		return res;
	}
	
	private boolean isBalanced(String s) {
		int h = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				h++;
			} else if (s.charAt(i) == ')') {
				if (--h < 0) {
					return false;
				}
			}
		}
		return h == 0;
	}
}
