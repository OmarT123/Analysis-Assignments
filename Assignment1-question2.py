import time
import matplotlib.pyplot as plt
import random

def mergeSort(arr):
    if len(arr) <= 1:
        return
    mid = len(arr)//2
    left = arr[:mid]
    right = arr[mid:]
    mergeSort(left)
    mergeSort(right)
    
    i = 0
    j = 0
    k = 0
    
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            arr[k] = left[i]
            i += 1
            k += 1
        else:
            arr[k] = right[j]
            j += 1
            k += 1
    while j < len(right):
        arr[k] = right[j]
        j += 1
        k += 1
    while i < len(left):
        arr[k] = left[i]
        i += 1
        k += 1

def binarySearch(arr, target):
    l = 0
    h = len(arr) - 1
    
    while l <= h:
        mid = l + (h-l)//2
        #print(arr[mid])
        #print(target)
        #print(arr[mid] == target)
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            l = mid + 1
        else:
            h = mid - 1
    return -1
        
def findPairsWithSum(arr, target):
    mergeSort(arr)
    #print(arr)
    pairs = []
    
    for i in range(len(arr)):
        #print(arr[i])
        search = target - arr[i]
        res = binarySearch(arr[i + 1:],search)
        if (res != -1):
            pairs.append((arr[i],arr[res]))
        #print("-----")
    return pairs

if __name__ == "__main__":
    inputVals = []
    executionTimes = []
    ranges = [10**i for i in range(5)]
    arr = []
    for i in range(1,1000000,1):
        arr.append(random.randint(1,100))
        if i == 10 or i == 100 or i == 1000 or i == 10000 or i == 100000 or i == 1000000:
            startTime = time.time()
            findPairsWithSum(arr,80)
            endTime = time.time()
            inputVals.append(i)
            executionTimes.append(endTime - startTime)
            print("done")
    print("all done")
    
    plt.plot(inputVals, executionTimes)
    plt.xlabel("Array Size")
    plt.ylabel("Time (seconds)")
    #plt.yscale("log")
    plt.title("Execution Time vs. Input Value")
    plt.legend()
    plt.grid(True)
    
    plt.show()













       
        