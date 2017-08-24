package com.example.lukasz.tictac5;

import monsters.Monster;
import monsters.Orcs;

/**
 * Created by Lukasz on 2017-08-07.
 */

public class OrcShaman extends Orcs {

    public OrcShaman(int hp, int ap, int dp, int sp, int ar) {
        super(hp, ap, dp, sp, ar);
        super.name= "Orc Shaman";
        super.atackName1="Blood Boil";
        super.atackName2="Slam";
        super.atackName3="Curse";
        super.atackName4="Fire Ball";

    }
    public String attack1(Monster target){
        int dmg=(int)(getAttackPoints()*0.05);
        setAtackPts((int)(getAttackPoints()*1.3));
        return super.attack1(this)+"\n"+getName()+" boosts his attack by "+(int)(getAttackPoints()*0.3)+"\n"+attack(this, dmg)+"\n"+"\n";
    }
    public String attack2(Monster target){
        int dmg=(int)(getAttackPoints()*0.6);
        return super.attack2(target)+"\n"+attack(target, dmg)+" "+attack(target, dmg)+"\n"+"\n";

    }
    public String attack3(Monster target){
        int dmg=(int)(getAttackPoints()*0.6);
        return super.attack3(target)+"\n"+attack(target, dmg)+" "+attack(target, dmg)+"\n"+"\n";

    }
    public String attack4(Monster target){
        int dmg=(int)(getAttackPoints()*0.6);
        return super.attack4(target)+"\n"+attack(target, dmg)+" "+attack(target, dmg)+"\n"+"\n";

    }
}