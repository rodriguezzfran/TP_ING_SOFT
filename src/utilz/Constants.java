package utilz;

import main.Game;

//Test
public class Constants {
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT* Game.SCALE);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT* Game.SCALE);

        }
    }

    public static class PlayerConstants {
        public static final int ATTACK = 0;
        public static final int DEAD = 1;
        public static final int DOOR_IN = 2;
        public static final int DOOR_OUT = 3;
        public static final int FALL = 4;
        public static final int GROUND = 5;
        public static final int HIT = 6;
        public static final int IDLE = 7;
        public static final int JUMP = 8;
        public static final int RUN = 9;

        public static int getSpriteAmount(int playerAction){
            switch (playerAction){
                case ATTACK: return 3;
                case DEAD: return 4;
                case DOOR_IN: return 8;
                case DOOR_OUT: return 8;
                case FALL: return 1;
                case GROUND: return 1;
                case HIT: return 2;
                case IDLE: return 11;
                case JUMP: return 1;
                case RUN: return 8;
                default: return 1;
            }
        }
    }
}

