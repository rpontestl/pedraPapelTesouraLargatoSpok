from bancoDados import DataBase

class Competicao:
    def __init__(self,myPlay,opponentPlay):
        self.me = myPlay
        self.opponent = opponentPlay

    def compara(self):
        if self.me == 'pedra' and (self.opponent == 'lagarto' or self.opponent == 'tesoura') :
            return 'Vitoria'

        elif self.me == 'spock' and (self.opponent == 'tesoura' or self.opponent == 'pedra'):  
            return 'Vitoria' 

        elif self.me == 'papel' and (self.opponent == 'pedra' or self.opponent == 'spock'):  
            return 'Vitoria'
        
        elif self.me == 'lagarto' and (self.opponent == 'spock' or self.opponent == 'papel'):  
            return 'Vitoria'

        elif self.me == 'tesoura' and (self.opponent == 'lagarto' or self.opponent == 'papel'):  
            return 'Vitoria'
        elif self.me == self.opponent:
            return 'Empate'
        else:
            return 'Derrota'
        