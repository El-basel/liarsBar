import java.util.Random;
public class Gun {
    int availableShots = 6;
    int bulletIndex;
    int alreadyShot = 0;
    Random random = new Random();
    Gun() {
        bulletIndex = random.nextInt(0, availableShots);
    }

    boolean getShot() {
        if(bulletIndex == alreadyShot) {
            return true;
        } else {
            ++alreadyShot;
            return false;
        }
    }

}
