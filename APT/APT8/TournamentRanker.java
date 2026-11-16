import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TournamentRanker {
    public String[] rankTeams(String[] names, String[] lostTo) {
        HashMap<String, Team> map = new HashMap<>();
        for (String name : names) {
            map.put(name, new Team(name));
        }

        for (int i = 0; i < lostTo.length; i++) {
            if (lostTo[i].equals("")) continue;
            map.get(names[i]).beatBy = map.get(lostTo[i]);
            map.get(lostTo[i]).wins++;
        }

        ArrayList<Team> list = new ArrayList<>();

        for (Team t : map.values()) {
            list.add(t);
        }

        list.sort(this::compareTeams);

        String[] arr = new String[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i).name;
        }

        return arr;
    }

    private int compareTeams(Team a, Team b) {
        while (a != null && b != null) {
            int result = Integer.compare(b.wins, a.wins);
            if (result != 0) return result;

            a = a.beatBy;
            b = b.beatBy;
        }

        if (a == null && b == null) return 0;
        return a == null ? -1 : 1;
    }

    public static class Team {
        public String name;
        public int wins;
        public Team beatBy;
        public Team (String name) {
            this.name = name;
        }

        public int getWins() {
            return this.wins;
        }

        public int getBeatByWins() {
            if (beatBy == null) return 0;
            return beatBy.wins;
        }

        public String getTeam() {
            return this.name;
        }
    }
}
