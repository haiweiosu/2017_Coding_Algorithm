//Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

//The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

public class Solution {
    public boolean isValid(String s) {
        //corner case
        if (s == null || s.length() == 0) {
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
            } else if (s.charAt(i) == '{') {
                stack.push('}');
            } else if (s.charAt(i) == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != s.charAt(i)){
                return false;
            }
        }
        
        return stack.isEmpty();
    }
}