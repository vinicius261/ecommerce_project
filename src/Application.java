import br.com.ecommerceproject.database.DataForTesting;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.view.MainMenuView;

public class Application {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        DataForTesting createData = new DataForTesting(dataBase);
        createData.createTestingData();

        MainMenuView mainMenuView = new MainMenuView(dataBase);
        mainMenuView.showMainMenuView();
    }
}