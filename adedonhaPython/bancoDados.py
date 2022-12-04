import random
import mysql.connector
import matplotlib.pyplot as plt

class DataBase:
    moves = ['pedra','spock','papel','lagarto','tesoura']
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

    def escolheMelhorJogada(self):
        lista = []
        mycursor = self.db.cursor()
        mycursor.execute("select Me, count(*) as qntDerrotas from ROUNDS where Result = 'Vitoria' group by Me;")
        resultado = mycursor.fetchall()
        max = 0

        ##ver o que mais perde
        for x in resultado:
            if max < x[1]:
                max = x[1]

        #Insere os que mais perdem
        for x in resultado:
            if max == x[1]:
                lista.append(x[0]) 

        #Escolhendo um elemento dentre os elemnetos que mais perdeu
        if len(lista) > 0:
            n = random.randrange(0,len(lista))
            for i in range(5):
                if self.moves[i] == lista[n]:
                    n = i
                    break

        #Caso não haja, escolhe qualquer um
        else:
            n = random.randrange(0,5)
        
        return n


    def printaBD(self):
        lista = self.recuperaBD()
        for x in lista:
            ##print("Rodada "+x[0]+": Me -"+x[1]+" X Oppenent - "+x[2]+" -> Result: "+x[3])
            print('Rodada: {0} - Me: {1} - Oponente: {2} - Resultado: {3}\n'.format(x[0],x[1],x[2],x[3]))
        
    def graficoBarras(self,result,jogador): 
        lista = []
        mycursor = self.db.cursor()
        query = "select {0}, count(*) as qnt from ROUNDS where Result = '{1}' group by {0};".format(jogador,result)
        mycursor.execute(query)
        resultado = mycursor.fetchall()

        for y in self.moves:
            usado = False
            for x in resultado:
                if x[0] == y:
                    lista.append(x[1])
                    usado = True
                    break
            if not usado:
                lista.append(0)    
        
        labels = self.moves
        vals = lista

        fig, ax = plt.subplots(figsize=(8,5))
        ax.set_title(jogador, fontsize=18)
        ax.set_ylabel(result,fontsize=14)
        ax.bar(labels,vals)
        plt.savefig('Resultado_{0}_{1}.pdf'.format(jogador,result))
        
        return plt.show()

    def deletaBD(self):
        mycursor = self.db.cursor()
        mycursor.execute("TRUNCATE ROUNDS")
        mycursor.close()