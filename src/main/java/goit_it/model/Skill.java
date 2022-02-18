package goit_it.model;

public class Skill {
    private int id_skill;
    private String language;
    private String level_skill;

    public int getId_skill() {
        return id_skill;
    }

    public void setId_skill(int id_skill) {
        this.id_skill = id_skill;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel_skill() {
        return level_skill;
    }

    public void setLevel_skill(String level_skill) {
        this.level_skill = level_skill;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id_skill=" + id_skill +
                ", language='" + language + '\'' +
                ", level_skill='" + level_skill + '\'' +
                '}';
    }
}
