class Solution:
    def knapsack(self, W: int, wt: list[int], val: list[int], n: int) -> int:
        # Space optimized
        prev = [0]*(W+1)

        for x in range(n-1,-1,-1):
            for capacity in range(W,-1,-1):
                if x == n-1:
                    if wt[x] <= capacity:
                        prev[capacity] = val[x]
                else:
                    not_take = prev[capacity]
                    take = float('-infinity')
                    if capacity-wt[x]>=0:
                        take = prev[capacity-wt[x]]+val[x]
                    prev[capacity] = max(take,not_take)
        return prev[W]
    

        # Approach with tabulation
        dp = [[0]*(W+1) for _ in range(n)]
        
        for x in range(n-1,-1,-1):
            for capacity in range(0,W+1):
                if x == n-1:
                    if wt[x] <= capacity:
                        dp[x][capacity] = val[x]
                else:
                    not_take = dp[x+1][capacity]
                    take = float('-infinity')
                    if capacity-wt[x]>=0:
                        take = dp[x+1][capacity-wt[x]]+val[x]
                    dp[x][capacity] = max(take,not_take)
        return dp[0][W]


        # Approach with memoization
        memo = {}
        def helper(index,capacity):
            if tuple([index,capacity]) in memo:
                return memo[tuple([index,capacity])]
            if index >= n or capacity <= 0:
                return 0
        
            not_take = helper(index+1,capacity)
            take = 0
            if capacity>=wt[index]:
                take = helper(index+1,capacity-wt[index]) + val[index]
            memo[tuple([index,capacity])] = max(take,not_take)
            return memo[tuple([index,capacity])]
        return helper(0,W)


def run_test_case(W: int, wt: list[int], val: list[int], n: int, expected: int) -> None:
    solution = Solution()
    result = solution.knapsack(W, wt, val, n)
    print(f"Input: W = {W}, wt = {wt}, val = {val}, n = {n}")
    print(f"Output: {result}")
    print(f"Expected: {expected}")
    print("Correct" if result == expected else "Incorrect")
    print()

if __name__ == "__main__":
    # Test Case 1
    W1, wt1, val1, n1 = 50, [10, 20, 30], [60, 100, 120], 3
    expected1 = 220
    run_test_case(W1, wt1, val1, n1, expected1)

    # Test Case 2
    W2, wt2, val2, n2 = 100, [10, 20, 30, 40, 50], [50, 60, 70, 80, 90], 5
    expected2 = 260
    run_test_case(W2, wt2, val2, n2, expected2)

    # Test Case 3
    W3, wt3, val3, n3 = 10, [5, 4, 6, 3], [10, 40, 30, 50], 4
    expected3 = 90
    run_test_case(W3, wt3, val3, n3, expected3)

    # Test Case 4
    W4, wt4, val4, n4 = 8, [3, 2, 4, 1], [5, 3, 7, 2], 4
    expected4 = 14
    run_test_case(W4, wt4, val4, n4, expected4)

    # Test Case 5
    W5, wt5, val5, n5 = 15, [2, 3, 4, 5, 6], [3, 4, 5, 6, 7], 5
    expected5 = 19
    run_test_case(W5, wt5, val5, n5, expected5)