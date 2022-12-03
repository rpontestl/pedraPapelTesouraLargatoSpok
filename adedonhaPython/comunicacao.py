from socket import *

class Cliente:
    def __init__(self,HOST,PORT):
        self.HOST, self.PORT = HOST, PORT

    def VerifyAsClient(self):
        with socket(AF_INET, SOCK_STREAM)  as s:
            s.connect((self.HOST, self.PORT))
            s.sendall(b"Let's play!")
            data = s.recv(1024)

        print(f"received ",repr(data))

    def SendMove(self,move):
        with socket(AF_INET, SOCK_STREAM)  as s:
            s.connect((self.HOST, self.PORT))
            s.sendall(move)
            data = int(s.recv(1024))
            return data
