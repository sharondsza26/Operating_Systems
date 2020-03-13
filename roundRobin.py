bt1 = []
bt = []
bustTime = []
completionTime = []
at = []
a = 0
process = []
p = 0
q = 0
total = 0
tat = []
waitingTime = []
temp = 0
atat = 0
awt = 0
count = 0
c = 0

n = int(input("Enter number of process: "))
q = int(input("Enter time quantum: "))
print("Enter process details: ")
for i in range(n):
    print("\nProcess ", i+1)
    p = i+1
    process.append(p)
    b = int(input("Enter BT: "))
    bustTime.append(b)
    a = int(input("Enter Arrival: "))
    at.append(a)
    bt1.append(b)
    completionTime.append(c)

print("Gnatt Chart: ")
print("|",end="")
i = 0
while (count < n):
    if(i == n):
        i = 0
        
    if(bustTime[i] > q):
        total = total + q
        bustTime[i] = bustTime[i] - q
        q = q + q
        
    elif(bustTime[i]<=q and bustTime[i] > 0):
        
        if(bustTime[i]!= q):
            total = total + bustTime[i]
            q = q + bustTime[i]
        else:
            total = total + q
            q = q + q
        count = count + 1
        bustTime[i] = 0
    completionTime[i] = total
    i = i + 1
   
    print("P",i,"|",end="")
    
print("\n")
for i in completionTime :
    tat.append(i)
    
for i in range(n):
    w = tat[i] - bt1[i]
    waitingTime.append(w)
    
print("PROCESS\tBurstTime\tArrivalTime\tCompletionTime\t\tTAT\t\tWT")

for i in range(n):
    print(process[i],"\t\t",bt1[i],"\t\t",at[i],"\t\t",completionTime[i],"\t\t",tat[i],"\t\t",waitingTime[i])
    
for i in range(n):
    atat = atat + tat[i]
    awt = awt + waitingTime[i]
    
print("Average TAT= ",atat/n)
print("Average wT= ",awt/n)

