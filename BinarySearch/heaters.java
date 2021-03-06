/*
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
*/

public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        //corner cases
        if (houses == null || heaters == null || houses.length == 0 || heaters.length == 0) {
            return -1;
        }
        
        int min = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
       
        int i = 0, j = 0;
        while (i < houses.length) {
            while (j < heaters.length - 1 && heaters[j] + heaters[j+1]  <= 2 * houses[i]) {
                j++;
            }
            min = Math.max(min, Math.abs(houses[i] - heaters[j]));
            i++;
        }
        
        return min;
    }
}