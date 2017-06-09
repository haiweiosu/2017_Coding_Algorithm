/* 
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".*/



public class Solution {
    public String shortestPalindrome(String s) {
        //corner cases
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        //create a temp string
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        
        int[] table = getTable(temp);
        
        //get the maximum palin part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
        
    }
    
    private int[] getTable(String temp) {
        //initializa look up table
        int[] table = new int[temp.length()];
        //pointer that points to matched char in prefix part
        int index = 0;
        
        for (int i = 1; i < temp.length(); i++) {
            if (temp.charAt(index) == temp.charAt(i)) {
                table[i] = table[i-1] + 1;
                index++;
            } else {
                //match failed, we then try to match shorter substring
                index = table[i-1];
                
                while(index > 0 && temp.charAt(index) != temp.charAt(i)){
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }
            
                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if(temp.charAt(index) == temp.charAt(i)){
                    //if match, then extend one char 
                    index ++ ;
                }
                
                table[i] = index;
                
            }
        }
        
        return table;
    }
}