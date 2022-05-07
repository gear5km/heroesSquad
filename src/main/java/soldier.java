public class soldier {
    int health;
    int skill;

    public soldier(int health, int skill){
        this.health=health;
        this.skill = skill;
    }

    static soldier brenGunner = new soldier(10,6);
    static soldier ThompsonSMG = new soldier(6,8);
    static soldier rifleMan = new soldier(8,7);
    static soldier sharpShooter = new soldier(4, 9);
}

