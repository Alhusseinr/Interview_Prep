package com.LeetCode;
import java.util.*;
import com.dataSctructures.*;

public class solutions {

    public static int[] twosum(int[] nums, int target) {
        int[] result = new int[2];
        if(nums.length == 0 || nums.length == 1) return null;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    result[0] = i;                    
                    result[1] = j;
                }
            }
        }
        return result;
    }

    public static int maxProfit(int[] prices) {
        if(prices.length == 0 || prices.length == 1) return 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            }
            if(prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    public static boolean isValid(String s) {
        if (s.length() == 0 || s.length() == 1) return false;
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){
                char topElement = stack.isEmpty() ? '#' : stack.pop();

                if(topElement != map.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static int reverse(int x) {
        int reversed = 0;

        if(x > Integer.MAX_VALUE || x < Integer.MIN_VALUE || x == 0) return 0;

        while(x != 0) {
            int last = x % 10;
            reversed = reversed*10+last;
            x /= 10;
        }
        if(reversed < Math.pow(-2, 31) || reversed > Math.pow(2, 31) - 1) return 0;
        return reversed;
        
    }

    // TODO: dont forget to do this one on 08/13/2021
    public static int countbinarysubstrings(String s) {
        int[] groups = new int[s.length()];
        int t = 0;

        groups[0] = 1;

        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i-1) != s.charAt(i)) {
                groups[++t] = 1;
            } else {
                groups[t]++;
            }
        }

        int ans = 0;
        for(int i = 1; i <= t; i++) {
            ans += Math.min(groups[i-1], groups[i]);
        }
        System.out.println(ans);
        return ans;
    }

    // TODO: count binary substring linear
    public static int countbinarysub(String s) {
        int ans = 0, prev = 0, curr = 1;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i - 1) != s.charAt(i)) {
                ans += Math.min(prev, curr);
                prev = curr;
                curr = 1;
            } else {
                curr++;
            }
        }
        System.out.println(ans + Math.min(prev, curr));
        return ans + Math.min(prev, curr);
    }

    public static int fib(int n) {
        if(n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fibO1(int n) {
        if(n <= 1) {
            return n;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 0;

        for(int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }
    
    // TODO: dont forget to do this one on 08/13/2021
    public static int getDecimalValue(ListNode head) {
        int num = head.val;
        while(head.next != null) {
            num = num * 2 + head.next.val;
            head = head.next;
        }
        return num;
    }

    //TODO: check number of steps to make array equal
    public static int numberOfSteps(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            count += nums[i] - nums[0];
        }
        System.out.print(count);
        return count;
    }

    //TODO: MergeIntervals
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] interval : intervals) {
            if(merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    //TODO: check number of steps to array equal DP
    public static int numberOfStepsDP(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;
        for(int i = 1; i < nums.length; i++) {
            int diff = (moves + nums[i]) - nums[i-1];
            nums[i] += moves;
            moves += diff;
        }
        System.out.println(moves);
        return moves;
    }

    // TODO: Decoded ways
    public static int decode(String s) {
        if(s.charAt(0) == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != '0') {
                dp[i+1] += dp[i];
            }

            int twoDigit = Integer.parseInt(s.substring(i-1, i + 1));
            if(twoDigit > 0 && twoDigit <= 26) {
                dp[i + 1] += dp[i - 1];
            }
        }
        System.out.println(dp[s.length()]);
        return dp[s.length()];
    }

    //TODO: Decoded ways optiemized
    public static int decodeOptemized(String s) {
        if(s.charAt(0) == 0) return 0;
        int oneBack = 1;
        int twoBack = 1;
        for(int i = 1; i < s.length(); i++) {
            int current = 0;
            if(s.charAt(i) != '0') {
                current += oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i-1, i + 1));
            if(twoDigit > 0 && twoDigit <= 26) {
                current += twoBack;
            }
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    public static String originalDigits(String s) {
        int[] count = new int[10];
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'x') count[6]++;
            if (c == 's') count[7]++; //7-6
            if (c == 'g') count[8]++;
            if (c == 'u') count[4]++; 
            if (c == 'f') count[5]++; //5-4
            if (c == 'h') count[3]++; //3-8
            if (c == 'i') count[9]++; //9-8-5-6
            if (c == 'o') count[1]++; //1-0-2-4
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];
        
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i <= 9; i++) {
            for(int j = 0; j < count[i]; j++) {
                sb.append(i);
            }
        }
        System.out.println(sb.toString());
        return sb.toString(); 
    }

    public static int power(int number) {
        System.out.println(number * number);
        return number * number;
    }

    // TODO: self-discriptive number
    public static boolean isSelfDescriptivenumber(int num) {
        String s = Integer.toString(num);

        for(int i = 0; i < s.length(); i++) {
            String temp_char = s.charAt(i) + "";
            int b = Integer.parseInt(temp_char);
            
            int count = 0;
            for(int j = 0; j < s.length(); j++) {
                int temp = Integer.parseInt(s.charAt(j) + "");
                if(temp == i) {
                    count++;
                }
            }

            if(count != b) {
                System.out.println("0");
                return false;
            }
        }
        System.out.println("1");
        return true;
    }

    // EPI Questions:
    public static int invertInteger(int n) {
        
        int inverted = 0;
        while(n != 0) {
            int last = n%10;
            inverted = inverted*10+last;
            n = n/10;
        }
        System.out.println(inverted);
        return inverted;
    }

    public static List<Integer> plusOne(List<Integer> A) {
        int n = A.size() - 1;
        A.set(n, A.get(n) + 1);
        for(int i = n; i > 0 && A.get(i) == 10; --i) {
            A.set(i, 0);
            A.set(i - 1, A.get(n - 1) + 1);
        } 
        if(A.get(0) == 10) {
            A.set(0, 1);
            A.add(0);
        }
        for(int i = 0; i < A.size(); i++) {
            System.out.println(A.get(i));
        }
        return A;
    }

    
}
