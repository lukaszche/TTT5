package monsters;

/**
 * Created by Lukasz on 2017-08-07.
 */

public abstract class Orcs extends Monster {

    public Orcs(int hp,int ap, int dp, int sp, int ar) {
        super(hp, ap, dp, sp, ar);
        super.faction=Faction.ORCS;

    }

}
