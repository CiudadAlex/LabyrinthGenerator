package org.leviatanplatform.labyrinth.generator.puzzle;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.util.RegionFiller;
import org.leviatanplatform.labyrinth.generator.util.WallUtils;
import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class PuzzleLabyrinth implements LabyrinthGenerator {
    @Override
    public Labyrinth generate(int numRowsInitial, int numColsInitial) {

        int numRows = numRowsInitial % 2 == 0 ? numRowsInitial + 1 : numRowsInitial;
        int numCols = numColsInitial % 2 == 0 ? numColsInitial + 1 : numColsInitial;

        Random random = new Random();

        Labyrinth labyrinth = new Labyrinth(numRows, numCols);
        labyrinth.setSquareOnlyIfBlank(1, 0, Square.START);
        labyrinth.setSquareOnlyIfBlank(numRows - 2, numCols - 1, Square.TARGET);

        WallUtils.makeOutsideWall(labyrinth);

        for (int r = 1; r < numRows; r = r + 2) {
            for (int c = 1; c < numCols; c = c + 2) {
                decideAndPaintPiece(labyrinth, r, c, random);
            }
        }

        //labyrinth = RegionFiller.fillReachableFromPoint(labyrinth, 1, 0, Square.FILL_START);
        //labyrinth = RegionFiller.fillReachableFromPoint(labyrinth, numRows - 2, numCols - 1, Square.FILL_TARGET);

        // FIXME finish

        return labyrinth;
    }

    private void decideAndPaintPiece(Labyrinth labyrinth, int row, int col, Random random) {

        List<Piece> listPossiblePieces = Stream.of(Piece.values()).filter(p -> fitsPiece(labyrinth, p, row, col)).toList();

        if (listPossiblePieces.isEmpty()) {
            return;
        }

        Piece randomPiece = selectRandomPieceUsingWeight(listPossiblePieces, random);
        paintPiece(labyrinth, randomPiece, row, col);
    }

    private Piece selectRandomPieceUsingWeight(List<Piece> listPieces, Random random) {

        int numWeighted = listPieces.stream().mapToInt(Piece::getWeight).sum();
        int randomIndicator = random.nextInt(numWeighted);
        int sum = 0;

        for (Piece piece : listPieces) {

            sum = sum + piece.getWeight();

            if (sum >= randomIndicator) {
                return piece;
            }
        }

        return null;
    }

    private boolean fitsPiece(Labyrinth labyrinth, Piece piece, int row, int col) {

        if (piece.isUp() && isThereAWallInDirection(labyrinth, row, col, Direction.UP)) {
            return false;
        }

        if (piece.isDown() && isThereAWallInDirection(labyrinth, row, col, Direction.DOWN)) {
            return false;
        }

        if (piece.isLeft() && isThereAWallInDirection(labyrinth, row, col, Direction.LEFT)) {
            return false;
        }

        if (piece.isRight() && isThereAWallInDirection(labyrinth, row, col, Direction.RIGHT)) {
            return false;
        }

        return true;
    }

    private boolean isThereAWallInDirection(Labyrinth labyrinth, int row, int col, Direction dir) {

        Square square = labyrinth.getSquare(row + dir.getDeltaR(), col + dir.getDeltaC());
        return square == Square.WALL;
    }

    private void paintPiece(Labyrinth labyrinth, Piece piece, int row, int col) {

        labyrinth.setWallOnlyIfBlank(row + 1, col + 1);
        labyrinth.setWallOnlyIfBlank(row - 1, col - 1);
        labyrinth.setWallOnlyIfBlank(row + 1, col - 1);
        labyrinth.setWallOnlyIfBlank(row - 1, col + 1);

        if (!piece.isUp()) {
            putWallInDirection(labyrinth, row, col, Direction.UP);
        }

        if (!piece.isDown()) {
            putWallInDirection(labyrinth, row, col, Direction.DOWN);
        }

        if (!piece.isLeft()) {
            putWallInDirection(labyrinth, row, col, Direction.LEFT);
        }

        if (!piece.isRight()) {
            putWallInDirection(labyrinth, row, col, Direction.RIGHT);
        }
    }

    private static void putWallInDirection(Labyrinth labyrinth, int row, int col, Direction dir) {
        labyrinth.setWallOnlyIfBlank(row + dir.getDeltaR(), col + dir.getDeltaC());
    }
}
