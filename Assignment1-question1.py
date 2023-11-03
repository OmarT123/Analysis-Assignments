import time
import matplotlib.pyplot as plt

def powerIterative(a, n):
    time.sleep(1.5)
    res = 1
    while n > 0:
        res *= a
        n = n - 1
    return res

def powerDivideAndConquer(a, n):
    time.sleep(0.5)
    if n == 0:
        return 1
    if n % 2 == 0:
        halfPower = powerDivideAndConquer(a, n // 2)
        return halfPower * halfPower
    else:
        halfPower = powerDivideAndConquer(a, (n - 1) // 2)
        return a * halfPower * halfPower

if __name__ == "__main__":
    inputVals = []
    inputVals2 = []
    executionTimes = []
    executionTimes2 = []
    a = 2
    n = 3
    ranges = [10**i for i in range(7)]
    for i in ranges:
        startTime = time.time()
        powerIterative(a, i)
        endTime = time.time()
        inputVals.append(i)
        executionTimes.append(endTime - startTime)
        
        startTime = time.time()
        powerDivideAndConquer(a, i)
        endTime = time.time()
        inputVals2.append(i)
        executionTimes2.append(endTime - startTime)

    plt.plot(inputVals, executionTimes, label="Iterative Power")
    plt.plot(inputVals2, executionTimes2, label="Divide and Conquer Power")
    plt.xlabel("Input Value")
    plt.ylabel("Time (seconds)")
    #plt.yscale("log")
    plt.title("Execution Time vs. Input Value")
    plt.legend()
    plt.grid(True)
    
    plt.show()