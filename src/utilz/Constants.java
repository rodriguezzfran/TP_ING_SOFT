package utilz;

import main.Game;

//Test
public class Constants {

    public static class EnemyConstants{
        public static final int KING_PIG = 5;

        public static final int ATTACK  = 0;
        public static final int DEAD  = 1;
        public static final int FALL  = 2;
        public static final int GROUND  = 3;
        public static final int HIT  = 4;
        public static final int IDLE  = 5;
        public static final int JUMP  = 6;
        public static final int RUN  = 7;

        public static final int KING_PIG_WIDTH_DEFAULT = 38;
        public static final int KING_PIG_HEIGHT_DEFAULT = 28;

        public static final int KING_PIG_WIDTH = (int)(KING_PIG_WIDTH_DEFAULT * Game.SCALE);
        public static final int KING_PIG_HEIGHT = (int)(KING_PIG_HEIGHT_DEFAULT * Game.SCALE);

        public static final int KING_PIG_DRAWOFFSET_X = (int)(11*Game.SCALE);
        public static final int KING_PIG_DRAWOFFSET_Y = (int)(9*Game.SCALE);

        public static int GetSpriteAmount(int enemy_type, int enemy_state){
            switch (enemy_type) {
                case KING_PIG:
                    switch (enemy_state){
                        case ATTACK:
                            return 5;
                        case DEAD:
                            return 4;
                        case FALL:
                            return 1;
                        case GROUND:
                            return 1;
                        case HIT:
                            return 2;
                        case IDLE:
                            return 12;
                        case JUMP:
                            return 1;
                        case RUN:
                            return 6;
                    }
            }
            return 0;

        }

        public static int GetMaxHealth(int enemy_type){
            switch (enemy_type){
                case KING_PIG:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int GetEnemyDmg(int enemy_type){
            switch (enemy_type){
                case KING_PIG:
                    return 15;
                default:
                    return 0;
            }
        }


    }

    public static class Directions {
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
        public static class URMButton{
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int)(URM_DEFAULT_SIZE*Game.SCALE);
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

