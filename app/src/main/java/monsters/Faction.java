package monsters;

/**
 * Created by Lukasz on 2017-08-07.
 */

public enum Faction {
    NORDS("Nords"),
    DWARVES("Dwarves"),
    ELVES("Elves"),
    ORCS ("Orcs"),
    LIZARDS ("Lizards"),
    MONSTERS ("Monsters"),
    EMPIRE ("Empire"),
    TELKERIYA ("Tel Keriya"),
    ELSTER ("Elster");

    private String factionName;

    private Faction(String name){
        this.factionName=name;
    }
    public String toString(){
        return factionName;
    }

    public String getFactionName(){
        return toString();
    }
}

