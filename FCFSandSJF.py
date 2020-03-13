n = int(input("Enter the number of processes:\t"))
process = []
p = 0
arrivalTime = []
burstTime = []
completionTime = []
c = 0
waitingTime = 0
w = []
awt = 0
tat = 0
print("Enter the process details:\n")

for i in range(n):
    print("Process ", i+1)
    p = i+1
    process.append(p)
    b = int(input("Burst time: "))
    burstTime.append(b)
    a = int(input("Enter the Arrival time: "))
    arrivalTime.append(a)
    
for i in range(n-1):
    waitingTime = waitingTime + burstTime[i]
    w.append(waitingTime)
    awt = awt + (waitingTime - arrivalTime[i])
awt = awt / n

for i in range(n):
    c = c + burstTime[i]
    completionTime.append(c)
    tat = tat + (c - arrivalTime[i])
tat = tat / n

print("FCFS : \n\t PROCESS \t BURST TIME \t ARRIVAL TIME \t CT \n")
for i in range(n) :
    print("\t",process[i],"\t\t",burstTime[i],"\t\t",arrivalTime[i],"\t\t",completionTime[i],"\t\t")

print("Gnatt Chart of Processes: " ,process)
print("Average waiting time= ",awt)
print("Average TAT= ",tat)

print("\n")

for i in range(0,len(burstTime)-1) :
    for j in range(0,len(burstTime)-i-1) :
        if(burstTime[j]>burstTime[j+1]) :
            temp = burstTime[j]
            burstTime[j] = burstTime[j+1]
            burstTime[j+1] = temp
            temp = process[j]
            process[j] = process[j+1]
            process[j+1] = temp
print("SJF :\n")
print("Gnatt Chart of Processes: " ,process)
c = 0
waitingTime = 0
awt = 0
tat = 0

for i in range(n-1):
    waitingTime = waitingTime + burstTime[i]
    w.append(waitingTime)
    awt = awt + (waitingTime - arrivalTime[i])
awt = awt / n

for i in range(n):
    c = c + burstTime[i]
    completionTime.append(c)
    tat = tat + (c - arrivalTime[i])
tat = tat / n

print("\t PROCESS \t BURST TIME \t ARRIVAL TIME \t CT \t WT \n")
for i in range(n):
    print("\t",process[i],"\t\t",burstTime[i],"\t\t",arrivalTime[i],"\t\t",completionTime[i],"\t\t",w[i])

print("Average waiting time= " , awt)
print("Average TAT=",tat)
