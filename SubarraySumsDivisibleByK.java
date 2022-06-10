class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        
        // Brute Force Approach, time -> O(n2), space -> O(1). (Finding all subarrays.)
        // int count = 0;
        // if(nums.length-1 == 0){
        //     if(nums[nums.length-1] % k == 0){
        //         return 1;
        //     }
        // }
        // for(int i = 0; i < nums.length-1; i++){
        //     int j = i+1;
        //     for(j = i+1; j < nums.length; j++){
        //         int sum = 0;
        //         for(int l = i; l < j; l++){
        //             sum += nums[l];
        //         }
        //         if(sum % k == 0){
        //             count++;
        //         }
        //         if((sum+ nums[j]) %k == 0 && j == nums.length-1){
        //             count++;
        //         }
        //     }
        // }
        // if(nums[nums.length-1] % k == 0){
        //         count++;
        // }
        // return count;
        
        
        /*used prefix sum(for easy calculation) and Hashmaps ofr storing the modulos.
        time -> O(n+n), space -> O(k);
        
        idea -> 
        As we know,
        sum(i, j) = sum(0, j) - sum(0, i-1) -----1
        So now, we can express sum of any subarray as 
        (quotient * k) + remainder.
        Hence,
        sum(0, j) -> (q1 * k) + rem1
        sum(0, i-1) -> (q2*k) + rem2
        By 1,
        sum(i, j) = (q1*k) + rem1 - ((q2*k) + rem2)
        sum(i, j) = (q1-q2)k + rem1 - rem2
        Now for any subarray to be divisible by k, we have to make its RHS divisible by k.
        In which q1-q2 is already a multiple of k, So our only task is to handle rem1 - rem2
        Hence rem1 = rem2, to follow the same.
        where  rem1 = Sum Of subarray(0, j) % k
        Hence we maintain a map to store all modulos after dividing sum of subarrays(which           we are storing through the prefix sum) with k. 
        
        To find count of combinations -> nC2 -> (n * n-1)/2
        However for 0 it's a bit different because 0 is itself a successful subarray.
        So we have to count itself a subarray.
        
        And one last thing is that for -ve sum of subarrays, to find the modulo we use 
        ((nums[i] % k)+k)%k this formula, through which we can make -1 % k = 1.
        */
        
        // int count = 0;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for(int i = 0; i < nums.length; i++){
        //     int n = ((nums[i] % k)+k)%k;
        //     map.put(n, map.getOrDefault(n, 0)+1);
        // }
        
        // for(int i = 0; i < nums.length; i++){
        //     int m = ((nums[i] % k)+k)%k;
        //     if(m == 0){
        //         int n = map.get(m);
        //         count += (n * (n-1)/2) + n;
        //         map.put(m, 0);
        //     }
        //     int n = map.get(((nums[i] % k)+k)%k);
        //     count += n * (n-1)/2;
        //     map.put(m, 0);
        // }
        // return count;
        
        
        // same idea, just instead of maps used arrays for storing modulos.
        // time -> O(n+k), space -> O(k);
        int count = 0;
        int arr[] = new int[k];
        Arrays.fill(arr, 0); 
        
        int curSum = 0;
        for(int i = 0; i < nums.length; i++){
            
            curSum += nums[i];
            
            arr[(((curSum%k)+k)%k)]++;
        }
        
        for(int i = 0; i < k; i++){
            if(i == 0){
                count += (arr[i] * (arr[i]-1)/2)+arr[i];
                continue;
            }
            count += arr[i] * (arr[i]-1)/2;
            
        }
        return count;
    }
}
