package questionablequality.rpglifeserver.entity;

import javax.persistence.*;

@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private int goal;
    private int progress;
    private int rewardxp;

    @ManyToOne
    private User user;

    public Quest() {
    }

    public Quest(String name, String description, int goal, int progress, int rewardxp, User user) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.progress = progress;
        this.rewardxp = rewardxp;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getRewardxp() {
        return rewardxp;
    }

    public void setRewardxp(int rewardxp) {
        this.rewardxp = rewardxp;
    }
}