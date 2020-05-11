/**
Given a string, find the length of the longest substring T that contains at most k distinct characters.
*/
class Solution340 {
    public int lengthOfLongestSubstringKDistinct(String str, int k) {
        if(str.length() == 0 || str == null) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxL = Integer.MIN_VALUE;
        for(end = 0; end < str.length(); end++){
            map.put(str.charAt(end), map.getOrDefault(str.charAt(end),0) + 1);
            while(map.size() > k){
                map.put(str.charAt(start),map.get(str.charAt(start)) - 1);
                if(map.get(str.charAt(start)) == 0){
                map.remove(str.charAt(start));
                }
                start++;
            }
        maxL = Math.max(maxL, end - start + 1);
        }
    return maxL;
    }
}
