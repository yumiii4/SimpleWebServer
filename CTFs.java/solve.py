from pwn import*
import time
approximate_seed=int(time.time()* 1000) + 150

def get_random(seed):
    alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    random.seed(seed)  # seeding with current time 
    s = ""
    for i in range(20):
        s += random.choice(alphabet)
    return s

conn = remote('verbal-sleep.picoctf.net',60483 )
print(conn.recvline().decode("utf-8"))

for i in range(200):
    result = conn.recvline().decode("utf-8")
    conn.sendline(get_random(approximate_seed + i).encode("utf-8"))
    if "pico" in result:
        print (result)


