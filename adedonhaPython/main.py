from bancoDados import DataBase
from comunicacao import Cliente
from jogar import Competicao
import random

if __name__ == '__main__':
    IP = input('Qual o IP do Oponente? ')
    PORT = input('\nQual a porta do oponente? ')
    opponent = Cliente(IP,int(PORT))
    banco = DataBase()

    termino = False
    i = 1
    moves = ['pedra','spock','papel','lagarto','tesoura']

    while termino is not True:
        nAleatorio = random.randrange(0,4)
        jogada = moves[nAleatorio]
        print(jogada)
        nOpponent = opponent.SendMove(str(nAleatorio))
        
        ans = moves[nOpponent]
        partida = Competicao(jogada,ans)
        resultado = partida.compara()
        
        print('\nRodada atual\n-------------------------------------------\nMe: {0} Opponent: {1} result: {2}\n'.format(jogada,ans,resultado))
        print('Histórico\n')
        banco.printaBD()
        print('\n')
        banco.insereResultado(str(i),jogada,ans,resultado)
        
        option = 0

        while not (option == 1 or option == 2):
            option = int(input('deseja jogar mais uma rodada([1]Sim [2]Não)? '))
            
        
        if option == 2:
            termino  = True
            opponent.SendMove("5")
            print("Saindo do Jogo")
        i = i + 1 

    banco.deletaBD()