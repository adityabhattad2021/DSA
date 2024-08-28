def recursive_fib(n,memo={}):
    if n in memo:
        return memo[n]
    if n<2:
        return n
    memo[n] = recursive_fib(n-1,memo)+recursive_fib(n-2,memo)
    return memo[n]


# This is called tabulation, here we go from bottom to up.
def iterative_fib(n):
    dp = [-1]*(n+1)
    dp[0] = 0
    dp[1] = 1
    for x in range(2,n+1):
        dp[x] = dp[x-1]+dp[x-2]
    return dp[n]

# Time complexity: O(n)
# Space complexity O(1)
def most_optimised_fib(n):
    prev = 1
    prev1 = 0
    for x in range(2,n+1):
        x = prev + prev1
        prev1 = prev
        prev = x
    return prev

if __name__=="__main__":
    assert recursive_fib(3200) == iterative_fib(3200) == most_optimised_fib(3200),"Fibonacci implementations do not match"
    print("All the functions works as expected!")
