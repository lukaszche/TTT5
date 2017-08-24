package monsters;

/**
 * Created by Lukasz on 2017-08-07.
 */

public enum MonsterType {
    MAGE("Mage"),
    CLOSE("Close"),
    RANGE("Range"),
    MACHINE ("Machine"),
    BIG ("Big"),
    SWARM ("Swarm");


    private String typeName;

    private MonsterType(String name){
        this.typeName=name;
    }
    public String toString(){
        return typeName;
    }

    public String getTypeName(){
        return toString();
    }
}
