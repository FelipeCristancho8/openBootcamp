package com.ejercicios.ejercicios;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        NotificacionService notificacionService = (NotificacionService) context.getBean("notificacionService");
        UserService userService = (UserService) context.getBean("userService");

        System.out.println(userService.notificacionService.saludar());
    }
}