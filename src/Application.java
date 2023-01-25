import br.com.ecommerceproject.view.MainMenuView;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao Mercado Saúde!\n\nO seu Ecommerce de exercícios e alimentação saudável.");

        MainMenuView mainMenuView = new MainMenuView();
        mainMenuView.menu();


    }
}