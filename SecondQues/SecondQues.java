package chess;

public class ChessGame {
    public enum Color {
        WHITE, BLACK
    }

    public enum PieceType {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
    }

    public static class Piece {
        private Color color;
        private PieceType type;

        public Piece(Color color, PieceType type) {
            this.color = color;
            this.type = type;
        }

        public Color getColor() {
            return color;
        }

        public PieceType getType() {
            return type;
        }

        public boolean isValidMove(int sourceX, int sourceY, int destX, int destY) {
            return false;
        }
    }

    public static class Pawn extends Piece {
        private boolean hasMoved;

        public Pawn(Color color) {
            super(color, PieceType.PAWN);
            this.hasMoved = false;
        }

        @Override
        public boolean isValidMove(int sourceX, int sourceY, int destX, int destY) {
            int direction = getColor() == Color.WHITE ? 1 : -1;

            if (destX == sourceX && destY == sourceY + direction) {
                return true;
            }

            if (!hasMoved && destX == sourceX && destY == sourceY + 2 * direction) {
                return true;
            }

            return false;
        }
    }

    public static class Rook extends Piece {
        public Rook(Color color) {
            super(color, PieceType.ROOK);
        }

        @Override
        public boolean isValidMove(int sourceX, int sourceY, int destX, int destY) {
            return sourceX == destX || sourceY == destY;
        }
    }

    public static class Board {
        private Piece[][] pieces;

        public Board() {
            pieces = new Piece[8][8];
            setupBoard();
        }

        private void setupBoard() {
            pieces[0][0] = new Rook(Color.WHITE);
            pieces[0][1] = new Knight(Color.WHITE);
            pieces[0][2] = new Bishop(Color.WHITE);
            pieces[0][3] = new Queen(Color.WHITE);
            pieces[0][4] = new King(Color.WHITE);
            pieces[0][5] = new Bishop(Color.WHITE);
            pieces[0][6] = new Knight(Color.WHITE);
            pieces[0][7] = new Rook(Color.WHITE);
            for (int i = 0; i < 8; i++) {
                pieces[1][i] = new Pawn(Color.WHITE);
            }

            pieces[7][0] = new Rook(Color.BLACK);
            pieces[7][1] = new Knight(Color.BLACK);
            pieces[7][2] = new Bishop(Color.BLACK);
            pieces[7][3] = new Queen(Color.BLACK);
            pieces[7][4] = new King(Color.BLACK);
            pieces[7][5] = new Bishop(Color.BLACK);
            pieces[7][6] = new Knight(Color.BLACK);
            pieces[7][7] = new Rook(Color.BLACK);
            for (int i = 0; i < 8; i++) {
                pieces[6][i] = new Pawn(Color.BLACK);
            }
        }

        public Piece getPiece(int x, int y) {
            return pieces[x][y];
        }

        public void movePiece(int sourceX, int sourceY, int destX, int destY) {
            if (isValidMove(sourceX, sourceY, destX, destY)) {
                Piece piece = pieces[sourceX][sourceY];
                pieces[destX][destY] = piece;
                pieces[sourceX][sourceY] = null;
            } else {
                System.out.println("Invalid move!");
            }
        }

        private boolean isValidMove(int sourceX, int sourceY, int destX, int destY) {
            if (destX < 0 || destX >= 8 || destY < 0 || destY >= 8) {
                return false;
            }

            Piece piece = pieces[sourceX][sourceY];
            if (piece == null) {
                return false;
            }

            return piece.isValidMove(sourceX, sourceY, destX, destY);
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        boolean isWhiteTurn = true;

        while (true) {
            printBoard(board);

            int sourceX = 0;
            int sourceY = 0;
            int destX = 1;
            int destY = 0;

            Piece piece = board.getPiece(sourceX, sourceY);
            if (piece != null && ((isWhiteTurn && piece.getColor() == Color.WHITE) || (!isWhiteTurn && piece.getColor() == Color.BLACK))) {
                board.movePiece(sourceX, sourceY, destX, destY);
                isWhiteTurn = !isWhiteTurn;
            } else {
                System.out.println("Invalid move. Please try again.");
            }

            boolean gameOver = false;
            if (gameOver) {
                break;
            }
        }
    }

    private static void printBoard(Board board) {
        System.out.println("Current state of the board:");
    }
}
