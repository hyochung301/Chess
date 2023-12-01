import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}

public class ChessBoard {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Piece[][] chessBoardStatus = new Piece[8][8];
    private ImageIcon[] pieceImage_b = new ImageIcon[7];
    private ImageIcon[] pieceImage_w = new ImageIcon[7];
    private JLabel message = new JLabel("Enter Reset to Start");

    ChessBoard(){
        initPieceImages();
        initBoardStatus();
        initializeGui();
    }

    public final void initBoardStatus(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
        }
    }

    public final void initPieceImages(){
        pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

        pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    }

    public ImageIcon getImageIcon(Piece piece){
        if(piece.color.equals(PlayerColor.black)){
            if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
            else return pieceImage_b[6];
        }
        else if(piece.color.equals(PlayerColor.white)){
            if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
            else return pieceImage_w[6];
        }
        else return pieceImage_w[6];
    }

    public final void initializeGui(){
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        JButton startButton = new JButton("Reset");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                initiateBoard();
            }
        });

        tools.add(startButton);
        tools.addSeparator();
        tools.add(message);

        chessBoard = new JPanel(new GridLayout(0, 8));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
        ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        Insets buttonMargin = new Insets(0,0,0,0);
        for(int i=0; i<chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.addActionListener(new ButtonListener(i, j));
                b.setMargin(buttonMargin);
                b.setIcon(defaultIcon);
                if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
                else b.setBackground(Color.gray);
                b.setOpaque(true);
                b.setBorderPainted(false);
                chessBoardSquares[j][i] = b;
            }
        }

        for (int i=0; i < 8; i++) {
            for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);

        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    //================================Utilize these functions========================================//

    class Piece{
        PlayerColor color;
        PieceType type;

        Piece(){
            color = PlayerColor.none;
            type = PieceType.none;
        }
        Piece(PlayerColor color, PieceType type){
            this.color = color;
            this.type = type;
        }
    }

    public void setIcon(int x, int y, Piece piece){
        chessBoardSquares[y][x].setIcon(getImageIcon(piece));
        chessBoardStatus[y][x] = piece;
    }

    public Piece getIcon(int x, int y){
        return chessBoardStatus[y][x];
    }

    public void markPosition(int x, int y){
        chessBoardSquares[y][x].setBackground(Color.pink);
    }

    public void unmarkPosition(int x, int y){
        if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
        else chessBoardSquares[y][x].setBackground(Color.gray);
    }

    public void setStatus(String inpt){
        message.setText(inpt);
    }

    public void initiateBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) setIcon(i, j, new Piece());
        }
        setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
        setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
        setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
        setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
        for(int i=0;i<8;i++){
            setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
            setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
        }
        setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
        setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
        setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
        setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) unmarkPosition(i, j);
        }
        onInitiateBoard();
    }
//======================================================Don't modify above==============================================================//




    //======================================================Implement below=================================================================//
    enum MagicType {MARK, CHECK, CHECKMATE};
    private int selX, selY;
    private boolean check, checkmate, end;
    private PlayerColor turn = PlayerColor.black;
    private PlayerColor winner = PlayerColor.none;
    boolean blackIsCheck = false, whiteIsCheck = false;

    class ButtonListener implements ActionListener{
        int x;
        int y;
        ButtonListener(int x, int y){
            this.x = x;
            this.y = y;
        }
        public void actionPerformed(ActionEvent e) {
            if (!end) {
                Piece selectedPiece = getIcon(x, y);
                if(selectedPiece.color == turn) {
                    // Unmark the old selected piece's possible moves
                    if(selX != -1 && selY != -1) {
                        unmarkPossibleMoves(selX, selY);
                    }
                    // Mark the new selected piece's possible moves
                    markPossibleMoves(x, y);
                    selX = x;
                    selY = y;
                } else if(selX != -1 && selY != -1) {
                    Piece pieceToMove = getIcon(selX, selY);
                    if(isInBounds(x, y) && isValidMove(pieceToMove, selX, selY, x, y)) {
                        // Unmark the moved piece's possible moves (before the move because of logic)
                        unmarkPossibleMoves(selX, selY);
                        movePiece(selX, selY, x, y);
                        selX = -1;
                        selY = -1;
                        checkBothColors();
                        if (check){
                            checkmate = isCheckmate(turn);
                        }
                        if (checkmate) {
                            check = false;
                            end = true;
                            winner = turn;
                            updateStatus();
                        }

                        turn = (turn == PlayerColor.black) ? PlayerColor.white : PlayerColor.black;
                        updateStatus();
                    } else {
                        // Unmark the selected piece's possible moves if an invalid move was attempted
                        unmarkPossibleMoves(selX, selY);
                        selX = -1;
                        selY = -1;
                    }
                }
            }
        }
    }


    void checkBothColors() {
        int[] blackKingPos = findKing(PlayerColor.black);
        Piece blackKing = getIcon(blackKingPos[0], blackKingPos[1]);
        blackIsCheck = isCheck(blackKing, blackKingPos[0], blackKingPos[1]);
    
        int[] whiteKingPos = findKing(PlayerColor.white);
        Piece whiteKing = getIcon(whiteKingPos[0], whiteKingPos[1]);
        whiteIsCheck = isCheck(whiteKing, whiteKingPos[0], whiteKingPos[1]);

        check = blackIsCheck || whiteIsCheck;
    }

    void markPossibleMoves(int x, int y) {
        Piece piece = getIcon(x, y);
        for(int j = 0; j < 8; j++) {
            for(int i = 0; i < 8; i++) {
                if(isInBounds(j, i) && isValidMove(piece, x, y, j, i)) {
                    markPosition(j, i);
                }
            }
        }
    }

    void unmarkPossibleMoves(int x, int y) {
        Piece piece = getIcon(x, y);
        for(int j = 0; j < 8; j++) {
            for(int i = 0; i < 8; i++) {
                if(isInBounds(j, i) && isValidMove(piece, x, y, j, i)) {
                    unmarkPosition(j, i);
                }
            }
        }
    }

    void movePiece(int startX, int startY, int endX, int endY) {
        Piece pieceToMove = getIcon(startX, startY);
        setIcon(endX, endY, pieceToMove);
        setIcon(startX, startY, new Piece());
    }

    void onInitiateBoard(){
        selX = -1;
        selY = -1;
        check = false;
        checkmate = false;
        end = false;
        turn = PlayerColor.black;
        updateStatus();
    }

    boolean isPathClear(int startX, int startY, int endX, int endY) {
        int dx = endX - startX;
        int dy = endY - startY;

        int stepX = Integer.signum(dx);
        int stepY = Integer.signum(dy);

        int currentX = startX + stepX;
        int currentY = startY + stepY;

        while (currentX != endX || currentY != endY) {
            Piece piece = getIcon(currentX, currentY);
            if (piece != null && piece.type != PieceType.none) {

                return false; // There is a piece in the way
            }
            currentX += stepX;
            currentY += stepY;
        }

        return true; // The path is clear
    }


    boolean isValidMove(Piece piece, int startX, int startY, int endX, int endY) {
        Piece destinationPiece = getIcon(endX, endY);
        if(destinationPiece.type != PieceType.none && destinationPiece.color == piece.color) {
            return false;
        }
        switch(piece.type) {
            case rook:
                return (startX == endX || startY == endY) && isPathClear(startX, startY, endX, endY);
            case bishop:
                return Math.abs(startX - endX) == Math.abs(startY - endY) && isPathClear(startX, startY, endX, endY);
            case queen:
                return (startX == endX || startY == endY || Math.abs(startX - endX) == Math.abs(startY - endY)) && isPathClear(startX, startY, endX, endY);
            case king:
                return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1 && !isCheck(piece, endX, endY);
            case knight:
                return (Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1) || (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2);
            case pawn:
                Piece frontPiece = null;
                if (isInBounds(startX + ((piece.color == PlayerColor.white) ? -1 : 1), startY)) {
                    frontPiece = getIcon(startX + ((piece.color == PlayerColor.white) ? -1 : 1), startY);
                }                
                Piece frontFrontPiece = null;
                if (isInBounds(startX + ((piece.color == PlayerColor.white) ? -2 : 2), startY)) {
                    frontFrontPiece = getIcon(startX + ((piece.color == PlayerColor.white) ? -2 : 2), startY);
                }                
                if (piece.color == PlayerColor.white) {
                    if ((startX - endX == 1) && (startY == endY) && frontPiece.type == PieceType.none) {
                        // One Forward
                        return true;
                    } else if ((destinationPiece.type != PieceType.none) && (startX - endX == 1) && (Math.abs(startY - endY) == 1)) {
                        // Diagonal capture
                        return true;
                    } else if ((startX == 6) && (startY - endY == 0) && ((startX - endX) == 2) && (frontFrontPiece.type == PieceType.none) && (frontPiece.type == PieceType.none)) {
                        // Two-square forward move from starting position
                        return true;
                    } else {
                        return false;
                    }
                } else if (piece.color == PlayerColor.black) {
                    if ((endX - startX == 1) && (startY == endY) && frontPiece.type == PieceType.none) {
                        // One Forward
                        return true;
                    } else if ((destinationPiece.type != PieceType.none) && (endX - startX == 1) && (Math.abs(startY - endY) == 1)) {
                        // Diagonal capture
                        return true;
                    } else if ((startX == 1) && (startY - endY == 0) && ((endX - startX) == 2) && (frontFrontPiece.type == PieceType.none) && (frontPiece.type == PieceType.none)) {
                        // Two-square forward move from starting position
                        return true;
                    } else {
                        return false;
                    }
                }
            default:
                return false;
        }
    }

    int[] findKing(PlayerColor color) {
        // PlayerColor oppositeColor = (color == PlayerColor.black) ? PlayerColor.white : PlayerColor.black;
        // Find the king of the given color
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getIcon(i, j);
                if (piece != null && piece.type == PieceType.king && piece.color == color) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // Return an invalid position if the king is not found
    }

    boolean isCheck(Piece king, int kingX, int kingY) {

        // Check if any opponent's piece can move to the given king's position
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Piece enemy = getIcon(i, j);
                if (enemy.type != PieceType.none && enemy.color != king.color && isValidMove(enemy, i, j, kingX, kingY)) {
                    return true; // The king is in check
                }
            }
        }

        return false; // The king is not in check
    }

    boolean isCheckmate(PlayerColor color) {
        PlayerColor oppositeColor = (color == PlayerColor.black) ? PlayerColor.white : PlayerColor.black;
        int[] kingPos = findKing(oppositeColor);
        Piece king = getIcon(kingPos[0], kingPos[1]);
        

        if (!(isCheck(king, kingPos[0], kingPos[1]))) {
            return false; // The king is not in check
        }

        // Check if the king can move to a safe square
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = kingPos[0] + dx;
                int newY = kingPos[1] + dy;
                if (isInBounds(newX, newY) && isValidMove(king, kingPos[0], kingPos[1], newX, newY)) {
                    return false; // The king can move to a safe square
                }
            }
        }

        return true; // The king is in checkmate
    }

    boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    void updateStatus() {
        if(checkmate) {
            setStatus(turn + " is in checkmate" + " / " + winner + " is the winner");
        } else if(check) {
            setStatus(turn + "'s turn / Check");
        } else if(end) {
            setStatus(winner + " is the winner");
        } else {
            setStatus(turn + "'s turn");
        }
    }
}