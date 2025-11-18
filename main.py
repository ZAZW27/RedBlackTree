

bayi = [
   [ 5, # umur (bulan) bayi 1
    "laki", 
    "20/2/2025", 
    5, # berat
    40, # tinggi
    [
        "yellow skin", 
        "yellow skin", 
        "yellow skin"
    ]], 
   [ 
    10, # umur (bulan) bayi 2
    "laki", 
    "20/2/2025", 
    5, # berat
    40, # tinggi
    [
        "red skin", 
        "red skin", 
        "red skin"
    ]
    ]
]

for i in range(len(bayi)): 
    print(i)
    for j in bayi[i][5]: 
        print(j)
