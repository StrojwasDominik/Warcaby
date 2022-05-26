import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class CheckersGame {
	GridPane board;
	BorderPane gameFrame;
	StackPane[][] checkerSquares;
	CheckersPiece[] pieces;
	Shape[][] boardPieces;
	int p1Pieces = 12, p2Pieces = 12, turn = 1;
	MenuBar menuBar;
	VBox info, top;
	Text turnText, winMessage;
	Stage stage;


	public CheckersGame() {
		board = new GridPane();
		checkerSquares = new StackPane[8][8];
		buildBoard();
		gameFrame = new BorderPane();
		buildMenuBar();
		winMessage = new Text();
		info = new VBox();
		turnText = new Text("Tura gracza 1");
		info.getChildren().add(turnText);
		info.getChildren().add(winMessage);
		top = new VBox();
		top.getChildren().add(menuBar);
		top.getChildren().add(info);
		gameFrame.setTop(top);
		gameFrame.setCenter(board);
		stage = new Stage();
	}


	private void buildBoard() {
		int pieceCount = 0;

		buildBoardPieces();
		buildCheckerPieces();
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				checkerSquares[x][y] = new StackPane();
				checkerSquares[x][y].getChildren().add(boardPieces[x][y]);
				//Adds pieces to checker square.
				if(x < 3) {
					if(x % 2 == 0 && y % 2 == 0) {
						checkerSquares[x][y].getChildren().add(pieces[pieceCount]);
						pieces[pieceCount].setPosition(x, y);
						pieceCount++;
					}
					else if(x == 1 && y % 2 != 0) {
						checkerSquares[x][y].getChildren().add(pieces[pieceCount]);
						pieces[pieceCount].setPosition(x, y);
						pieceCount++;
					}
				}
				if(x > 4) {
					if(x % 2 == 0 && y % 2 == 0) {
						checkerSquares[x][y].getChildren().add(pieces[pieceCount]);
						pieces[pieceCount].setPosition(x, y);
						pieceCount++;

					}
					else if(x % 2 != 0 && y % 2 != 0) {
						checkerSquares[x][y].getChildren().add(pieces[pieceCount]);
						pieces[pieceCount].setPosition(x, y);
						pieceCount++;
					}
				}
				board.add(checkerSquares[x][y], y, x);
			}
		}
	}

	private void buildBoardPieces() {
		boardPieces = new Rectangle[8][8];
		for(int x = 0; x < 8; x++) {
			if(x % 2 == 0) {
				for(int y = 0; y < 8; y++) {
					if(y % 2 == 0) {
						boardPieces[x][y] = new Rectangle(40, 40, Color.WHEAT);
					}
					else {
						boardPieces[x][y] = new Rectangle(40, 40, Color.MAROON);
					}
					boardPieces[x][y].setStroke(Color.BLACK);
				}
			}
			else {
				for(int y = 0; y < 8; y++) {
					if(y % 2 != 0) {
						boardPieces[x][y] = new Rectangle(40, 40, Color.WHEAT);
					}
					else {
						boardPieces[x][y] = new Rectangle(40, 40, Color.MAROON);
					}
					boardPieces[x][y].setStroke(Color.BLACK);
				}
			}
		}
	}

	private void buildCheckerPieces() {
		pieces = new CheckersPiece[24];
		for(int i = 0; i < 24; i++) {
			if(i < 12) {
				CheckersPiece piece = new CheckersPiece("BLACK");
				piece.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent movePiece) {
						System.out.println("Kliknieto czarny pionek");
						if(turn == 1) {
							unHighlight();
							waitForMove(piece);
						}
					}});
				pieces[i] = piece;
			}
			else {
				CheckersPiece piece = new CheckersPiece("RED");
				piece.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent movePiece) {
						System.out.println("Kliknieto czerwony pionek");
						if(turn == 2) {
							unHighlight();
							waitForMove(piece);
						}
					}});
				pieces[i] = piece;
			}
		}
	}

	protected void waitForMove(CheckersPiece piece) {
		if(piece.getType().equals("BLACK")){

		}
		else if(piece.getType().equals("RED")) {

		}
	}

	protected void resetBoardPieces() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				boardPieces[x][y].setOnMouseClicked(null);
			}
		}
	}

	protected boolean checkForPiece(int row, int col) {
		boolean found = false;
		for(int i = 0; i < pieces.length; i++) {
			if(pieces[i].row == row && pieces[i].col == col) {
				found = true;
				break;
			}
		}
		return found;
	}

	protected void unHighlight() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				boardPieces[x][y].setStroke(Color.BLACK);
			}
		}
	}

	protected void buildMenuBar() {
		menuBar = new MenuBar();
		MenuItem exit = new MenuItem("Wyjdz");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent closeProgram) {
				closeGame();
			}});
		Menu fileMenu = new Menu("Opcje"); //Creates file menu and then adds exit item
		fileMenu.getItems().add(exit);
		menuBar.getMenus().add(fileMenu);
	}

	protected BorderPane getBoard()
	{
		return gameFrame;
	}


	protected Stage getStage()
	{
		return stage;
	}

	protected void closeGame()
	{
		stage.close();
	}
}
