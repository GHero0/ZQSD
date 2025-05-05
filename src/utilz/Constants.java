package utilz;

public class Constants {
    
    public static class Directions{
        public static final int UP = 0;
        public static final int DOWN = 1;
        public static final int LEFT = 2;
        public static final int RIGHT = 3;
    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING  = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;

        public static int getSpriteAmount(int playerAction){
            switch (playerAction) {
                case IDLE :
                    return 8;                
                case RUNNING : 
                    return 10;
                case JUMPING :
                    return 4;
                case FALLING :
                    return 4;
                default:
                    return 0;
            }

        }
    }
}
