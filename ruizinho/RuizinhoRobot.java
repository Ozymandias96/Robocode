package ruizinho;
import java.awt.Color;



import robocode.*;


/**
 * RuizinhoRobot - Based on the Sample Walls (Basically is like a Pseudo-WallṀs).
 * <p/>
 */
public class RuizinhoRobot extends Robot {
	boolean peek;
	double moveAmount;
	
	/**
	 * run: Move around the walls
	 */
	public void run() {
		setBodyColor(Color.yellow);
    	setGunColor(Color.red);
    	setScanColor(Color.white);
    	
    	moveAmount=Math.max(getBattleFieldWidth(),getBattleFieldHeight());//maximum distance that the robot can moved
    	
    	peek=false;
    	
    	//find the closest wall to move too
    	turnLeft(getHeading()%90);
    	ahead(moveAmount);
    	
    	
    	
    	peek=true;
    	turnGunRight(90);
    	
    	turnRight(90);
    	fire(2);
    	
        while (true) {
            
        	//Look before we go foward
        	peek=true;
            
            ahead(moveAmount);
            
            
            peek=false;
            //turn to next wall
            turnRight(90);
            
        }
    }
	

	/**
	 * onHitRobot:  Ram a little the enemy robot and then move away.
	 */
	public void onHitRobot(HitRobotEvent e){
		//in front of us
		if(e.getBearing() > -90 && e.getBearing() < 90){
			back(25);
			ahead(50);
			back(100);
		}
		else{
			ahead(100);
		}
			
	}
 
	
	/**
	 * onScannedRobot:  Fire wisely
	 */
    public void onScannedRobot(ScannedRobotEvent e) {
    	smartFire(e.getDistance());
    	
    	//to not lose track of the robot
    	if(peek==true){
    		scan();
    	}
    }
    
    /**
	 * smartFire:  Fire with intelligence
	 */
    public void smartFire(double robotDistance){
    	if(robotDistance >230 || getEnergy()<15){
    		fire(2);
    	}
    	else{
    		fire(3);
    	}
    }
    	
    
    /**
	 * Win:  Winning Dance
	 */
    public void Win(WinEvent e){
    	for (int i = 0; i < 100; i++) {
			turnRight(20);
			turnLeft(20);
		}
    }
}
