package cate.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String STARTUP_MESSAGE =
            "Hello! I'm Cate\n"
                    + "What can I do for you?\n";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Cate cate;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image cateImage = new Image(this.getClass().getResourceAsStream("/images/DaCate.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getCateDialog(STARTUP_MESSAGE, cateImage)
        );
    }

    /** Injects the Cate instance */
    public void setCate(Cate d) {
        cate = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cate's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cate.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCateDialog(response, cateImage)
        );
        userInput.clear();

        if (cate.isExit()) {
            javafx.application.Platform.exit();
        }
    }
}
