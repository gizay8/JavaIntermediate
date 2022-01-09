//Girilen takımlar için rastgele maç fikstürü oluşturan program
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        ArrayList<Team> team = new ArrayList<>();
        team.add(new Team("Galatasaray"));
        team.add(new Team("Bursaspor"));
        team.add(new Team("Fenerbahçe"));
        team.add(new Team("Beşiktaş"));
        team.add(new Team("Başakşehir"));
        team.add(new Team("Trabzonspor"));

        System.out.println("TAKIM SAYISI ÇİFT SENARYOSU");
        LeagueTable match = new LeagueTable(team);
        match.match();
        match.print();

        System.out.println("TAKIM SAYISI TEK SENARYOSU");
        team.add(new Team("Boluspor"));
        LeagueTable match2 = new LeagueTable(team);
        match2.match();
        match2.print();
    }
}
