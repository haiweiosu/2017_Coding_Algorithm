/*Given a positive integer num, write a 
function which returns True if num is a perfect square else False.*/

public class Solution {
    public boolean isPerfectSquare(int num) {
        //corner case
        if (num < Integer.MIN_VALUE || num > Integer.MAX_VALUE) {
            return false;
        }
        
        int start = 1; 
        int end = num;
        
        while (start <= end) {
            long mid = start + (end - start)/2;
            if (mid * mid > num) {
                end = (int) mid - 1;
            } else if (mid * mid < num) {
                start = (int) mid + 1;
            } else{
                return true;
            }
        }
        
        return false;
    }
}