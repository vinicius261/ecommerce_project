import br.com.ecommerceproject.database.CreateData;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.view.MainMenuView;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao Mercado Saúde!\n\nO seu Ecommerce de itens esportivos e alimentação saudável.");

        DataBase dataBase = new DataBase();

        CreateData createData = new CreateData(dataBase);
        createData.createData();

        MainMenuView mainMenuView = new MainMenuView(dataBase);
        mainMenuView.menu();
    }
}