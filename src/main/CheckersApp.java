package main;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CheckersApp extends Application {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private int hX, hY;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);
//        ArrayList<Piece> whites = new ArrayList<Piece>();
        
        Button select = new Button("Select");
        select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	hX = 0;
            	hY = 0;
            	hX = ThreadLocalRandom.current().nextInt(0, 8);
            	hY = ThreadLocalRandom.current().nextInt(0, 8);
            	do{
            		hX = ThreadLocalRandom.current().nextInt(0, 8);
                	hY = ThreadLocalRandom.current().nextInt(0, 8);
            	} while (board[hX][hY].getPiece() == null || 
            			board[hX][hY].getPiece().getType() == PieceType.RED ||
            			(tryMove(board[hX][hY].getPiece(), hX+1, hY-1).getType() == MoveType.NONE &&
            			tryMove(board[hX][hY].getPiece(), hX-1, hY-1).getType() == MoveType.NONE &&
            			tryMove(board[hX][hY].getPiece(), hX-2, hY-2).getType() == MoveType.NONE &&
            			tryMove(board[hX][hY].getPiece(), hX+2, hY-2).getType() == MoveType.NONE));
            	board[hX][hY].getPiece().setIsHigh(true);
            	System.out.println(hX + " " + hY);
            }
        });
        Button move = new Button("Move");
        move.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int wX, wY, rX, rY;
//            	whites.clear();
            	wX = hX;
            	wY = hY;
            	if(tryMove(board[wX][wY].getPiece(), wX-2, wY-2).getType() != MoveType.NONE){
                 	
                 	makePieceWithYourself(board[wX][wY].getPiece().getType(), wX, wY, wX-2, wY-2);
                 	
                 }
                 else if(tryMove(board[wX][wY].getPiece(), wX+2, wY-2).getType() != MoveType.NONE){
                 	
                 	makePieceWithYourself(board[wX][wY].getPiece().getType(), wX, wY, wX+2, wY-2);
                 	
                 }
                else if(tryMove(board[wX][wY].getPiece(), wX+1, wY-1).getType() != MoveType.NONE){
                	
                	makePieceWithYourself(board[wX][wY].getPiece().getType(), wX, wY, wX+1, wY-1);
                	
                }
                else if(tryMove(board[wX][wY].getPiece(), wX+1, wY-1).getType() != MoveType.NONE){
                	
                	makePieceWithYourself(board[wX][wY].getPiece().getType(), wX, wY, wX-1, wY-1);
                	
                }
           
                rX = ThreadLocalRandom.current().nextInt(0, 8);
            	rY = ThreadLocalRandom.current().nextInt(0, 8);
            	do{
            		rX = ThreadLocalRandom.current().nextInt(0, 8);
                	rY = ThreadLocalRandom.current().nextInt(0, 8);
            	}while (board[rX][rY].getPiece() == null || 
            			board[rX][rY].getPiece().getType() == PieceType.WHITE || 
            			(tryMove(board[rX][rY].getPiece(), rX-1, rY+1).getType() == MoveType.NONE &&
            			tryMove(board[rX][rY].getPiece(), rX+1, rY+1).getType() == MoveType.NONE &&
            			tryMove(board[rX][rY].getPiece(), rX-2, rY+2).getType() == MoveType.NONE &&
            			tryMove(board[rX][rY].getPiece(), rX+2, rY+2).getType() == MoveType.NONE));
            	if(tryMove(board[rX][rY].getPiece(), rX+1, rY+1).getType() != MoveType.NONE){
            		
                	makePieceWithYourself(board[rX][rY].getPiece().getType(), rX, rY, rX+1, rY+1);
                	
                }
                else if(tryMove(board[rX][rY].getPiece(), rX+1, rY+1).getType() != MoveType.NONE){
                	
                	makePieceWithYourself(board[rX][rY].getPiece().getType(), rX, rY, rX-1, rY+1);
                	
                }
                else if(tryMove(board[rX][rY].getPiece(), rX-2, rY+2).getType() != MoveType.NONE){
                	
                	makePieceWithYourself(board[rX][rY].getPiece().getType(), rX, rY, rX-2, rY+2);
                	
                }
                else if(tryMove(board[rX][rY].getPiece(), rX+2, rY+2).getType() != MoveType.NONE){
                	
                	makePieceWithYourself(board[rX][rY].getPiece().getType(), rX, rY, rX+2, rY+2);
                	
                }
                System.out.println(wX + " " + wY + " " + rX + " " + rY);
//                for (int a = 0; a < 8; a++){
//		    		for (int b = 0; b < 8; b++){
//		    			if (board[a][b].getPiece().getType() == PieceType.WHITE){
//		    				whites.add(board[a][b].getPiece());
//		    			}
//		    		}
//		    	}
            }
        });
        root.getChildren().add(move);
        root.getChildren().add(select);
        
        root.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                	move.fire();
                }
            }
        });
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
//                    whites.add(piece);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
    	if (newX >= 7 || newX <= 0 || newY >= 7 || newY <=0){
    		return new MoveResult(MoveType.NONE);
    	}
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveResult(MoveType.NORMAL, board[x0][y0].getPiece());
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece(), board[x0][y0].getPiece());
            }
        }

        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Piece makePieceWithYourself(PieceType type, int x, int y, int newX, int newY){
    	Piece piece = new Piece(type, x, y);
        MoveResult result;
        if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
            result = new MoveResult(MoveType.NONE);
        } else {
            result = tryMove(piece, newX, newY);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());
        switch (result.getType()) {
            case NONE:
                piece.abortMove();
                break;
            case NORMAL:
                Piece newPiece = new Piece (type, newX, newY);
                pieceGroup.getChildren().add(newPiece);
                pieceGroup.getChildren().remove(result.getThisPiece());
                board[x0][y0].setPiece(null);
                board[newX][newY].setPiece(newPiece);
                break;
            case KILL:
                newPiece = new Piece(type, newX, newY);
                pieceGroup.getChildren().add(newPiece);
            	board[x0][y0].setPiece(null);
                board[newX][newY].setPiece(newPiece);

                Piece otherPiece = result.getPiece();
                board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                pieceGroup.getChildren().remove(otherPiece);
                pieceGroup.getChildren().remove(result.getThisPiece());
                break;
        }
    	return piece;
    }

    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = tryMove(piece, newX, newY);
            }

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
        });

        return piece;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
