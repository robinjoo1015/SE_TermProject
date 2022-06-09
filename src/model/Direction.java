package model;

public enum Direction {
    U, D, L, R;

    public Direction getOpposite() {
        return switch (this) {
            case U -> D;
            case D -> U;
            case L -> R;
            case R -> L;
        };
    }
}
