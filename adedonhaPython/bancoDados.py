import mysql.connector

class DataBase:
    def __init__(self):
        self.db = mysql.connector.connect(
        host ='127.0.0.1',
        user='pontes',
        password='robozinho123',
        database ='competicao',
        port=3306
        )


    def insereResultado(self,rodada,myMove,opponentMove,result):
        mycursor = self.db.cursor()
        mycursor.execute( "INSERT INTO ROUNDS (`Rodada`,`Me`,`He`,`Result`) values (%s,%s,%s,%s)",(rodada,myMove,opponentMove,result))
        self.db.commit()
        mycursor.close()

    def recuperaBD(self):
        lista = []
        mycursor = self.db.cursor()
        mycursor.execute("select * from ROUNDS")
        resultado = mycursor.fetchall()
        for x in resultado:
            lista.append(x)  

        mycursor.close()
        return lista 

    def printaBD(self):
        lista = self.recuperaBD()
        for x in lista:
            ##print("Rodada "+x[0]+": Me -"+x[1]+" X Oppenent - "+x[2]+" -> Result: "+x[3])
            print(x)
        
    def deletaBD(self):
        mycursor = self.db.cursor()
        mycursor.execute("TRUNCATE ROUNDS")
        mycursor.close()