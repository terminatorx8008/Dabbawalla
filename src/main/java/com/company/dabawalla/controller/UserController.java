package com.company.dabawalla.controller;

import com.company.dabawalla.dao.*;
import com.company.dabawalla.entities.*;
import com.company.dabawalla.helper.DateHelper;
import com.company.dabawalla.helper.Message;
import com.company.dabawalla.service.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MessRepo messRepo;
    @Autowired
    private MessReviewRepo messReviewRepo;
    @Autowired
    private NotificationService notificationService;
    private Customer user;

    @RequestMapping("/index")
    public String home(){
        return "Normal/index";
    }

    @RequestMapping("/home")
    public String home(@RequestParam("city") String city ,Model model, Principal principal, HttpSession session){
        this.user =  userRepo.findByCustomerEmail(principal.getName()).get();
        model.addAttribute("user",this.user);
        session.setAttribute("user",this.user.getCustomerId());
        List<Mess> results = messRepo.findByCityContainingIgnoreCase(city);
        model.addAttribute("messes", results);
        return "Normal/home";
    }
    @RequestMapping("/profile")
    public String profile(Model model){
        model.addAttribute("user", this.user);
        String imagePath = "classpath:static/IMG/Customer/" +user.getCustomerImage();
        Resource resource = resourceLoader.getResource(imagePath);
        boolean imageExists = resource.exists();
        // Set the image URL accordingly
        if (imageExists) {
            model.addAttribute( "profileImage", "/img/Customer/" + user.getCustomerImage());
        } else {
            model.addAttribute("profileImage", "https://fakeimg.pl/600x400?text=No+Image");
        }
        return "Normal/profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(Customer customer, @RequestParam("Image") MultipartFile file){
        try{
            user.setCustomerName(customer.getCustomerName());
            user.setCustomerAddress(customer.getCustomerAddress());
            user.setCustomerContact(customer.getCustomerContact());
            user.setCustomerEmail(customer.getCustomerEmail());
            user.setCustomerImage(file.getOriginalFilename());
            userRepo.save(user);
            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/IMG/Customer").getFile();
                Path path = Path.of(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/user/profile";
    }

    @RequestMapping("/fav")
    public String fav(Model model){
        List<Mess> messes = this.user.getFaviorateMesses();
        model.addAttribute("messes",messes);
        model.addAttribute("user",this.user);
        return "Normal/fav";
    }
    @RequestMapping("/Subscriptions")
    public String settings(Model model){
        model.addAttribute("subscriptions",this.user.getSubscribtions());
        return "Normal/subscriptions";
    }


    @RequestMapping("/mess-details/{messId}")
    public String messDetails(@PathVariable("messId") int messId, Model model,HttpSession session) {
        Mess mess = messRepo.findById(messId).get();
        session.setAttribute("user",this.user.getCustomerId());
        List<Menu> menus = menuRepo.findByMessIdAndMenuDay(messId, DateHelper.getCurrentDay());
        model.addAttribute("menus", menus);
        model.addAttribute("allMessReviews", messReviewRepo.findMessReviewByMess(mess));
        model.addAttribute("mess", mess);
        model.addAttribute("user",this.user);
        if(mess.getSubscribtions().contains(new Subscribtion(this.user,mess))){
            model.addAttribute("subscription",mess.getSubscribtions().stream().filter(subscribtion -> subscribtion.getCustomer().getCustomerId() == this.user.getCustomerId()).findFirst().get());
        }else{
            model.addAttribute("subscription",new Subscribtion());
        }
        return "Normal/mess-details";
    }

    @RequestMapping("/add-to-fav/{messId}")
    @ResponseBody
    public ResponseEntity<?> addToFav(@PathVariable("messId") int messId){
        try{
            Mess mess = messRepo.findById(messId).get();
            boolean userAlreadyInFavorites = false;
            for (Customer favoriteCustomer : mess.getFaviorateCustomers()) {
                if (favoriteCustomer.getCustomerId() == user.getCustomerId()) {
                    userAlreadyInFavorites = true;
                    break;
                }
            }
            String messageText;
            if (userAlreadyInFavorites) {
                mess.getFaviorateCustomers().remove(user);
                user.getFaviorateMesses().remove(mess);
                messageText = "Removed from favorites";
            } else {
                mess.getFaviorateCustomers().add(user);
                user.getFaviorateMesses().add(mess);
                messageText = "Added to favorites";
            }

            messRepo.save(mess);
            userRepo.save(user);
            return ResponseEntity.ok(new Message(messageText,"success"));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(new Message("Something Went Wrong","danger"));
        }
    }

    @PostMapping("/save-comment/{messId}")
    public String saveUserComment(@RequestParam("comment") String comment, @PathVariable("messId") int messId ,HttpSession session){
        try{
            Mess mess = messRepo.findById(messId).get();
            MessReview messReview = new MessReview();
            messReview.setMessReview(comment);
            messReview.setMess(mess);
            messReview.setCustomer(user);
            messReview.setMessReviewDate(new Date());
            mess.getMessReviews().add(messReview);
            messRepo.save(mess);
            messReviewRepo.save(messReview);
            session.setAttribute("message",new Message("Comment Added Successfully","success"));
        }catch (Exception e){
            System.out.println(e);
            session.setAttribute("message",new Message("Something Went Wrong","danger"));
        }
        return "redirect:/user/mess-details/"+messId;
    }
    @PostMapping("/save-subscription/{messId}")
    public String saveSubscription(@RequestParam("userAddress") String userAddress,@ModelAttribute("subscription") Subscribtion subscription,@PathVariable("messId") int messId,                                   HttpSession session,Model model){
        try {
            Mess mess = messRepo.findById(messId).get();
            this.user.setCustomerAddress(userAddress);
//            send a request to the mess owner
            notificationService.sendNotification(mess);
            subscription.setMess(mess);
            subscription.setCustomer(this.user);
            if(mess.getSubscribtions().contains(subscription) && subscription.isSubscribtionStatus()){
                session.setAttribute("subscriptionMessage", new Message("Already Subscribed", "danger"));
                return "redirect:/user/mess-details/" + messId;
            }
            mess.getSubscribtions().add(subscription);
            user.getSubscribtions().add(subscription);
            messRepo.save(mess);
            userRepo.save(this.user);
            model.addAttribute("user",this.user);
            session.setAttribute("subscriptionMessage", new Message("Subscription request Successfully", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("subscriptionMessage", new Message("Something Went Wrong", "danger"));
        }
        return "redirect:/user/mess-details/" + messId;
    }
    @RequestMapping("/request-cancellation/{messId}")
    public String requestCancellation(@PathVariable("messId") int messId,HttpSession session){
        try{
            Mess mess = messRepo.findById(messId).get();
            Subscribtion subscription = mess.getSubscribtions().stream().filter(subscribtion -> subscribtion.getCustomer().getCustomerId() == this.user.getCustomerId()).findFirst().get();
            subscription.setSubscribtionStatus(false);
            mess.getSubscribtions().add(subscription);
            messRepo.save(mess);
            session.setAttribute("subscriptionMessage", new Message("Request Sent Successfully", "success"));
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("subscriptionMessage", new Message("Something Went Wrong", "danger"));
        }
        return "redirect:/user/mess-details/"+messId;
    }

}


