package monsters;

import exceptions.BadPotionException;
import exceptions.DeadPokemonException;

/**
 * Created by Lukasz on 2017-08-07.
 */

public abstract class Monster {

    private int hpMax;
    private int hpCurr;
    private int atackPts;
    private int defPts;
    private int armor;
    private int speedPts;
    protected String name;
    protected String atackName1;
    protected String atackName2;
    protected String atackName3;
    protected String atackName4;
    protected Faction faction;
    protected MonsterType type;

    public Monster(int hp,int ap, int dp, int sp, int ar){
        this.atackPts=ap;
        this.hpMax=hp;
        this.defPts=dp;
        this.speedPts=sp;
        this.hpCurr=hp;
        this.armor=ar;
    }

    public void heal(int hp) throws BadPotionException, DeadPokemonException {

        if(hp<0){
            throw new BadPotionException("Heal value can only be positive.");
        }

        if(hpCurr>0){
            if (hpCurr+hp>hpMax){
                hpCurr=hpMax;
            }
            else{
                hpCurr+=hp;
            }
        }
        else{
            throw new DeadPokemonException("Your Unit is dead...");
        }
    }

    public String attack1(Monster target){

        return getName()+" used "+getAttackName1()+".";

    }
    public String attack2(Monster target){
        return getName()+" used "+getAttackName2()+".";

    }
    public String attack3(Monster target){
        return getName()+" used "+getAttackName3()+".";
    }
    public String attack4(Monster target){
        return getName()+" used "+getAttackName4()+".";
    }

    public String attack(Monster target, int dmg){
        return target.getAttacked(dmg);
    }

    public String getAttacked(int dmg){
        dmg=(int)(dmg/2)+((dmg-defPts)/2);
        dmg -=(int)(armor);
        if(dmg<=0){
            dmg=1;
        }
        if(hpCurr>dmg){
            hpCurr-=dmg;
        }
        else{
            hpCurr=0;
            return attackInfo(dmg)+"/n"+deadInfo();
        }
        return attackInfo(dmg);
    }



    private final String deadInfo(){
        return getName()+" is dead.";
    }

    private String attackInfo(int dmg){
        if(dmg>0){
            return "Dealt "+dmg+" to "+getName();
        }else{
            return "";
        }
    }


    public int getHpMax() {
        return hpMax;
    }

    public int getHpCurrent() {
        return hpCurr;
    }

    public int getAttackPoints() {
        return atackPts;
    }

    public int getDefPoints() {
        return defPts;
    }
    public int getArmor() {
        return armor;
    }
    public String getName() {
        return name;
    }

    public String getAttackName1() {
        return atackName1;
    }
    public String getAttackName2() {
        return atackName2;
    }
    public String getAttackName3() {
        return atackName3;
    }
    public String getAttackName4() {
        return atackName4;
    }

    public Faction getFaction() {
        return faction;
    }

    public MonsterType getType() {
        return type;
    }

    public void setAtackPts(int atackPts) {
        this.atackPts = atackPts;
    }

    public void setDefPts(int defPts) {
        this.defPts = defPts;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
