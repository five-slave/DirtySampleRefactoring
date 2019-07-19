package ac.kr.ajou.refactoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseService {

    private final Map<String, Player> userDb;

    public DataBaseService() {
        userDb = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Player player = new Player();
            player.setAge(i + 10);
            player.setPassword("test" + i);
            player.setUserId("test" + i);
            player.setUserName("testUserName" + i);
            userDb.put("test" + i, player);
        }
    }

    public Player getPlayerById(String userId) {
        return userDb.getOrDefault(userId, null);
    }

    public boolean userExists(String userId) {
        return userDb.containsKey(userId);
    }

    public List<Player> getAllPlayers() {
        List<Player> playerList = new ArrayList<>();
        userDb.forEach((key, player) -> {
            playerList.add(player);
        });
        return playerList;
    }
}
