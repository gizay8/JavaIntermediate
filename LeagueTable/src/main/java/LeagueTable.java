import java.util.ArrayList;
public class LeagueTable {
    private ArrayList<Team> teams;
    private int size;
    private ArrayList<ArrayList<String>> rounds;

    public LeagueTable(ArrayList<Team> teams) {
        this.teams = teams;
        this.size = teams.size();
        if (this.size % 2 != 0) {
            this.teams.add(new Team("Bay")); 
            this.size++;
        }
    }

    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ArrayList<String>> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<ArrayList<String>> rounds) {
        this.rounds = rounds;
    }

    public void match(){
        int roundAmount = (this.size - 1) * 2;
        int matchPerRound= this.size / 2;
        ArrayList<ArrayList<String>> round = new ArrayList<>((roundAmount));
        for(int i=0; i < roundAmount; i++) {
            round.add(new ArrayList());
        }

        for (int i = 0 ; i < roundAmount / 2 ; i++) {
            for(int j = 0 ; j < matchPerRound ; j++){
                int firstIndex = j;
                int secondIndex = (this.size - 1) - j;
                int k = i + this.size - 1;
                if (i % 2 == 0){
                    round.get(i).add(this.teams.get(firstIndex).getName() +" vs "+ this.teams.get(secondIndex).getName());
                    round.get(k).add(this.teams.get(secondIndex).getName() +" vs "+this.teams.get(firstIndex).getName());

                }else {
                    round.get(i).add(this.teams.get(secondIndex).getName() +" vs "+this.teams.get(firstIndex).getName());
                    round.get(k).add(this.teams.get(firstIndex).getName() +" vs "+ this.teams.get(secondIndex).getName());
                }
            }

            ArrayList<Team> teamList = new ArrayList<>();
            teamList.add(this.teams.get(0));
            teamList.add(this.teams.get(this.teams.size()-1));
            for(int k = 1 ; k < this.teams.size()-1 ; k++){
                teamList.add(this.teams.get(k));
            }
            this.teams = teamList;
        }

        this.rounds = round;
    }

    public void print(){
        int roundAmount = (this.size - 1) * 2;
        for (int i = 0 ; i < roundAmount ; i++) {
            System.out.println("Round : " + (i + 1));
            for(int j=0 ; j < this.rounds.get(i).size() ; j++){
                System.out.println(this.rounds.get(i).get(j));
            }
            System.out.println("----------------------");
        }
    }
}