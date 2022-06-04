package model;

public enum Direction {
//    UP, DOWN, LEFT, RIGHT;
    U, D, L, R;
    public Direction getOpposite() {
//        return switch(this) {
//            case UP -> DOWN;
//            case DOWN -> UP;
//            case LEFT -> RIGHT;
//            case RIGHT -> LEFT;
//        };
        return switch(this) {
            case U -> D;
            case D -> U;
            case L -> R;
            case R -> L;
        };
    }
}
