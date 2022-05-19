import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Heroww2Test{
    @Test
    public void checkSoldierInit(){
        soldier testSolder =new soldier("test",100,100 );
        assertEquals(false, testSolder instanceof soldier);
        assertEquals(2,testSolder.all().size());
    }

    @Test
    public void checkSoldierSkill(){
        soldier testSolder =new soldier("test",100,90 );
        assertEquals(100, testSolder.skill);
    }

    @Test
    public void checkSoldierId(){
        soldier testSolder =new soldier("test",100,90 );
        assertEquals(0, testSolder.id);
    }

    @Test
    public void checkFireTeamComposition(){
        soldier testSolder =new soldier("test",100,90 );
        soldier testSolder2 =new soldier("test",100,90 );
        fireteam.team1.add(testSolder);fireteam.team1.add(testSolder2);
        assertEquals(2,fireteam.getTeam1().size());

    }
}
