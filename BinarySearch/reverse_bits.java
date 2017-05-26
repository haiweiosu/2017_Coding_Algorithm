/*Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n < Integer.MIN_VALUE || n > Integer.MAX_VALUE) {
            return -1;
        }
        
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) {
                result++;
            }
            n >>= 1;
        }
        
        return result;
    }
}