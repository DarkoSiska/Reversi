package sk.stuba.fei.uim.oop.players;


public class Tiles {
    private int locationX;
    private int locationY;
    private PlayerColor playerColor;
    private boolean mouseOver;
    private boolean isPlayable;
    private boolean south;
    private boolean north;
    private boolean west;
    private boolean east;
    private boolean eastNorth;
    private boolean westSouth;
    private boolean westNorth;
    private boolean eastSouth;
    private int biggestChange;
    private int oldBiggestChange;

    public int getOldBiggestChange() {
        return oldBiggestChange;
    }

    public void setOldBiggestChange(int oldBiggestChange) {
        this.oldBiggestChange = oldBiggestChange;
    }

    public int getBiggestChange() {
        return biggestChange;
    }

    public void setBiggestChange(int biggestChange) {
        this.biggestChange = biggestChange;
    }

    public boolean isSouth() {
        return south;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public boolean isNorth() {
        return north;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public boolean isWest() {
        return west;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean isEast() {
        return east;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean isEastNorth() {
        return eastNorth;
    }

    public void setEastNorth(boolean eastNorth) {
        this.eastNorth = eastNorth;
    }

    public boolean isWestSouth() {
        return westSouth;
    }

    public void setWestSouth(boolean westSouth) {
        this.westSouth = westSouth;
    }

    public boolean isWestNorth() {
        return westNorth;
    }

    public void setWestNorth(boolean westNorth) {
        this.westNorth = westNorth;
    }

    public boolean isEastSouth() {
        return eastSouth;
    }

    public void setEastSouth(boolean eastSouth) {
        this.eastSouth = eastSouth;
    }

    public boolean isPlayable() {
        return isPlayable;
    }

    public void setPlayable(boolean playable) {
        isPlayable = playable;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public Tiles(int locationX, int locationY) {
        setLocationX(locationX);
        setLocationY(locationY);
        setPlayerColor(PlayerColor.EMPTY);
        setBiggestChange(0);
        setMouseOver(false);
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }
}
