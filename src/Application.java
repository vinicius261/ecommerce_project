import br.com.ecommerceproject.database.CreateInitialData;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.view.MainMenuView;

public class Application {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        CreateInitialData createData = new CreateInitialData(dataBase);
        createData.createData();

        MainMenuView mainMenuView = new MainMenuView(dataBase);
        mainMenuView.menu();
    }
}