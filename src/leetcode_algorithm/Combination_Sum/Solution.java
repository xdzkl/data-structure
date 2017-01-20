package leetcode_algorithm.Combination_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum(new int[]{2 , 2 , 3 , 7} , 7));
        System.out.println(new Solution().combinationSum(new int[]{2 , 2 , 3 , 7} , 1));
        System.out.println(new Solution().combinationSum(new int[]{2 , 2 , 3 , 7} , 3));
        System.out.println(new Solution().combinationSum(new int[]{2 , 2 , 3 , 7} , 5));
        System.out.println(new Solution().combinationSum(new int[]{2 , 2 , 3 , 7} , 2));
        System.out.println(new Solution().combinationSum2(new int[]{2 , 2 , 3 , 7} , 7));
    }


    int index = 0;

    /**
     * 个人解法
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = index;i<candidates.length;i++) {
            if(i >0 && candidates[i] == candidates[i-1]) continue;
            if(candidates[i] > target) break;
            if(candidates[i] == target){
                List<Integer> integers = new ArrayList<>();
                integers.add(candidates[i]);
                result.add(integers);
                continue;
            }
            index = i;
            List<List<Integer>> childResult =  combinationSum(candidates , target - candidates[i]);
            if(childResult.isEmpty()) continue;
            for (List<Integer> list : childResult) {
                list.add(candidates[i]);
                result.add(list);
            }
        }
        return result;
    }


    /**
     * 推荐 ， 回溯法
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list , new ArrayList<>() , nums , target , 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempLit , int [] nums, int remain, int start){
        if(remain < 0) return ;
        else if(remain == 0) list.add(new ArrayList<>(tempLit));
        else {
            for(int i= start;i < nums.length;i++) {
                if(i >0 && nums[i] == nums[i-1]) continue;
                if(remain < nums[i]) return;
                tempLit.add(nums[i]);
                backtrack(list , tempLit , nums , remain- nums[i] , i);
                tempLit.remove(tempLit.size() - 1);
            }
        }
    }

}
