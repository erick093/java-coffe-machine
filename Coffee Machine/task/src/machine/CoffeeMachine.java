package machine;

public class CoffeeMachine {

    public static int water = Constants.INITIAL_WATER_LEVEL;
    public static int milk = Constants.INITIAL_MILK_LEVEL;
    public static int coffeeBeans = Constants.INITIAL_COFFEE_BEANS_LEVEL;
    public static int disposableCups = Constants.INITIAL_DISPOSABLE_CUPS_LEVEL;
    public static int money = Constants.INITIAL_MONEY_AMOUNT;

    CoffeeMachineState coffeeMachineState;

    public CoffeeMachine() {
        displayMainMenu();
    }

    public void processState(String action) {
        switch (coffeeMachineState) {
            case CHOOSING_ACTION:
                processAction(action);
                break;
            case CHOOSING_COFFEE:
                buyCoffee(action);
                break;
            case FILLING_WATER:
                fillWater(action);
                break;
            case FILLING_MILK:
                fillMilk(action);
                break;
            case FILLING_COFFEE_BEANS:
                fillCoffeeBeans(action);
                break;
            case FILLING_DISPOSABLE_CUPS:
                fillDisposableCups(action);
                break;
        }
    }

    public void processAction(String action) {

        CoffeeMachineAction coffeeMachineAction = CoffeeMachineAction.valueOf(action.toUpperCase());
        switch (coffeeMachineAction) {
            case BUY:
                this.coffeeMachineState = CoffeeMachineState.CHOOSING_COFFEE;
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case FILL:
                this.coffeeMachineState = CoffeeMachineState.FILLING_WATER;
                System.out.println("Write how many ml of water do you want to add:");
                break;
            case TAKE:
                takeMoney();
                break;
            case REMAINING:
                displayRemaining();
                break;
            case EXIT:
                break;
            default:
                this.coffeeMachineState = CoffeeMachineState.CHOOSING_ACTION;
                throw new IllegalArgumentException("Invalid action");
        }
    }

    public void buyCoffee(String coffeeSelection) {
        switch (coffeeSelection) {
            case "1":
                makeCoffee(Constants.ESPRESSO_WATER_NEEDED, Constants.ESPRESSO_MILK_NEEDED, Constants.ESPRESSO_COFFEE_BEANS_NEEDED, 1, Constants.ESPRESSO_PRICE);
                displayMainMenu();
                break;
            case "2":
                makeCoffee(Constants.LATTE_WATER_NEEDED, Constants.LATTE_MILK_NEEDED, Constants.LATTE_COFFEE_BEANS_NEEDED, 1, Constants.LATTE_PRICE);
                displayMainMenu();
                break;
            case "3":
                makeCoffee(Constants.CAPPUCCINO_WATER_NEEDED, Constants.CAPPUCCINO_MILK_NEEDED, Constants.CAPPUCCINO_COFFEE_BEANS_NEEDED, 1, Constants.CAPPUCCINO_PRICE);
                displayMainMenu();
                break;
            case "back":
                displayMainMenu();
                break;
        }
    }

    public void makeCoffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int disposableCupsNeeded, int price) {
        if (water < waterNeeded) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (coffeeBeans < coffeeBeansNeeded) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }
        if (milk < milkNeeded) {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if (disposableCups < disposableCupsNeeded) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }
        water -= waterNeeded;
        coffeeBeans -= coffeeBeansNeeded;
        disposableCups -= disposableCupsNeeded;
        milk -= milkNeeded;
        money += price;
        System.out.println("I have enough resources, making you a coffee!");

    }

    public void fillWater(String quantity) {
        water += Integer.parseInt(quantity);
        this.coffeeMachineState = CoffeeMachineState.FILLING_MILK;
        System.out.println("Write how many ml of milk do you want to add:");
    }

    public void fillMilk(String quantity) {
        milk += Integer.parseInt(quantity);
        this.coffeeMachineState = CoffeeMachineState.FILLING_COFFEE_BEANS;
        System.out.println("Write how many grams of coffee beans do you want to add:");
    }

    public void fillCoffeeBeans(String quantity) {
        coffeeBeans += Integer.parseInt(quantity);
        this.coffeeMachineState = CoffeeMachineState.FILLING_DISPOSABLE_CUPS;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
    }

    public void fillDisposableCups(String quantity) {
        disposableCups += Integer.parseInt(quantity);
        displayMainMenu();
    }

    public void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
        displayMainMenu();
    }

    public void displayRemaining() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println("$" + money + " of money");
        displayMainMenu();
    }

    public void displayMainMenu() {
        this.coffeeMachineState = CoffeeMachineState.CHOOSING_ACTION;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

}
