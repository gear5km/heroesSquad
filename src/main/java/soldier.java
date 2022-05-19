import java.util.ArrayList;

public class soldier {

    private static int count = 0;
    int id;
    String name;
    int health;
    int skill;

    private static ArrayList<soldier> members = new ArrayList<soldier>();

    public soldier(String name,int health, int skill){
        this.id = count++;
        this.name =name;
        this.health=health;
        this.skill = skill;
        members.add(this);

    }

    public int getId(){return this.id;}

    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

    public int getSkill(){
        return skill;
    }

    public static ArrayList<soldier> all() {
        return members;
    }


    static int brenGunnerSkill = 50;
    static int brenGunnerHealth = 100;
    static int thompsonGunnerSkill =70;
    static int thompsonGunnerHealth= 60;
    static int rifleManSkill =60;
    static int rifleManHealth= 80;

    //static int baseIdValue=0;
    //static soldier ThompsonSMG = new soldier(6,8);
    //static soldier rifleMan = new soldier(8,7);
    //static soldier sharpShooter = new soldier(4, 9);
}

