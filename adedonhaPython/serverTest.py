import socket

my_con = True

s = socket.socket()
host = '172.15.4.166'
port = 8000

s.bind((host,port))
s.listen(5)

c,addr = s.accept()

print ("got connection from",addr)

while my_con:
    msg = c.recv(1024)
    print (msg.decode("utf-8"))

    if msg == "quit":
        c.close()
        my_con = False