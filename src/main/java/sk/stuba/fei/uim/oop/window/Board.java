package sk.stuba.fei.uim.oop.window;

import sk.stuba.fei.uim.oop.players.PlayerColor;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.players.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Board extends JPanel implements MouseMotionListener, MouseListener {
    private int boardDimensions;
    private Tiles[][] tileBoard;
    private int oldXCoordinate;
    private int oldYCoordinate;
    private int[] newXYCoordinate;
    private int blackPawns;
    private int whitePawns;

    public int getBlackPawns() {
        return blackPawns;
    }

    public void setBlackPawns(int blackPawns) {
        this.blackPawns = blackPawns;
    }

    public int getWhitePawns() {
        return whitePawns;
    }

    public void setWhitePawns(int whitePawns) {
        this.whitePawns = whitePawns;
    }

    public int[] getNewXYCoordinate() {
        return newXYCoordinate;
    }

    public void setNewXYCoordinate(int[] newXYCoordinate) {
        this.newXYCoordinate = newXYCoordinate;
    }

    public int getOldXCoordinate() {
        return oldXCoordinate;
    }

    public void setOldXCoordinate(int oldXCoordinate) {
        this.oldXCoordinate = oldXCoordinate;
    }

    public int getOldYCoordinate() {
        return oldYCoordinate;
    }

    public void setOldYCoordinate(int oldYCoordinate) {
        this.oldYCoordinate = oldYCoordinate;
    }

    public Tiles[][] getTileBoard() {
        return tileBoard;
    }

    public void setTileBoard(Tiles[][] tileBoard) {
        this.tileBoard = tileBoard;
    }

    public int getBoardDimensions() {
        return boardDimensions;
    }

    public void setBoardDimensions(int boardDimensions) {
        this.boardDimensions = boardDimensions;
    }

    private int[] biggestChainCords = new int[] {0, 0};

    public int[] getBiggestChainCords() {
        return biggestChainCords;
    }

    public void setBiggestChainCords(int[] biggestChainCords) {
        this.biggestChainCords = biggestChainCords;
    }

    public Board(int boardDimensions) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setOldXCoordinate(0);
        setOldYCoordinate(0);
        setBoardDimensions(boardDimensions);
        setTileBoard(initializeTiles(getBoardDimensions()));
        getTileBoard()[getBoardDimensions()/2-1][getBoardDimensions()/2-1].setPlayerColor(PlayerColor.WHITE);
        getTileBoard()[getBoardDimensions()/2][getBoardDimensions()/2].setPlayerColor(PlayerColor.WHITE);
        getTileBoard()[getBoardDimensions()/2][getBoardDimensions()/2-1].setPlayerColor(PlayerColor.BLACK);
        getTileBoard()[getBoardDimensions()/2-1][getBoardDimensions()/2].setPlayerColor(PlayerColor.BLACK);
        checkPlayable(this, PlayerColor.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        paintBoard(g, getBoardDimensions());
    }

    private Tiles[][] initializeTiles(int dimensions) {
        Tiles[][] tileArray = new Tiles[dimensions][dimensions];
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                tileArray[i][j] = new Tiles(i, j);
            }
        }
        return tileArray;
    }

    private void paintBoard(Graphics g, int dimensions) {
        Dimension r = this.getSize();
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                decideColor(g, i, j);
                g.fillRect(i*r.width/dimensions, j* r.height/dimensions, r.width/dimensions, r.height/dimensions);
                if (getTileBoard()[i][j].isPlayable()) {
                    g.setColor(Color.LIGHT_GRAY);
                    smallCircle(g, dimensions, r, i, j);
                }
                if (getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && getTileBoard()[i][j].isMouseOver() && getTileBoard()[i][j].isPlayable()) {
                    g.setColor(Color.DARK_GRAY);
                    smallCircle(g, dimensions, r, i, j);
                }
                if (getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY) {
                    if (getTileBoard()[i][j].getPlayerColor() == PlayerColor.BLACK) {
                        new Player(g, i*r.width/dimensions, j* r.height/dimensions, r.width/dimensions, r.height/dimensions, Color.BLACK);
                    }
                    if (getTileBoard()[i][j].getPlayerColor() == PlayerColor.WHITE) {
                        new Player(g, i*r.width/dimensions, j* r.height/dimensions, r.width/dimensions, r.height/dimensions, Color.WHITE);
                    }
                }
            }
        }
    }

    private void smallCircle(Graphics g, int dimensions, Dimension r, int i, int j) {
        g.fillOval((i*r.width/dimensions)+(r.width/dimensions)/6, (j* r.height/dimensions)+(r.height/dimensions)/6, (r.width/dimensions)-(r.width/dimensions)/3, (r.height/dimensions)-(r.height/dimensions)/3);
        decideColor(g, i, j);
        g.fillOval((i*r.width/dimensions)+(r.width/dimensions)/4, (j* r.height/dimensions)+(r.height/dimensions)/4, (r.width/dimensions)-(r.width/dimensions)/2, (r.height/dimensions)-(r.height/dimensions)/2);
    }

    private void decideColor(Graphics g, int i, int j) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
            g.setColor(new Color(0, 255, 100));
        }
        else {
            g.setColor(new Color(80, 255, 10));
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClickedMethod(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setOldXCoordinate(3);
        setOldYCoordinate(3);
        mouseMovedMethod(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseExitedMethod(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMovedMethod(e);
    }

    private void mouseClickedMethod(MouseEvent e) {
        setNewXYCoordinate(valueXY(e));
        if ((getTileBoard()[getNewXYCoordinate()[0]][getNewXYCoordinate()[1]].isPlayable() && e.getButton() == 1) || (!checkPlayable(this, PlayerColor.BLACK) && checkPlayable(this, PlayerColor.WHITE))) {
            if (checkPlayable(this, PlayerColor.BLACK) || checkPlayable(this, PlayerColor.WHITE)) {
                if (checkPlayable(this, PlayerColor.BLACK)) {
                    if (getTileBoard()[getNewXYCoordinate()[0]][getNewXYCoordinate()[1]].isPlayable() && e.getButton() == 1) {
                        checkPlayable(this, PlayerColor.BLACK);
                        tilePlayed(this, getTileBoard()[getNewXYCoordinate()[0]][getNewXYCoordinate()[1]], PlayerColor.BLACK);
                        repaint();
                    }
                }
                if (checkPlayable(this, PlayerColor.WHITE)) {
                    checkPlayable(this, PlayerColor.WHITE);
                    tilePlayed(this, getTileBoard()[getBiggestChainCords()[0]][getBiggestChainCords()[1]], PlayerColor.WHITE);
                    checkPlayable(this, PlayerColor.BLACK);
                    repaint();
                }
                if (!checkPlayable(this, PlayerColor.BLACK)) {
                    System.out.println("NO LEGAL MOVE");
                }
                if (!checkPlayable(this, PlayerColor.WHITE)) {
                    System.out.println("PC HAS NO LEGAL MOVE");
                    if (checkPlayable(this, PlayerColor.WHITE)) {
                        checkPlayable(this, PlayerColor.WHITE);
                        tilePlayed(this, getTileBoard()[getBiggestChainCords()[0]][getBiggestChainCords()[1]], PlayerColor.WHITE);
                        checkPlayable(this, PlayerColor.BLACK);
                        repaint();
                    }
                }
            }
            if (!checkPlayable(this, PlayerColor.BLACK) && !checkPlayable(this, PlayerColor.WHITE)) {
                if (getBlackPawns() > getWhitePawns()) {
                    System.out.println("Winner: Black (" + getBlackPawns() + ")");
                }
                if (getBlackPawns() < getWhitePawns()) {
                    System.out.println("Winner: White (" + getWhitePawns() + ")");
                }
                if (getWhitePawns() == getBlackPawns()) {
                    System.out.println("IT'S A DRAW");
                    System.out.println("BLACK PAWNS: " + getBlackPawns());
                    System.out.println("WHITE PAWNS: " + getWhitePawns());
                }
            }
        }
    }

    private void mouseExitedMethod(MouseEvent e) {
        setNewXYCoordinate(valueXY(e));
        getTileBoard()[getNewXYCoordinate()[0]][getNewXYCoordinate()[1]].setMouseOver(false);
        repaint();
    }

    private void mouseMovedMethod(MouseEvent e) {
        setNewXYCoordinate(valueXY(e));
        if (newXYCoordinate[0] != getOldXCoordinate() || newXYCoordinate[1] != getOldYCoordinate()) {
            getTileBoard()[getOldXCoordinate()][getOldYCoordinate()].setMouseOver(false);
            setOldXCoordinate(newXYCoordinate[0]);
            setOldYCoordinate(newXYCoordinate[1]);
            getTileBoard()[newXYCoordinate[0]][newXYCoordinate[1]].setMouseOver(true);
            repaint();
        }
    }

    private int[] valueXY(MouseEvent e) {
        int[] valueXY = new int[2];
        int x = e.getX()/(super.getSize().width/getBoardDimensions());
        int y = e.getY()/(super.getSize().height/getBoardDimensions());
        if (x >= getBoardDimensions())
            x=getBoardDimensions()-1;
        if (x < 0)
            x = 0;
        if (y >= getBoardDimensions())
            y=getBoardDimensions()-1;
        if (y < 0)
            y = 0;
        valueXY[0] = x;
        valueXY[1] = y;
        return valueXY;
    }

    private void isPlayableSouth(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            j++;
            if (j >= getBoardDimensions()) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i][j-1].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange( board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && j - tiles.getLocationY() < 2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setSouth(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private void isPlayableNorth(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            j--;
            if (j <= -1) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i][j+1].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange( board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && j - tiles.getLocationY() > -2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setNorth(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private void isPlayableEast(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            i++;
            if (i >= getBoardDimensions()) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i-1][j].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange( board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && i - tiles.getLocationX() < 2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setEast(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private void isPlayableWest(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            i--;
            if (i <= -1) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i+1][j].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange( board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && i - tiles.getLocationX() > -2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setWest(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private void isPlayableEastNorth(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            i++;
            j--;
            if (j <= -1 && i >= getBoardDimensions()) {
                break;
            }
            if (i >= getBoardDimensions()) {
                break;
            }
            if (j <= -1) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i-1][j+1].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange( board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && i - tiles.getLocationX() > -2 && j - tiles.getLocationY() > -2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setEastNorth(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private void isPlayableWestSouth(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            i--;
            j++;
            if (j >= getBoardDimensions() && i <= -1) {
                break;
            }
            if (j >= getBoardDimensions()) {
                break;
            }
            if (i <= -1) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i+1][j-1].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange( board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && i - tiles.getLocationX() > -2 && j - tiles.getLocationY() > -2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setWestSouth(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private void isPlayableWestNorth(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            i--;
            j--;
            if (j <= -1 && i <= -1) {
                break;
            }
            if (i <= -1) {
                break;
            }
            if (j <= -1) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i+1][j+1].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange( board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && i - tiles.getLocationX() > -2 && j - tiles.getLocationY() > -2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setWestNorth(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private void isPlayableEastSouth(Board board, Tiles tiles, PlayerColor playerColor) {
        int i = tiles.getLocationX();
        int j = tiles.getLocationY();
        board.getTileBoard()[i][j].setOldBiggestChange(board.getTileBoard()[i][j].getBiggestChange());
        while (true) {
            i++;
            j++;
            if (j >= getBoardDimensions() && i >= getBoardDimensions()) {
                break;
            }
            if (i >= getBoardDimensions()) {
                break;
            }
            if (j >= getBoardDimensions()) {
                break;
            }
            if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY && board.getTileBoard()[i-1][j-1].getPlayerColor() != playerColor)
                break;
            if (board.getTileBoard()[i][j].getPlayerColor() != PlayerColor.EMPTY && board.getTileBoard()[i][j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getBiggestChange() + 1);
            }
            else {
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor && i - tiles.getLocationX() < 2 && j - tiles.getLocationY() < 2)
                    break;
                if (board.getTileBoard()[i][j].getPlayerColor() == playerColor) {
                    board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setEastSouth(true);
                    return;
                }
            }
        }
        board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].setBiggestChange(board.getTileBoard()[tiles.getLocationX()][tiles.getLocationY()].getOldBiggestChange());
    }

    private boolean checkPlayable(Board board, PlayerColor playerColor) {
        int biggestChain = 0;
        setBlackPawns(0);
        setWhitePawns(0);
        for (int i = 0; i < boardDimensions; i++) {
            for (int j = 0; j < boardDimensions; j++) {
                if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.BLACK) {
                    setBlackPawns(getBlackPawns()+1);
                }
                if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.WHITE) {
                    setWhitePawns(getWhitePawns()+1);
                }
                board.getTileBoard()[i][j].setPlayable(false);
                board.getTileBoard()[i][j].setSouth(false);
                board.getTileBoard()[i][j].setNorth(false);
                board.getTileBoard()[i][j].setWest(false);
                board.getTileBoard()[i][j].setEast(false);
                board.getTileBoard()[i][j].setEastNorth(false);
                board.getTileBoard()[i][j].setWestSouth(false);
                board.getTileBoard()[i][j].setWestNorth(false);
                board.getTileBoard()[i][j].setEastSouth(false);
                if (board.getTileBoard()[i][j].getPlayerColor() == PlayerColor.EMPTY) {
                    board.getTileBoard()[i][j].setBiggestChange(0);
                    isPlayableSouth(board, board.getTileBoard()[i][j], playerColor);
                    isPlayableNorth(board, board.getTileBoard()[i][j], playerColor);
                    isPlayableWest(board, board.getTileBoard()[i][j], playerColor);
                    isPlayableEast(board, board.getTileBoard()[i][j], playerColor);
                    isPlayableEastNorth(board, board.getTileBoard()[i][j], playerColor);
                    isPlayableWestSouth(board, board.getTileBoard()[i][j], playerColor);
                    isPlayableWestNorth(board, board.getTileBoard()[i][j], playerColor);
                    isPlayableEastSouth(board, board.getTileBoard()[i][j], playerColor);
                }
                if (board.getTileBoard()[i][j].isSouth() || board.getTileBoard()[i][j].isNorth() ||
                        board.getTileBoard()[i][j].isWest() || board.getTileBoard()[i][j].isEast() ||
                        board.getTileBoard()[i][j].isEastNorth() || board.getTileBoard()[i][j].isWestSouth() ||
                        board.getTileBoard()[i][j].isWestNorth() || board.getTileBoard()[i][j].isEastSouth()) {
                    board.getTileBoard()[i][j].setPlayable(true);
                    if (biggestChain < board.getTileBoard()[i][j].getBiggestChange()) {
                        setBiggestChainCords(new int[]{i, j});
                        biggestChain = board.getTileBoard()[i][j].getBiggestChange();
                    }
                }
            }
        }
        return biggestChain > 0;
    }
    private void tilePlayed(Board board, Tiles tile, PlayerColor playerColor) {
        tile.setPlayerColor(playerColor);
        if (tile.isEast()) {
            int i = 1;
            while (board.getTileBoard()[tile.getLocationX()+i][tile.getLocationY()].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()+i][tile.getLocationY()].setPlayerColor(playerColor);
                i++;
            }
        }
        if (tile.isWest()) {
            int i = 1;
            while (board.getTileBoard()[tile.getLocationX()-i][tile.getLocationY()].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()-i][tile.getLocationY()].setPlayerColor(playerColor);
                i++;
            }
        }
        if (tile.isNorth()) {
            int i = 1;
            while (board.getTileBoard()[tile.getLocationX()][tile.getLocationY()-i].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()][tile.getLocationY()-i].setPlayerColor(playerColor);
                i++;
            }
        }
        if (tile.isSouth()) {
            int i = 1;
            while (board.getTileBoard()[tile.getLocationX()][tile.getLocationY()+i].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()][tile.getLocationY()+i].setPlayerColor(playerColor);
                i++;
            }
        }
        if (tile.isEastNorth()) {
            int i = 1;
            int j = 1;
            while (board.getTileBoard()[tile.getLocationX()+i][tile.getLocationY()-j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()+i][tile.getLocationY()-j].setPlayerColor(playerColor);
                i++;
                j++;
            }
        }
        if (tile.isWestSouth()) {
            int i = 1;
            int j = 1;
            while (board.getTileBoard()[tile.getLocationX()-i][tile.getLocationY()+j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()-i][tile.getLocationY()+j].setPlayerColor(playerColor);
                i++;
                j++;
            }
        }
        if (tile.isWestNorth()) {
            int i = 1;
            int j = 1;
            while (board.getTileBoard()[tile.getLocationX()-i][tile.getLocationY()-j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()-i][tile.getLocationY()-j].setPlayerColor(playerColor);
                i++;
                j++;
            }
        }
        if (tile.isEastSouth()) {
            int i = 1;
            int j = 1;
            while (board.getTileBoard()[tile.getLocationX()+i][tile.getLocationY()+j].getPlayerColor() != playerColor) {
                board.getTileBoard()[tile.getLocationX()+i][tile.getLocationY()+j].setPlayerColor(playerColor);
                i++;
                j++;
            }
        }
    }
}
