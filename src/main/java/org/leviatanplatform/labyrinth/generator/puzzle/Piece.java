package org.leviatanplatform.labyrinth.generator.puzzle;

public enum Piece {

    BI_UD( true,  true, false, false),
    BI_LR(false, false,  true,  true),
    BI_UR( true, false, false,  true),
    BI_DR(false,  true, false, true),
    BI_DL(false,  true,  true, false),
    BI_UL( true, false,  true, false),

    TRI_U(false,  true,  true,  true),
    TRI_D( true, false,  true,  true),
    TRI_L( true,  true, false,  true),
    TRI_R( true,  true,  true, false);

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    Piece(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
