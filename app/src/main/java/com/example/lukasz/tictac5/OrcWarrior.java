package com.example.lukasz.tictac5;

import monsters.Monster;
import monsters.Orcs;

/**
 * Created by Lukasz on 2017-08-07.
 */

public class OrcWarrior extends Orcs {

    public OrcWarrior(int hp, int ap, int dp, int sp, int ar) {
        super(hp, ap, dp, sp, ar);
        super.name= "Orc Warrior";
        super.atackName1="Slash";
        super.atackName2="Charge";

    }
    public String attack1(Monster target){
        int dmg=(int)(getAttackPoints()*0.8);
        return super.attack1(target)+"\n"+attack(target, dmg)+"\n"+"\n";
    }
    public String attack2(Monster target){
        int dmg=(int)(getAttackPoints()*0.6);
        return super.attack2(target)+"\n"+attack(target, dmg)+" "+attack(target, dmg)+"\n"+"\n";

    }
}
