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

    private final boolean up;
    private final boolean down;
    private final boolean left;
    private final boolean right;

    /**
     * Constructor of the Piece.
     *
     * @param up true if the piece is open up.
     * @param down true if the piece is open down.
     * @param left true if the piece is open left.
     * @param right true if the piece is open right.
     */
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

    public int getWeight() {
        if (numberOfOpenings() == 2) {
            return 1;
        }

        return 2;
    }

    public int numberOfOpenings() {

        int openings = 0;

        if (isUp()) {
            openings++;
        }

        if (isDown()) {
            openings++;
        }

        if (isLeft()) {
            openings++;
        }

        if (isRight()) {
            openings++;
        }

        return openings;
    }
}
