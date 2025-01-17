package com.example.foodle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("views")
public class ViewController {

    @GetMapping
    public String userMain() {
        return "user/main"; // Điều hướng đến templates/user/index.html
    }

    @GetMapping("/users/login")
    public String loginPage() {
        return "user/login"; // Điều hướng đến templates/user/login.html
    }

    @GetMapping("/users/signup")
    public String signupPage() {
        return "user/signup"; // Điều hướng đến templates/user/signup.html
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/admin"; // Điều hướng đến templates/user/index.html
    }

    @GetMapping("/logout")
    public String logout(){
        return "user/logout";
    }


    @GetMapping("/users")
    public String userHome() {
        return "user/home"; // Điều hướng đến templates/user/index.html
    }

    @GetMapping("/users/home") // Thêm endpoint cho home
    public String userHomePage() {
        return "user/home"; // Điều hướng đến templates/user/home.html
    }

    @GetMapping("user/myinfo")
    public String myPage() {
        return "user/myinfo";
    }

    @GetMapping("/users/update")
    public String userUpdatePage() {
        return "user/update";
    }

    @GetMapping("restaurant/all")
    public String restaurantPage() {
        return "restaurant/restaurantAll";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String restaurantDetailPage(@PathVariable Long restaurantId) {
        return "restaurant/restaurantDetail"; // Redirect to the restaurant detail page
    }

    @GetMapping("/users/signup-owner")
    public String signupOwner(){
        return "user/signupOwner";
    }

    @GetMapping("/myrestaurant")
    public String myRestaurant(){
        return "restaurant/myRestaurant";
    }


    @GetMapping("/requestOpen")
    public String requestOpen(){
        return "restaurant/requestOpen";
    }

    @GetMapping("/restaurant/update")
    public String restaurantUpdate(){
        return "restaurant/updateRestaurant";
    }

    @GetMapping("/restaurant-info")
    public String restaurantInfo(){
        return "restaurant/restaurant-info";
    }
    @GetMapping("/reservation/create")
    public String reservationCreate(){
        return "reservation/reservation";
    }
    @GetMapping("/users/reservation")
    public String userReservations() {
        // This should return the path to the HTML template for listing reservations
        return "reservation/userReservation";  // Adjust if the path to the template is different
    }
    @GetMapping("/restaurant/reservation")
    public String restaurantReservation() {
        return "reservation/restaurantReservations";
    }
}
