package conjunto;

public class Jogar{
    String me;
    String opponent;
    public Jogar(String myPlay, String opponentPlay){
        this.me = myPlay;
        this.opponent = opponentPlay;
    }

    public String compara(){
        if(this.me.equals("pedra")&&(this.opponent.equals("lagarto") || this.opponent.equals("tesoura"))){
            return "Vitoria";
        }
        else if(this.me.equals("spock")&&(this.opponent.equals("tesoura") || this.opponent.equals("pedra"))){
            return "Vitoria";
        }
        else if(this.me.equals("papel")&&(this.opponent.equals("pedra") || this.opponent.equals("spock"))){
            return "Vitoria";
        }
        else if(this.me.equals("lagarto")&&(this.opponent.equals("spock") || this.opponent.equals("papel"))){
            return "Vitoria";
        }
        else if(this.me.equals("tesoura")&&(this.opponent.equals("lagarto") || this.opponent.equals("papel"))){
            return "Vitoria";
        }
        else if(this.me.equals(this.opponent)){
            return "Empate";
        }
        else return "Derrota";
    }
}
