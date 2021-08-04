import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

enum Ingredients {
    WHITESOCKS("Белые носки", -5), APPLE("Яблоко", 6), CHICKEN("Курица", -2),
    ICE("Лёд", 0), BANANA("Банан",3), RUSKS("Сухарики", -4), CRISPS("Чипсы", 5);
    private final String name;
    public int value;
    Ingredients(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Ingredients findByName(String searchName) throws NoSuchElementException {
        for(Ingredients x : Ingredients.values()) {
            if (x.name.equalsIgnoreCase(searchName))
                return x;
        }
        throw new NoSuchElementException();
    }

    public String getName() {
        return name;
    }
}

enum Drinks {
    APPLEJUICE("Яблочный сок", 2), WATER("Вода", 1), MILK("Молоко",2), BEER("Пиво",2),
    COLA("Кола",-1), KEFIR("Кефир", -9), CHERRYSYRUP("Вишнёвый сироп", +3);
    private final String name;
    public int value;
    Drinks(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Drinks findByName(String searchName) throws NoSuchElementException {
        for(Drinks x : Drinks.values()) {
            if (x.name.equalsIgnoreCase(searchName))
                return x;
        }
        throw new NoSuchElementException();
    }

    public String getName() {
        return name;
    }
}

public class TextGame {

    public static void writeMenu(ArrayList<?> usedDrinks, ArrayList<?> usedIngredients) {
        System.out.print("\nНапитки: ");
        for (Drinks f : Drinks.values()) {
            if (usedDrinks.contains(f))
                continue;
            System.out.print(f.getName() + ", ");
        }
        System.out.print("\nИнгредиенты: ");
        for (Ingredients i : Ingredients.values()) {
            if (usedIngredients.contains(i))
                continue;
            System.out.print(i.getName() + ", ");
        }
    }
    public static void main(String[] args) {
        ArrayList<Ingredients> usedIngredients = new ArrayList<>();
        ArrayList<Drinks> usedDrinks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        int Health = 10;
        System.out.println("Вы бармен.\nВам нечего делать в выходной, и вы решили попробовать сделать напитки из необычных ингредиентов.");
        System.out.println("Вы можете использовать каждый ингредиент только раз за игру, совмещать можно один напиток с одной вещью.");
        System.out.println("У вас 10 здоровья, Вы выиграете, если у вас будет 20 здоровья и проиграете, если здоровье опустится до нуля.");


        while(true) {
           writeMenu(usedDrinks, usedIngredients);
           try {
               System.out.print("\n\nВведите название напитка: ");
               Drinks drink = Drinks.findByName(scan.nextLine());
               System.out.print("Введите название ингредиента: ");
               Ingredients ingredient = Ingredients.findByName(scan.nextLine());

               System.out.printf("Вы совмешали %s и %s, и у вас получилась какая-то жижа!", drink.getName(), ingredient.getName());
               System.out.print("\nПробуем... ");
               int getHP = drink.value + ingredient.value;

               Health += getHP;
               if (getHP >= 0) {
                   System.out.printf("Вы восполнили %s здоровья!\nТеперь у вас %s здоровья", getHP, Health);
               } else {
                   System.out.printf("Вы получили %s урона!\nТеперь у вас %s здоровья", -getHP, Health);
               }

               usedDrinks.add(drink);
               usedIngredients.add(ingredient);
               if (Health <= 0) {
                   System.out.println("\nКакая жалость, вы умерли, и ваш дом продали!");
                   break;
               }
               if (Health >= 20) {
                   System.out.println("\nПоздравляем, вы выиграли!\nВы решили, что вам хватит проб напитков и вы пошли гулять");
                   break;
               }
               System.out.println("\nНажмите, чтобы продолжить. ");
               scan.nextLine();
           } catch (NoSuchElementException e) {
               System.out.println("Такого элемента нет в списке или вы допустили ошибку! Попробуйте снова");
           }
        }
    }
}
