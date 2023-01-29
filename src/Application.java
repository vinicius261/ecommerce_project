import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.view.MainMenuView;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao Mercado Saúde!\n\nO seu Ecommerce de exercícios e alimentação saudável.");

        DataBase dataBase = new DataBase();

        MainMenuView mainMenuView = new MainMenuView(dataBase);
        mainMenuView.menu();

        System.out.println(dataBase.getCostumers().size());
    }
}