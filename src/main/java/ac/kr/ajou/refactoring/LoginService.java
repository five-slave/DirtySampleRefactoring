package ac.kr.ajou.refactoring;

import java.util.List;

public class LoginService {

    private final DataBaseService dataBaseService;

    public LoginService () {
        this.dataBaseService = new DataBaseService();
    }

    public boolean login (String userId, String password) {
        if (userId != null && !userId.isEmpty()) {
            if (dataBaseService.userExists(userId)) {
                Player player = dataBaseService.getPlayerById(userId);
                if (player.getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getUserNameById(String id) {
        if (dataBaseService.userExists(id)) {
            Player player = dataBaseService.getPlayerById(id);
            return player.getUserName();
        } else {
            return null;
        }
    }

    public List<Player> getAge(int a) {
        List<Player> allPlayers = dataBaseService.getAllPlayers();
        return allPlayers;
    }
}
