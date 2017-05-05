package main;

public class MoveResult {

    private MoveType type;

    public MoveType getType() {
        return type;
    }

    private Piece piece;
    private Piece thisPiece;

    public Piece getPiece() {
        return piece;
    }
    
    public Piece getThisPiece(){
    	return thisPiece;
    }
    
    public MoveResult(MoveType type){
    	this(type, null, null);
    }
    
    public MoveResult(MoveType type, Piece thisPiece) {
        this(type, null, thisPiece);
    }

    public MoveResult(MoveType type, Piece piece, Piece thisPiece) {
        this.type = type;
        this.piece = piece;
        this.thisPiece = thisPiece;
    }
}
