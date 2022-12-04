from bancoDados import DataBase
from comunicacao import Cliente
from jogar import Competicao
import random

if __name__ == '__main__':
    IP = input('Qual o IP do Oponente? ')
    PORT = input('\nQual a porta do oponente? ')
    opponent = Cliente(IP,int(PORT))
    banco = DataBase()

    i = 1
    moves = ['pedra','spock','papel','lagarto','tesoura']
    nDerrota = 0
    nVitoria = 0

    while i <= 15:
        #define a propria jogada
        nAleatorio = banco.escolheMelhorJogada()
        jogada = moves[nAleatorio]
        
        #Recebe a jogada do oponente
        nOpponent = int(opponent.SendMove(str(nAleatorio)))
        
        ans = moves[nOpponent]
        partida = Competicao(jogada,ans)
        resultado = partida.compara()

        ##Contabiliza os resultados
        if resultado == 'Vitoria':
            nVitoria+=1
        if resultado == 'Derrota':
            nDerrota+=1 
        
        #Apresenta resultados até o momento
        print('\nRodada atual:{3}\n-------------------------------------------\nMe: {0} Opponent: {1} result: {2}\n'.format(jogada,ans,resultado,i))
        print('Histórico\n')
        banco.printaBD()
        print('\n')

        #Insere o resultado no Bando de dados
        banco.insereResultado(str(i),jogada,ans,resultado)
        i = i + 1 
        print('\nPlacar: {0} X {1}\n'.format(nVitoria,nDerrota))
    
    print("\nFim de jogo\n")

    #verifica os resultados
    if nVitoria > nDerrota:
        print('Python player win\n')
    elif nVitoria == nDerrota:
        print('Empate\n')
    else:
        print('Java player win\n')

    #deleta o banco de dados
    banco.deletaBD()