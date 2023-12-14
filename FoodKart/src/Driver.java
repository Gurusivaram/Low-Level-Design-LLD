import app.Application;

public class Driver {

    public static void main(String[] args) {
        Application app = Application.getInstance();

        app.registerUser("Pralove", "M", "phoneNumber-1", "HSR");
        app.registerUser("Nitesh", "M", "phoneNumber-2", "BTM");
        app.registerUser("Vatsal", "M", "phoneNumber-3", "BTM");

        app.loginUser("phoneNumber-1");
        System.out.println(app.getLoggedInUserName());
        app.registerRestaurant("Food Court-1", "BTM/HSR", "NI Thali", "100", "5");
        app.registerRestaurant("Food Court-2", "BTM", "Burger", "120", "3");

        app.loginUser("phoneNumber-2");
        System.out.println(app.getLoggedInUserName());
        app.registerRestaurant("Food Court-3", "HSR", "SI Thali", "150", "1");

        app.loginUser("phoneNumber-3");
        System.out.println(app.showRestaurant("price"));

        if(app.placeOrder("Food Court-1", 2)) {
            System.out.println("Order Placed Successfully.");
        } else {
            System.out.println("Cannot place order");
        }
        System.out.println(app.getOrders());

        if(app.placeOrder("Food Court-2", 3)) {
            System.out.println("Order Placed Successfully.");
        } else {
            System.out.println("Cannot place order");
        }
        System.out.println(app.getOrders());

        if(app.placeOrder("Food Court-1", 4)) {
            System.out.println("Order Placed Successfully.");
        } else {
            System.out.println("Cannot place order");
        }
        System.out.println(app.getOrders());

        app.createReview("Food Court-2", 3, "Good Food");
        app.createReview("Food Court-1", 4, "Nice Food");
        System.out.println(app.showRestaurant("rating"));

        app.loginUser("phoneNumber-1");
        app.updateQuantity("Food Court-2", 5);
        System.out.println(app.getQuantity("Food Court-2"));
        System.out.println(app.getLocations("Food Court-2"));

        app.updateLocation("Food Court-2", "BTM/HSR");
        System.out.println(app.getLocations("Food Court-2"));

    }
}
