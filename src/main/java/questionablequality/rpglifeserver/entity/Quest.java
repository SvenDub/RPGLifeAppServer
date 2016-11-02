package questionablequality.rpglifeserver.entity;

import com.sun.istack.internal.Nullable;
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
    private String placeName;
    private double placeLat;
    private double placeLong;

    @Nullable
    @ManyToOne
    private User user;
    
    @Nullable
    @ManyToOne
    private Guild guild;

    public Quest() {
    }

    public int getId() {
        return id;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getPlaceLat() {
        return placeLat;
    }

    public void setPlaceLat(double placeLat) {
        this.placeLat = placeLat;
    }

    public double getPlaceLong() {
        return placeLong;
    }

    public void setPlaceLong(double placeLong) {
        this.placeLong = placeLong;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }
    
    
}
