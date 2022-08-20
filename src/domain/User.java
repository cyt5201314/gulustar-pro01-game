package domain;

/**
 * 用户类
 */
public class User {
    private int id;
    private String username;
    private String password;
    private int curexp;
    private int points;
    private int level;

    public User(){

    }

    public User(int id, String username, String password, int curexp, int points, int level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.curexp = curexp;
        this.points = points;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurexp() {
        return curexp;
    }

    public void setCurexp(int curexp) {
        this.curexp = curexp;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", curexp=" + curexp +
                ", points=" + points +
                ", level=" + level +
                '}';
    }
}
