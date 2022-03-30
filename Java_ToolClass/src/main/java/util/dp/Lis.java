package util.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class Lis {

    public int lengthOfLIS(ArrayList<Integer> nums) {
        int[] dp = new int[nums.size()];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (nums.get(i) > nums.get(j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;
    }

}
