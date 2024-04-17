package zachson.dev.setscore.extra;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import zachson.dev.setscore.SetScore;

import java.util.List;

public class SetScoreboard {

    private ScoreboardManager manager;
    private Scoreboard board;
    private Objective obj;

    public SetScoreboard(Player player) {
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("SB-" + player.getName(), "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(Formatting.getColor(Formatting.getPlaceholders(player, SetScore.getInstance().getConfig().getString("title"))));
        List<String> scores = SetScore.getInstance().getConfig().getStringList("scores");
        int scoreId = scores.size();
        String emptyScore = "&r";
        for(String score : scores) {
            if(score.isEmpty()) {
                score = emptyScore;
                emptyScore += "&r";
            }
            Score s = obj.getScore(Formatting.getColor(Formatting.getPlaceholders(player, score.toString())));
            s.setScore(scoreId);
            scoreId--;
        }
    }

    public Scoreboard get() {
        return board;
    }
}