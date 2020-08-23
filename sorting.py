import random
import time


def insertionSort(array):
    for i in range(1, len(array)):
        temp = array[i]
        j = i - 1
        while j >= 0 and temp < array[j]:
            array[j + 1] = array[j]
            j -= 1
        array[j + 1] = temp


def quicksort(arr, low, high):
    if low < high:
        if len(arr) <= 10:
            insertionSort(arr)
        pi = partition(arr, low, high)
        quicksort(arr, low, pi - 1)
        quicksort(arr, pi + 1, high)


def partition(arr, low, high):
    mid = (low + high) // 2
    piviot = medianOfThree(mid, low, high, arr)
    i, s = low, low
    arr[piviot], arr[high] = arr[high], arr[piviot]
    while i < high:
        if arr[i] <= arr[high]:
            arr[i], arr[s] = arr[s], arr[i]
            i += 1
            s += 1
        else:
            i += 1
    arr[high], arr[s] = arr[s], arr[high]
    return s


def medianOfThree(a, b, c, arr):
    if (arr[a] > arr[b]) != (arr[a] > arr[c]):
        return a
    elif (arr[b] > arr[a]) != (arr[b] > arr[c]):
        return b
    else:
        return c


def isOrdered(arr):
    for i in range(len(arr) - 1):
        if arr[i] > arr[i + 1]:
            return False
    return True


if __name__ == '__main__':
    while True:
        print("1. Sorted")
        print("2. Random")
        print("3. Reverse")
        print("4. Exit")
        choice = int(input("Choose one of the above"))
        if choice == 4:
            break
        #for k in range(10):
        elif choice == 1:
            original = []
            for i in range(0, 100001):
                original.append(i)
            for j in range(0,5):
                duplicate1 = list(original)
                if j == 4:
                    startTime = time.time()
                insertionSort(duplicate1)
                if j == 4:
                    endTime = time.time()-startTime
                if j == 4:
                    print("Insertion Sort on Duplicate1:", isOrdered(duplicate1))
                    print(str(endTime)+" insertionSort1")
                duplicate2 = list(original)
                if j == 4:
                    startTime = time.time()
                quicksort(duplicate2, 0, len(original) - 1)
                if j == 4:
                    endTime = time.time()-startTime
                if j == 4:
                    print("Quick Sort on Duplicate1:", isOrdered(duplicate1))
                    print(str(endTime)+" quickSort1")
        elif choice == 2:
            original = []
            for i in range(0, 100001):
                original.append(random.randint(0, 100000))
            for j in range(0,5):

                duplicate1 = list(original)
                if j == 4:
                    startTime = time.time()
                insertionSort(duplicate1)
                if j == 4:
                    endTime = time.time()-startTime
                if j == 4:
                    print("Insertion Sort on Duplicate2:", isOrdered(duplicate1))
                    print(str(endTime)+" insertionSort2")
                duplicate2 = list(original)
                if j == 4:
                    startTime = time.time()
                quicksort(duplicate2, 0, len(original) - 1)
                if j == 4:
                    endTime = time.time()-startTime
                if j == 4:
                    print("Quick Sort on Duplicate2:", isOrdered(duplicate1))
                    print(str(endTime)+" quickSort2")
        elif choice == 3:
            original = []
            for i in range(0, 100001):
                original.append(i)
            original.reverse()

            for j in range(0,5):
                duplicate1 = list(original)
                if j == 4:
                    startTime = time.time()
                insertionSort(duplicate1)
                if j == 4:
                    endTime = time.time()-startTime
                if j == 4:
                    print("Insertion Sort on Duplicate3:", isOrdered(duplicate1))
                    print(str(endTime)+" insertionSort3")
                duplicate2 = list(original)
                if j == 4:
                    startTime = time.time()
                quicksort(duplicate2, 0, len(original) - 1)
                if j == 4:
                    endTime = time.time()-startTime 
                if j == 4:
                    print("Quick Sort on Duplicate3:", isOrdered(duplicate1))
                    print((str(endTime)+" quickSort3"))

